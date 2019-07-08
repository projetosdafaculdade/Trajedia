package model.vo;

public class RoupaVenda {

    private int idRoupaVenda;
    private int desconto;
    private int ativo;

    public RoupaVenda() {
    }

    public RoupaVenda(int idRoupaVenda, int desconto, int ativo) {
        this.idRoupaVenda = idRoupaVenda;
        this.desconto = desconto;
        this.ativo = ativo;
    }

    public int getIdRoupaVenda() {
        return idRoupaVenda;
    }

    public void setIdRoupaVenda(int idRoupaVenda) {
        this.idRoupaVenda = idRoupaVenda;
    }

    public int getDesconto() {
        return desconto;
    }

    public void setDesconto(int desconto) {
        this.desconto = desconto;
    }

    public int getAtivo() {
        return ativo;
    }

    public void setAtivo(int ativo) {
        this.ativo = ativo;
    }

    @Override
    public String toString() {
        return "RoupaVenda{" + "idRoupaVenda=" + idRoupaVenda + ", desconto=" + desconto + '}';
    }

}
