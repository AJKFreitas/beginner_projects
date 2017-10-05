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
import model.Departamento;
import model.persistence.DepartamentoDAO;
import tratamentoErro.ErroSQL;

/**
 *
 * @author paulo.junior6
 */
public class ConfirmarEditarDepartamentoAction implements Action{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int codDepartamento = Integer.parseInt(request.getParameter("txtCodDepartamento"));
        String nome = request.getParameter("txtNomeDepartamento");
        String telefone = request.getParameter("txtTelefoneDepartamento");
        Departamento departamento = new Departamento (codDepartamento, nome, telefone);
        try{
            DepartamentoDAO.editar(departamento);
            response.sendRedirect("FrontController?action=ListarDepartamentos");

        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            request.getSession().setAttribute("mensagem",  ErroSQL.getInstance().trataErro(ex.getErrorCode()));
            request.getSession().setAttribute("caminho", "ListarDepartamentos");
            response.sendRedirect ("erro.jsp");
        }
    }
    
}
