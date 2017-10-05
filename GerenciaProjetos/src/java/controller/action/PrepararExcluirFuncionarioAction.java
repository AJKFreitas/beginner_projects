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
import model.Endereco;
import model.Funcionario;

/**
 *
 * @author paulo.junior6
 */
public class PrepararExcluirFuncionarioAction implements Action{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int matricula = Integer.parseInt(request.getParameter("matricula"));
        try {
            request.setAttribute("operacao", "Excluir");
            request.setAttribute("departamentos", Departamento.obterDepartamentos());
            Funcionario funcionario = Funcionario.obterFuncionario(matricula);
            funcionario.setEndereco(Endereco.obterEndereco(funcionario));
            request.setAttribute("funcionario", funcionario);
            RequestDispatcher view = request.getRequestDispatcher("/manterFuncionario.jsp");
            view.forward(request, response);
        } catch (ServletException ex) {
        } catch (IOException ex) {
        } catch (ClassNotFoundException ex) {
        } catch (SQLException ex) {
        } catch (Exception ex) {
        }
    }
    
}
