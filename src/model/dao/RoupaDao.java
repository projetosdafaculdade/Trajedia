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

    public RoupaDao() {
        super();
    }

    @Override
    public int cadastrar(Roupa obj) {
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement(
                    "insert into roupa(nome, vlr, idcategoria)"
                    + " values(?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, obj.getNome());
            stmt.setDouble(2, obj.getVlr());
            stmt.setInt(3, obj.getCategoria().getIdCategoria());
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
    public List<Roupa> listar() {
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement("select idroupa, nome, vlr, idcategoria, ativo from roupa"
                    + " where ativo = 1 order by idroupa asc",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            ResultSet result = stmt.executeQuery();
            List<Roupa> lista = new ArrayList<Roupa>();
            while (result.next()) {
                Roupa r = new Roupa();
                r.setIdRoupa(result.getInt("idroupa"));
                r.setNome(result.getString("nome"));
                r.setVlr(result.getDouble("vlr"));
                CategoriaDao categoriaDao = new CategoriaDao();
                r.setCategoria(categoriaDao.lerPorId(result.getInt("idcategoria")));
                r.setAtivo(result.getInt("ATIVO"));
                lista.add(r);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public boolean alterar(Roupa obj) {
       try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement("update roupa"
                    + " set nome = ?, vlr = ?, idcategoria = ? where idroupa = ?");
            stmt.setString(1, obj.getNome());
            stmt.setDouble(2, obj.getVlr());
            stmt.setInt(3, obj.getCategoria().getIdCategoria());
            stmt.setInt(4, obj.getIdRoupa());
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
            stmt = conexao.prepareStatement("update roupa set ativo = 0 where idroupa = ?");
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public Roupa lerPorId(int id) {
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement("select idroupa, nome, vlr, idcategoria from roupa where ativo = 1 and idroupa = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String nome = rs.getString("NOME");
                Double valor = rs.getDouble("vlr");
                Integer idCategoria = rs.getInt("idcategoria");
                Roupa roupa = new Roupa();
                roupa.setNome(nome);
                roupa.setVlr(valor);
                CategoriaDao categoriaDao = new CategoriaDao();
                roupa.setCategoria(categoriaDao.lerPorId(rs.getInt("idcategoria")));
                roupa.setIdRoupa(id);
                return roupa;
            }
            return null;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public List<Roupa> pesquisar(String termo) {
        List<Roupa> roupas = new ArrayList<>();
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement("select r.idroupa, r.nome, r.vlr, r.idcategoria, c.nomeC"
                    + " from roupa r left join categoria c on r.idcategoria = c.idcategoria"
                    + " where ativo = 1 and (r.idroupa = ? or r.nome like ? or"
                    + " r.vlr = ? or c.nomeC = ?");
            stmt.setString(1, termo);
            stmt.setString(2, termo + "%");
            stmt.setString(3, termo);
            stmt.setString(4, termo);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Roupa roupa = new Roupa();
                roupa.setIdRoupa(rs.getInt("idroupa"));
                roupa.setNome(rs.getString("nome"));
                roupa.setVlr(rs.getDouble("vlr"));
                roupa.getCategoria().setNome(rs.getString("nomeC"));
                roupas.add(roupa);
            }

            return roupas;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

}
