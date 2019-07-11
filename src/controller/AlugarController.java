package controller;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.table.DefaultTableModel;
import model.dao.CategoriaDao;
import model.dao.ClienteDao;
import model.dao.FuncionarioDao;
import model.dao.LocacaoDao;
import model.dao.RoupaDao;
import model.dao.TrajeDao;
import model.vo.Categoria;
import model.vo.Cliente;
import model.vo.Locacao;
import model.vo.Roupa;
import model.vo.RoupaTraje;
import model.vo.Traje;
import util.JPane;
import util.SelectOptions;
import util.Validar;
import view.Alugar;
import view.Inicial;
import view.RoupaTrajeAdd;

public class AlugarController {

    private javax.swing.JButton btnAdicionarRoupa;
    private javax.swing.JButton btnAdicionarTraje;
    private javax.swing.JButton btnAlugar;
    private javax.swing.JButton btnRemoverRoupa;
    private javax.swing.JButton btnRemoverTraje;
    private javax.swing.JButton btnSelecionarCliente;
    private javax.swing.JTextField jtfCliente;
    private javax.swing.JTextField jtfDesconto;
    private javax.swing.JLabel lblValorTotal;
    private javax.swing.JTable tableRoupas;
    private javax.swing.JTable tableTrajes;
    private Alugar alugar;

    private Traje traje;
    private List<Roupa> roupasAdicionadas = new ArrayList<>();
    private List<Traje> trajesAdicionadas = new ArrayList<>();

    public AlugarController(Alugar alugar, JButton btnAdicionarRoupa, JButton btnAdicionarTraje, JButton btnAlugar, JButton btnRemoverRoupa, JButton btnSelecionarCliente, JTextField jtfCliente, JTextField jtfDesconto, JLabel lblValorTotal, JTable tableRoupas, JTable tableTrajes, Traje traje) {
        this.alugar = alugar;
        this.btnAdicionarRoupa = btnAdicionarRoupa;
        this.btnAdicionarTraje = btnAdicionarTraje;
        this.btnAlugar = btnAlugar;
        this.btnRemoverRoupa = btnRemoverRoupa;
        this.btnSelecionarCliente = btnSelecionarCliente;
        this.jtfCliente = jtfCliente;
        this.jtfDesconto = jtfDesconto;
        this.lblValorTotal = lblValorTotal;
        this.tableRoupas = tableRoupas;
        this.tableTrajes = tableTrajes;
        this.traje = traje;
    }

    private RoupaDao roupaDao;
    private TrajeDao trajeDao;
    private Cliente cliente;

    public List<Roupa> listarRoupas() {
        roupaDao = new RoupaDao();
        roupasAdicionadas = roupaDao.listar();
        return roupaDao.listar();

    }

    public void listarNaTabelaRoupa() {
        DefaultTableModel model = (DefaultTableModel) tableRoupas.getModel();
        int qtd = model.getRowCount();
        for (int i = 0; qtd > i; i++) {
            model.removeRow(qtd - i - 1);
        }
        model = (DefaultTableModel) tableRoupas.getModel();
        for (model.vo.Roupa roupa : roupasAdicionadas) {
            Object[] linha = {
                roupa.getIdRoupa(),
                roupa.getNome(),
                roupa.getVlr(),
                roupa.getCategoria().getNome()
            };
            model.addRow(linha);
        }
    }

    public void adicionarRoupa() {
        try {
            roupaDao = new RoupaDao();
            List<Roupa> roupasListadas = roupaDao.listar();
            //DEFININDO UMA ROUPA
            SelectOptions selectOptions = new SelectOptions();
            for (Roupa roupa : roupasListadas) {
                selectOptions.adicionar(roupa.getNome());
            }
            selectOptions.setTitulo("Selecione uma Roupa");
            selectOptions.instanciar(selectOptions);
            selectOptions.getTitulo();
            Validar.continuar(selectOptions.getRetorno());
            //SETANDO E CADASTRANDO
            roupasAdicionadas.add(roupasListadas.get(selectOptions.getIndice()));
            listarNaTabelaRoupa();
        } catch (Exception e) {
        }
    }

