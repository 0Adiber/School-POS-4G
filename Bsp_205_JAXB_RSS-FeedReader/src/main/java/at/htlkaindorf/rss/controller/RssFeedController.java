package at.htlkaindorf.rss.controller;

import at.htlkaindorf.rss.beans.FeedSite;
import at.htlkaindorf.rss.beans.Item;
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
        
        RSS_FEEDS.add(new FeedSite("Die Presse - Innenpolitik", "https://www.diepresse.com/rss/Innenpolitik"));
        RSS_FEEDS.add(new FeedSite("Die Presse - Unternehmen", "https://www.diepresse.com/rss/Unternehmen"));

        
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
        
        if(request.getSession().getAttribute("read") == null) {
            request.getSession().setAttribute("read", new ArrayList<String>());
        }
        
        if(request.getSession().getAttribute("hidden") == null) {
            request.getSession().setAttribute("hidden", new ArrayList<String>());
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
        String unsubscribe = request.getParameter("unsubscribe");
        String read = request.getParameter("read");
        String unread = request.getParameter("unread");
        String hide = request.getParameter("remove");
        
        List<FeedSite> feeds = (ArrayList<FeedSite>)request.getSession().getAttribute("feeds");
        List<String> readArticles = (ArrayList<String>)request.getSession().getAttribute("read");
        List<String> hidenArticles = (ArrayList<String>)request.getSession().getAttribute("hidden");
        
        if(url != null && !url.trim().isEmpty()) {
            
            try {
                RSS result = XMLAccess.getInstance().fetchFeed(new URL(url));
                                
                result.getChannel().getItems().removeIf(i -> hidenArticles.contains(i.getGuid()));
                
                result.getChannel().getItems().stream().filter(item -> (readArticles.contains(item.getGuid()))).forEachOrdered(item -> {
                    item.setRead(true);
                });
                
                request.setAttribute("result", result);
            } catch (JAXBException ex) {
                request.setAttribute("error", "Could not fetch RSS Feed");
            }
            
        } else if(feed != null && !feed.trim().isEmpty()) {
                      
            if(!feeds.stream().anyMatch(f -> f.getUrl().equals(url)))
                try {
                    RSS result = XMLAccess.getInstance().fetchFeed(new URL(feed));

                    feeds.add(new FeedSite(result.getChannel().getTitle(), feed));
                    request.getSession().setAttribute("feeds", feeds);
                } catch (JAXBException | MalformedURLException ex) {
                    request.setAttribute("error", "Could not add RSS feed");
                }

        } else if(unsubscribe != null && !unsubscribe.trim().isEmpty()) {
            
            feeds.removeIf(f -> f.getUrl().equals(unsubscribe));
            request.getSession().setAttribute("feeds", feeds);
            
        } else if(read != null && !read.trim().isEmpty()) {
            
            readArticles.add(read);
            request.getSession().setAttribute("read", readArticles);
            
        } else if(hide != null && !hide.trim().isEmpty()) {
                        
            hidenArticles.add(hide);
            request.getSession().setAttribute("hidden", hidenArticles);
            
        } else if(unread != null && !unread.trim().isEmpty()) {
                        
            readArticles.remove(unread);
            request.getSession().setAttribute("read", readArticles);
            
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
