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
import model.vo.Funcionario;
import model.vo.RoupaVenda;

/**
 *
 * @author Alunos
 */
public class RoupaVendaDao extends Dao implements DaoI<RoupaVenda> {

    public RoupaVendaDao() {
        super();
    }

    @Override
    public List<RoupaVenda> listar() {
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement("select idroupavenda, desconto from roupavenda"
                    + " where ativo = 1 order by idroupavenda desc",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            ResultSet result = stmt.executeQuery();
            List<RoupaVenda> lista = new ArrayList<RoupaVenda>();
            while (result.next()) {
                RoupaVenda rv = new RoupaVenda();
                rv.setIdRoupaVenda(result.getInt("idroupavenda"));
                rv.setDesconto(result.getInt("desconto"));
                lista.add(rv);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public int cadastrar(RoupaVenda obj) {
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement(
                    "insert into roupavenda(desconto)"
                    + " values(?)", PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, obj.getDesconto());
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
    public boolean alterar(RoupaVenda obj) {
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement("update roupavenda"
                    + " set desconto = ? where idroupavenda = ?");
            stmt.setInt(1, obj.getDesconto());
            stmt.setInt(2, obj.getIdRoupaVenda());
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
            stmt = conexao.prepareStatement("update roupavenda set ativo = 0 where idroupavenda = ?");
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public RoupaVenda lerPorId(int id) {
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement("select idroupavenda, desconto from roupavenda where ativo = 1 and idroupavenda = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Integer desconto = rs.getInt("desconto");
                RoupaVenda roupaV = new RoupaVenda();
                roupaV.setDesconto(desconto);
                roupaV.setIdRoupaVenda(id);
                return roupaV;
            }
            return null;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public List<RoupaVenda> pesquisar(String termo) {
        List<RoupaVenda> roupasV = new ArrayList<>();
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement("select idroupavenda, desconto from roupavenda"
                    + " where ativo = 1 and (idroupavenda = ? or desconto like ?)");
            stmt.setString(1, termo);
            stmt.setString(2, termo + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                RoupaVenda roupaV = new RoupaVenda();
                roupaV.setDesconto(rs.getInt("desconto"));
                roupaV.setIdRoupaVenda(rs.getInt("idroupavenda"));
                roupasV.add(roupaV);
            }

            return roupasV;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

}
