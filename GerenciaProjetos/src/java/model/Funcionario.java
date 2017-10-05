/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import model.strategy.Funcao;
import java.sql.SQLException;
import java.util.List;
import model.persistence.FuncionarioDAO;

/**
 *
 * @author paulo.junior6
 */
public class Funcionario {
    
    private int matricula;
    private String nome;
    private String cpf;
    private List<Telefone> telefones;
    private String telefone;
    private Endereco endereco;
    private String formAcademica;
    private String areaAtuacao;
    private String login;
    private String senha;
    private Departamento departamento;
    private Funcao funcao;
    private int cargaHoraria=8;

    public Funcionario(int matricula) {
        super();
        this.matricula = matricula;
    }
    
    public Funcionario(int matricula, String nome) {
        super();
        this.matricula = matricula;
        this.nome = nome;
    }

    public Funcionario(String login, String senha) {
        super();
        this.login = login;
        this.senha = senha;
    }

    public Funcionario() {
    }

    public int getMatricula() {        return matricula;    }
    public void setMatricula(int matricula) {        this.matricula = matricula;    }

    public String getNome() {        return nome;    }
    public void setNome(String nome) {        this.nome = nome;    }

    public List<Telefone> getTelefones() {        return telefones;    }
    public void setTelefones(List<Telefone> telefones) {        this.telefones = telefones;    }

    public String getTelefone() {        return telefone;    }
    public void setTelefone(String telefone) {        this.telefone = telefone;    }

    public Endereco getEndereco() {        return endereco;    }
    public void setEndereco(Endereco endereco) {        this.endereco = endereco;    }

    public Departamento getDepartamento() {     return departamento;    }
    public void setDepartamento(Departamento departamento) throws Exception {
        this.departamento = departamento;
    }

    public String getCpf() {        return cpf;    }
    public void setCpf(String cpf) {        this.cpf = cpf;    }

    public String getFormAcademica() {        return formAcademica;    }
    public void setFormAcademica(String formAcademica) {        this.formAcademica = formAcademica;    }

    public String getAreaAtuacao() {        return areaAtuacao;    }
    public void setAreaAtuacao(String areaAtuacao) {        this.areaAtuacao = areaAtuacao;    }

    public String getLogin() {        return login;    }
    public void setLogin(String login) {        this.login = login;    }

    public String getSenha() {        return senha;    }
    public void setSenha(String senha) {        this.senha = senha;    }

    public Funcao getFuncao() {        return funcao;    }
    public void setFuncao(Funcao funcao) {        this.funcao = funcao;    }

    public int getCargaHoraria() {        return cargaHoraria;    }
    public void setCargaHoraria(int cargaHoraria) {        this.cargaHoraria = cargaHoraria;    }
    
    public void incluir() throws SQLException, 
            ClassNotFoundException,
            Exception {
        FuncionarioDAO.incluir(this);
    }
    
    public void editar() throws SQLException, 
            ClassNotFoundException {
        FuncionarioDAO.editar(this);
    }
    
    public void excluir() throws SQLException, 
            ClassNotFoundException {
        FuncionarioDAO.excluir(this);
    }
    
    public static Funcionario obterFuncionario(int matricula) 
            throws ClassNotFoundException, Exception {
        return FuncionarioDAO.obterFuncionario(matricula);
    }
    
    public static List<Funcionario> obterFuncionarios() 
            throws ClassNotFoundException, Exception {
        return FuncionarioDAO.obterFuncionarios();
    }
    
    public static List<Funcionario> obterFuncionarioNAlocados(int codProjeto)  
            throws ClassNotFoundException, Exception {
        return FuncionarioDAO.obterFuncionarioNAlocados(codProjeto);
    }
    
    public List<Funcionario> autenticar() throws SQLException, 
            ClassNotFoundException,
            Exception {
        return FuncionarioDAO.autenticar(this);
    }
    
    public static String convertPasswordToMD5(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        BigInteger hash = new BigInteger(1, md.digest(password.getBytes()));
        return String.format("%32x", hash);
    }
}
