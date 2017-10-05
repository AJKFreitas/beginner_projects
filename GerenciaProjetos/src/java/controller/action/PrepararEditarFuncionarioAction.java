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
import model.Funcionario;
import tratamentoErro.ErroSQL;

/**
 *
 * @author paulo.junior6
 */
public class PrepararEditarFuncionarioAction implements Action{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            request.setAttribute("operacao", "Editar");
            request.setAttribute("departamentos", Departamento.obterDepartamentos());
            int matricula = Integer.parseInt(request.getParameter("matricula"));
            Funcionario funcionario = Funcionario.obterFuncionario(matricula);
            request.setAttribute("funcionario", funcionario);
            RequestDispatcher view = request.getRequestDispatcher("/manterFuncionario.jsp");
            view.forward(request, response);
        } catch (ServletException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            request.getSession().setAttribute("mensagem",  ErroSQL.getInstance().trataErro(ex.getErrorCode()));
            request.getSession().setAttribute("caminho", "PrepararIncluirFuncionario");
            response.sendRedirect ("erro.jsp");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
}
