package model.vo;

public class Tela {

    private int idTela;
    private String instancia;
    private int numeroDaTela;
    private int ativo;

    public Tela() {
    }

    public Tela(int idTela, String instancia, int numeroDaTela, int ativo) {
        this.idTela = idTela;
        this.instancia = instancia;
        this.numeroDaTela = numeroDaTela;
        this.ativo = ativo;
    }

    public int getIdTela() {
        return idTela;
    }

    public void setIdTela(int idTela) {
        this.idTela = idTela;
    }

    public String getInstancia() {
        return instancia;
    }

    public void setInstancia(String instancia) {
        this.instancia = instancia;
    }

    public int getNumeroDaTela() {
        return numeroDaTela;
    }

    public void setNumeroDaTela(int numeroDaTela) {
        this.numeroDaTela = numeroDaTela;
    }

    public int getAtivo() {
        return ativo;
    }

    public void setAtivo(int ativo) {
        this.ativo = ativo;
    }

    @Override
    public String toString() {
        return "Tela{" + "idTela=" + idTela + ", instancia=" + instancia + ", numeroDaTela=" + numeroDaTela + '}';
    }

}
