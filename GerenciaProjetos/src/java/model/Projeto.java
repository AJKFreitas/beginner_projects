/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.SQLException;
import java.util.List;
import java.util.Observable;
import model.state.ProjetoState;
import model.persistence.ProjetoDAO;

/**
 *
 * @author paulo.junior6
 */
public class Projeto extends Observable{

    private int codProjeto;
    private String nome;
    private String descricao;
    private Departamento departamento;
    private String cliente;
    private String telCliente;
    private String dataInicio;
    private String dataTermino;
    private ProjetoState status;

    public Projeto(int codProjeto, String nome, String dataInicio, String dataTermino, Departamento departamento) {
        super();
        this.codProjeto = codProjeto;
        this.nome = nome;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
        this.departamento = departamento;
    }
    
    public Projeto(int codProjeto, String nome, String dataInicio, String dataTermino, Departamento departamento, ProjetoState status) {
        super();
        this.codProjeto = codProjeto;
        this.nome = nome;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
        this.departamento = departamento;
        this.status = status;
    }

    public Projeto(int codProjeto) {
        super();
        this.codProjeto = codProjeto;
    }

    public int getCodProjeto() {        return codProjeto;    }
    public void setCodProjeto(int codProjeto) {       this.codProjeto = codProjeto;    }

    public String getNome() {       return nome;    }
    public void setNome(String nome) {        this.nome = nome;    }

    public String getDescricao(){   return descricao;    }
    public void setDescricao(String descricao) {        this.descricao = descricao;    }

    public String getCliente() {        return cliente;    }
    public void setCliente(String cliente) {        this.cliente = cliente;    }

    public String getTelCliente() {        return telCliente;    }
    public void setTelCliente(String telCliente) {        this.telCliente = telCliente;    }
    
    public String getDataInicio() {        return dataInicio;    }
    public void setDataInicio(String dataInicio) {        this.dataInicio = dataInicio;    }

    public String getDataTermino() {        return dataTermino;    }
    public void setDataTermino(String dataTermino) {        this.dataTermino = dataTermino;    }
    
    public Departamento getDepartamento(){      return this.departamento;    }
    public void setDepartamento(Departamento departamento) {        this.departamento = departamento;    }
    
    public ProjetoState getStatus() {        return status;    }
    public void setStatus(ProjetoState status) {        this.status = status;    }
    
    public String getNomeStatus(){ return status.getStatus();    }
    
    public String negociacao(){        return status.negociacao(this);    }
    
    public String desenvolvimento(){        return status.desenvolvimento(this);    }
    
    public String cancelado(){        return status.cancelado(this);    }
    
    public String entregue(){        return status.entregue(this);    }
    
    public void novoProjeto(){
        setChanged();
        notifyObservers();
    }
    public void incluir() throws SQLException, 
            ClassNotFoundException {
        ProjetoDAO.incluir(this);
    }
    
    public void editar() throws SQLException, 
            ClassNotFoundException {
        ProjetoDAO.editar(this);
    }
    
    public void excluir() throws SQLException, 
            ClassNotFoundException {
        ProjetoDAO.excluir(this);
    }
    
    public static Projeto obterProjeto(int codProjeto)
            throws ClassNotFoundException, SQLException {
        return ProjetoDAO.obterProjeto(codProjeto);
    }

    public static List<Projeto> obterProjetos()
            throws ClassNotFoundException, Exception {
        return ProjetoDAO.obterProjetos();
    }
}
