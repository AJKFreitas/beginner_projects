/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.action;

import controller.Action;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Funcionario;

/**
 *
 * @author Donizeth
 */
public class AutenticaFuncionarioAction implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        try {
            Funcionario funcionario = new Funcionario(login, Funcionario.convertPasswordToMD5(senha));
            if(!funcionario.autenticar().isEmpty()){
                request.getSession().setAttribute("logado", funcionario);
                response.sendRedirect("FrontController?action=Home");
            }else{
                response.sendRedirect("FrontController?action=Login&msg=Falha");
            }
        } catch (ClassNotFoundException ex) {
        } catch (Exception ex) {
        }
    }
    
}
