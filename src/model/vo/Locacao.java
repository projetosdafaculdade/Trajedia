package model.vo;

import java.util.Date;
import java.util.List;

public class Locacao {

    private int idLocacao;
    private Date dataLocacao;
    private Date dataEvento;
    private Date dataDevolucao;
    private double vlrTotal;
    private Funcionario funcionario;
    private int tipoDeLocacao;
    private int ativo;
    private Cliente cliente;
    private List<Roupa> roupas;
    private List<Traje> trajes;

    public Locacao() {
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Roupa> getRoupas() {
        return roupas;
    }

    public void setRoupas(List<Roupa> roupas) {
        this.roupas = roupas;
    }

    public List<Traje> getTrajes() {
        return trajes;
    }

    public void setTrajes(List<Traje> trajes) {
        this.trajes = trajes;
    }
    
    

    public Locacao(int idLocacao, Date dataLocacao, Date dataEvento, Date dataDevolucao, double vlrTotal, Funcionario funcionario, int tipoDeLocacao, int ativo) {
        this.idLocacao = idLocacao;
        this.dataLocacao = dataLocacao;
        this.dataEvento = dataEvento;
        this.dataDevolucao = dataDevolucao;
        this.vlrTotal = vlrTotal;
        this.funcionario = funcionario;
        this.tipoDeLocacao = tipoDeLocacao;
        this.ativo = ativo;
    }

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

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public double getVlrTotal() {
        return vlrTotal;
    }

    public void setVlrTotal(double vlrTotal) {
        this.vlrTotal = vlrTotal;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public int getTipoDeLocacao() {
        return tipoDeLocacao;
    }

    public void setTipoDeLocacao(int tipoDeLocacao) {
        this.tipoDeLocacao = tipoDeLocacao;
    }

    public int getAtivo() {
        return ativo;
    }

    public void setAtivo(int ativo) {
        this.ativo = ativo;
    }
    
    @Override
    public String toString() {
        return "Locacao{" + "idLocacao=" + idLocacao + ", dataLocacao=" + dataLocacao + ", dataEvento=" + dataEvento + ", dateEvolucao=" + dataDevolucao + ", vlrTotal=" + vlrTotal + ", funcionario=" + funcionario + ", tipoDeLocacao=" + tipoDeLocacao + '}';
    }

   
    

   

}
