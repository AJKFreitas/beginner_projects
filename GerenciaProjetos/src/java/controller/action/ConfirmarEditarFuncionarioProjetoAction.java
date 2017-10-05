/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.action;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Action;
import controller.StrategyFactory;
import model.Funcionario;
import model.FuncionarioProjeto;
import model.Projeto;
import tratamentoErro.ErroSQL;

/**
 *
 * @author simara.salgado
 */
public class ConfirmarEditarFuncionarioProjetoAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int codProjeto = Integer.parseInt(request.getParameter("txtCodProjeto"));
        int matricula = Integer.parseInt(request.getParameter("optFuncionario"));
        String funcao = request.getParameter("optFuncao");
        int cargaHoraria = Integer.parseInt(request.getParameter("txtCargaHoraria"));
        String dataInicio = request.getParameter("txtDataInicio");
        String dataTermino = request.getParameter("txtDataTermino");
        String observacao = request.getParameter("txtObservacao");

        FuncionarioProjeto funcionarioProjeto = new FuncionarioProjeto();
        funcionarioProjeto.setProjeto(new Projeto(codProjeto));
        funcionarioProjeto.setFuncionario(new Funcionario(matricula));
        funcionarioProjeto.setFuncao(StrategyFactory.create(funcao));
        funcionarioProjeto.setCargaHoraria(cargaHoraria);
        funcionarioProjeto.setDataInicio(dataInicio);
        funcionarioProjeto.setDataTermino(dataTermino);
        funcionarioProjeto.setObservacao(observacao);
        try {
            funcionarioProjeto.editar();
            response.sendRedirect("FrontController?action=PrepararEditarProjeto&codProjeto="+codProjeto);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            request.getSession().setAttribute("mensagem", ErroSQL.getInstance().trataErro(ex.getErrorCode()));
            request.getSession().setAttribute("caminho", "PrepararEditarFuncionarioProjeto&cod="+codProjeto+"&matricula="+matricula);
            response.sendRedirect("erro.jsp");
        }

    }
}
