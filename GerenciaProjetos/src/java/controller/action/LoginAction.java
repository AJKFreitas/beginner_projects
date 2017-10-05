/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.action;

import controller.Action;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Donizeth
 */
public class LoginAction implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(request.getParameter("msg")!=null && request.getParameter("msg").equals("Falha")){
            try {
                request.setAttribute("msgFalha", "Login ou Senha Incorreto!");
                RequestDispatcher view = request.getRequestDispatcher("index.jsp");
                view.forward(request, response);
            } catch (ServletException ex) {
            }
        }else{
            response.sendRedirect("index.jsp");    
        }
    }
    
    
}
