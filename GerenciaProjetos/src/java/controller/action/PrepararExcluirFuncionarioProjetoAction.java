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
import model.FuncionarioProjeto;
import model.Projeto;

/**
 *
 * @author simara.salgado
 */
public class PrepararExcluirFuncionarioProjetoAction implements Action{
    
    @Override
    public void execute (HttpServletRequest request, HttpServletResponse response)
        throws  IOException {
        int codProjeto = Integer.parseInt(request.getParameter("cod"));
        int matricula = Integer.parseInt(request.getParameter("matricula"));
        FuncionarioProjeto fp = new FuncionarioProjeto(new Funcionario(matricula), new Projeto(codProjeto));
        try {
            request.setAttribute("operacao", "Excluir");
            request.setAttribute("projeto", Projeto.obterProjeto(codProjeto));
            request.setAttribute("funcionarios", Funcionario.obterFuncionarios());
            request.setAttribute("funcionarioProjeto", FuncionarioProjeto.obterFuncionarioProjeto(fp));
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


