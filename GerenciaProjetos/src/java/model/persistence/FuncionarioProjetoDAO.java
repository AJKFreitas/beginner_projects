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
import model.Funcionario;
import model.FuncionarioProjeto;
import model.Projeto;

/**
 *
 * @author paulo.junior6
 */
public class FuncionarioProjetoDAO {
    private static FuncionarioProjetoDAO instance = new FuncionarioProjetoDAO();

    private FuncionarioProjetoDAO(){}
    public static FuncionarioProjetoDAO getInstance(){
        return instance;
    }
    
    public static void incluir (FuncionarioProjeto fp) throws 
            SQLException, ClassNotFoundException{
        Connection conn = null;
        PreparedStatement pstm = null;
        
        try {
            conn = DatabaseLocator.getInstance().getConnection();
            pstm = conn.prepareStatement("INSERT INTO "
                    + "funcionario_projeto (funcao, cargaHoraria, dataInicio, dataTermino, observacao, codProjeto, matricula) "
                    + "VALUES (?,?,?,?,?,?,?)");
            montarStatement(pstm, fp);
            pstm.execute();
        } catch (SQLException e) {
            throw e;
        } finally{
            closeResource(conn, pstm);
        }
    }
    
    public static void editar(FuncionarioProjeto fp) throws     
            SQLException, ClassNotFoundException{
        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            conn = DatabaseLocator.getInstance().getConnection();
            pstm = conn.prepareStatement("UPDATE funcionario_projeto "
                    + "SET funcao=?, cargaHoraria=?, dataInicio=?, dataTermino=?, observacao=? "
                    + "WHERE codProjeto=? AND matricula=?");
            montarStatement(pstm, fp);
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally{
            closeResource(conn, pstm);
        }
    }
        
    public static void excluir(FuncionarioProjeto fp) throws SQLException, 
            ClassNotFoundException{
        Connection conn = null;
        PreparedStatement pstm = null;
        
        try {
            conn = DatabaseLocator.getInstance().getConnection();
            pstm = conn.prepareStatement("DELETE FROM funcionario_projeto WHERE codProjeto=? AND matricula=?");
            pstm.setInt(1, fp.getProjeto().getCodProjeto());
            pstm.setInt(2, fp.getFuncionario().getMatricula());
            pstm.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally{
            closeResource(conn, pstm);
        }
    }
    
    public static FuncionarioProjeto obterFuncionarioProjeto(FuncionarioProjeto fp) throws SQLException, 
            ClassNotFoundException{
        Connection conn = null;
        PreparedStatement pstm = null;
        FuncionarioProjeto funcionarioProjeto = new FuncionarioProjeto();
        try {
            conn = DatabaseLocator.getInstance().getConnection();
            pstm = conn.prepareStatement("SELECT * FROM funcionario_projeto WHERE codProjeto=? AND matricula=?");
            pstm.setInt(1, fp.getProjeto().getCodProjeto());
            pstm.setInt(2, fp.getFuncionario().getMatricula());
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                montarObjeto(funcionarioProjeto, rs);
            }
            rs.close();
        } catch (Exception e) {
            throw e;
        } finally{
            closeResource(conn, pstm);
        }
        return funcionarioProjeto;
    }

    public static List<FuncionarioProjeto> obterFuncionarioProjetos(int codProjeto) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        Statement st = null;
        List<FuncionarioProjeto> funcionarioProjetos = new ArrayList<>();
        try {
            conn = DatabaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM funcionario_projeto as fp" +
                                           "    INNER JOIN funcionario as f ON (fp.matricula = f.matricula)" +
                                           "WHERE fp.codProjeto="+codProjeto);
            while (rs.next()) {
                FuncionarioProjeto  funcionarioProjeto = new FuncionarioProjeto();
                montarObjeto(funcionarioProjeto, rs);
                funcionarioProjeto.getFuncionario().setNome(rs.getString("nome"));
                funcionarioProjetos.add(funcionarioProjeto);
            }
       } catch (SQLException | ClassNotFoundException e) {
            throw e;
        } finally{
            closeResource(conn, st);
        }
        return funcionarioProjetos;
    }
    
    public static int obterCargaHoraria(FuncionarioProjeto fp) throws SQLException, 
            ClassNotFoundException{
        Connection conn = null;
        PreparedStatement pstm = null;
        int horasAlocadas = 0;
        try {
            conn = DatabaseLocator.getInstance().getConnection();
            pstm = conn.prepareStatement("SELECT SUM(cargaHoraria) as horasAlocadas FROM funcionario_projeto WHERE codProjeto=? AND matricula=?");
            pstm.setInt(1, fp.getProjeto().getCodProjeto());
            pstm.setInt(2, fp.getFuncionario().getMatricula());
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                horasAlocadas = rs.getInt("horasAlocadas");
            }
            rs.close();
        } catch (Exception e) {
            throw e;
        } finally{
            closeResource(conn, pstm);
        }
        return horasAlocadas;
    }
    
    public static void closeResource (Connection conn, Statement st){
        try {
            if(st!=null) st.close();
            if(conn!=null) conn.close();
        } catch (Exception e) {
        }
    }
    
    private static void montarStatement(PreparedStatement pstm, FuncionarioProjeto fp) throws SQLException {
        pstm.setString(1, fp.getFuncao().getFuncao());
        pstm.setInt(2, fp.getCargaHoraria());
        pstm.setString(3, fp.getDataInicio());
        pstm.setString(4, fp.getDataTermino());
        pstm.setString(5, fp.getObservacao());
        pstm.setInt(6, fp.getProjeto().getCodProjeto());
        pstm.setInt(7, fp.getFuncionario().getMatricula());
    }
    
    private static void montarObjeto(FuncionarioProjeto funcionarioProjeto, ResultSet rs) throws SQLException {
        funcionarioProjeto.setProjeto(new Projeto(rs.getInt("codProjeto")));
        funcionarioProjeto.setFuncionario(new Funcionario(rs.getInt("matricula")));
        funcionarioProjeto.setFuncao(StrategyFactory.create(rs.getString("funcao")));
        funcionarioProjeto.setCargaHoraria(rs.getInt("cargaHoraria"));
        funcionarioProjeto.setDataInicio(rs.getString("dataInicio"));
        funcionarioProjeto.setDataTermino(rs.getString("dataTermino"));
        funcionarioProjeto.setObservacao(rs.getString("observacao"));
    }
}
