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
import model.vo.Categoria;

/**
 *
 * @author Alunos
 */
public class CategoriaDao extends Dao implements DaoI<Categoria>{

    public CategoriaDao() {
        super();
    }

    @Override
    public List<Categoria> listar() {
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement("select idcategoria, nome from categoria"
                    + " where ativo = 1 order by idcategoria desc",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            ResultSet result = stmt.executeQuery();
            List<Categoria> lista = new ArrayList<Categoria>();
            while (result.next()) {
                Categoria c = new Categoria();
                c.setIdCategoria(result.getInt("idcategoria"));
                c.setNome(result.getString("nome"));
                lista.add(c);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public int cadastrar(Categoria obj) {
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement(
                    "insert into categoria(nome)"
                    + " values(?)", PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, obj.getNome());
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
    public boolean alterar(Categoria obj) {
         try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement("update categoria"
                    + " set nome = ? where idcategoria = ?");
            stmt.setString(1, obj.getNome());
            stmt.setInt(2, obj.getIdCategoria());
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
            stmt = conexao.prepareStatement("update categoria set ativo = 0 where idcategoria = ?");
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public Categoria lerPorId(int id) {
         try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement("select idcategoria, nome from categoria where ativo = 1 and idcategoria = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String nome = rs.getString("nome");
                Categoria categoria = new Categoria();
                categoria.setNome(nome);
                categoria.setIdCategoria(id);
                return categoria;
            }
            return null;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }

    }

    @Override
    public List<Categoria> pesquisar(String termo) {
        List<Categoria> categorias = new ArrayList<>();
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement("select idcategoria, nome from categoria"
                    + " where ativo = 1 and (idcategoria = ? or nome like ?)");
            stmt.setString(1, termo);
            stmt.setString(2, termo + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setNome(rs.getString("nome"));
                categoria.setIdCategoria(rs.getInt("idcategoria"));
                categorias.add(categoria);
            }

            return categorias;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
}
