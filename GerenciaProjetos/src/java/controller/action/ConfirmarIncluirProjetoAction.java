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
import controller.StateFactory;
import model.Departamento;
import model.Projeto;
import model.ProjetoObservado;
import model.state.ProjetoState;
import tratamentoErro.ErroSQL;

/**
 *
 * @author simara.salgado
 */
public class ConfirmarIncluirProjetoAction implements Action{
    
    @Override
    public void execute (HttpServletRequest request, HttpServletResponse response)
        throws  IOException {
    int codProjeto = Integer.parseInt(request.getParameter("txtCodProjeto"));
    String nome = request.getParameter("txtNomeProjeto");
    String descricao = request.getParameter("txtDescricaoProjeto");
    int codDepartamento = Integer.parseInt(request.getParameter("optCodDepartamento"));
    String cliente = request.getParameter("txtClienteProjeto");
    String telCliente = request.getParameter("txtTelClienteProjeto");
    String dataInicio = request.getParameter("txtDataInicio");
    String dataTermino = request.getParameter("txtDataTermino");
        try{
            Departamento departamento = null;
            if (codDepartamento != 0) {
                departamento = Departamento.obterDepartamento(codDepartamento);
            }
            ProjetoState projetoState = StateFactory.create("Negociacao");
            Projeto projeto = new Projeto (codProjeto, nome, dataInicio, dataTermino, departamento);
            projeto.setDescricao(descricao);
            projeto.setCliente(cliente);
            projeto.setTelCliente(telCliente);
            projeto.setStatus(projetoState);
            projeto.incluir();
            departamento = new Departamento(projeto);
            departamento.setCodDepartamento(codDepartamento);
            projeto.novoProjeto();
            ProjetoObservado proObs = new ProjetoObservado(projeto, departamento, departamento.getNotificacao());
            proObs.incluir();
            response.sendRedirect("FrontController?action=ListarProjetos");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
            request.getSession().setAttribute("mensagem",  ErroSQL.getInstance().trataErro(ex.getErrorCode()));
            request.getSession().setAttribute("caminho", "PrepararIncluirProjeto");
            response.sendRedirect ("erro.jsp");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }
}


