package controller;

import model.dao.PermissaoDao;
import util.JPane;
import view.Agendar;
import view.Alugar;
import view.Cliente;
import view.CriarCategoria;
import view.Endereco;
import view.Fornecedor;
import view.Funcionario;
import view.Permissao;
import view.RealizarDevolucao;
import view.Roupa;
import view.Traje;

public class InicialController {

    private int idFuncionario;
    PermissaoDao permissaoDao;

    public InicialController(int idFuncionario) {
        this.idFuncionario = idFuncionario;
        permissaoDao = new PermissaoDao();
    }

    public void abrirCliente() {
        int tela = 1;
        if ((permissaoDao.lerPorId(idFuncionario, tela)) == null) {
            Cliente cliente = new Cliente();
            cliente.setVisible(true);
        } else {
            JPane.show.STRING("AVISO!", "Você não tem acesso a essa tela");
        }
    }

    public void abrirRoupa() {
        int tela = 2;
        if ((permissaoDao.lerPorId(idFuncionario, tela)) == null) {
            Roupa roupa = new Roupa();
            roupa.setVisible(true);
        } else {
            JPane.show.STRING("AVISO!", "Você não tem permissão nessa tela");
        }
    }

    public void abrirTraje() {
        int tela = 3;
        if ((permissaoDao.lerPorId(idFuncionario, tela)) == null) {
            Traje traje = new Traje();
            traje.setVisible(true);
        } else {
            JPane.show.STRING("AVISO!", "Você não tem permissão nessa tela");
        }
    }

    public void abrirFuncionario() {
        int tela = 4;
        if ((permissaoDao.lerPorId(idFuncionario, tela)) == null) {
            Funcionario funcionario = new Funcionario();
            funcionario.setVisible(true);
        } else {
            JPane.show.STRING("AVISO!", "Você não tem permissão nessa tela");
        }
    }

    public void abrirCategoria() {
        int tela = 5;
        if ((permissaoDao.lerPorId(idFuncionario, tela)) == null) {
            CriarCategoria categoria = new CriarCategoria();
            categoria.setVisible(true);
        } else {
            JPane.show.STRING("AVISO!", "Você não tem permissão nessa tela");
        }
    }

    public void abrirFornecedor() {
        int tela = 6;
        if ((permissaoDao.lerPorId(idFuncionario, tela)) == null) {
            Fornecedor fornecedor = new Fornecedor();
            fornecedor.setVisible(true);
        } else {
            JPane.show.STRING("AVISO!", "Você não tem permissão nessa tela");
        }
    }

    public void abrirEndereco() {
        int tela = 7;
        if ((permissaoDao.lerPorId(idFuncionario, tela)) == null) {
            Endereco endereco = new Endereco();
            endereco.setVisible(true);
        } else {
            JPane.show.STRING("AVISO!", "Você não tem permissão nessa tela");
        }
    }

    public void agendar() {
        int tela = 8;
        if ((permissaoDao.lerPorId(idFuncionario, tela)) == null) {
            Agendar agendar = new Agendar();
            agendar.setVisible(true);
        } else {
            JPane.show.STRING("AVISO!", "Você não tem permissão nessa tela");
        }
    }

    public void alugar() {
        int tela = 9;
        if ((permissaoDao.lerPorId(idFuncionario, tela)) == null) {
            Alugar alugar = new Alugar();
            alugar.setVisible(true);
        } else {
            JPane.show.STRING("AVISO!", "Você não tem permissão nessa tela");
        }
    }

    public void permissoes() {
        int tela = 10;
        if ((permissaoDao.lerPorId(idFuncionario, tela)) == null) {
            Permissao permissao = new Permissao();
            permissao.setVisible(true);
        } else {
            JPane.show.STRING("AVISO!", "Você não tem permissão nessa tela");
        }
    }

    public void realizarDevolucao() {
        RealizarDevolucao realizarDevolucao = new RealizarDevolucao();
        realizarDevolucao.setVisible(true);
    }

}

