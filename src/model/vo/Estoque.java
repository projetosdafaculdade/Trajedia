package model.vo;

public class Estoque {

    private int idEstoque;
    private int quantidade;
    private Roupa roupa;
    private int ativo;

    public Estoque() {
    }

    public Estoque(int idEstoque, int quantidade, Roupa roupa, int ativo) {
        this.idEstoque = idEstoque;
        this.quantidade = quantidade;
        this.roupa = roupa;
        this.ativo = ativo;
    }

    public int getIdEstoque() {
        return idEstoque;
    }

    public void setIdEstoque(int idEstoque) {
        this.idEstoque = idEstoque;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Roupa getRoupa() {
        return roupa;
    }

    public void setRoupa(Roupa roupa) {
        this.roupa = roupa;
    }

    public int getAtivo() {
        return ativo;
    }

    public void setAtivo(int ativo) {
        this.ativo = ativo;
    }

    @Override
    public String toString() {
        return "Estoque{" + "idEstoque=" + idEstoque + ", quantidade=" + quantidade + ", roupa=" + roupa + '}';
    }

}
