/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.SQLException;
import java.util.List;
import model.persistence.FuncionarioProjetoDAO;
import model.strategy.Funcao;

/**
 *
 * @author donizeth
 */
public class FuncionarioProjeto {
    private Funcionario funcionario;
    private Projeto projeto;
    private Funcao funcao;
    private int cargaHoraria;
    private String dataInicio;
    private String dataTermino;
    private String Observacao;

    public FuncionarioProjeto(Funcionario funcionario, Projeto projeto) {
        this.funcionario = funcionario;
        this.projeto = projeto;
    }

    public FuncionarioProjeto() {
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public Funcao getFuncao() {        return funcao;    }
    public void setFuncao(Funcao funcao) {        this.funcao = funcao;    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(String dataTermino) {
        this.dataTermino = dataTermino;
    }

    public String getObservacao() {
        return Observacao;
    }

    public void setObservacao(String Observacao) {
        this.Observacao = Observacao;
    }
    
    public void incluir() throws SQLException, 
            ClassNotFoundException {
        FuncionarioProjetoDAO.incluir(this);
    }
    
    public void editar() throws SQLException, 
            ClassNotFoundException {
        FuncionarioProjetoDAO.editar(this);
    }
    
    public void excluir() throws SQLException, 
            ClassNotFoundException {
        FuncionarioProjetoDAO.excluir(this);
    }
    
    public static FuncionarioProjeto obterFuncionarioProjeto(FuncionarioProjeto fp)
            throws ClassNotFoundException, SQLException {
        return FuncionarioProjetoDAO.obterFuncionarioProjeto(fp);
    }

    public static List<FuncionarioProjeto> obterFuncionarioProjetos(int codProjeto)
            throws ClassNotFoundException, Exception {
        return FuncionarioProjetoDAO.obterFuncionarioProjetos(codProjeto);
    }
    
    public String validarCargaHoraria() throws SQLException, ClassNotFoundException{
        int horasAlocadas = FuncionarioProjetoDAO.obterCargaHoraria(this);
        int total = horasAlocadas+getCargaHoraria();
        String msg = "ok";
        if(getCargaHoraria()>8 || total>8){
            return msg = "Funcionário só tem disponiveis "+(8-horasAlocadas)+" horas para serem alocadas.";
        }
        return msg;
    }
}
