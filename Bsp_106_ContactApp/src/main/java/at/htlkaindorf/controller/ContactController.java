package at.htlkaindorf.controller;

import at.htlkaindorf.beans.Gender;
import at.htlkaindorf.beans.Sortings;
import at.htlkaindorf.json.DynamicContactComparator;
import at.htlkaindorf.io.JSONAccess;
import at.htlkaindorf.pojos.Company;
import at.htlkaindorf.pojos.Contact;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
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
            JSONAccess.ReadResult rs = JSONAccess.readJson(config.getServletContext().getRealPath("/contacts.json"));
            contacts = rs.getContacts();
            companies = rs.getCompanies().stream()
                    .sorted(Comparator.comparing(Company::getName).thenComparing(Company::getStockmarket))
                    .collect(Collectors.toList());
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
        
        //Pagination
        int page;
        List<Contact> curcontacts = (List<Contact>) request.getSession().getAttribute("contacts");
        try {
            page = Integer.parseInt(request.getParameter("page"));
            page = page < 1 ? 0 : Math.ceil(curcontacts.size()/30) >= page ? page : page-1;
        }catch(NumberFormatException ex) {
            page = 0;
        }
        curcontacts = curcontacts.stream()
                .skip(page*30)
                .limit(30)
                .collect(Collectors.toList());
        
        request.setAttribute("contacts", curcontacts);
        request.setAttribute("page", page);
        request.setAttribute("companies", companies);
        request.setAttribute("genders", Gender.values());
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
        if(request.getSession().getAttribute("contacts") == null) {
            request.getSession().setAttribute("contacts", contacts);
            request.getSession().setAttribute("session_contacts", contacts);
        }
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
        
        List<Contact> curcontacts = new ArrayList<>((List<Contact>)request.getSession().getAttribute("session_contacts"));
        
        /*******************
               DELETING
        ********************/
        if(request.getParameter("action") != null && request.getParameter("action").equalsIgnoreCase("delete")) {
            for(int i = 0; i<30; i++) {
                try {
                    int cid = Integer.parseInt(request.getParameter("c_list_" + i));
                    curcontacts.removeIf(c -> c.getId() == cid);
                }catch(NumberFormatException e) {}
            }
            request.getSession().setAttribute("session_contacts", curcontacts);
        }
        
        /*******************
               FAVORITE
        ********************/
        if(request.getParameter("action") != null && request.getParameter("action").equalsIgnoreCase("favorite")) {
            for(int i = 0; i<30; i++) {
                try {
                    int cid = Integer.parseInt(request.getParameter("c_list_" + i));
                    curcontacts.stream()
                              .filter(c -> c.getId() == cid)
                              .findFirst()
                              .get().setFavorite(true);
                }catch(NumberFormatException e) {}
            }
        }
        
        /*******************
            SAVE FAVORITE
        ********************/
        if(request.getParameter("action") != null && request.getParameter("action").equalsIgnoreCase("savefavorite")) {
            response.setHeader("Content-Disposition", "attachment;filename=favorites.json");
            List<Contact> save = curcontacts.stream()
                      .filter(c -> c.isFavorite())
                      .collect(Collectors.toList());
            byte[] f = JSONAccess.saveJson(save);
            
            OutputStream out = response.getOutputStream();
            for(byte b : f) {
                out.write(b);
            }
            out.flush();
            return;
        }
        
        /*******************
              FILTERING
        ********************/
        //COMPANY
        try {
            Company curcompany = companies.stream()
                    .filter(c -> c.getName().concat(c.getStockmarket()).equals(request.getParameter("company")))
                    .findFirst()
                    .get();
            //curcontacts = curcontacts.stream().filter(c -> c.getCompany().equals(curcompany)).collect(Collectors.toList()); 
            curcontacts = new ArrayList<>(curcompany.getContacts());
            request.setAttribute("curcompany", curcompany);
        }catch(NoSuchElementException e) {
        }
        //GENDER
        try {
            Gender curgender = Gender.valueOf(request.getParameter("gender"));
            curcontacts = curcontacts.stream()
                    .filter(c -> c.getGender().equals(curgender))
                    .collect(Collectors.toList()); 
            request.setAttribute("curgender", curgender);
        }catch(IllegalArgumentException | NullPointerException e) {
        }
        //NAME
        String name = request.getParameter("fname");
        if(name != null && !name.trim().isEmpty()) {
            curcontacts = curcontacts.stream()
                    .filter(c -> c.getFirstname().concat(" "+c.getLastname()).toLowerCase().contains(name.toLowerCase()))
                    .collect(Collectors.toList());
            request.setAttribute("curname", name);
        }
        
        /*******************
              SORTING
        ********************/
        //first find all sortings
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
        Collections.sort(curcontacts,new DynamicContactComparator(sortings));
        
        request.getSession().setAttribute("sort", sortings);
        request.getSession().setAttribute("sortStr", sortings.toString()); //because we are only allowed to use JSTL, and JSTL cannot use enums, i have to do this like that
        request.getSession().setAttribute("contacts", curcontacts);
        
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
