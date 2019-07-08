package model.vo;

import java.util.Date;

public class Agenda {

    private int idAgenda;
    private Date dtAgendamento;
    private Cliente cliente;
    private Funcionario funcionario;
    private int ativo;

    public Agenda() {
    }

    public Agenda(int idAgenda, Date dtAgendamento, Cliente cliente, Funcionario funcionario, int ativo) {
        this.idAgenda = idAgenda;
        this.dtAgendamento = dtAgendamento;
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.ativo = ativo;
    }

    public int getIdAgenda() {
        return idAgenda;
    }

    public void setIdAgenda(int idAgenda) {
        this.idAgenda = idAgenda;
    }

    public Date getDtAgendamento() {
        return dtAgendamento;
    }

    public void setDtAgendamento(Date dtAgendamento) {
        this.dtAgendamento = dtAgendamento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public int getAtivo() {
        return ativo;
    }

    public void setAtivo(int ativo) {
        this.ativo = ativo;
    }

    @Override
    public String toString() {
        return "Agenda{" + "idAgenda=" + idAgenda + ", dtAgendamento=" + dtAgendamento + ", cliente=" + cliente + ", funcionario=" + funcionario + '}';
    }

}
