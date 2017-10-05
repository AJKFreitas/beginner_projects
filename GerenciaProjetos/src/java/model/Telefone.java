/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.SQLException;
import java.util.List;
import model.persistence.TelefoneDAO;

/**
 *
 * @author donizeth
 */
public class Telefone {
    
    private Funcionario funcionario;
    private String numTelefone;

    public Telefone(Funcionario matricula, String numTelefone) {
        this.funcionario = matricula;
        this.numTelefone = numTelefone;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public String getNumTelefone() {
        return numTelefone;
    }

    public void setNumTelefone(String numTelefone) {
        this.numTelefone = numTelefone;
    }
    
    public void editar() throws SQLException, 
            ClassNotFoundException {
        TelefoneDAO.editar(this);
    }
    
    public void excluir() throws SQLException, 
            ClassNotFoundException {
        TelefoneDAO.excluir(this.funcionario);
    }
    
    public static Telefone obterTelefone(Funcionario funcionario) 
            throws ClassNotFoundException, Exception {
        return TelefoneDAO.obterTelefone(funcionario);
    }
    
    public static List<Telefone> obterTelefones(Funcionario funcionario) 
            throws ClassNotFoundException, Exception {
        return TelefoneDAO.obterTelefones(funcionario);
    }
    
}
