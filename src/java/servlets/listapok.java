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
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ferran
 */
@WebServlet(name = "listapok", urlPatterns = {"/listapok"})
public class listapok extends HttpServlet {
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
            List<Pokemon> pokemons = ejb.pokemonsortvid();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet listapok</title>");
            out.println("     <link href=\"css/bootstrap.min.css\" rel=\"stylesheet\" type=\"text/css\"/>");
 
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='jumbotron'>");
            out.println("<h1>Servlet listapok at " + request.getContextPath() + "</h1>");
            out.println("</div>");
                  
            out.println("<div class=\"container\">");
            
            if (pokemons.size()==0){
                 out.print("<h1>No hay  pokemons</h1>");
            }
            else{
            out.println("<table class='table table-bordered'>");
            out.println("<tr>");
            out.println("<th> Nombre </th>");
            out.println("<th> Tipo </th>");
            out.println("<th> Habilidad</th>");
            out.println("<th> Ataque </th>");
            out.println("<th> Velocidad </th>");
            out.println("<th> Vida </th>");
            out.println("<th> Nivel </th>");
            out.println("</tr>");
            for (Pokemon p : pokemons) {

                out.println("<tr>");
                out.println("<td>" + p.getName() + "</td>");
                out.println("<td>" + p.getType() + "</td>");
                out.println("<td>" + p.getAbility() + "</td>");
                out.println("<td>" + p.getAttack() + "</td>");
                out.println("<td>" + p.getSpeed() + "</td>");
                out.println("<td>" + p.getLife() + "</td>");
                out.println("<td>" + p.getLevel() + "</td>");
                out.println("</tr>");

            }
            out.println("</table>");
            }
            out.println(" <a href=\"index.html\" class=\"btn btn-warning\">Volver a indice</a>");
                 out.println("</div>");
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
