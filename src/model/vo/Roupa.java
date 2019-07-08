package model.vo;

public class Roupa {

    private int idRoupa;
    private String nome;
    private double vlr;
    private Categoria categoria;
    private int ativo;

    public Roupa() {
        this.categoria = new Categoria();
    }

    public Roupa(int idRoupa, String nome, double vlr, Categoria categoria, int ativo) {
        this.idRoupa = idRoupa;
        this.nome = nome;
        this.vlr = vlr;
        this.categoria = categoria;
        this.ativo = ativo;
        this.categoria = new Categoria();
    }

    public int getIdRoupa() {
        return idRoupa;
    }

    public void setIdRoupa(int idRoupa) {
        this.idRoupa = idRoupa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getVlr() {
        return vlr;
    }

    public void setVlr(double vlr) {
        this.vlr = vlr;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public int getAtivo() {
        return ativo;
    }

    public void setAtivo(int ativo) {
        this.ativo = ativo;
    }

    @Override
    public String toString() {
        return "Roupa{" + "idRoupa=" + idRoupa + ", nome=" + nome + ", vlr=" + vlr + ", categoria=" + categoria + ", ativo=" + ativo + '}';
    }

}
