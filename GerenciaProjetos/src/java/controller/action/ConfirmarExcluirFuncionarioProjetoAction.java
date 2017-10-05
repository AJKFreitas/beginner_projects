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
import model.Funcionario;
import model.FuncionarioProjeto;
import model.Projeto;
import tratamentoErro.ErroSQL;

/**
 *
 * @author simara.salgado
 */
public class ConfirmarExcluirFuncionarioProjetoAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int matricula = Integer.parseInt(request.getParameter("optFuncionario"));
        int codProjeto = Integer.parseInt(request.getParameter("txtCodProjeto"));
        FuncionarioProjeto funcionarioProjeto = new FuncionarioProjeto(new Funcionario(matricula), new Projeto(codProjeto));
        try {
            funcionarioProjeto.excluir();
            response.sendRedirect("FrontController?action=PrepararEditarProjeto&codProjeto=" + codProjeto);

        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();

        } catch (SQLException ex) {
            request.getSession().setAttribute("mensagem", ErroSQL.getInstance().trataErro(ex.getErrorCode()));
            request.getSession().setAttribute("caminho", "ListarFuncionarios");
            response.sendRedirect("erro.jsp");
        }

    }
}
