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
import model.vo.Agenda;

/**
 *
 * @author Alunos
 */
public class AgendaDao extends Dao implements DaoI<Agenda>{

    public AgendaDao() {
        super();
    }
    
    

    @Override
    public List<Agenda> listar() {
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement("select idagenda, dtagendamento, idcliente, idfuncionario from agenda"
                    + " where ativo = 1 order by idagenda desc",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            ResultSet result = stmt.executeQuery();
            List<Agenda> lista = new ArrayList<Agenda>();
            while (result.next()) {
                Agenda a = new Agenda();
                a.setIdAgenda(result.getInt("idagenda"));
                a.setDtAgendamento(result.getDate("dtagendamento"));
                a.getCliente().setIdCliente(result.getInt("idcliente"));
                a.getFuncionario().setIdFuncionario(result.getInt("idfuncionario"));
                lista.add(a);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public int cadastrar(Agenda obj) {
       try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement(
                    "insert into agenda(dtagendamento, idcliente, idfuncionario)"
                    + " values(?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setDate(1, (Date) obj.getDtAgendamento());
            stmt.setInt(2, obj.getCliente().getIdCliente());
            stmt.setInt(3, obj.getFuncionario().getIdFuncionario());
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
    public boolean alterar(Agenda obj) {
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement("update agenda"
                    + " set dtagendamento = ?, idcliente = ?, idfuncionario = ? where idagenda = ?");
            stmt.setDate(1, (Date) obj.getDtAgendamento());
            stmt.setInt(2, obj.getCliente().getIdCliente());
            stmt.setInt(3, obj.getFuncionario().getIdFuncionario());
            stmt.setInt(4, obj.getIdAgenda());
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
            stmt = conexao.prepareStatement("update agenda set ativo = 0 where idagenda = ?");
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public Agenda lerPorId(int id) {
         try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement("select idagenda, dtagendamento, idcliente, idfuncionario from agenda where ativo = 1 and idcliente = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Date data = rs.getDate("dtagendamento");
                Integer idCliente = rs.getInt("idcliente");
                Integer idFuncionario = rs.getInt("idfuncionario");
                Agenda agenda = new Agenda();
                agenda.setDtAgendamento(data);
                agenda.getCliente().setIdCliente(idCliente);
                agenda.getFuncionario().setIdFuncionario(idFuncionario);
                agenda.setIdAgenda(id);
                return agenda;
            }
            return null;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public List<Agenda> pesquisar(String termo) {
         List<Agenda> agendas = new ArrayList<>();
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement("select idagenda, dtagendamento, idcliente, idfuncionario from agenda"
                    + " where ativo = 1 and (idagenda = ? or dtagendamento like ? or idcliente = ? or idfuncionario = ?)");
            stmt.setString(1, termo);
            stmt.setString(2, termo + "%");
            stmt.setString(3, termo);
            stmt.setString(4, termo);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Agenda agenda = new Agenda();
                agenda.setDtAgendamento(rs.getDate("dtagendamento"));
                agenda.getCliente().setIdCliente(rs.getInt("idcliente"));
                agenda.getFuncionario().setIdFuncionario(rs.getInt("idfuncionario"));
                agenda.setIdAgenda(rs.getInt("idagenda"));
                agendas.add(agenda);
            }

            return agendas;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
}
