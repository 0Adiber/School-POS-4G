package at.htlkaindorf.controller;

import at.htlkaindorf.beans.DAYS;
import at.htlkaindorf.bl.SupplierplanBL;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SupplierplanController", urlPatterns = {"/SupplierplanController"})
public class SupplierplanController extends HttpServlet {

    private SupplierplanBL bl;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config); //To change body of generated methods, choose Tools | Templates.
        
        try {
            bl = new SupplierplanBL(config.getServletContext().getRealPath("/stundenplan.csv"));
            config.getServletContext().setAttribute("bl", bl);
            config.getServletContext().setAttribute("days", DAYS.values());
        } catch (IOException ex) {
            Logger.getLogger(SupplierplanController.class.getName()).log(Level.SEVERE, null, ex);
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
        
        request.getRequestDispatcher("supplierplanView.jsp").forward(request, response);
                
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
        DAYS day = null;
        int lesson = -1;
        String subject, teacher;
        boolean error = false; //if error, set to true and do not change anything
        
        //parse day
        try {
            day = DAYS.valueOf(request.getParameter("day").toUpperCase());
        }catch(NullPointerException | IllegalArgumentException e) {
            request.setAttribute("dayError", "Ungültiger Tag");
            error = true;
        }
        
        try {
            lesson = Integer.parseInt(request.getParameter("lesson"));
            if(lesson < 0 || lesson > 10) {
                throw new NumberFormatException();
            }
        } catch(NumberFormatException e) {
            request.setAttribute("lessonError", "Ungültige Stunde: 1 <= Stunde <= 10");
            error = true;
        }
        
        subject = request.getParameter("subject");
        teacher = request.getParameter("teacher");
        
        if(subject == null || subject.isEmpty()) {
            request.setAttribute("subjectError", "Fach eingeben!");
            error = true;
        }
        
        if(subject == null || subject.isEmpty()) {
            request.setAttribute("teacherError", "Lehrer eingeben!");
            error = true;
        }
        
        if(error) {
            processRequest(request, response);
            return;
        }
        
        List<String> teachers = Arrays.asList(teacher.split("[,;]"));
        
        if(!bl.setLesson(day, lesson, subject, teachers)) {
            request.setAttribute("error", "Supplierung einer Freistunde nicht möglich!");
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
