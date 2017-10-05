/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.action;

import controller.Action;
import controller.StrategyFactory;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Departamento;
import model.Endereco;
import model.Funcionario;
import model.Telefone;
import tratamentoErro.ErroSQL;

/**
 *
 * @author paulo.junior6
 */
public class ConfirmarEditarFuncionarioAction implements Action{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try{
            Funcionario funcionario = montaFuncionario(request);
            funcionario.editar();
            response.sendRedirect("FrontController?action=ListarFuncionarios");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            request.getSession().setAttribute("mensagem",  ErroSQL.getInstance().trataErro(ex.getErrorCode()));
            request.getSession().setAttribute("caminho", "ListarFuncionarios");
            response.sendRedirect ("erro.jsp");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public Funcionario montaFuncionario (HttpServletRequest request) throws Exception{
        Funcionario funcionario = new Funcionario();
        int matricula = Integer.parseInt(request.getParameter("txtMatricula"));
        int codDepartamento = Integer.parseInt(request.getParameter("optCodDepartamento"));
        String nome = request.getParameter("txtNome");
        String cpf = request.getParameter("txtCPF");
        String formAcadem = request.getParameter("optFormAcad");
        String area = request.getParameter("optArea");
        String funcao = request.getParameter("optFuncao");
        String login = request.getParameter("txtLogin");
        funcionario.setMatricula(matricula);
        funcionario.setNome(nome);
        funcionario.setCpf(cpf);
        funcionario.setTelefones(montaTelefones(request));
        funcionario.setEndereco(montaEndereco(request));
        funcionario.setFormAcademica(formAcadem);
        funcionario.setAreaAtuacao(area);
        funcionario.setDepartamento(Departamento.obterDepartamento(codDepartamento));
        funcionario.setFuncao(StrategyFactory.create(funcao));
        funcionario.setLogin(login);
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
        Endereco endereco = new Endereco((new Funcionario(matricula)), logradouro, numero, bairro, cidade, uf, cep);
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
}
