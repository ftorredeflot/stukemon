/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.StukemonEjb;
import entities.Pokemon;
import entities.Trainer;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author ferran
 */
@WebServlet(name = "Alta", urlPatterns = {"/Alta"})
public class Alta extends HttpServlet {
 @EJB StukemonEjb ejb;
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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            List<Pokemon> pokemons = ejb.pokemonsortvid();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Alta</title>");
            out.println("     <link href=\"css/bootstrap.min.css\" rel=\"stylesheet\" type=\"text/css\"/>");
 
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='jumbotron'>");
            out.println("<h1>Servlet Alta at " + request.getContextPath() + "</h1>");
            out.println("</div>");
            String name = request.getParameter("name");
            int potions = Integer.parseInt(request.getParameter("potions"));
            int pokeballs = Integer.parseInt(request.getParameter("pokeballs"));
            Trainer t = new Trainer(name,pokeballs,potions,0);
            //out.println(t);
            if (ejb.insertTrainer(t)) {
                out.println("entrenador dada de alta.");
            } else {
                out.println("Ya existe este entrenador.");
                
            }
            out.println(" <a href=\"index.html\" class=\"btn btn-warning\">Volver a indice</a>");
            out.println("</body>");
            out.println("</html>");
        }
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
