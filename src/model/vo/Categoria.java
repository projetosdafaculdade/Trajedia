package model.vo;

public class Categoria {

    private int idCategoria;
    private String nome;
    private int ativo;

    public Categoria() {
    }

    public Categoria(int idCategoria, String nome, int ativo) {
        this.idCategoria = idCategoria;
        this.nome = nome;
        this.ativo = ativo;
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

    @Override
    public String toString() {
        return "Categoria{" + "idCategoria=" + idCategoria + ", nome=" + nome + '}';
    }

}
