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
import model.Projeto;

/**
 *
 * @author simara.salgado
 */
public class ListarProjetosAction implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            request.setAttribute("projetos", Projeto.obterProjetos());
            RequestDispatcher view = 
                    request.getRequestDispatcher("/mostraProjeto.jsp");
            view.forward(request, response);
        } catch (Exception ex) {
        }
    }
}
