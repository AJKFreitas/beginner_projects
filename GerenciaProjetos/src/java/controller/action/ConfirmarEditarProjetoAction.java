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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Departamento;
import model.Projeto;
import tratamentoErro.ErroSQL;

/**
 *
 * @author paulo.junior6
 */
public class ConfirmarEditarProjetoAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int codProjeto = Integer.parseInt(request.getParameter("txtCodProjeto"));
        String nome = request.getParameter("txtNomeProjeto");
        String descricao = request.getParameter("txtDescricaoProjeto");
        int codDepartamento = Integer.parseInt(request.getParameter("optCodDepartamento"));
        String cliente = request.getParameter("txtClienteProjeto");
        String telCliente = request.getParameter("txtTelClienteProjeto");
        String dataInicio = request.getParameter("txtDataInicio");
        String dataTermino = request.getParameter("txtDataTermino");
        String status = request.getParameter("optStatus");
        try {
            Departamento departamento = null;
            if (codDepartamento != 0) {
                departamento = Departamento.obterDepartamento(codDepartamento);
            }
            Projeto projeto = Projeto.obterProjeto(codProjeto);
            projeto.setNome(nome);
            projeto.setDescricao(descricao);
            projeto.setCliente(cliente);
            projeto.setTelCliente(telCliente);
            projeto.setDataInicio(dataInicio);
            projeto.setDepartamento(departamento);
            projeto.setDataTermino(dataTermino);
            RequestDispatcher view = null;
            request.setAttribute("codProjeto", codProjeto);
            if(!projeto.getStatus().getStatus().equals(status)){
                if (status.equals("Negociacao")) {
                    request.setAttribute("mensagem", projeto.negociacao());
                } else if (status.equals("Desenvolvimento")) {
                    request.setAttribute("mensagem", projeto.desenvolvimento());
                } else if (status.equals("Entregue")) {
                    request.setAttribute("mensagem", projeto.entregue());
                } else if (status.equals("Cancelado")) {
                    request.setAttribute("mensagem", projeto.cancelado());
                }
                projeto.editar();
                view = request.getRequestDispatcher("/mensagem.jsp");
                view.forward(request, response);
            }else{                
                projeto.editar();
                response.sendRedirect("FrontController?action=ListarProjetos");
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();

        } catch (SQLException ex) {
            request.getSession().setAttribute("mensagem",  ErroSQL.getInstance().trataErro(ex.getErrorCode()));
            request.getSession().setAttribute("caminho", "ListarProjetos");
            response.sendRedirect ("erro.jsp");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
