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
import model.dao.FornecedorDao;
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
import view.Fornecedor;

public class FornecedorController {

    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnFechar;
    private javax.swing.JButton btnRemover;
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JTable tableFornecedor;
    private view.Fornecedor fornecedor;

    public FornecedorController(view.Fornecedor fornecedor, JButton btnEditar, JButton btnFechar, JButton btnRemover, JButton btnAdicionar, JTable tableFornecedor) {
        this.btnEditar = btnEditar;
        this.btnFechar = btnFechar;
        this.btnRemover = btnRemover;
        this.btnAdicionar = btnAdicionar;
        this.tableFornecedor = tableFornecedor;
        this.fornecedor = fornecedor;
    }

    private FornecedorDao fornecedorDao;
    List<model.vo.Fornecedor> fornecedors = new ArrayList<>();

    public List<model.vo.Fornecedor> listarFornecedor() {
        fornecedorDao = new FornecedorDao();
        fornecedors = fornecedorDao.listar();
        return fornecedors;
    }

    public void listarNaTabela() {
        DefaultTableModel model = (DefaultTableModel) tableFornecedor.getModel();
        int qtd = model.getRowCount();
        for (int i = 0; qtd > i; i++) {
            model.removeRow(qtd - i - 1);
        }
        model = (DefaultTableModel) tableFornecedor.getModel();
        for (model.vo.Fornecedor fornecedor : listarFornecedor()) {
            Object[] linha = {
                fornecedor.getIdFornecedor(),
                fornecedor.getRazaoSocial(),
                fornecedor.getEndereco().getPais(),
                fornecedor.getTelefone()
            };
            model.addRow(linha);
        }
    }

    public void adicionarFornecedor() {
        model.vo.Fornecedor fornecedor = new model.vo.Fornecedor();
        FornecedorDao fornecedorDao = new FornecedorDao();
        EnderecoDao enderecoDao = new EnderecoDao();
        List<Endereco> enderecos = enderecoDao.listar();
        //DEFININDO NOME
        String razaoSocial = JPane.input.STRING("Inserir", "Digite o nome do fornecedor:");
        Validar.continuar(razaoSocial);
        //DEFININDO Telefone
        String telefone = JPane.input.STRING("Inserir", "Digite o telefone do fornecedor:");
        Validar.continuar(telefone);
        //DEFININDO CATEGORIA
        SelectOptions selectOptions = new SelectOptions();
        for (Endereco endereco : enderecos) {
            selectOptions.adicionar(endereco.getBairro());
        }
        selectOptions.setTitulo("Selecione um Endereço");
        selectOptions.instanciar(selectOptions);
        Validar.continuar(selectOptions.getRetorno());
        //SETANDO E CADASTRANDO
        fornecedor.setRazaoSocial(razaoSocial);
        fornecedor.setEndereco(enderecos.get(selectOptions.getIndice()));
        fornecedor.setTelefone(telefone);
        fornecedorDao.cadastrar(fornecedor);
        listarNaTabela();
    }

    public void removerFornecedor() {
        if (tableFornecedor.getSelectedRow() >= 0) {
            DefaultTableModel model = (DefaultTableModel) tableFornecedor.getModel();
            int linhaSelecionada = tableFornecedor.getSelectedRow();
            fornecedorDao = new FornecedorDao();
            fornecedorDao.deletar(fornecedors.get(linhaSelecionada).getIdFornecedor());
            JPane.show.STRING("Alerta!", fornecedors.get(linhaSelecionada).getRazaoSocial() + " foi excluído!");
            listarNaTabela();
        } else {
            JPane.show.STRING("AVISO!", "Para excluir, escolha uma linha!");
        }
    }

    public void fechar() {
        fornecedor.dispose();
    }

    public void editarFornecedor() {
        if (tableFornecedor.getSelectedRow() >= 0) {
            model.vo.Fornecedor fornecedor = this.fornecedors.get(tableFornecedor.getSelectedRow());
            FornecedorDao fornecedorDao = new FornecedorDao();
            List<model.vo.Fornecedor> fornecedors = fornecedorDao.listar();
            try {
                EnderecoDao enderecoDao = new EnderecoDao();
                List<Endereco> enderecos = enderecoDao.listar();
                //DEFININDO NOME
                String razaoSocial = JPane.input.STRING("Inserir", "Digite o nome do fornecedor:");
                Validar.continuar(razaoSocial);
                //DEFININDO Telefone
                String telefone = JPane.input.STRING("Inserir", "Digite o telefone do fornecedor:");
                Validar.continuar(telefone);
                //DEFININDO CATEGORIA
                SelectOptions selectOptions = new SelectOptions();
                for (Endereco endereco : enderecos) {
                    selectOptions.adicionar(endereco.getBairro());
                }
                selectOptions.setTitulo("Selecione um Endereço");
                selectOptions.instanciar(selectOptions);
                Validar.continuar(selectOptions.getRetorno());
                //SETANDO E CADASTRANDO
                fornecedor.setRazaoSocial(razaoSocial);
                fornecedor.setEndereco(enderecos.get(selectOptions.getIndice()));
                fornecedor.setTelefone(telefone);
                fornecedorDao.alterar(fornecedor);
                listarNaTabela();
            } catch (Exception e) {

            }
        } else {
            JPane.show.STRING("AVISO!", "Para editar, escolha uma linha!");
        }
    }

 
}
