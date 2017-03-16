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
@WebServlet(name = "BorrarP", urlPatterns = {"/BorrarP"})
public class BorrarP extends HttpServlet {
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
           List<Pokemon> pokemons = ejb.GetAllPokemons();
             out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet BorraP</title>");
            out.println("     <link href=\"css/bootstrap.min.css\" rel=\"stylesheet\" type=\"text/css\"/>");
 
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='jumbotron'>");
            out.println("<h1>Servlet  BorraP at " + request.getContextPath() + "</h1>");
            out.println("</div>");
            
            out.println("<div class=\"container\">");
            if (pokemons.size()==0){
                 out.print("<h1>No hay  pokemons</h1>");
            }
            else{
            out.println("<form method=\"POST\">");
            out.println("<select class='form-control' name=\"pokemon\">");
            
            for (Pokemon p : pokemons) {
                    out.println("<option value=\"" + p.getName() + "\">" + p.getName() + "</option>");
                }
            out.println("</select>");
            out.println("<input type=\"submit\" name=\"borrar\" value=\"borrar\">");
            out.println("</form>");
            
            if ("borrar".equals(request.getParameter("borrar"))) {
                if(ejb.RmPokemon(request.getParameter("pokemon"))){
                    out.println("<h1>Pokemon borrado</h1>");
                }else{
                    out.println("<h1>Pokemon no encontrado</h1>");
                }
            }}
             out.println("<a href=\"index.html\" class=\"btn btn-warning\">Volver a indice</a>");
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
