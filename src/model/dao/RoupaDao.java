package model.dao;

import factory.Dao;
import interfaces.DaoI;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.vo.Roupa;

public class RoupaDao extends Dao implements DaoI<Roupa> {

    @Override
    public int cadastrar(Roupa obj) {
        String sql = "INSERT INTO ROUPA(NOME,VLR,IDCATEGORIA) VALUES(?,?,?)";
        PreparedStatement stmt;
        try {
            stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, obj.getNome());
            stmt.setDouble(2, obj.getVlr());
            stmt.setInt(3, obj.getIdCategoria());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println("ERRO:" + ex.getMessage());
        }
        return 0;

    }

    @Override
    public List<Roupa> listar() {
        String sql = "SELECT * FROM ROUPA ORDER BY IDROUPA DESC";
        PreparedStatement stmt;
        List<Roupa> roupas = new ArrayList<>();
        try {

            stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Roupa temp = new Roupa();
                temp.setIdRoupa(rs.getInt("IDROUPA"));
                temp.setAtivo(rs.getInt("ATIVO"));
                temp.setNome(rs.getString("NOME"));
                temp.setIdCategoria(rs.getInt("IDCATEGORIA"));
                temp.setVlr(rs.getDouble("VLR"));
                roupas.add(temp);
            }
        } catch (SQLException ex) {
            System.out.println("ERRO:" + ex.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao listar Roupa, contate um Administrador do Sistema!");
        }
        return roupas;
    }

    @Override
    public boolean alterar(Roupa obj) {
        String sql = "UPDATE ROUPA IDCATEGORIA SET = ?, NOME = ?, VLR = ? WHERE ID = IDROUPA";
        PreparedStatement stmt;
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, obj.getIdCategoria());
            stmt.setString(2, obj.getNome());
            stmt.setString(3, obj.getNome());
            stmt.setDouble(4, obj.getVlr());
            stmt.setInt(5, obj.getIdRoupa());
            if (stmt.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("ERRO:" + ex.getSQLState());
            JOptionPane.showMessageDialog(null, "Erro ao alterar roupa, contate um Administrador do Sistema!");
        }
        return false;
    }

    @Override
    public boolean deletar(int id) {
        String sql = "UPDATE ROUPA SET ATIVO = 0 WHERE IDCATEGORIA = " + id;
        PreparedStatement stmt;
        try {
            stmt = conexao.prepareStatement(sql);
            if (stmt.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("ERRO:" + ex.getMessage());
        }
        return false;
    }

    @Override
    public Roupa lerPorId(int id) {
        String sql = "SELECT * WHERE ROUPA ORDER BY IDROUPA DESC";
        PreparedStatement stmt;
        Roupa roupa = new Roupa();
        try {
            stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                roupa.setIdRoupa(rs.getInt("IDROUPA"));
                roupa.setAtivo(rs.getInt("ATIVO"));
                roupa.setNome(rs.getString("NOME"));
                roupa.setIdCategoria(rs.getInt("IDCATEGORIA"));
                roupa.setVlr(rs.getDouble("VLR"));
            }
        } catch (SQLException ex) {
            System.out.println("ERRO:" + ex.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao listar Roupa, contate um Administrador do Sistema!");
        }
        return roupa;
    }

    @Override
    public List<Roupa> pesquisar(String termo) {
        String sql = "SELECT * WHERE ROUPA ORDER BY IDROUPA DESC LIKE";
        PreparedStatement stmt;
        List<Roupa> roupas = new ArrayList<>();
        try {

            stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Roupa temp = new Roupa();
                temp.setIdRoupa(rs.getInt("IDROUPA"));
                temp.setIdCategoria(rs.getInt(("IDCATEGORIA")));
                temp.setAtivo(rs.getInt("ATIVO"));
                temp.setNome(rs.getString("NOME"));
                temp.setVlr(rs.getDouble("VLR"));
                roupas.add(temp);
            }
        } catch (SQLException ex) {
            System.out.println("ERRO:" + ex.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao listar Roupa, contate um Administrador do Sistema!");
        }
        return roupas;
    }

}
