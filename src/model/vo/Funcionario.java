package model.vo;

public class Funcionario {

    private int idFuncionario;
    private String usuario;
    private String senha;
    private int ativo;

    public Funcionario() {
    }

    public Funcionario(int idFuncionario, String usuario, String senha, int ativo) {
        this.idFuncionario = idFuncionario;
        this.usuario = usuario;
        this.senha = senha;
        this.ativo = ativo;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getAtivo() {
        return ativo;
    }

    public void setAtivo(int ativo) {
        this.ativo = ativo;
    }

    @Override
    public String toString() {
        return "Funcionario{" + "idFuncionario=" + idFuncionario + ", usuario=" + usuario + ", senha=" + senha + '}';
    }

}
