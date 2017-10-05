/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.persistence;

import controller.StrategyFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Departamento;
import model.Endereco;
import model.Funcionario;
import model.Telefone;

/**
 *
 * @author paulo.junior6
 */
public class FuncionarioDAO {
    private static FuncionarioDAO instance = new FuncionarioDAO();

    private FuncionarioDAO(){}
    public static FuncionarioDAO getInstance(){
        return instance;
    }
    
    public static void incluir (Funcionario funcionario) throws 
            SQLException, ClassNotFoundException, Exception{
        Connection conn = null;
        PreparedStatement pstm = null;
        
        try {
            conn = DatabaseLocator.getInstance().getConnection();
            pstm = conn.prepareStatement("INSERT INTO "
                    + "funcionario (matricula, nome, cpf, formAcademica, area, codDepartamento, funcao, login, senha) "
                    + "VALUES (?,?,?,?,?,?,?,?,?)");
            pstm.setInt(1, funcionario.getMatricula());
            pstm.setString(2, funcionario.getNome());
            pstm.setString(3, funcionario.getCpf());
            pstm.setString(4, funcionario.getFormAcademica());
            pstm.setString(5, funcionario.getAreaAtuacao());
            pstm.setInt(6, funcionario.getDepartamento().getCodDepartamento());
            pstm.setString(7, funcionario.getFuncao().getFuncao());
            pstm.setString(8, funcionario.getLogin());
            pstm.setString(9, funcionario.getSenha());
            pstm.execute();
            EnderecoDAO.incluir(funcionario.getEndereco());
            TelefoneDAO.incluir(funcionario.getTelefones());
        } catch (SQLException e) {
            throw e;
        } finally{
            closeResource(conn, pstm);
        }
    }
    
