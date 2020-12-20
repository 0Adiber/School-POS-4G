package at.htlkaindorf.controller;

import at.htlkaindorf.pojos.Movie;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
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

        String search = request.getParameter("search");
        if (search != null && !search.trim().isEmpty()) {
            int page = 1;

            JsonNode sNode = mapper.readTree(new URL(URL + "&s=" + search + "&p=" + page));

            List<Movie> movies = new ArrayList<>();

            for (int i = 0; i < 10; i++) {
                JsonNode rNode = sNode.get("Search").get(i);
                String id = rNode.get("imdbID").asText();
                
                movies.add(mapper.readValue(new URL(URL + "&i=" + id), Movie.class));
            }
            
            request.setAttribute("movies", movies);
        }

        request.getRequestDispatcher("movieView.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
