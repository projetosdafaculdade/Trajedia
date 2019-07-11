package controller;

import view.Agendar;
import view.Alugar;
import view.Cliente;
import view.CriarCategoria;
import view.Endereco;
import view.Fornecedor;
import view.Funcionario;
import view.Permissao;
import view.Roupa;
import view.Traje;

public class InicialController {

    public InicialController() {
    }


    public void abrirCliente() {
        Cliente cliente = new Cliente();
        cliente.setVisible(true);
    }

    public void abrirRoupa() {
        Roupa roupa = new Roupa();
        roupa.setVisible(true);
    }

    public void abrirTraje() {
        Traje traje = new Traje();
        traje.setVisible(true);
    }

    public void abrirFuncionario() {
        Funcionario funcionario = new Funcionario();
        funcionario.setVisible(true);
    }

    public void abrirCategoria() {
        CriarCategoria categoria = new CriarCategoria();
        categoria.setVisible(true);
    }

    public void abrirFornecedor() {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setVisible(true);
    }

    public void abrirEndereco() {
        Endereco endereco = new Endereco();
        endereco.setVisible(true);
    }

    public void agendar() {
        Agendar agendar = new Agendar();
        agendar.setVisible(true);
    }

    public void alugar() {
        Alugar alugar = new Alugar();
        alugar.setVisible(true);
    }

    public void permissoes() {
        Permissao permissao = new Permissao();
        permissao.setVisible(true);
    }
}
