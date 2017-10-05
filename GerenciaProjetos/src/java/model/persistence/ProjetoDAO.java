/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.persistence;

import controller.StateFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import model.Departamento;
import model.Projeto;

/**
 *
 * @author paulo.junior6
 */
public class ProjetoDAO {
    private static ProjetoDAO instance = new ProjetoDAO();

    private ProjetoDAO(){}
    public static ProjetoDAO getInstance(){
        return instance;
    }
    
    public static void incluir (Projeto projeto) throws 
            SQLException, ClassNotFoundException{
        Connection conn = null;
        PreparedStatement pstm = null;
        
        try {
            conn = DatabaseLocator.getInstance().getConnection();
            pstm = conn.prepareStatement("insert into projeto (nome, dataInicio, dataTermino, codDepartamento, status, descricao, cliente, telCliente, codProjeto) values (?,?,?,?,?,?,?,?,?);");
            montarStatement(pstm, projeto);
            pstm.execute();
        } catch (SQLException e) {
            throw e;
        } finally{
            closeResource(conn, pstm);
        }
    }
    
    public static void editar (Projeto projeto) throws 
            SQLException, ClassNotFoundException{
        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            conn = DatabaseLocator.getInstance().getConnection();
            pstm = conn.prepareStatement("UPDATE projeto SET nome=?, dataInicio=?, dataTermino=?, codDepartamento=?, status=?, descricao=?, cliente=?, telCliente=? WHERE codProjeto=?");
            montarStatement(pstm, projeto);
            pstm.execute();
        } catch (SQLException e) {
            throw e;
        } finally{
            closeResource(conn, pstm);
        }
    }
    
    public static Projeto obterProjeto(int codProjeto) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        PreparedStatement pstm = null;
        Projeto projeto = new Projeto(0);
        try {
            conn = DatabaseLocator.getInstance().getConnection();
            pstm = conn.prepareStatement("select * from projeto WHERE codProjeto=?");
            pstm.setInt(1, codProjeto);
            ResultSet rs = pstm.executeQuery();
            rs.first();
            montarObjeto(projeto, rs);
            rs.close();
        } catch (Exception e) {
            throw e;
        } finally{
            closeResource(conn, pstm);
        }
        return projeto;
    }
    
    public static List<Projeto> obterProjetos() throws ClassNotFoundException, Exception {
        Connection conn = null;
        Statement st = null;
        List<Projeto> projetos = new ArrayList<>();
        try {
            conn = DatabaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from projeto");
            while (rs.next()) {
                Projeto projeto = new Projeto(0);
                montarObjeto(projeto, rs);
                projetos.add(projeto);
            }
       } catch (Exception e) {
            throw e;
        } finally{
            closeResource(conn, st);
        }
        return projetos;
    }
    
    public static void excluir(Projeto projeto) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        Statement st = null;
        
        try {
            conn = DatabaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            st.execute("DELETE FROM projeto WHERE codProjeto="+projeto.getCodProjeto());
        } catch (Exception e) {
            throw e;
        } finally{
            closeResource(conn, st);
        }
    }
    
    public static void closeResource (Connection conn, Statement st){
        try {
            if(st!=null) st.close();
            if(conn!=null) conn.close();
        } catch (Exception e) {
        }
    }
    
    private static void montarStatement(PreparedStatement pstm, Projeto projeto) throws SQLException {
        pstm.setString(1, projeto.getNome());
        pstm.setString(2, projeto.getDataInicio());
        pstm.setString(3, projeto.getDataTermino());
        pstm.setInt(4, projeto.getDepartamento().getCodDepartamento());
        if (projeto.getStatus()== null) {
            pstm.setNull(5, Types.NULL);
        } else {
            pstm.setString(5, projeto.getStatus().getStatus());
        }
        pstm.setString(6, projeto.getDescricao());
        pstm.setString(7, projeto.getCliente());
        pstm.setString(8, projeto.getTelCliente());
        pstm.setInt(9, projeto.getCodProjeto());
    }
    
    private static void montarObjeto(Projeto projeto, ResultSet rs) throws SQLException {
        projeto.setCodProjeto(rs.getInt("codProjeto"));
        projeto.setNome(rs.getString("nome"));
        projeto.setDataInicio(rs.getString("dataInicio"));
        projeto.setDataTermino(rs.getString("dataTermino"));
        projeto.setDepartamento(new Departamento(rs.getInt("codDepartamento")));
        projeto.setStatus(StateFactory.create(rs.getString("status")));
        projeto.setCliente(rs.getString("cliente"));
        projeto.setTelCliente(rs.getString("telCliente"));
        projeto.setDescricao(rs.getString("descricao"));
    }
}
