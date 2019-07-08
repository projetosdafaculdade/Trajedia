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
import model.vo.Funcionario;

/**
 *
 * @author Alunos
 */
public class FuncionarioDao extends Dao implements DaoI<Funcionario>{

    public FuncionarioDao() {
        super();
    }
    
    @Override
    public List<Funcionario> listar() {
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement("select idfuncionario, usuario, senha from funcionario"
                    + " where ativo = 1 order by idfuncionario desc",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            ResultSet result = stmt.executeQuery();
            List<Funcionario> lista = new ArrayList<Funcionario>();
            while (result.next()) {
                Funcionario f = new Funcionario();
                f.setIdFuncionario(result.getInt("idcategoria"));
                f.setUsuario(result.getString("usuario"));
                f.setSenha(result.getString("senha"));
                lista.add(f);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public int cadastrar(Funcionario obj) {
         try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement(
                    "insert into funcionario(usuario, senha)"
                    + " values(?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, obj.getUsuario());
            stmt.setString(2, obj.getSenha());
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
    public boolean alterar(Funcionario obj) {
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement("update funcionario"
                    + " set usuario = ?, senha = ? where idfuncionario = ?");
            stmt.setString(1, obj.getUsuario());
            stmt.setString(2, obj.getSenha());
            stmt.setInt(3, obj.getIdFuncionario());
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
            stmt = conexao.prepareStatement("update funcionario set ativo = 0 where idfuncionario = ?");
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public Funcionario lerPorId(int id) {
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement("select idfuncionario, usuario, senha from funcionario where ativo = 1 and idfuncionario = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String usuario = rs.getString("usuario");
                String senha = rs.getString("senha");
                Funcionario funcionario = new Funcionario();
                funcionario.setUsuario(usuario);
                funcionario.setSenha(senha);
                funcionario.setIdFuncionario(id);
                return funcionario;
            }
            return null;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public List<Funcionario> pesquisar(String termo) {
        List<Funcionario> funcionarios = new ArrayList<>();
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement("select idfuncionario, usuario, senha from funcionario"
                    + " where ativo = 1 and (idfuncionario = ? or usuario like ? or senha = ?)");
            stmt.setString(1, termo);
            stmt.setString(2, termo + "%");
            stmt.setString(3, termo);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setUsuario(rs.getString("usuario"));
                funcionario.setSenha(rs.getString("senha"));
                funcionario.setIdFuncionario(rs.getInt("idfuncionario"));
                funcionarios.add(funcionario);
            }

            return funcionarios;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
}
