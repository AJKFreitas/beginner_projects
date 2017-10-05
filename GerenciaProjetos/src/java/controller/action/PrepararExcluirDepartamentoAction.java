/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.action;

import controller.Action;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Departamento;

/**
 *
 * @author paulo.junior6
 */
public class PrepararExcluirDepartamentoAction implements Action{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            request.setAttribute("operacao", "Excluir");
            int codDepartamento = Integer.parseInt(request.getParameter("codDepartamento"));
            Departamento departamento = Departamento.obterDepartamento(codDepartamento);
            request.setAttribute("departamento", departamento);
            RequestDispatcher view = request.getRequestDispatcher("/manterDepartamento.jsp");
            view.forward(request, response);
        } catch (ServletException ex) {
        } catch (IOException ex) {
        } catch (ClassNotFoundException ex) {
        } catch (SQLException ex) {
        } catch (Exception ex) {
        }
    }
    
}
