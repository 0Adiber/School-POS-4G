/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlkaindorf.controller;

import at.htlkaindorf.beans.Pizza;
import at.htlkaindorf.bl.LanguageSelect;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "PizzaOrderController", urlPatterns = {"/PizzaOrderController"})
public class PizzaOrderController extends HttpServlet {
    
    private List<Pizza> pizzas = new ArrayList<>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config); //To change body of generated methods, choose Tools | Templates.
        try {
            pizzas = Files.lines(Paths.get(this.getServletContext().getRealPath("/pizzas.csv"))).skip(1).map(Pizza::new).collect(Collectors.toList());
        } catch (IOException ex) {
            Logger.getLogger(PizzaOrderController.class.getName()).log(Level.SEVERE, null, ex);
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
        request.getServletContext().setAttribute("pizzas", pizzas);
        request.getRequestDispatcher("PizzaOrder.jsp").forward(request, response);
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
        
        if(request.getParameter("language") != null) {
            Cookie cLan = new Cookie("language", request.getParameter("language"));
            cLan.setMaxAge(30*24*3600);
            response.addCookie(cLan);
            LanguageSelect.setCurrent(cLan.getValue());
            request.getRequestDispatcher("PizzaOrder.jsp").forward(request, response);
            return;
        }
        
        Map<Pizza, Integer> order = (HashMap<Pizza,Integer>)request.getSession().getAttribute("order");
        
        if(order == null)
            order = new HashMap<>();
        
        for(Pizza p : pizzas) {
            if(request.getParameter(p.getName() + "_amount") != null) {
                try {
                    int amount = Integer.parseInt(request.getParameter(p.getName() + "_amount"));
                    if(amount == 0)
                        continue;
                    order.put(p,amount);
                }catch(NumberFormatException e) {
                    
                }
            }
        }
                
        request.getSession().setAttribute("order", order);
        request.getSession().setAttribute("address", request.getParameter("address"));
        
        request.getRequestDispatcher("PizzaOrderSummary.jsp").forward(request, response);
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