    public static void editar(Funcionario funcionario) throws     
            SQLException, ClassNotFoundException{
        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            conn = DatabaseLocator.getInstance().getConnection();
            pstm = conn.prepareStatement("update funcionario set nome=?, cpf=?, formAcademica=?, area=?, codDepartamento=?, funcao=?, login=? WHERE matricula=?");
            pstm.setString(1, funcionario.getNome());
            pstm.setString(2, funcionario.getCpf());
            pstm.setString(3, funcionario.getFormAcademica());
            pstm.setString(4, funcionario.getAreaAtuacao());
            pstm.setInt(5, funcionario.getDepartamento().getCodDepartamento());
            pstm.setString(6, funcionario.getFuncao().getFuncao());
            pstm.setString(7, funcionario.getLogin());
            pstm.setInt(8, funcionario.getMatricula());
            pstm.execute();
            funcionario.getEndereco().editar();
            TelefoneDAO.excluir(funcionario);
            TelefoneDAO.incluir(funcionario.getTelefones());
        } catch (SQLException e) {
            throw e;
        } catch (Exception ex) {
            throw ex;
        } finally{
            closeResource(conn, pstm);
        }
        
    }
    
    public static List<Funcionario> autenticar(Funcionario funcionario) throws Exception {
        Connection conn = null;
        PreparedStatement pstm = null;
        List<Funcionario> funcionarios = new ArrayList<>();
        try {
            conn = DatabaseLocator.getInstance().getConnection();
            pstm = conn.prepareStatement("SELECT * FROM Funcionario WHERE login=? AND senha=?");
            pstm.setString(1, funcionario.getLogin());
            pstm.setString(2, funcionario.getSenha());
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                funcionario.setNome(rs.getString("nome"));
                funcionario.setFuncao(StrategyFactory.create(rs.getString("funcao")));
                funcionarios.add(funcionario);
            }
            rs.close();
        } catch (Exception e) {
            throw e;
        } finally{
            closeResource(conn, pstm);
        }
        return funcionarios;
    }
    
    public static Funcionario obterFuncionario(int matricula) throws SQLException, ClassNotFoundException, Exception{
        Connection conn = null;
        PreparedStatement pstm = null;
        Funcionario funcionario = null;
        try {
            conn = DatabaseLocator.getInstance().getConnection();
            pstm = conn.prepareStatement("select * from funcionario WHERE matricula=?");
            pstm.setInt(1, matricula);
            ResultSet rs = pstm.executeQuery();
            rs.first();
            funcionario = new Funcionario(rs.getInt("matricula"));
            funcionario.setNome(rs.getString("nome"));
            funcionario.setCpf(rs.getString("cpf"));
            funcionario.setFormAcademica(rs.getString("formAcademica"));
            funcionario.setAreaAtuacao(rs.getString("area"));
            funcionario.setDepartamento(Departamento.obterDepartamento(rs.getInt("codDepartamento")));
            funcionario.setFuncao(StrategyFactory.create(rs.getString("funcao")));
            funcionario.setLogin(rs.getString("login"));
            funcionario.setTelefones(Telefone.obterTelefones(funcionario));
            funcionario.setEndereco(Endereco.obterEndereco(funcionario));
            rs.close();
        } catch (Exception e) {
            throw e;
        } finally{
            closeResource(conn, pstm);
        }
        return funcionario;
    }
    
    public static void excluir(Funcionario funcionario) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        Statement st = null;
        
        try {
            conn = DatabaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            st.execute("DELETE FROM funcionario WHERE matricula="+funcionario.getMatricula());
            Endereco endereco = new Endereco();
            endereco.setFuncionario(funcionario);
            endereco.excluir();
            TelefoneDAO.excluir(funcionario);
        } catch (Exception e) {
            throw e;
        } finally{
            closeResource(conn, st);
        }
    }
    
    /**
     *
     * @return
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public static List<Funcionario> obterFuncionarios() throws SQLException, ClassNotFoundException, Exception {
        Connection conn = null;
        Statement st = null;
        List<Funcionario> funcionarios = new ArrayList<>();
        try {
            conn = DatabaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from funcionario");
            while (rs.next()) {
                Funcionario funcionario = new Funcionario(rs.getInt("matricula"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setFormAcademica(rs.getString("formAcademica"));
                funcionario.setAreaAtuacao(rs.getString("area"));
                funcionario.setDepartamento(Departamento.obterDepartamento(rs.getInt("codDepartamento")));
                funcionario.setFuncao(StrategyFactory.create(rs.getString("funcao")));
                funcionarios.add(funcionario);
            }
       } catch (SQLException | ClassNotFoundException e) {
            throw e;
        } finally{
            closeResource(conn, st);
        }
        return funcionarios;
    }
    
    public static List<Funcionario> obterFuncionarioNAlocados(int codProjeto)  throws SQLException, ClassNotFoundException, Exception {
        Connection conn = null;
        Statement st = null;
        List<Funcionario> funcionarios = new ArrayList<>();
        try {
            conn = DatabaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * "
                    + "FROM funcionario as f "
                    + "WHERE matricula NOT IN ( "
                    + "SELECT fp.matricula "
                    + "FROM funcionario_projeto as fp "
                    + "WHERE fp.codProjeto = "+codProjeto
                    + ")");
            while (rs.next()) {
                Funcionario funcionario = new Funcionario(rs.getInt("matricula"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setFormAcademica(rs.getString("formAcademica"));
                funcionario.setAreaAtuacao(rs.getString("area"));
                funcionario.setDepartamento(Departamento.obterDepartamento(rs.getInt("codDepartamento")));
                funcionario.setFuncao(StrategyFactory.create(rs.getString("funcao")));
                funcionarios.add(funcionario);
            }
       } catch (SQLException | ClassNotFoundException e) {
            throw e;
        } finally{
            closeResource(conn, st);
        }
        return funcionarios;
    }

    public static int loginExiste(String login) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        PreparedStatement pstm = null;
        int sequencial=0;
        try {
            conn = DatabaseLocator.getInstance().getConnection();
            pstm = conn.prepareStatement("select count(login) as sequencial from funcionario WHERE login LIKE ?");
            pstm.setString(1, login+"%");
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                sequencial = rs.getInt("sequencial");
            }
            rs.close();
        } catch (Exception e) {
            throw e;
        } finally{
            closeResource(conn, pstm);
        }
        return sequencial;
    }
    
    public static void closeResource (Connection conn, Statement st){
        try {
            if(st!=null) st.close();
            if(conn!=null) conn.close();
        } catch (Exception e) {
        }
    }
    
}
