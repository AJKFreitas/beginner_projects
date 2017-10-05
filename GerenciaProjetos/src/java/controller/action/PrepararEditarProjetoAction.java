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
import model.FuncionarioProjeto;
import model.Projeto;

/**
 *
 * @author paulo.junior6
 */
public class PrepararEditarProjetoAction implements Action{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int codProjeto = Integer.parseInt(request.getParameter("codProjeto"));
        try {
            request.setAttribute("operacao", "Editar");
            request.setAttribute("departamentos", Departamento.obterDepartamentos());
            request.setAttribute("projeto", Projeto.obterProjeto(codProjeto));
            request.setAttribute("funcionariosProjeto", FuncionarioProjeto.obterFuncionarioProjetos(codProjeto));
            RequestDispatcher view = request.getRequestDispatcher("/manterProjeto.jsp");
            view.forward(request, response);
        } catch (ServletException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
}
