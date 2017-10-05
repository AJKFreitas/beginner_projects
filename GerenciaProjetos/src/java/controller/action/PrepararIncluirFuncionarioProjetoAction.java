/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.action;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Action;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import model.Funcionario;
import model.Projeto;

public class PrepararIncluirFuncionarioProjetoAction implements Action{
    
    @Override
    public void execute (HttpServletRequest request, HttpServletResponse response)
        throws  IOException {
        int codProjeto = Integer.parseInt(request.getParameter("cod"));
        try {
            request.setAttribute("operacao", "Incluir");
            request.setAttribute("projeto", Projeto.obterProjeto(codProjeto));
            request.setAttribute("funcionarios", Funcionario.obterFuncionarioNAlocados(codProjeto));
            RequestDispatcher view
                    = request.getRequestDispatcher("/manterFuncionarioProjeto.jsp");
            view.forward(request, response);
        } catch (ServletException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}


