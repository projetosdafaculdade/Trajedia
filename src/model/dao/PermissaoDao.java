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
import model.vo.Permissao;

/**
 *
 * @author Alunos
 */
public class PermissaoDao extends Dao implements DaoI<Permissao> {

    public PermissaoDao() {
        super();
    }

    @Override
    public List<Permissao> listar() {
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement("select idpermissao, visualizar, editar, cadastrar, deletar, idtela, idfuncionario from permissao"
                    + " where ativo = 1 order by idpermissao desc",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            ResultSet result = stmt.executeQuery();
            List<Permissao> lista = new ArrayList<Permissao>();
            while (result.next()) {
                Permissao p = new Permissao();
                p.setIdPermissao(result.getInt("idpermissao"));
                p.setVisualizar(result.getInt("visualizar"));
                p.setEditar(result.getInt("editar"));
                p.setCadastrar(result.getInt("cadastrar"));
                p.setDeletar(result.getInt("deletar"));
                p.getTela().setIdTela(result.getInt("idtela"));
                p.getFuncionario().setIdFuncionario(result.getInt("idfuncionario"));
                lista.add(p);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public int cadastrar(Permissao obj) {
         try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement(
                    "insert into permissao(visualizar, editar, cadastrar, deletar, idtela, idfuncionario)"
                    + " values(?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, obj.getVisualizar());
            stmt.setInt(2, obj.getEditar());
            stmt.setInt(3, obj.getCadastrar());
            stmt.setInt(4, obj.getDeletar());
            stmt.setInt(5, obj.getTela().getIdTela());
            stmt.setInt(6, obj.getFuncionario().getIdFuncionario());
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
    public boolean alterar(Permissao obj) {
         try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement("update permissao"
                    + " set visualizar = ?, editar = ?, cadastrar = ?, deletar = ?, idtela = ?, idfuncionario = ? where idpermissao = ?");
            stmt.setInt(1, obj.getVisualizar());
            stmt.setInt(2, obj.getEditar());
            stmt.setInt(3, obj.getCadastrar());
            stmt.setInt(4, obj.getDeletar());
            stmt.setInt(5, obj.getTela().getIdTela());
            stmt.setInt(6, obj.getFuncionario().getIdFuncionario());
            stmt.setInt(7, obj.getIdPermissao());
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
            stmt = conexao.prepareStatement("update permissao set ativo = 0 where idpermissao = ?");
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public Permissao lerPorId(int id) {
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement("select idpermissao, visualizar, editar, cadastrar, deletar, idtela, idfuncionario from permissao where ativo = 1 and idpermissao = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Integer visualizar = rs.getInt("visualizar");
                Integer editar = rs.getInt("editar");
                Integer cadastrar = rs.getInt("cadastrar");
                Integer deletar = rs.getInt("deletar");
                Integer idTela = rs.getInt("idtela");
                Integer idFuncionario = rs.getInt("idfuncionario");
                Permissao permissao = new Permissao();
                permissao.setVisualizar(visualizar);
                permissao.setEditar(editar);
                permissao.setCadastrar(cadastrar);
                permissao.setDeletar(deletar);
                permissao.getTela().setIdTela(idTela);
                permissao.getFuncionario().setIdFuncionario(idFuncionario);
                permissao.setIdPermissao(id);
                return permissao;
            }
            return null;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public List<Permissao> pesquisar(String termo) {
        List<Permissao> permissoes = new ArrayList<>();
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement("select idpermissao, visualizar, editar, cadastrar, deletar, idtela, idfuncionario from permissao"
                    + " where ativo = 1 and (idpermissao = ? or visualizar like ? or editar = ? or cadastrar = ? or deletar = ? or idtela = ? or idfuncionario = ?)");
            stmt.setString(1, termo);
            stmt.setString(2, termo + "%");
            stmt.setString(3, termo);
            stmt.setString(4, termo);
            stmt.setString(5, termo);
            stmt.setString(6, termo);
            stmt.setString(7, termo);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Permissao permissao = new Permissao();
                permissao.setVisualizar(rs.getInt("visualizar"));
                permissao.setEditar(rs.getInt("editar"));
                permissao.setCadastrar(rs.getInt("cadastrar"));
                permissao.setDeletar(rs.getInt("deletar"));
                permissao.getTela().setIdTela(rs.getInt("idtela"));
                permissao.getFuncionario().setIdFuncionario(rs.getInt("idfuncionario"));
                permissao.setIdPermissao(rs.getInt("idpermissao"));
                permissoes.add(permissao);
            }

            return permissoes;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

}
