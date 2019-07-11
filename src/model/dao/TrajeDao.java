package model.dao;

import factory.Dao;
import interfaces.DaoI;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.vo.Roupa;
import model.vo.Traje;


public class TrajeDao extends Dao implements DaoI<Traje> {

    public TrajeDao() {
        super();
    }

    @Override
    public List<Traje> listar() {
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement("SELECT \n" +
"	NOME\n" +
"    , IDTRAJE\n" +
"    , DESCONTO\n" +
"    , (SELECT (SUM(VLR) * (100 - DESCONTO) / 100) FROM ROUPA INNER JOIN ROUPADOTRAJE ON ROUPADOTRAJE.IDROUPA = ROUPA.IDROUPA WHERE IDTRAJE = TRAJE.IDTRAJE)  AS VLRTRAJE\n" +
"FROM \n" +
"	TRAJE\n" +
"WHERE \n" +
"	ATIVO = 1 \n" +
"ORDER BY \n" +
"	IDTRAJE ASC",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            ResultSet result = stmt.executeQuery();
            List<Traje> lista = new ArrayList<Traje>();
            while (result.next()) {
                Traje t = new Traje();
                t.setIdTraje(result.getInt("idtraje"));
                t.setNome(result.getString("NOME"));
                t.setDesconto(result.getInt("desconto"));
                t.setValorTraje(result.getDouble("VLRTRAJE"));
                lista.add(t);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public int cadastrar(Traje obj) {
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement(
                    "insert into traje(desconto, nome)"
                    + " values(?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, obj.getDesconto());
            stmt.setString(2, obj.getNome());
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
    public boolean alterar(Traje obj) {
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement("update traje"
                    + " set nome = ?,desconto = ? where idtraje = ?");
            stmt.setString(1, obj.getNome());
            stmt.setInt(2, obj.getDesconto());
            stmt.setInt(3, obj.getIdTraje());
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
            stmt = conexao.prepareStatement("update traje set ativo = 0 where idtraje = ?");
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public Traje lerPorId(int id) {
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement("select idtraje, desconto, idfornecedor from traje where ativo = 1 and idtraje = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Integer desconto = rs.getInt("desconto");
                Integer idFornecedor = rs.getInt("idfornecedor");
                Traje traje = new Traje();
                traje.setDesconto(desconto);
                traje.getFornecedor().setIdFornecedor(idFornecedor);
                traje.setIdTraje(id);
                return traje;
            }
            return null;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public List<Traje> pesquisar(String termo) {
        List<Traje> trajes = new ArrayList<>();
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement("select idtraje, desconto, idfornecedor from traje"
                    + " where ativo = 1 and (idtraje = ? or desconto like ? or idfornecedor = ?)");
            stmt.setString(1, termo);
            stmt.setString(2, termo + "%");
            stmt.setString(3, termo);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Traje traje = new Traje();
                traje.setDesconto(rs.getInt("desconto"));
                traje.getFornecedor().setIdFornecedor(rs.getInt("idfornecedor"));
                traje.setIdTraje(rs.getInt("idtraje"));
                trajes.add(traje);
            }

            return trajes;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public void cadastrarRoupaNoTraje(Roupa roupa, Traje traje) {
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement(
                    "INSERT INTO ROUPADOTRAJE(IDTRAJE, IDROUPA) VALUES (?,?)");
            System.out.println("X" + traje.getIdTraje());
            System.out.println("X" + roupa.getIdRoupa());
            stmt.setInt(1, traje.getIdTraje());
            stmt.setInt(2, roupa.getIdRoupa());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Roupa> listarRoupas(Traje traje) {
        List<Roupa> roupas = new ArrayList<>();
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement("SELECT \n"
                    + "	ROUPADOTRAJE.IDTRAJE, \n"
                    + "    ROUPADOTRAJE.IDROUPA,\n"
                    + "    ROUPA.NOME,\n"
                    + "    ROUPA.VLR,\n"
                    + "    CATEGORIA.IDCATEGORIA\n"
                    + "FROM\n"
                    + "	ROUPADOTRAJE\n"
                    + "INNER JOIN ROUPA ON\n"
                    + "ROUPADOTRAJE.IDROUPA = ROUPA.IDROUPA \n"
                    + "INNER JOIN CATEGORIA ON\n"
                    + "CATEGORIA.IDCATEGORIA = ROUPA.IDCATEGORIA\n"
                    + "WHERE ROUPADOTRAJE.IDTRAJE = ?");
            stmt.setInt(1, traje.getIdTraje());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Roupa roupa = new Roupa();
                roupa.setIdRoupa(rs.getInt("IDROUPA"));
                roupa.setNome(rs.getString("NOME"));
                roupa.setVlr(rs.getDouble("VLR"));
                CategoriaDao categoriaDao = new CategoriaDao();
                roupa.setCategoria(categoriaDao.lerPorId(rs.getInt("IDCATEGORIA")));
                roupas.add(roupa);
            }

            return roupas;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public void limparRelacionamento(Traje traje) {
        PreparedStatement stmt;
        try {
            stmt = conexao.prepareStatement("DELETE FROM ROUPADOTRAJE WHERE IDTRAJE = ?");
            stmt.setInt(1, traje.getIdTraje());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TrajeDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void removerRoupaDoTraje(Traje traje, Roupa roupa) {
        PreparedStatement stmt;
        try {
            stmt = conexao.prepareStatement("DELETE FROM ROUPADOTRAJE WHERE IDTRAJE = ? AND IDROUPA = ?");
            stmt.setInt(1, traje.getIdTraje());
            stmt.setInt(2, roupa.getIdRoupa());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TrajeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
