/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import factory.Dao;
import interfaces.DaoI;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.vo.Tela;

/**
 *
 * @author Windows
 */
public class TelaDao extends Dao implements DaoI<Tela> {

    public TelaDao() {
        super();
    }

    @Override
    public List<Tela> listar() {
         try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement("select idtela, instancia, numerodatela from tela"
                    + " where ativo = 1 order by idtela desc",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            ResultSet result = stmt.executeQuery();
            List<Tela> lista = new ArrayList<Tela>();
            while (result.next()) {
                Tela t = new Tela();
                t.setIdTela(result.getInt("idtela"));
                t.setInstancia(result.getString("instancia"));
                t.setNumeroDaTela(result.getInt("numerodatela"));
                lista.add(t);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public int cadastrar(Tela obj) {
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement(
                    "insert into tela(instancia, numerodatela)"
                    + " values(?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, obj.getInstancia());
            stmt.setInt(2, obj.getNumeroDaTela());
            ResultSet rs;
            if (stmt.executeUpdate() > 0) {
                rs = stmt.getGeneratedKeys();
                rs.next();
                return rs.getInt(1);
            } else {
                return 0;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return 0;
        }
    }

    @Override
    public boolean alterar(Tela obj) {
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement("update tela"
                    + " set instancia = ?, numerodatela = ? where idtela = ?");
            stmt.setString(1, obj.getInstancia());
            stmt.setInt(2, obj.getNumeroDaTela());
            stmt.setInt(3, obj.getIdTela());
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean deletar(int id) {
         try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement("update tela set ativo = 0 where idtela = ?");
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public Tela lerPorId(int id) {
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement("select idtela, instancia, numerodatela from tela where ativo = 1 and idtela = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String instancia = rs.getString("instancia");
                Integer numeroTela = rs.getInt("numerodatela");
                Tela tela = new Tela();
                tela.setInstancia(instancia);
                tela.setNumeroDaTela(numeroTela);
                tela.setIdTela(id);
                return tela;
            }
            return null;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public List<Tela> pesquisar(String termo) {
        List<Tela> telas = new ArrayList<>();
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement("select idtela, instancia, numerodatela from tela"
                    + " where ativo = 1 and (idtela = ? or instancia = ? or numerodatela like ?)");
            stmt.setString(1, termo);
            stmt.setString(2, termo);
            stmt.setString(3, termo + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Tela tela = new Tela();
                tela.setInstancia(rs.getString("instancia"));
                tela.setNumeroDaTela(rs.getInt("numerodatela"));
                tela.setIdTela(rs.getInt("idtela"));
                telas.add(tela);
            }

            return telas;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

}
