package model.vo;

public class Permissao {

    private int idPermissao;
    private Tela tela;
    private int visualizar;
    private int editar;
    private int cadastrar;
    private int deletar;
    private Funcionario funcionario;
    private int ativo;

    public Permissao() {
    }

    public Permissao(int idPermissao, Tela tela, int visualizar, int editar, int cadastrar, int deletar, Funcionario funcionario, int ativo) {
        this.idPermissao = idPermissao;
        this.tela = tela;
        this.visualizar = visualizar;
        this.editar = editar;
        this.cadastrar = cadastrar;
        this.deletar = deletar;
        this.funcionario = funcionario;
        this.ativo = ativo;
    }

    public int getIdPermissao() {
        return idPermissao;
    }

    public void setIdPermissao(int idPermissao) {
        this.idPermissao = idPermissao;
    }

    public Tela getTela() {
        return tela;
    }

    public void setTela(Tela tela) {
        this.tela = tela;
    }

    public int getVisualizar() {
        return visualizar;
    }

    public void setVisualizar(int visualizar) {
        this.visualizar = visualizar;
    }

    public int getEditar() {
        return editar;
    }

    public void setEditar(int editar) {
        this.editar = editar;
    }

    public int getCadastrar() {
        return cadastrar;
    }

    public void setCadastrar(int cadastrar) {
        this.cadastrar = cadastrar;
    }

    public int getDeletar() {
        return deletar;
    }

    public void setDeletar(int deletar) {
        this.deletar = deletar;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public int getAtivo() {
        return ativo;
    }

    public void setAtivo(int ativo) {
        this.ativo = ativo;
    }

    @Override
    public String toString() {
        return "Permissao{" + "idPermissao=" + idPermissao + ", tela=" + tela + ", visualizar=" + visualizar + ", editar=" + editar + ", cadastrar=" + cadastrar + ", deletar=" + deletar + ", funcionario=" + funcionario + ", ativo=" + ativo + '}';
    }
    
    

}