    public void removerRoupa() {
        try {
            if (tableRoupas.getSelectedRow() >= 0) {
                roupasAdicionadas.remove(tableRoupas.getSelectedRow());
                listarNaTabelaRoupa();
            } else {
                JPane.show.STRING("AVISO!", "Para excluir, escolha uma linha!");
            }
        } catch (Exception e) {
        }
    }

    public void fechar() {
        alugar.dispose();
    }

    public List<Traje> listarTrajes() {
        trajeDao = new TrajeDao();
        trajesAdicionadas = trajeDao.listar();
        return trajeDao.listar();

    }

    public void listarNaTabelaTraje() {
        DefaultTableModel model = (DefaultTableModel) tableTrajes.getModel();
        int qtd = model.getRowCount();
        for (int i = 0; qtd > i; i++) {
            model.removeRow(qtd - i - 1);
        }
        model = (DefaultTableModel) tableTrajes.getModel();
        for (model.vo.Traje traje : trajesAdicionadas) {
            Object[] linha = {
                traje.getIdTraje(),
                traje.getNome(),
                traje.getDesconto(),
                traje.getValorTraje()
            };
            model.addRow(linha);
        }
    }

    public void adicionarTraje() {
        try {
            trajeDao = new TrajeDao();
            List<Traje> trajesListadas = trajeDao.listar();
            //DEFININDO UMA ROUPA
            SelectOptions selectOptions = new SelectOptions();
            for (Traje traje : trajesListadas) {
                selectOptions.adicionar(traje.getNome());
            }
            selectOptions.setTitulo("Selecione uma Traje");
            selectOptions.instanciar(selectOptions);
            selectOptions.getTitulo();
            Validar.continuar(selectOptions.getRetorno());
            //SETANDO E CADASTRANDO
            trajesAdicionadas.add(trajesListadas.get(selectOptions.getIndice()));
            listarNaTabelaTraje();
        } catch (Exception e) {
        }
    }

    public void removerTraje() {
        try {
            if (tableTrajes.getSelectedRow() >= 0) {
                trajesAdicionadas.remove(tableTrajes.getSelectedRow());
                listarNaTabelaTraje();
            } else {
                JPane.show.STRING("AVISO!", "Para excluir, escolha uma linha!");
            }
        } catch (Exception e) {
        }
    }

    public void escolherCliente() {
        try {
            ClienteDao clienteDao = new ClienteDao();
            //DEFININDO UMA ROUPA
            SelectOptions selectOptions = new SelectOptions();
            List<Cliente> clientes = clienteDao.listar();
            for (Cliente cliente : clientes) {
                selectOptions.adicionar(cliente.getNome());
            }
            selectOptions.setTitulo("Selecione uma Cliente");
            selectOptions.instanciar(selectOptions);
            selectOptions.getTitulo();
            Validar.continuar(selectOptions.getRetorno());
            cliente = clientes.get(selectOptions.getIndice());
            //SETANDO E CADASTRANDO 
            jtfCliente.setText(clientes.get(selectOptions.getIndice()).getIdCliente() + " - " + selectOptions.getRetorno());
        } catch (Exception e) {
        }
    }

    public void alugar() {
//        try {
            Locacao locacao = new Locacao();
            locacao.setTrajes(trajesAdicionadas);
            locacao.setCliente(cliente);
            FuncionarioDao funcionario = new FuncionarioDao();
            locacao.setFuncionario(funcionario.lerPorId(idFuncionario));
            System.out.println(funcionario.lerPorId(InicialController.idFuncionario).getSenha());
            System.out.println(funcionario.lerPorId(InicialController.idFuncionario).getIdFuncionario());
            System.out.println(funcionario.lerPorId(InicialController.idFuncionario).getUsuario());
//            LocacaoDao locacaoDao = new Lo
//            LocacaoDao locacaocacaoDao();
//            locacaoDao.cadastrar(locacao);
//        } catch (Exception e) {
//        }
    }

}
