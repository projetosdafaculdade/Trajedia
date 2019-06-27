package model.vo;

import java.util.Date;

public class Locacao {

    int idLocacao;
    Date dataLocacao;
    Date dataEvento;
    Date dateEvolucao;
    double vlrTotal;
    int idFuncionario;
    int tipoDeLocacao;

    public int getIdLocacao() {
        return idLocacao;
    }

    public void setIdLocacao(int idLocacao) {
        this.idLocacao = idLocacao;
    }

    public Date getDataLocacao() {
        return dataLocacao;
    }

    public void setDataLocacao(Date dataLocacao) {
        this.dataLocacao = dataLocacao;
    }

    public Date getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(Date dataEvento) {
        this.dataEvento = dataEvento;
    }

    public Date getDateEvolucao() {
        return dateEvolucao;
    }

    public void setDateEvolucao(Date dateEvolucao) {
        this.dateEvolucao = dateEvolucao;
    }

    public double getVlrTotal() {
        return vlrTotal;
    }

    public void setVlrTotal(double vlrTotal) {
        this.vlrTotal = vlrTotal;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public int getTipoDeLocacao() {
        return tipoDeLocacao;
    }

    public void setTipoDeLocacao(int tipoDeLocacao) {
        this.tipoDeLocacao = tipoDeLocacao;
    }

}
