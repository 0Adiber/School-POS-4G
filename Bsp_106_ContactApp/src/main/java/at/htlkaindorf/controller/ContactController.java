package at.htlkaindorf.controller;

import at.htlkaindorf.io.IOAccess;
import at.htlkaindorf.pojos.Company;
import at.htlkaindorf.pojos.Contact;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/ContactController"})
public class ContactController extends HttpServlet {

    private List<Contact> contacts;
    private List<Company> companies;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        
        try {
            IOAccess.ReadResult rs = IOAccess.readJson(config.getServletContext().getRealPath("/contacts.json"));
            contacts = rs.getContacts();
            companies = rs.getCompanies();
            config.getServletContext().setAttribute("contacts", contacts);
        } catch (IOException ex) {
            Logger.getLogger(ContactController.class.getName()).log(Level.SEVERE, null, ex);
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
        int page;
        try {
            page = Integer.parseInt(request.getParameter("page"));
            page = page < 1 ? 0 : Math.ceil(contacts.size()/30) >= page ? page : page-1;
        }catch(NumberFormatException ex) {
            page = 0;
        }
        
        request.setAttribute("page", page);
        request.getRequestDispatcher("contactView.jsp").forward(request, response);
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
        return "Contact App Servlet";
    }// </editor-fold>
    
}
