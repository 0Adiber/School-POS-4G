package at.htlkaindorf.controller;

import at.htlkaindorf.json.DynamicMovieComparator;
import at.htlkaindorf.pojos.Movie;
import beans.Sortings;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
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
        
        //MOVIES
        List<Movie> movies = new ArrayList<>();
        try {
            movies.addAll((List<Movie>)request.getSession().getAttribute("movies"));
        }catch(NullPointerException e) {
            
        }
        
        /*
        * IF SEARCH OR LOADMORE
        */
        String search = (String)request.getAttribute("search");
        
        if (search != null && !search.trim().isEmpty()) {
            search = search.trim();
            request.getSession().setAttribute("search", search);
                        
            int curres;
            try {
                curres = Integer.parseInt(request.getParameter("curres"));
            }catch(NumberFormatException e) {
                curres = 1;
            }
            
            JsonNode sNode = mapper.readTree(new URL(URL + "&s=" + URLEncoder.encode(search) + "&page=" + curres)); //.encode(<String>) is deprecated in Java14, but not Java8
            
            try {
                for (int i = 0; i < 10; i++) {
                    //movies.add(mapper.treeToValue(sNode.get("Search").get(i),Movie.class)); // ==> cannot use this, because we have to be able to filter for genre, which is only visible when requesting the detailed information
                    
                    JsonNode rNode = sNode.get("Search").get(i); 
                    String id = rNode.get("imdbID").asText();
                    movies.add(mapper.readValue(new URL(URL + "&plot=full&i=" + id), Movie.class));
                }
            }catch(NullPointerException e) {
                //no more results
            }
            
            //genres
            Set<String> genres = new HashSet<>();
            for(Movie m : movies) {
                genres.addAll(Arrays.asList(m.getGenre()));
            }
            request.getSession().setAttribute("genres", genres);
            
            request.getSession().setAttribute("movies", movies);
            request.getSession().setAttribute("curres", curres);
        }
        
        /*******************
              FILTERING
        ********************/
        String temp = (String) request.getParameter("filter");
        if(temp == null)
            temp = (String)request.getSession().getAttribute("filter");
        
        final String filter = temp;
        
        if(filter != null && !filter.equalsIgnoreCase("all")) {
            movies = movies.stream()
                      .filter(m -> Arrays.asList(m.getGenre()).contains(filter))
                      .collect(Collectors.toList());
        }
        request.getSession().setAttribute("filter", filter);
        
        /*******************
              SORTING
        ********************/
        List<Sortings> sortings = (List<Sortings>)request.getSession().getAttribute("sort");
        if(sortings == null)
            sortings = new ArrayList<>();
        for(Sortings s : Sortings.values()) {
            final String param = request.getParameter("sort_" + s);
            if(param != null) {
                if(!sortings.contains(s))
                    sortings.add(s);
            } else {
                sortings.remove(s);
            }
        }
        //now sort using the sortings
        Collections.sort(movies,new DynamicMovieComparator(sortings));
        
        request.getSession().setAttribute("sort", sortings);
        request.getSession().setAttribute("sortStr", sortings.toString()); //because we are only allowed to use JSTL, and JSTL cannot use enums, i have to do this like that
                
        /*
        * PAGINATION
        */
        int page, maxpage = (int)Math.ceil(movies.size()/20.0);;
        try {
            page = Integer.parseInt(request.getParameter("page"));
            page = page < 1 ? 1 : page > maxpage ? maxpage : page;
        }catch(NumberFormatException e) {
            page = 1;
        }
        
        request.setAttribute("movies", movies.stream().skip((page-1)*20).limit(20).collect(Collectors.toList()));
        request.setAttribute("page", page);
        request.getSession().setAttribute("maxpage", maxpage);
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
        } else if(request.getParameter("curres") != null){
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
