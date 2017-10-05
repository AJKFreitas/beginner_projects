/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.SQLException;
import java.util.List;
import model.persistence.ProjetoObservadoDAO;

/**
 *
 * @author paulo.junior6
 */
public class ProjetoObservado {
    private int idProjetoObservado;
    private Projeto projeto;
    private Departamento departamento;
    private String notificacao;
    private int lido;

    public ProjetoObservado( Projeto projeto, Departamento departamento, String notificacao) {
        this.projeto = projeto;
        this.departamento = departamento;
        this.notificacao = notificacao;
    }

    public ProjetoObservado(int idProjetoObservado) {
        this.idProjetoObservado = idProjetoObservado;
    }

    public int getIdProjetoObservado() {
        return idProjetoObservado;
    }

    public void setIdProjetoObservado(int idProjetoObservado) {
        this.idProjetoObservado = idProjetoObservado;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public String getNotificacao() {
        return notificacao;
    }

    public void setNotificacao(String notificacao) {
        this.notificacao = notificacao;
    }

    public int getLido() {
        return lido;
    }

    public void setLido(int lido) {
        this.lido = lido;
    }
    
    public void incluir() throws SQLException, 
            ClassNotFoundException {
        ProjetoObservadoDAO.incluir(this);
    }
    
    public void excluir() throws SQLException, 
            ClassNotFoundException {
        ProjetoObservadoDAO.excluir(this);
    }
    
    public static ProjetoObservado obterProjetoObservado(int codProjetoObservado) 
            throws ClassNotFoundException, Exception {
        return ProjetoObservadoDAO.obterProjetoObservado(codProjetoObservado);
    }
    
    public static List<ProjetoObservado> obterProjetoObservados() 
            throws ClassNotFoundException, Exception {
        return ProjetoObservadoDAO.obterProjetoObservados();
    }
    
}
