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
import java.util.ArrayList;
import java.util.List;
import model.Departamento;

/**
 *
 * @author paulo.junior6
 */
public class DepartamentoDAO {
    private static DepartamentoDAO instance = new DepartamentoDAO();

    private DepartamentoDAO(){}
    public static DepartamentoDAO getInstance(){
        return instance;
    }
    
    public static void incluir (Departamento departamento) throws 
            SQLException, ClassNotFoundException{
        Connection conn = null;
        PreparedStatement pstm = null;
        
        try {
            conn = DatabaseLocator.getInstance().getConnection();
            pstm = conn.prepareStatement("insert into departamento (nome, telefone, codDepartamento) values (?,?,?)");
            departamentoStatement(pstm, departamento);
            pstm.execute();
        } catch (SQLException e) {
            throw e;
        } finally{
            closeResource(conn, pstm);
        }
    }

    public static void editar(Departamento departamento) throws     
            SQLException, ClassNotFoundException{
        Connection conn = null;
        PreparedStatement pstm = null;
        
        try {
            conn = DatabaseLocator.getInstance().getConnection();
            pstm = conn.prepareStatement("UPDATE departamento SET nome=?, telefone=? WHERE codDepartamento=?");
            departamentoStatement(pstm, departamento);
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally{
            closeResource(conn, pstm);
        }
    }
    
    public static Departamento obterDepartamento(int codDepartamento) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        PreparedStatement pstm = null;
        Departamento departamento = null;
        try {
            conn = DatabaseLocator.getInstance().getConnection();
            pstm = conn.prepareStatement("select * from departamento WHERE codDepartamento=?");
            pstm.setInt(1, codDepartamento);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                departamento = montarDepartamento(departamento, rs);
            }
            rs.close();
            pstm.close();

        } catch (Exception e) {
            throw e;
        } finally{
            closeResource(conn, pstm);
        }
        return departamento;
    }
    
    public static void excluir(Departamento departamento) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        PreparedStatement pstm = null;
        
        try {
            conn = DatabaseLocator.getInstance().getConnection();
            pstm = conn.prepareStatement("DELETE FROM departamento WHERE codDepartamento=?");
            pstm.setInt(1, departamento.getCodDepartamento());
            pstm.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally{
            closeResource(conn, pstm);
        }
    }
    
    /**
     *
     * @return
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public static List<Departamento> obterDepartamentos() throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pstm = null;
        List<Departamento> departamentos = new ArrayList<>();
        try {
            conn = DatabaseLocator.getInstance().getConnection();
            pstm = conn.prepareStatement("select * from departamento");
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Departamento departamento = null;
                departamento = montarDepartamento(departamento, rs);
                departamentos.add(departamento);
            }
            rs.close();
       } catch (SQLException | ClassNotFoundException e) {
            throw e;
        } finally{
            closeResource(conn, pstm);
        }
        return departamentos;
    }
    
    public static void closeResource (Connection conn, PreparedStatement pstm){
        try {
            if(pstm!=null) pstm.close();
            if(conn!=null) conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void departamentoStatement(PreparedStatement pstm, Departamento departamento) throws SQLException {
        pstm.setString(1, departamento.getNome());
        pstm.setString(2, departamento.getTelefone());
        pstm.setInt(3, departamento.getCodDepartamento());
    }
    
    private static Departamento montarDepartamento (Departamento departamento, ResultSet rs) throws SQLException{
        departamento = new Departamento(0);
        departamento.setCodDepartamento(rs.getInt("codDepartamento"));
        departamento.setNome(rs.getString("nome"));
        departamento.setTelefone(rs.getString("telefone"));
        return departamento;
    }
}
