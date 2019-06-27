package model.vo;

public class Permissao {
    int idPermissao;
    int idTela;
    int visualizar;
    int editar;
    int cadastrar;
    int deletar;
    int idFuncionario;

    public int getIdPermissao() {
        return idPermissao;
    }

    public void setIdPermissao(int idPermissao) {
        this.idPermissao = idPermissao;
    }

    public int getIdTela() {
        return idTela;
    }

    public void setIdTela(int idTela) {
        this.idTela = idTela;
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

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }
    
}
