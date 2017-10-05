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
import java.sql.SQLException;
import model.Projeto;
import model.persistence.ProjetoDAO;
import tratamentoErro.ErroSQL;

/**
 *
 * @author simara.salgado
 */
public class ConfirmarExcluirProjetoAction implements Action{

    @Override
    public void execute (HttpServletRequest request, HttpServletResponse response)
        throws  IOException {
    int codProjeto = Integer.parseInt(request.getParameter("txtCodProjeto"));
    Projeto projeto = new Projeto (codProjeto);
    try{
        ProjetoDAO.getInstance().excluir(projeto);
        response.sendRedirect("FrontController?action=ListarProjetos");

    } catch (ClassNotFoundException ex) {
        ex.printStackTrace();

    } catch (SQLException ex) {
        request.getSession().setAttribute("mensagem",  ErroSQL.getInstance().trataErro(ex.getErrorCode()));
        request.getSession().setAttribute("caminho", "ListarProjetos");
        response.sendRedirect ("erro.jsp");
    }
    
    }
}