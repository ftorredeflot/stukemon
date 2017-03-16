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
@WebServlet(name = "AltaP", urlPatterns = {"/AltaP"})
public class AltaP extends HttpServlet {
@EJB StukemonEjb ejb;

    /**@EJB StukemonEjb ejb;
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
            List<Trainer> trainers = ejb.getTrainersForAddPokemons();
            
           out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AltaP</title>");
            out.println("     <link href=\"css/bootstrap.min.css\" rel=\"stylesheet\" type=\"text/css\"/>");
 
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='jumbotron'>");
            out.println("<h1>Servlet AltaP at " + request.getContextPath() + "</h1>");
            out.println("</div>");
            
             if (trainers.size() > 0) {
                out.println("<div class=\"container\">");
                out.println("<form method=\"POST\">");
                out.println("<p><label>Nombre:</label> <input type=\"text\" class=\"form-control\" name=\"name\" required></p>");
                out.println("<p><label>Tipo</label> <input type=\"text\" class=\"form-control\"  name=\"type\" required></p>");
                out.println("<p><label>Habilidad</label> <input type=\"text\" class=\"form-control\" name=\"ability\" required></p>");
                out.println("<p><label>Ataque </label> <input type=\"text\" class=\"form-control\" name=\"atack\" required></p>");
                out.println("<p><label>Defensa </label> <input type=\"text\" class=\"form-control\" name=\"defense\" required></p> ");
                out.println("<p><label>Velocidad </label> <input type=\"text\" class=\"form-control\" name=\"speed\" required></p>");
                out.println("<p><label>Vida </label> <input type=\"text\" class=\"form-control\" name=\"life\" required></p>");
                out.println("<p><label>¿A qué entrenado quieres asignar el pokemon?</label></p>");
                out.println("<select class=\"form-control\" name=\"trainer\">");

                for (Trainer t : trainers) {
                    out.println("<option value=\"" + t.getName() + "\">" + t.getName() + "</option>");
                }

                out.println("</select>");
                out.println("<input class='btn btn-warning' type=\"submit\" name=\"alta\" value=\"guardar\">");
                out.println("</form>");
                out.println("</div>");
                

                if ("guardar".equals(request.getParameter("alta"))) {
                    
                    Pokemon p = new Pokemon(request.getParameter("name"),
                            request.getParameter("type"),
                            request.getParameter("ability"),
                            Integer.parseInt(request.getParameter("atack")),
                            Integer.parseInt(request.getParameter("defense")),
                            Integer.parseInt(request.getParameter("speed")),
                            Integer.parseInt(request.getParameter("life")),
                            0);
                    
                    Trainer t = ejb.findTrainerByName(request.getParameter("trainer"));
                    p.setTrainer(t);
                    
                    if (ejb.insertarPokemon(p)) {
                        System.out.println("<h2>Pokemon insertado</h2>");
                    } else {
                        System.out.println("<h2>El pokemon ya existe</h2>");
                    }
                }
            } else {
                out.print("<h1>No hay entrenadores disponibles para agregar pokemons</h1>");
            }
            out.println("<a href=\"index.html\" class=\"btn btn-warning\">Volver a indice</a>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods.">
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
