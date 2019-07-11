/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import factory.Dao;
import interfaces.DaoI;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.vo.Cliente;
import model.vo.Locacao;

/**
 *
 * @author Alunos
 */
public class LocacaoDao extends Dao implements DaoI<Locacao>{

    public LocacaoDao() {
        super();
    }
    
    

    @Override
    public List<Locacao> listar() {
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement("select idlocacao, datalocacao, dataevento, vlrtotal, dtdevolucao, idfuncionario, tipodelocacao from locacao"
                    + " where ativo = 1 order by idlocacao desc",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            ResultSet result = stmt.executeQuery();
            List<Locacao> lista = new ArrayList<Locacao>();
            while (result.next()) {
                Locacao l = new Locacao();
                l.setIdLocacao(result.getInt("idlocacao"));
                l.setDataLocacao(result.getDate("datalocacao"));
                l.setDataEvento(result.getDate("dataevento"));
                l.setVlrTotal(result.getDouble("vlrtotal"));
                l.setDataDevolucao(result.getDate("dtdevolucao"));
                l.getFuncionario().setIdFuncionario(result.getInt("idfuncionario"));
                l.setTipoDeLocacao(result.getInt("tipodelocacao"));
                lista.add(l);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public int cadastrar(Locacao obj) {
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement(
                    "insert into locacao(datalocacao, dataevento, vlrtotal, dtdevolucao, idfuncionario, tipodelocacao) "
                    + " values(?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setDate(1, (new Date(obj.getDataLocacao().getTime())));
            stmt.setDate(2, (new Date(obj.getDataEvento().getTime())));
            stmt.setDouble(3, obj.getVlrTotal());
            stmt.setDate(4, (Date) obj.getDataDevolucao());
            stmt.setInt(5, obj.getFuncionario().getIdFuncionario());
            stmt.setInt(6, obj.getTipoDeLocacao());
            System.out.println(stmt.getResultSet());
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
    public boolean alterar(Locacao obj) {
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement("update locacao"
                    + " set datalocacao = ?, dataevento = ?, vlrtotal = ?, dtdevolucao = ?, idfuncionario = ?, tipodelocacao = ? where idlocacao = ?");
            stmt.setDate(1, (Date) obj.getDataLocacao());
            stmt.setDate(2, (Date) obj.getDataEvento());
            stmt.setDouble(3, obj.getVlrTotal());
            stmt.setDate(4, (Date) obj.getDataDevolucao());
            stmt.setInt(5, obj.getFuncionario().getIdFuncionario());
            stmt.setInt(6, obj.getTipoDeLocacao());
            stmt.setInt(7, obj.getIdLocacao());
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
            stmt = conexao.prepareStatement("update locacao set ativo = 0 where idlocacao = ?");
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public Locacao lerPorId(int id) {
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement("select idlocacao, datalocacao, dataevento, vlrtotal, dtdevolucao, idfuncionario, tipodelocacao from locacao where ativo = 1 and idlocacao = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Date dataLocacao = rs.getDate("datalocacao");
                Date dataEvento = rs.getDate("dataevento");
                Double valorTotal = rs.getDouble("vlrtotal");
                Date dataDevolucao = rs.getDate("dtdevolucao");
                Integer idFuncionario = rs.getInt("idfuncionario");
                Integer tipoLocacao = rs.getInt("tipodelocacao");
                Locacao locacao = new Locacao();
                locacao.setDataLocacao(dataLocacao);
                locacao.setDataEvento(dataEvento);
                locacao.setVlrTotal(valorTotal);
                locacao.setDataDevolucao(dataDevolucao);
                locacao.getFuncionario().setIdFuncionario(idFuncionario);
                locacao.setIdLocacao(id);
                return locacao;
            }
            return null;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public List<Locacao> pesquisar(String termo) {
         List<Locacao> locacoes = new ArrayList<>();
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement("select idlocacao, datalocacao, dataevento, vlrtotal, dtdevolucao, idfuncionario, tipodelocacao from locacao"
                    + " where ativo = 1 and (idlocacao = ? or datalocacao like ? or dataevento = ? or vlrtotal = ? or dtdevolucao = ? or idfuncionario = ?"
                    + " or tipodelocacao = ?)");
            stmt.setString(1, termo);
            stmt.setString(2, termo + "%");
            stmt.setString(3, termo);
            stmt.setString(4, termo);
            stmt.setString(5, termo);
            stmt.setString(6, termo);
            stmt.setString(7, termo);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Locacao locacao = new Locacao();
                locacao.setDataLocacao(rs.getDate("datalocacao"));
                locacao.setDataEvento(rs.getDate("dataevento"));
                locacao.setVlrTotal(rs.getDouble("vlrtotal"));
                locacao.setDataDevolucao(rs.getDate("dtdevolucao"));
                locacao.getFuncionario().setIdFuncionario(rs.getInt("idfuncionario"));
                locacao.setTipoDeLocacao(rs.getInt("tipodelocacao"));
                locacao.setIdLocacao(rs.getInt("idlocacao"));
                locacoes.add(locacao);
            }

            return locacoes;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
}
