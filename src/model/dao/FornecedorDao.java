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
import model.vo.Fornecedor;

/**
 *
 * @author Alunos
 */
public class FornecedorDao extends Dao implements DaoI<Fornecedor>{

    public FornecedorDao() {
        super();
    }

    @Override
    public List<Fornecedor> listar() {
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement("select idfornecedor, razaosocial, telefone, idendereco from fornecedor"
                    + " where ativo = 1 order by idfornecedor desc",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            ResultSet result = stmt.executeQuery();
            List<Fornecedor> lista = new ArrayList<Fornecedor>();
            while (result.next()) {
                Fornecedor f = new Fornecedor();
                f.setIdFornecedor(result.getInt("idfornecedor"));
                f.setRazaoSocial(result.getString("razaosocial"));
                f.setTelefone(result.getInt("telefone"));
                f.getEndereco().setIdEndereco(result.getInt("idendereco"));
                lista.add(f);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public int cadastrar(Fornecedor obj) {
         try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement(
                    "insert into fornecedor(razaosocial, telefone, idendereco)"
                    + " values(?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, obj.getRazaoSocial());
            stmt.setInt(2, obj.getTelefone());
            stmt.setInt(3, obj.getEndereco().getIdEndereco());
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
    public boolean alterar(Fornecedor obj) {
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement("update fornecedor"
                    + " set razaosocial = ?, telefone = ?, idendereco = ? where idfornecedor = ?");
            stmt.setString(1, obj.getRazaoSocial());
            stmt.setInt(2, obj.getTelefone());
            stmt.setInt(3, obj.getEndereco().getIdEndereco());
            stmt.setInt(4, obj.getIdFornecedor());
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
            stmt = conexao.prepareStatement("update fornecedor set ativo = 0 where idfornecedor = ?");
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public Fornecedor lerPorId(int id) {
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement("select idfornecedor, razaosocial, telefone, idendereco from fornecedor where ativo = 1 and idfornecedor = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String razaosocial = rs.getString("razaosocial");
                Integer telefone = rs.getInt("telefone");
                Integer idEndereco = rs.getInt("idendereco");
                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setRazaoSocial(razaosocial);
                fornecedor.setTelefone(telefone);
                fornecedor.getEndereco().setIdEndereco(idEndereco);
                fornecedor.setIdFornecedor(id);
                return fornecedor;
            }
            return null;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public List<Fornecedor> pesquisar(String termo) {
        List<Fornecedor> fornecedores = new ArrayList<>();
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement("select f.idfornecedor, f.razaosocial, f.telefone, f.idendereco, e.numero, e.rua, e.bairro,"
                    + " e.cidade, e.estado, e.uf, e.pais from fornecedor f left join endereco e on f.idendereco = e.idendereco"
                    + " where ativo = 1 and (f.idfornecedor = ? or f.razaosocial like ? or"
                    + " f.telefone = ? or e.numero = ? or e.rua = ? or e.bairro = ? or e.cidade = ? or e.estado = ?"
                    + " or e.uf = ? or e.pais = ?)");
            stmt.setString(1, termo);
            stmt.setString(2, termo + "%");
            stmt.setString(3, termo);
            stmt.setString(4, termo);
            stmt.setString(5, termo);
            stmt.setString(6, termo);
            stmt.setString(7, termo);
            stmt.setString(8, termo);
            stmt.setString(9, termo);
            stmt.setString(10, termo);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setIdFornecedor(rs.getInt("idfornecedor"));
                fornecedor.setRazaoSocial(rs.getString("razaosocial"));
                fornecedor.setTelefone(rs.getInt("telefone"));
                fornecedor.getEndereco().setIdEndereco(rs.getInt("idendereco"));
                fornecedor.getEndereco().setNumero(rs.getString("numero"));
                fornecedor.getEndereco().setRua(rs.getString("rua"));
                fornecedor.getEndereco().setBairro(rs.getString("bairro"));
                fornecedor.getEndereco().setCidade(rs.getString("cidade"));
                fornecedor.getEndereco().setEstado(rs.getString("estado"));
                fornecedor.getEndereco().setUf(rs.getString("uf"));
                fornecedor.getEndereco().setPais(rs.getString("pais"));
                fornecedores.add(fornecedor);
            }

            return fornecedores;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
   
    
}
