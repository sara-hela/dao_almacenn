
package com.mycompany.controlador;

import com.mycompany.dao.AvisoDAO;
import com.mycompany.dao.AvisoDAOImpl;
import com.mycompany.modelo.Aviso;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Inicio", urlPatterns = {"/inicio"})
public class Inicio extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            AvisoDAO dao = new AvisoDAOImpl();
            int id;
            Aviso avi = new Aviso();
            String action = (request.getParameter("action") != null) ? request.getParameter("action"): "view";
            
           switch(action){
               case "add":
                   request.setAttribute("aviso", avi);
                   request.getRequestDispatcher("frmaviso.jsp").forward(request, response);
                   break;
               case "edit":
                   id= Integer.parseInt(request.getParameter("id"));
                   avi = dao.getById(id);
                   System.out.println(avi);
                   request.setAttribute("aviso", avi);
                   request.getRequestDispatcher("frmaviso.jsp").forward(request, response);
                   break;
               case "delete":
                   id= Integer.parseInt(request.getParameter("id"));
                   dao.delete(id);
                   response.sendRedirect(request.getContextPath()+"/inicio");
                   break;
               case "view":
                   List<Aviso> lista = dao.getAll();
                   request.setAttribute("avisos", lista);
                   request.getRequestDispatcher("index.jsp").forward(request, response);
                default:
                   break;           
           }
        }catch (Exception ex){
                   System.out.println("Error "+ex.getMessage());
                   }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String descripcion = request.getParameter("descripcion");
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        float precio= Float.parseFloat(request.getParameter("Precio"));
        String categoria = request.getParameter("categoria");
        
        Aviso avi = new Aviso();
        
        avi.setId(id);
        avi.setDescripcion(descripcion);
        avi.setCantidad(cantidad);
        avi.setPrecio(precio);
        avi.setCategoria(categoria);
        
        if(id==0){
            try{
                AvisoDAO dao = new AvisoDAOImpl();
                dao.insert(avi);
                response.sendRedirect(request.getContextPath()+"/inicio");
            } catch( Exception ex){
                System.out.println("Error "+ex.getMessage());
            }
        }
        else{
            try{
               AvisoDAO dao = new AvisoDAOImpl();
               dao.update(avi);
               response.sendRedirect(request.getContextPath()+"/inicio");  
            } catch (Exception ex){
                System.out.println("Error "+ex.getMessage());
            }
        }
    }

}
