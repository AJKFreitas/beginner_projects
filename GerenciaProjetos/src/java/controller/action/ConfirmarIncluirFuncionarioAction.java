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
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import model.Departamento;
import model.Endereco;
import model.Funcionario;
import model.Telefone;
import model.persistence.FuncionarioDAO;
import tratamentoErro.ErroSQL;

/**
 *
 * @author paulo.junior6
 */
public class ConfirmarIncluirFuncionarioAction implements Action{
    
    @Override
    public void execute (HttpServletRequest request, HttpServletResponse response)
        throws  IOException {
        String caminho;
        if(request.getSession().getAttribute("logado")==null){
            caminho = "Login";
        }else{
            caminho = "ListarFuncionarios";
        }
        int codDepartamento = Integer.parseInt(request.getParameter("optCodDepartamento"));
            try{
                Departamento departamento = null;
                if (codDepartamento != 0) {
                    departamento = Departamento.obterDepartamento(codDepartamento);
                }
                Funcionario funcionario = montaFuncionario(request);
                funcionario.setDepartamento(departamento);
                funcionario.incluir();
                if(!funcionario.getLogin().equals(request.getParameter("txtLogin"))){
                    request.getSession().setAttribute("mensagem",  "Este login ja existe, por isso foi adicionado um sequencial e ficou da seguinte forma: "+funcionario.getLogin());
                    request.getSession().setAttribute("caminho", caminho);
                    response.sendRedirect ("erro.jsp");
                }
                response.sendRedirect("FrontController?action="+caminho);
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            } catch (SQLException ex) {
                ex.printStackTrace();
                request.getSession().setAttribute("mensagem",  ErroSQL.getInstance().trataErro(ex.getErrorCode()));
                request.getSession().setAttribute("caminho", "PrepararIncluirFuncionario");
                response.sendRedirect ("erro.jsp");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        
    }
    
    public Funcionario montaFuncionario (HttpServletRequest request) throws SQLException, ClassNotFoundException, NoSuchAlgorithmException{
        Funcionario funcionario = new Funcionario();
        int matricula = Integer.parseInt(request.getParameter("txtMatricula"));
        String nome = request.getParameter("txtNome");
        String cpf = request.getParameter("txtCPF");
        String formAcadem = request.getParameter("optFormAcad");
        String area = request.getParameter("optArea");
        String funcao = request.getParameter("optFuncao");
        String login = request.getParameter("txtLogin");
        String senha = request.getParameter("txtSenha");
        funcionario.setMatricula(matricula);
        funcionario.setNome(nome);
        funcionario.setCpf(cpf);
        funcionario.setTelefones(montaTelefones(request));
        funcionario.setEndereco(montaEndereco(request));
        funcionario.setFormAcademica(formAcadem);
        funcionario.setAreaAtuacao(area);
        if(funcao == null){funcao="Analista";}
        funcionario.setFuncao(StrategyFactory.create(funcao));
        funcionario.setLogin(validarLogin(login));
        funcionario.setSenha(Funcionario.convertPasswordToMD5(senha));
        return funcionario;
    }

    private Endereco montaEndereco(HttpServletRequest request) {
        int matricula = Integer.parseInt(request.getParameter("txtMatricula"));
        String logradouro = request.getParameter("txtEndLogradouro");
        int numero = Integer.parseInt(request.getParameter("txtEndNum"));
        String bairro = request.getParameter("txtEndBairro");
        String cidade = request.getParameter("txtEndCidade");
        String uf = request.getParameter("txtEndUF");
        String cep = request.getParameter("txtEndCEP");
        Endereco endereco = new Endereco(new Funcionario(matricula), logradouro, numero, bairro, cidade, uf, cep);
        return endereco;
    }
    
    private List<Telefone> montaTelefones (HttpServletRequest request){
        int matricula = Integer.parseInt(request.getParameter("txtMatricula"));
        List<Telefone> telefones = new ArrayList();
        int aux = 0;
        while((request.getParameter("tel"+aux)!=null)&&(!request.getParameter("tel"+aux).isEmpty())){
            Telefone telefone = new Telefone(new Funcionario(matricula), request.getParameter("tel"+aux));
            telefones.add(telefone);
            aux++;
        }
        return telefones;
    }

    private String validarLogin(String login) throws SQLException, ClassNotFoundException {
        int sequencial = FuncionarioDAO.loginExiste(login);
        if(sequencial>0){
            return login+sequencial;
        }else{
            return login;
        }
    }
    
}


