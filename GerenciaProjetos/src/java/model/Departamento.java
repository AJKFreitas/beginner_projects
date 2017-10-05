/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.SQLException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.persistence.DepartamentoDAO;

/**
 *
 * @author paulo.junior6
 */
public class Departamento implements Observer{
    
    private int codDepartamento;
    private String nome;
    private String telefone;
    private String notificacao;
    private Observable projeto;
    
    public Departamento(int codDepartamento, String nome, String telefone){
        super();
        this.codDepartamento = codDepartamento;
        this.nome = nome;
        this.telefone = telefone;
    }

    public Departamento(int codDepartamento) {
        super();
        this.codDepartamento = codDepartamento;
    }
    
    public Departamento(Observable projeto) {
        super();
        this.projeto = projeto;
        projeto.addObserver(this);
    }

    public int getCodDepartamento() {
        return codDepartamento;
    }

    public void setCodDepartamento(int codDepartamento) {
        this.codDepartamento = codDepartamento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Observable getProjeto() {
        return projeto;
    }

    public void setProjeto(Observable projeto) {
        this.projeto = projeto;
    }

    public String getNotificacao() {
        return notificacao;
    }

    public void setNotificacao(String notificacao) {
        this.notificacao = notificacao;
    }
    
    public void incluir() throws SQLException, 
            ClassNotFoundException {
        DepartamentoDAO.incluir(this);
    }
    
    public void editar() throws SQLException, 
            ClassNotFoundException {
        DepartamentoDAO.editar(this);
    }
    
    public void excluir() throws SQLException, 
            ClassNotFoundException {
        DepartamentoDAO.excluir(this);
    }
    
    public static Departamento obterDepartamento(int codDepartamento) 
            throws ClassNotFoundException, Exception {
        return DepartamentoDAO.obterDepartamento(codDepartamento);
    }
    
    public static List<Departamento> obterDepartamentos() 
            throws ClassNotFoundException, Exception {
        return DepartamentoDAO.obterDepartamentos();
    }

    @Override
    public void update(Observable projetoObservado, Object arg) {
        if(projetoObservado instanceof Projeto){
            Projeto projeto = (Projeto) projetoObservado;
            try {
                this.notificacao="<strong>Atenção!</strong> O projeto <strong>"+projeto.getNome()+"</strong> "
                        + "foi alocado ao departamento <strong>"+projeto.getDepartamento().getNome()+"</strong>!";
            } catch (Exception ex) {
            }
        }
    }
    
}
