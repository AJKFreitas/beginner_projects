/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.action;

import controller.Action;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ProjetoObservado;
import model.persistence.ProjetoObservadoDAO;

/**
 *
 * @author paulo.junior6
 */
public class ConfirmarNotificacaoAction implements Action{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int idProjetoObservado = Integer.parseInt(request.getParameter("idProjetoObservado"));
            ProjetoObservado projetoObservado = new ProjetoObservado(idProjetoObservado);
            try{
                ProjetoObservadoDAO.confirmar(projetoObservado);
                response.sendRedirect("FrontController?action=ListarDepartamentos");

            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();

            } catch (SQLException ex) {
                response.sendRedirect ("FrontController?action=ListarDepartamentos");
                ex.printStackTrace();
            }
        } catch (Exception e) {
        }
    }
    
}
