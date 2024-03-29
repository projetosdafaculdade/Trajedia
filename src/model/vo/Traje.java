package model.vo;

public class Traje {

    private int idTraje;
    private String nome;
    private int desconto;
    private Fornecedor fornecedor;
    private int ativo;
    double valorTraje;

    public double getValorTraje() {
        return valorTraje;
    }

    public void setValorTraje(double valorTraje) {
        this.valorTraje = valorTraje;
    }
    
    

    public Traje() {
    }

    public Traje(int idTraje, int desconto, Fornecedor fornecedor, int ativo) {
        this.idTraje = idTraje;
        this.desconto = desconto;
        this.fornecedor = fornecedor;
        this.ativo = ativo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    
    public int getIdTraje() {
        return idTraje;
    }

    public void setIdTraje(int idTraje) {
        this.idTraje = idTraje;
    }

    public int getDesconto() {
        return desconto;
    }

    public void setDesconto(int desconto) {
        this.desconto = desconto;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public int getAtivo() {
        return ativo;
    }

    public void setAtivo(int ativo) {
        this.ativo = ativo;
    }

    @Override
    public String toString() {
        return "Traje{" + "idTraje=" + idTraje + ", desconto=" + desconto + ", fornecedor=" + fornecedor + '}';
    }


}
