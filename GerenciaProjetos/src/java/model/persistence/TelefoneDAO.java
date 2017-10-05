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
import model.Telefone;

/**
 *
 * @author paulo.junior6
 */
public class TelefoneDAO {
    private static TelefoneDAO instance = new TelefoneDAO();

    private TelefoneDAO(){}
    public static TelefoneDAO getInstance(){
        return instance;
    }
    
    public static void incluir (List<Telefone> telefones) throws 
            SQLException, ClassNotFoundException{
        Connection conn = null;
        PreparedStatement pstm = null;
        
        try {
            conn = DatabaseLocator.getInstance().getConnection();
            for (Telefone telefone : telefones) {
                pstm = conn.prepareStatement("insert into telefonefuncionario (matriculaFuncionario, numTelefone) values (?,?)");
                pstm.setInt(1, telefone.getFuncionario().getMatricula());
                pstm.setString(2, telefone.getNumTelefone());
                pstm.execute();
            }
        } catch (SQLException e) {
            throw e;
        } finally{
            closeResource(conn, pstm);
        }
    }
    
    public static void editar(Telefone telefone) throws     
            SQLException, ClassNotFoundException{
        Connection conn = null;
        PreparedStatement pstm = null;
        
        try {
            conn = DatabaseLocator.getInstance().getConnection();
            pstm = conn.prepareStatement("UPDATE telefonefuncionario SET numTelefone WHERE matriculaFuncionario=?");
            pstm.setString(1, telefone.getNumTelefone());
            pstm.setInt(2, telefone.getFuncionario().getMatricula());
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally{
            closeResource(conn, pstm);
        }
    }
        
    public static void excluir(Funcionario funcionario) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        PreparedStatement pstm = null;
        
        try {
            conn = DatabaseLocator.getInstance().getConnection();
            pstm = conn.prepareStatement("DELETE FROM telefonefuncionario WHERE matriculaFuncionario=?");
            pstm.setInt(1, funcionario.getMatricula());
            pstm.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally{
            closeResource(conn, pstm);
        }
    }
    
    public static Telefone obterTelefone(Funcionario funcionario) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        PreparedStatement pstm = null;
        Telefone telefone = null;
        try {
            conn = DatabaseLocator.getInstance().getConnection();
            pstm = conn.prepareStatement("SELECT * FROM telefonefuncionario WHERE matriculaFuncionario=?");
            pstm.setInt(1, funcionario.getMatricula());
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                telefone = new Telefone(new Funcionario(rs.getInt("matriculaFuncionario")),rs.getString("numTelefone"));
            }
            rs.close();
        } catch (Exception e) {
            throw e;
        } finally{
            closeResource(conn, pstm);
        }
        return telefone;
    }

    
    public static List<Telefone> obterTelefones(Funcionario funcionario) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        Statement st = null;
        List<Telefone> telefones = new ArrayList<>();
        try {
            conn = DatabaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM telefonefuncionario WHERE matriculaFuncionario="+funcionario.getMatricula());
            while (rs.next()) {
                telefones.add(new Telefone(new Funcionario(rs.getInt("matriculaFuncionario")), rs.getString("numTelefone")));
            }
       } catch (SQLException | ClassNotFoundException e) {
            throw e;
        } finally{
            closeResource(conn, st);
        }
        return telefones;
    }
    
    public static void closeResource (Connection conn, Statement st){
        try {
            if(st!=null) st.close();
            if(conn!=null) conn.close();
        } catch (Exception e) {
        }
    }

}
