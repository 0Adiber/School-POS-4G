package at.htlkaindorf.controller;

import at.htlkaindorf.pojos.Movie;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MovieController", urlPatterns = {"/MovieController"})
public class MovieController extends HttpServlet {

    private final String APIKEY = "82fd3ad1";
    private final String URL = "http://www.omdbapi.com/?apikey=" + APIKEY;
    private static JsonMapper mapper = new JsonMapper();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String search = (String)request.getAttribute("search");
        if (search != null && !search.trim().isEmpty()) {
            search = search.trim();
            request.getSession().setAttribute("search", search);
            /*int page;
            try {
                page = Integer.parseInt(request.getParameter("page"));
                int maxpage = (int)request.getSession().getAttribute("maxpage");
                assert page >= 1 && page <= maxpage;
            }catch(NumberFormatException | AssertionError e) {
                page = 1;
            }*/
            
            int curres;
            try {
                System.out.println("curres: " + request.getParameter("curres"));
                curres = Integer.parseInt(request.getParameter("curres"));
            }catch(NumberFormatException e) {
                curres = 1;
            }
            
            JsonNode sNode = mapper.readTree(new URL(URL + "&s=" + URLEncoder.encode(search) + "&page=" + curres)); //.encode(<String>) is deprecated in Java14, but not Java8

            List<Movie> movies = (List<Movie>)request.getSession().getAttribute("movies");
            if(movies == null || (request.getSession().getAttribute("search") != null && request.getSession().getAttribute("search") != search)) {
                movies = new ArrayList<>();
            }
            
            try {
                sNode.get("Error").asText();
            }catch(NullPointerException e) {
                for (int i = 0; i < 10; i++) {
                    JsonNode rNode = sNode.get("Search").get(i);
                    String id = rNode.get("imdbID").asText();

                    movies.add(mapper.readValue(new URL(URL + "&i=" + id), Movie.class));
                }
            }
            
            request.getSession().setAttribute("movies", movies);
            request.getSession().setAttribute("curres", curres);
            //request.getSession().setAttribute("page", page);
            //request.getSession().setAttribute("maxpage", Math.floorDiv(sNode.get("totalResults").asInt(),10));
        }

        request.getRequestDispatcher("movieView.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(request.getParameter("search") != null) {
            request.getSession().invalidate();
            request.setAttribute("search", request.getParameter("search"));
        } else {
            request.setAttribute("search", request.getSession().getAttribute("search"));
        }
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "https://www.youtube.com/watch?v=dQw4w9WgXcQ";
    }// </editor-fold>

}
