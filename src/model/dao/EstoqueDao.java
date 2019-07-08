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
import model.vo.Cliente;
import model.vo.Estoque;

/**
 *
 * @author Alunos
 */
public class EstoqueDao extends Dao implements DaoI<Estoque> {

    public EstoqueDao() {
        super();
    }
    
    

    @Override
    public List<Estoque> listar() {
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement("select idestoque, quantidade, idroupa from estoque"
                    + " where ativo = 1 order by idestoque desc",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            ResultSet result = stmt.executeQuery();
            List<Estoque> lista = new ArrayList<Estoque>();
            while (result.next()) {
                Estoque e = new Estoque();
                e.setIdEstoque(result.getInt("idestoque"));
                e.setQuantidade(result.getInt("quantidade"));
                e.getRoupa().setIdRoupa(result.getInt("idroupa"));
                lista.add(e);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public int cadastrar(Estoque obj) {
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement(
                    "insert into estoque(quantidade, idroupa)"
                    + " values(?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, obj.getQuantidade());
            stmt.setInt(2, obj.getRoupa().getIdRoupa());
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
    public boolean alterar(Estoque obj) {
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement("update estoque"
                    + " set quantidade = ?, idroupa = ? where idestoque = ?");
            stmt.setInt(1, obj.getQuantidade());
            stmt.setInt(2, obj.getRoupa().getIdRoupa());
            stmt.setInt(3, obj.getIdEstoque());
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
            stmt = conexao.prepareStatement("update estoque set ativo = 0 where idestoque = ?");
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public Estoque lerPorId(int id) {
         try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement("select idestoque, quantidade, idroupa from estoque where ativo = 1 and idestoque = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Integer quantidade = rs.getInt("quantidade");
                Integer idRoupa = rs.getInt("idroupa");
                Estoque estoque = new Estoque();
                estoque.setQuantidade(quantidade);
                estoque.getRoupa().setIdRoupa(idRoupa);
                estoque.setIdEstoque(id);
                return estoque;
            }
            return null;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public List<Estoque> pesquisar(String termo) {
       List<Estoque> estoques = new ArrayList<>();
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement("select idestoque, quantidade, idroupa from estoque"
                    + " where ativo = 1 and (idestoque = ? or quantidade like ? or idroupa = ?)");
            stmt.setString(1, termo);
            stmt.setString(2, termo + "%");
            stmt.setString(3, termo);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Estoque estoque = new Estoque();
                estoque.setQuantidade(rs.getInt("quantidade"));
                estoque.getRoupa().setIdRoupa(rs.getInt("idroupa"));
                estoque.setIdEstoque(rs.getInt("idestoque"));
                estoques.add(estoque);
            }

            return estoques;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

}
