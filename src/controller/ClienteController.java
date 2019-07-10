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
import model.dao.ClienteDao;
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
import view.Cliente;

public class ClienteController {

    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnFechar;
    private javax.swing.JButton btnRemover;
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JTable tableCliente;
    private view.Cliente cliente;

    public ClienteController(view.Cliente cliente, JButton btnEditar, JButton btnFechar, JButton btnRemover, JButton btnAdicionar, JTable tableCliente) {
        this.btnEditar = btnEditar;
        this.btnFechar = btnFechar;
        this.btnRemover = btnRemover;
        this.btnAdicionar = btnAdicionar;
        this.tableCliente = tableCliente;
        this.cliente = cliente;
    }

    private ClienteDao clienteDao;
    List<model.vo.Cliente> clientes = new ArrayList<>();

    public List<model.vo.Cliente> listarCliente() {
        clienteDao = new ClienteDao();
        clientes = clienteDao.listar();
        return clientes;
    }

    public void listarNaTabela() {
        DefaultTableModel model = (DefaultTableModel) tableCliente.getModel();
        int qtd = model.getRowCount();
        for (int i = 0; qtd > i; i++) {
            model.removeRow(qtd - i - 1);
        }
        model = (DefaultTableModel) tableCliente.getModel();
        for (model.vo.Cliente cliente : listarCliente()) {
            Object[] linha = {
                cliente.getIdCliente(),
                cliente.getNome(),
                cliente.getCpf(),
                cliente.getTelefone(),
                cliente.getEndereco().getPais()
            };
            model.addRow(linha);
        }
    }

    public void adicionarCliente() {
        model.vo.Cliente cliente = new model.vo.Cliente();
        ClienteDao clienteDao = new ClienteDao();
        EnderecoDao enderecoDao = new EnderecoDao();
        List<Endereco> enderecos = enderecoDao.listar();
        //DEFININDO NOME
        String nome = JPane.input.STRING("Inserir", "Digite o nome do cliente:");
        Validar.continuar(nome);
        //DEFININDO CPF
        String cpf = JPane.input.STRING("Inserir", "Digite o CPF do cliente:");
        Validar.continuar(cpf);
        //DEFININDO Telefone
        String telefone = JPane.input.STRING("Inserir", "Digite o telefone do cliente:");
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
        cliente.setCpf(cpf);
        cliente.setEndereco(enderecos.get(selectOptions.getIndice()));
        cliente.setNome(nome);
        cliente.setTelefone(telefone);
        clienteDao.cadastrar(cliente);
        listarNaTabela();

    }

    public void removerCliente() {
        if (tableCliente.getSelectedRow() >= 0) {
            DefaultTableModel model = (DefaultTableModel) tableCliente.getModel();
            int linhaSelecionada = tableCliente.getSelectedRow();
            clienteDao = new ClienteDao();
            clienteDao.deletar(clientes.get(linhaSelecionada).getIdCliente());
            JPane.show.STRING("Alerta!", clientes.get(linhaSelecionada).getNome() + " foi excluído!");
            listarNaTabela();
        } else {
            JPane.show.STRING("AVISO!", "Para excluir, escolha uma linha!");
        }
    }

    public void fechar() {
        cliente.dispose();
    }

    public void editarCliente() {
        if (tableCliente.getSelectedRow() >= 0) {
            model.vo.Cliente cliente = this.clientes.get(tableCliente.getSelectedRow());
            ClienteDao clienteDao = new ClienteDao();
            List<model.vo.Cliente> clientes = clienteDao.listar();
            try {
                EnderecoDao enderecoDao = new EnderecoDao();
                List<Endereco> enderecos = enderecoDao.listar();
                //DEFININDO NOME
                String nome = JPane.input.STRING("Inserir", "Digite o nome do cliente:");
                Validar.continuar(nome);
                //DEFININDO CPF
                String cpf = JPane.input.STRING("Inserir", "Digite o CPF do cliente:");
                Validar.continuar(cpf);
                //DEFININDO Telefone
                String telefone = JPane.input.STRING("Inserir", "Digite o telefone do cliente:");
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
                cliente.setCpf(cpf);
                cliente.setEndereco(enderecos.get(selectOptions.getIndice()));
                cliente.setNome(nome);
                cliente.setTelefone(telefone);
                clienteDao.alterar(cliente);
                listarNaTabela();
            } catch (Exception e) {

            }
        } else {
            JPane.show.STRING("AVISO!", "Para editar, escolha uma linha!");
        }
    }

    public void gerarRelatorio() {
        JasperReport jReport = null;
        try {
            jReport = JasperCompileManager.compileReport("Reporter.jrxml");
        } catch (JRException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        JasperPrint jPrint = null;
        try {
            jPrint = JasperFillManager.fillReport(jReport, null, Conexao.getConexao());
        } catch (JRException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        JasperViewer.viewReport(jPrint, false);
    }

}
