package at.htlkaindorf.rss.controller;

import at.htlkaindorf.rss.beans.FeedSite;
import at.htlkaindorf.rss.beans.RSS;
import at.htlkaindorf.rss.xml.XMLAccess;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;

@WebServlet(name = "RssFeedController", urlPatterns = {"/RssFeedController"})
public class RssFeedController extends HttpServlet {

    private static List<FeedSite> RSS_FEEDS; // default feeds
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config); //To change body of generated methods, choose Tools | Templates.
        
        RSS_FEEDS = new ArrayList<>();
        
        try {
            RSS_FEEDS.add(new FeedSite("Die Presse - Innenpolitik", new URL("https://www.diepresse.com/rss/Innenpolitik")));
            RSS_FEEDS.add(new FeedSite("Die Presse - Unternehmen", new URL("https://www.diepresse.com/rss/Unternehmen")));
        } catch (MalformedURLException ex) {
            Logger.getLogger(RssFeedController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
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
        
        //request.setAttribute("allfeeds", RSS_FEEDS);
        
        if(request.getSession().getAttribute("feeds") == null) {
            request.getSession().setAttribute("feeds", new ArrayList<>(RSS_FEEDS));
        }
        
        request.getRequestDispatcher("feed.jsp").forward(request, response);
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
        
        String url = request.getParameter("rss");
        String feed = request.getParameter("feed");
                
        if(url != null && !url.trim().isEmpty()) {
            
            try {
                RSS result = XMLAccess.getInstance().fetchFeed(new URL(url));
                
                request.setAttribute("result", result);
            } catch (JAXBException ex) {
                request.setAttribute("error", "Could not fetch RSS Feed");
            }
            
        } else if(feed != null && !feed.trim().isEmpty()) {
            
            List<FeedSite> feeds = (ArrayList<FeedSite>)request.getSession().getAttribute("feeds");
                        
            try {
                RSS result = XMLAccess.getInstance().fetchFeed(new URL(feed));
                feeds.add(new FeedSite(result.getChannel().getTitle(), new URL(feed)));
                request.getSession().setAttribute("feeds", feeds);
            } catch (JAXBException | MalformedURLException ex) {
                request.setAttribute("error", "Could not add RSS feed");
            }
            
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
        return "Short description";
    }// </editor-fold>

}
