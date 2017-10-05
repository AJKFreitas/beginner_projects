/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.SQLException;
import model.persistence.EnderecoDAO;

/**
 *
 * @author Donizeth
 */
public class Endereco {
    private Funcionario funcionario;
    private String logradouro;
    private int numero;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;

    public Endereco() {
        super();
    }

    public Endereco(Funcionario funcionario, String logradouro, int numero, String bairro, String cidade, String uf, String cep) {
        super();
        this.funcionario = funcionario;
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.cep = cep;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
    
    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
    
    public void incluir() throws SQLException, 
            ClassNotFoundException,
            Exception {
        EnderecoDAO.incluir(this);
    }
    
    public void editar() throws SQLException, 
            ClassNotFoundException {
        EnderecoDAO.editar(this);
    }
    
    public void excluir() throws SQLException, 
            ClassNotFoundException {
        EnderecoDAO.excluir(this);
    }
    
    public static Endereco obterEndereco(Funcionario funcionario) 
            throws ClassNotFoundException, Exception {
        return EnderecoDAO.obterEndereco(funcionario);
    } 
}
