/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.action;

import controller.Action;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Departamento;
import model.ProjetoObservado;

/**
 *
 * @author paulo.junior6
 */
public class ListarDepartamentosAction implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            List<ProjetoObservado> notificacoes = ProjetoObservado.obterProjetoObservados();
            if(notificacoes.size()>0){
                request.setAttribute("notificacoes", notificacoes);
                request.setAttribute("totalNotificacoes", notificacoes.size());
            }
            request.setAttribute("departamentos", Departamento.obterDepartamentos());
            RequestDispatcher view = 
                    request.getRequestDispatcher("/mostraDepartamento.jsp");
            view.forward(request, response);
        } catch (Exception ex) {
        }
    }
    
}
