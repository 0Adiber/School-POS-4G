/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlkaindorf.guestbook.controller;

import at.htlkaindorf.guestbook.beans.GuestBookEntry;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "GuestBookController", urlPatterns = {"/GuestBookController"})
public class GuestBookController extends HttpServlet {

    private List<GuestBookEntry> entries = new ArrayList<>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config); //To change body of generated methods, choose Tools | Templates.
        // wird einmal aufgerufen & daher kann man hier dateien einlesen
        System.out.println(this.getServletContext().getRealPath("/"));
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
        //response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession(); //für session scope
        request.getServletContext().getAttribute("whatever"); //für application scope
        request.setAttribute("whatever", this); //und eben request scope
        
        request.getRequestDispatcher("guestbook.jsp").forward(request, response);
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
        request.setCharacterEncoding("UTF-8");
        
        String nickname = request.getParameter("nickname");
        if(nickname == null || nickname.trim().isEmpty()) {
            nickname = "unknown";
        }
        String email = request.getParameter("email");
        if(email == null || email.trim().isEmpty()) {
            nickname = "unknown";
        }
        String comment = request.getParameter("comment");
        if(comment == null || comment.trim().isEmpty()) {
            comment = "no comment";
        }
        
        GuestBookEntry entry = new GuestBookEntry(nickname, email, comment);
        entries.add(entry);
        request.setAttribute("guestbookEntires", entries);
        
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
