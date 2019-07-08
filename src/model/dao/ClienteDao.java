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

/**
 *
 * @author Alunos
 */
public class ClienteDao extends Dao implements DaoI<Cliente> {

    public ClienteDao() {
        super();
    }

    @Override
    public List<Cliente> listar() {
          try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement("select IDCLIENTE, NOME, CPF, TELEFONE, IDENDERECO from cliente"
                    + " where ativo = 1 order by IDCLIENTE desc",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            ResultSet result = stmt.executeQuery();
            List<Cliente> lista = new ArrayList<Cliente>();
            while (result.next()) {
                Cliente c = new Cliente();
                c.setIdCliente(result.getInt("IDCLIENTE"));
                c.setNome(result.getString("NOME"));
                c.setCpf(result.getString("CPF"));
                c.setTelefone(result.getString("TELEFONE"));
                c.getEndereco().setIdEndereco(result.getInt("IDENDERECO"));
                lista.add(c);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public int cadastrar(Cliente obj) {
          try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement(
                    "insert into CLIENTE(NOME, CPF, TELEFONE, IDENDERECO)"
                    + " values(?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getCpf());
            stmt.setString(3, obj.getTelefone());
            stmt.setInt(4, obj.getEndereco().getIdEndereco());
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
    public boolean alterar(Cliente obj) {
         try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement("update CLIENTE"
                    + " set NOME = ?, CPF = ?, TELEFONE = ?, IDENDERECO = ? where idcliente = ?");
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getCpf());
            stmt.setString(3, obj.getTelefone());
            stmt.setInt(4, obj.getEndereco().getIdEndereco());
            stmt.setInt(5, obj.getIdCliente());
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
            stmt = conexao.prepareStatement("update cliente set ativo = 0 where idcliente = ?");
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public Cliente lerPorId(int id) {
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement("select IDCLIENTE, NOME, CPF, TELEFONE, IDENDERECO from CLIENTE where ativo = 1 and idcliente = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String nome = rs.getString("NOME");
                String cpf = rs.getString("CPF");
                String telefone = rs.getString("TELEFONE");
                Integer idEndereco = rs.getInt("IDENDERECO");
                Cliente cliente = new Cliente();
                cliente.setNome(nome);
                cliente.setCpf(cpf);
                cliente.setTelefone(telefone);
                cliente.getEndereco().setIdEndereco(idEndereco);
                cliente.setIdCliente(id);
                return cliente;
            }
            return null;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public List<Cliente> pesquisar(String termo) {
        List<Cliente> clientes = new ArrayList<>();
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement("select c.IDCLIENTE, c.NOME, c.CPF, c.TELEFONE, c.IDENDERECO, e.NUMERO, e.rua, e.bairro,"
                    + " e.cidade, e.estado, e.uf, e.pais from CLIENTE c left join endereco e on c.idendereco = e.idendereco"
                    + " where ativo = 1 and (c.idcliente = ? or c.NOME like ? or"
                    + " c.CPF = ? or c.TELEFONE = ? or e.numero = ? or e.rua = ? or e.bairro = ? or e.cidade = ? or e.estado = ?"
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
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("idcliente"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.getEndereco().setIdEndereco(rs.getInt("idendereco"));
                cliente.getEndereco().setNumero(rs.getString("numero"));
                cliente.getEndereco().setRua(rs.getString("rua"));
                cliente.getEndereco().setBairro(rs.getString("bairro"));
                cliente.getEndereco().setCidade(rs.getString("cidade"));
                cliente.getEndereco().setEstado(rs.getString("estado"));
                cliente.getEndereco().setUf(rs.getString("uf"));
                cliente.getEndereco().setPais(rs.getString("pais"));
                clientes.add(cliente);
            }

            return clientes;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

}
