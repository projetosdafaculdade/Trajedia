package controller;

import factory.Conexao;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.dao.CategoriaDao;
import model.dao.FuncionarioDao;
import model.dao.EnderecoDao;
import model.dao.RoupaDao;
import model.vo.Categoria;
import model.vo.Endereco;
import model.vo.Roupa;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import util.JPane;
import util.SelectOptions;
import util.Validar;
import view.Funcionario;

public class FuncionarioController {

    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnFechar;
    private javax.swing.JButton btnRemover;
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JTable tableFuncionario;
    private view.Funcionario funcionario;

    public FuncionarioController(view.Funcionario funcionario, JButton btnEditar, JButton btnFechar, JButton btnRemover, JButton btnAdicionar, JTable tableFuncionario) {
        this.btnEditar = btnEditar;
        this.btnFechar = btnFechar;
        this.btnRemover = btnRemover;
        this.btnAdicionar = btnAdicionar;
        this.tableFuncionario = tableFuncionario;
        this.funcionario = funcionario;
    }

    private FuncionarioDao funcionarioDao;
    List<model.vo.Funcionario> funcionarios = new ArrayList<>();

    public List<model.vo.Funcionario> listarFuncionario() {
        funcionarioDao = new FuncionarioDao();
        funcionarios = funcionarioDao.listar();
        return funcionarios;
    }

    public void listarNaTabela() {
        DefaultTableModel model = (DefaultTableModel) tableFuncionario.getModel();
        int qtd = model.getRowCount();
        for (int i = 0; qtd > i; i++) {
            model.removeRow(qtd - i - 1);
        }
        model = (DefaultTableModel) tableFuncionario.getModel();
        for (model.vo.Funcionario funcionario : listarFuncionario()) {
            Object[] linha = {
                funcionario.getIdFuncionario(),
                funcionario.getUsuario(),
                funcionario.getSenha()
            };
            model.addRow(linha);
        }
    }

    public void adicionarFuncionario() {
        model.vo.Funcionario funcionario = new model.vo.Funcionario();
        FuncionarioDao funcionarioDao = new FuncionarioDao();
        //DEFININDO USUÁRIO
        String usuario = JPane.input.STRING("Inserir", "Digite o usuário do funcionario:");
        Validar.continuar(usuario);
        //DEFININDO SENHA
        String senha = JPane.input.STRING("Inserir", "Digite a senha funcionario:");
        Validar.continuar(senha);;
        //SETANDO E CADASTRANDO
        funcionario.setUsuario(usuario);
        funcionario.setSenha(senha);
        funcionarioDao.cadastrar(funcionario);
        listarNaTabela();
    }

    public void removerFuncionario() {
        if (tableFuncionario.getSelectedRow() >= 0) {
            DefaultTableModel model = (DefaultTableModel) tableFuncionario.getModel();
            int linhaSelecionada = tableFuncionario.getSelectedRow();
            funcionarioDao = new FuncionarioDao();
            funcionarioDao.deletar(funcionarios.get(linhaSelecionada).getIdFuncionario());
            JPane.show.STRING("Alerta!", funcionarios.get(linhaSelecionada).getUsuario()+ " foi excluído!");
            listarNaTabela();
        } else {
            JPane.show.STRING("AVISO!", "Para excluir, escolha uma linha!");
        }
    }

    public void fechar() {
        funcionario.dispose();
    }

    public void editarFuncionario() {
        if (tableFuncionario.getSelectedRow() >= 0) {
            model.vo.Funcionario funcionario = this.funcionarios.get(tableFuncionario.getSelectedRow());
            FuncionarioDao funcionarioDao = new FuncionarioDao();
            List<model.vo.Funcionario> funcionarios = funcionarioDao.listar();
            try {
                //DEFININDO USUÁRIO
                String usuario = JPane.input.STRING("Inserir", "Digite o usuário do funcionario:");
                Validar.continuar(usuario);
                //DEFININDO SENHA
                String senha = JPane.input.STRING("Inserir", "Digite a senha funcionario:");
                Validar.continuar(senha);;
                //SETANDO E CADASTRANDO
                funcionario.setUsuario(usuario);
                funcionario.setSenha(senha);
                funcionarioDao.alterar(funcionario);
                listarNaTabela();
            } catch (Exception e) {

            }
        } else {
            JPane.show.STRING("AVISO!", "Para editar, escolha uma linha!");
        }
    }

}
