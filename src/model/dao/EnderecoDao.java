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
import model.vo.Endereco;
import model.vo.Funcionario;

/**
 *
 * @author Alunos
 */
public class EnderecoDao extends Dao implements DaoI<Endereco> {

    public EnderecoDao() {
        super();
    }
    
    @Override
    public List<Endereco> listar() {
         try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement("select idendereco, numero, rua, bairro, cidade, estado, uf, pais from endereco"
                    + " where ativo = 1 order by idendereco desc",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            ResultSet result = stmt.executeQuery();
            List<Endereco> lista = new ArrayList<Endereco>();
            while (result.next()) {
                Endereco e = new Endereco();
                e.setIdEndereco(result.getInt("idendereco"));
                e.setNumero(result.getString("numero"));
                e.setRua(result.getString("rua"));
                e.setBairro(result.getString("bairro"));
                e.setCidade(result.getString("cidade"));
                e.setEstado(result.getString("estado"));
                e.setUf(result.getString("uf"));
                e.setPais(result.getString("pais"));
                lista.add(e);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public int cadastrar(Endereco obj) {
         try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement(
                    "insert into endereco(numero, rua, bairro, cidade, estado, uf, pais)"
                    + " values(?, ?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, obj.getNumero());
            stmt.setString(2, obj.getRua());
            stmt.setString(3, obj.getBairro());
            stmt.setString(4, obj.getCidade());
            stmt.setString(5, obj.getEstado());
            stmt.setString(6, obj.getUf());
            stmt.setString(7, obj.getPais());
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
    public boolean alterar(Endereco obj) {
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement("update endereco"
                    + " set numero = ?, rua = ?, bairro = ?, cidade = ?, estado = ?, uf = ?, pais = ? where idendereco = ?");
            stmt.setString(1, obj.getNumero());
            stmt.setString(2, obj.getRua());
            stmt.setString(3, obj.getBairro());
            stmt.setString(4, obj.getCidade());
            stmt.setString(5, obj.getEstado());
            stmt.setString(6, obj.getUf());
            stmt.setString(7, obj.getPais());
            stmt.setInt(8, obj.getIdEndereco());
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
            stmt = conexao.prepareStatement("update endereco set ativo = 0 where idendereco = ?");
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public Endereco lerPorId(int id) {
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement("select idendereco, numero, rua, bairro, cidade, estado, uf, pais from endereco where ativo = 1 and idendereco = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String numero = rs.getString("numero");
                String rua = rs.getString("rua");
                String bairro = rs.getString("bairro");
                String cidade = rs.getString("cidade");
                String estado = rs.getString("estado");
                String uf = rs.getString("uf");
                String pais = rs.getString("pais");
                Endereco endereco = new Endereco();
                endereco.setNumero(numero);
                endereco.setRua(rua);
                endereco.setBairro(bairro);
                endereco.setCidade(cidade);
                endereco.setEstado(estado);
                endereco.setUf(uf);
                endereco.setPais(pais);
                endereco.setIdEndereco(id);
                return endereco;
            }
            return null;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public List<Endereco> pesquisar(String termo) {
        List<Endereco> enderecos = new ArrayList<>();
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement("select idendereco, numero, rua, bairro, cidade, estado, uf, pais from endereco"
                    + " where ativo = 1 and (idendereco = ? or numero like ? or rua like ? or bairro like ? or cidade like ? or estado like ?"
                    + " or uf like ? or pais like ?)");
            stmt.setString(1, termo);
            stmt.setString(2, termo + "%");
            stmt.setString(3, termo + "%");
            stmt.setString(4, termo + "%");
            stmt.setString(5, termo + "%");
            stmt.setString(6, termo + "%");
            stmt.setString(7, termo + "%");
            stmt.setString(8, termo + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Endereco endereco = new Endereco();
                endereco.setNumero(rs.getString("numero"));
                endereco.setRua(rs.getString("rua"));
                endereco.setBairro(rs.getString("bairro"));
                endereco.setCidade(rs.getString("cidade"));
                endereco.setEstado(rs.getString("estado"));
                endereco.setUf(rs.getString("uf"));
                endereco.setPais(rs.getString("pais"));
                endereco.setIdEndereco(rs.getInt("idendereco"));
                enderecos.add(endereco);
            }

            return enderecos;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
}
