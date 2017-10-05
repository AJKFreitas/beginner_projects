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
import model.Departamento;
import model.Projeto;
import model.ProjetoObservado;

/**
 *
 * @author paulo.junior6
 */
public class ProjetoObservadoDAO {
    private static ProjetoObservadoDAO instance = new ProjetoObservadoDAO();

    private ProjetoObservadoDAO(){}
    public static ProjetoObservadoDAO getInstance(){
        return instance;
    }
    
    public static void incluir (ProjetoObservado projetoObservado) throws 
            SQLException, ClassNotFoundException{
        Connection conn = null;
        PreparedStatement pstm = null;
        
        try {
            conn = DatabaseLocator.getInstance().getConnection();
            pstm = conn.prepareStatement("insert into projeto_observado (codProjeto, codDepartamento, notificacao, lido) "
                                         + "values (?,?,?,?);");
            pstm.setInt(1, projetoObservado.getProjeto().getCodProjeto());
            pstm.setInt(2, projetoObservado.getDepartamento().getCodDepartamento());
            pstm.setString(3, projetoObservado.getNotificacao());
            pstm.setInt(4, projetoObservado.getLido());
            pstm.execute();
        } catch (SQLException e) {
            throw e;
        } finally{
            closeResource(conn, pstm);
        }
    }
     
    public static ProjetoObservado obterProjetoObservado(int idProjetoObservado) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        PreparedStatement pstm = null;
        ProjetoObservado projetoObservado = null;
        try {
            conn = DatabaseLocator.getInstance().getConnection();
            pstm = conn.prepareStatement("select * from projeto_observado WHERE id_projeto_observado=?");
            pstm.setInt(1, idProjetoObservado);
            ResultSet rs = pstm.executeQuery();
            rs.first();
            projetoObservado = new ProjetoObservado(0);
            montarObjeto(projetoObservado, rs);
            rs.close();
        } catch (Exception e) {
            throw e;
        } finally{
            closeResource(conn, pstm);
        }
        return projetoObservado;
    }
    
    public static void excluir(ProjetoObservado projetoObservado) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        Statement st = null;
        
        try {
            conn = DatabaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            st.execute("DELETE FROM projeto_observado WHERE id_projeto_observado="+projetoObservado.getIdProjetoObservado());
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
    public static List<ProjetoObservado> obterProjetoObservados() throws SQLException, ClassNotFoundException {
        Connection conn = null;
        Statement st = null;
        List<ProjetoObservado> projetoObservados = new ArrayList<>();
        try {
            conn = DatabaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from projeto_observado where lido=0");
            while (rs.next()) {
                ProjetoObservado projetoObservado = new ProjetoObservado(0);
                montarObjeto(projetoObservado, rs);
                projetoObservados.add(projetoObservado);
            }
       } catch (SQLException | ClassNotFoundException e) {
            throw e;
        } finally{
            closeResource(conn, st);
        }
        return projetoObservados;
    }

    public static void closeResource (Connection conn, Statement st){
        try {
            if(st!=null) st.close();
            if(conn!=null) conn.close();
        } catch (Exception e) {
        }
    }
    
    public static void confirmar(ProjetoObservado projetoObservado) throws     
            SQLException, ClassNotFoundException{
        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            conn = DatabaseLocator.getInstance().getConnection();
            pstm = conn.prepareStatement("update projeto_observado set lido=1 "
                                        + "WHERE id_projeto_observado=?");
            pstm.setInt(1, projetoObservado.getIdProjetoObservado());
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally{
            closeResource(conn, pstm);
        }
    }
    
    private static void montarObjeto(ProjetoObservado projetoObservado, ResultSet rs) throws SQLException {
        projetoObservado.setProjeto(new Projeto(rs.getInt("codProjeto")));
        projetoObservado.setDepartamento(new Departamento(rs.getInt("codDepartamento")));
        projetoObservado.setNotificacao(rs.getString("notificacao"));
        projetoObservado.setIdProjetoObservado(rs.getInt("id_projeto_observado"));
        projetoObservado.setLido(rs.getInt("lido"));
    }
    
}
