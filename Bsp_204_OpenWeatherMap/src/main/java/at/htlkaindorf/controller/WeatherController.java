package at.htlkaindorf.controller;

import at.htlkaindorf.beans.Current;
import at.htlkaindorf.xml.XMLAccess;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;

@WebServlet(name = "WeatherController", urlPatterns = {"/WeatherController"})
public class WeatherController extends HttpServlet {

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
        
        String city = request.getParameter("city");
        String lang = request.getParameter("lang");
        
        if(lang == null || lang.trim().isEmpty())
            lang = "de";
        
        request.setAttribute("lang", lang);
        
        if(city != null && !city.trim().isEmpty())        
            try {
                Current result = XMLAccess.getInstance().getWeather(city, lang);
                request.setAttribute("city", city);
                request.setAttribute("result", result);
                LocalTime now = LocalTime.now(ZoneOffset.UTC);
                now = now.plusSeconds(result.getCity().getTimezone());
                String img = "sunny";

                if(Integer.parseInt(result.getClouds().getValue())>50)
                    img = "cloudy";

                if(result.getWeather().getValue().toLowerCase().contains("rain") || result.getWeather().getValue().toLowerCase().contains("regen"))
                    img = "rainy";

                if(now.isAfter(LocalTime.of(17,0)) || now.isBefore(LocalTime.of(6,0)))
                    img = "night";

                request.setAttribute("img", img);
            } catch (JAXBException | FileNotFoundException e) {
                request.setAttribute("error", "City not found");
            }
        
        request.getRequestDispatcher("weatherView.jsp").forward(request, response);
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
        return "WeatherMap Main Servlet";
    }// </editor-fold>

}
