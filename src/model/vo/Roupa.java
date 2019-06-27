package model.vo;

public class Roupa {

    String nome;
    int idRoupa;
    double vlr;
    int idCategoria;
    int ativo;

    public int getIdRoupa() {
        return idRoupa;
    }

    public void setIdRoupa(int idRoupa) {
        this.idRoupa = idRoupa;
    }

    public double getVlr() {
        return vlr;
    }

    public void setVlr(double vlr) {
        this.vlr = vlr;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAtivo() {
        return ativo;
    }

    public void setAtivo(int ativo) {
        this.ativo = ativo;
    }

}
