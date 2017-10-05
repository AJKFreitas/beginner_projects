/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Funcionario;
import model.Endereco;

/**
 *
 * @author paulo.junior6
 */
public class EnderecoDAO {
    private static EnderecoDAO instance = new EnderecoDAO();

    private EnderecoDAO(){}
    public static EnderecoDAO getInstance(){
        return instance;
    }
    
    public static void incluir (Endereco endereco) throws 
            SQLException, ClassNotFoundException{
        Connection conn = null;
        PreparedStatement pstm = null;
        
        try {
            conn = DatabaseLocator.getInstance().getConnection();
            pstm = conn.prepareStatement("INSERT INTO "
                    + "enderecofuncionario (logradouro, numero, bairro, cidade, uf, cep, matriculaFuncionario) "
                    + "VALUES (?,?,?,?,?,?,?)");
            enderecoStatement(pstm, endereco);
            pstm.execute();
        } catch (SQLException e) {
            throw e;
        } finally{
            closeResource(conn, pstm);
        }
    }
    
    public static void editar(Endereco endereco) throws     
            SQLException, ClassNotFoundException{
        Connection conn = null;
        PreparedStatement pstm = null;
        
        try {
            conn = DatabaseLocator.getInstance().getConnection();
            pstm = conn.prepareStatement("UPDATE enderecofuncionario "
                    + "SET logradouro=?, numero=?, bairro=?, cidade=?, uf=?, cep=? "
                    + "WHERE matriculaFuncionario=?");
            enderecoStatement(pstm, endereco);
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally{
            closeResource(conn, pstm);
        }
    }
        
    public static void excluir(Endereco endereco) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        PreparedStatement pstm = null;
        
        try {
            conn = DatabaseLocator.getInstance().getConnection();
            pstm = conn.prepareStatement("DELETE FROM enderecofuncionario WHERE matriculaFuncionario=?");
            pstm.setInt(1, endereco.getFuncionario().getMatricula());
            pstm.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally{
            closeResource(conn, pstm);
        }
    }
    
    public static Endereco obterEndereco(Funcionario funcionario) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        PreparedStatement pstm = null;
        Endereco endereco = null;
        try {
            conn = DatabaseLocator.getInstance().getConnection();
            pstm = conn.prepareStatement("SELECT * FROM enderecofuncionario WHERE matriculaFuncionario=?");
            pstm.setInt(1, funcionario.getMatricula());
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                endereco = montarEndereco(endereco, rs);
            }
            rs.close();
        } catch (Exception e) {
            throw e;
        } finally{
            closeResource(conn, pstm);
        }
        return endereco;
    }

    
    /**
     *
     * @return
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public static List<Endereco> obterEnderecos() throws SQLException, ClassNotFoundException {
        Connection conn = null;
        Statement st = null;
        List<Endereco> enderecos = new ArrayList<>();
        try {
            conn = DatabaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM enderecofuncionario");
            while (rs.next()) {
                Endereco endereco = null;
                endereco = montarEndereco(endereco, rs);
                enderecos.add(endereco);
            }
       } catch (SQLException | ClassNotFoundException e) {
            throw e;
        } finally{
            closeResource(conn, st);
        }
        return enderecos;
    }
    
    public static void closeResource (Connection conn, Statement st){
        try {
            if(st!=null) st.close();
            if(conn!=null) conn.close();
        } catch (Exception e) {
        }
    }
    
        
    private static void enderecoStatement(PreparedStatement pstm, Endereco endereco) throws SQLException {
        pstm.setString(1, endereco.getLogradouro());
        pstm.setInt(2, endereco.getNumero());
        pstm.setString(3, endereco.getBairro());
        pstm.setString(4, endereco.getCidade());
        pstm.setString(5, endereco.getUf());
        pstm.setString(6, endereco.getCep());
        pstm.setInt(7, endereco.getFuncionario().getMatricula());
    }
    
    private static Endereco montarEndereco (Endereco endereco, ResultSet rs) throws SQLException{
        endereco = new Endereco(
            new Funcionario(rs.getInt("matriculaFuncionario")),
            rs.getString("logradouro"),
            rs.getInt("numero"),
            rs.getString("bairro"),
            rs.getString("cidade"),
            rs.getString("uf"),
            rs.getString("cep"));
        return endereco;
    }
}
