/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.vo;

/**
 *
 * @author Alunos
 */
public class Fornecedor {

    private int idFornecedor;
    private String razaoSocial;
    private Endereco endereco;
    private String telefone;
    private int ativo;

    public Fornecedor() {
        this.endereco = new Endereco();
    }

    public Fornecedor(int idFornecedor, String razaoSocial, Endereco endereco, String telefone, int ativo) {
        this.idFornecedor = idFornecedor;
        this.razaoSocial = razaoSocial;
        this.endereco = endereco;
        this.telefone = telefone;
        this.ativo = ativo;
        this.endereco = new Endereco();
    }

    public int getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(int idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getAtivo() {
        return ativo;
    }

    public void setAtivo(int ativo) {
        this.ativo = ativo;
    }

    @Override
    public String toString() {
        return "Fornecedor{" + "idFornecedor=" + idFornecedor + ", razaoSocial=" + razaoSocial + ", endereco=" + endereco + ", telefone=" + telefone + '}';
    }

}
