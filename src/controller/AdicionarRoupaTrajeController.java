package controller;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.table.DefaultTableModel;
import model.dao.CategoriaDao;
import model.dao.RoupaDao;
import model.dao.TrajeDao;
import model.vo.Categoria;
import model.vo.Roupa;
import model.vo.RoupaTraje;
import model.vo.Traje;
import util.JPane;
import util.SelectOptions;
import util.Validar;
import view.RoupaTrajeAdd;

public class AdicionarRoupaTrajeController {

    private javax.swing.JToggleButton btnAdicionarTraje;
    private javax.swing.JButton btnCriarTraje;
    private javax.swing.JButton btnAdicionarRoupa;
    private javax.swing.JButton btnFechar;
    private javax.swing.JButton btnRemoverRoupa;
    private javax.swing.JTextField jtfDesconto;
    private javax.swing.JTextField jtfNomeTraje;
    private javax.swing.JTable tableAdicionarRoupaTraje;
    private RoupaTrajeAdd roupaTrajeAdd;
    private List<RoupaTraje> roupaTrajes = new ArrayList<>();
    private Traje traje;
    private List<Roupa> roupasAdicionadas = new ArrayList<>();

    public AdicionarRoupaTrajeController() {
    }

    public AdicionarRoupaTrajeController(RoupaTrajeAdd roupaTrajeAdd, JToggleButton btnAdicionarTraje, JButton btnCriarTraje, JButton btnAdicionarRoupa, JButton btnFechar, JButton btnRemoverRoupa, JTextField jtfDesconto, JTextField jtfNomeTraje, JTable tableAdicionarRoupaTraje, Traje traje) {
        this.btnAdicionarTraje = btnAdicionarTraje;
        this.btnCriarTraje = btnCriarTraje;
        this.btnAdicionarRoupa = btnAdicionarRoupa;
        this.btnFechar = btnFechar;
        this.btnRemoverRoupa = btnRemoverRoupa;
        this.jtfDesconto = jtfDesconto;
        this.jtfNomeTraje = jtfNomeTraje;
        this.tableAdicionarRoupaTraje = tableAdicionarRoupaTraje;
        this.roupaTrajeAdd = roupaTrajeAdd;
        this.traje = traje;
    }

    private RoupaDao roupaDao;
    List<Roupa> roupas = new ArrayList<>();

    public List<Roupa> listarRoupas() {
        roupaDao = new RoupaDao();
        roupas = roupaDao.listar();
        return roupaDao.listar();

    }

    public void listarNaTabela() {
        DefaultTableModel model = (DefaultTableModel) tableAdicionarRoupaTraje.getModel();
        int qtd = model.getRowCount();
        for (int i = 0; qtd > i; i++) {
            model.removeRow(qtd - i - 1);
        }
        model = (DefaultTableModel) tableAdicionarRoupaTraje.getModel();
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
            if (traje != null) {
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
                listarNaTabela();
            } else {
                JPane.show.STRING("Aviso!", "Primeiro crie o traje");
            }
        } catch (Exception e) {
        }
    }

    public void AdicionarRoupaNaTable(Roupa roupa) {
        try {
            DefaultTableModel model = (DefaultTableModel) tableAdicionarRoupaTraje.getModel();
            Object[] linhas = {roupa.getIdRoupa(), roupa.getNome(), roupa.getVlr(), roupa.getCategoria().getNome()};
            model.addRow(linhas);
        } catch (Exception e) {
        }
    }

    public void removerRoupa() {
        try {
            if (traje != null) {
                if (tableAdicionarRoupaTraje.getSelectedRow() >= 0) {
                    DefaultTableModel model = (DefaultTableModel) tableAdicionarRoupaTraje.getModel();
                    int linhaSelecionada = tableAdicionarRoupaTraje.getSelectedRow();
                    roupasAdicionadas.remove(linhaSelecionada);
                    listarNaTabela();
                } else {
                    JPane.show.STRING("AVISO!", "Para excluir, escolha uma linha!");
                }
            } else {
                JPane.show.STRING("Aviso!", "Primeiro crie o traje");
            }
        } catch (Exception e) {
        }
    }

    public void fechar() {
        roupaTrajeAdd.dispose();
    }

    public void editarRoupa() {
        if (tableAdicionarRoupaTraje.getSelectedRow() >= 0) {
            Roupa roupa = this.roupas.get(tableAdicionarRoupaTraje.getSelectedRow());
            CategoriaDao categoriaDao = new CategoriaDao();
            List<Categoria> categorias = categoriaDao.listar();
            try {
                //DEFININDO NOME
                String nome = JPane.input.STRING("Alterar", "Digite o nome da roupa:");
                Validar.continuar(nome);
                //DEFININDO VALOR
                double vlr = JPane.input.DOUBLE("Alterar", "Digite o preço da roupa:");
                Validar.continuar(vlr);
                //DEFININDO CATEGORIA
                SelectOptions selectOptions = new SelectOptions();
                for (Categoria categoria : categorias) {
                    selectOptions.adicionar(categoria.getNome());
                }
                selectOptions.setTitulo("Selecione uma Categoria");
                selectOptions.instanciar(selectOptions);
                Validar.continuar(selectOptions.getRetorno());
                //SETANDO E CADASTRANDO
                roupa.setCategoria(categorias.get(selectOptions.getIndice()));
                roupa.setNome(nome);
                roupa.setVlr(vlr);
                roupaDao.alterar(roupa);
                listarNaTabela();
            } catch (Exception e) {

            }
        } else {
            JPane.show.STRING("AVISO!", "Para editar, escolha uma linha!");
        }
    }

    public void criarTraje() {
        try {
            Validar.continuar(jtfNomeTraje.getText());
            Validar.INT(jtfDesconto.getText());
            traje = new Traje();
            traje.setNome(jtfNomeTraje.getText());
            traje.setDesconto(Integer.parseInt(jtfDesconto.getText()));
            btnCriarTraje.setEnabled(false);
        } catch (Exception e) {
            JPane.show.STRING("Aviso", "Preencha todos os campos corretamente!");
        }
    }

    public void cadastrar() {
        try {
            TrajeDao trajeDao = new TrajeDao();
            if (traje.getIdTraje() == 0) {
                traje.setIdTraje(trajeDao.cadastrar(traje));
            } else {
                Validar.INT(jtfDesconto.getText());
                Validar.continuar(jtfNomeTraje.getText());
                traje.setNome(jtfNomeTraje.getText());
                traje.setDesconto(Integer.parseInt(jtfDesconto.getText()));
                trajeDao.alterar(traje);
                trajeDao.limparRelacionamento(traje);
            }

            for (Roupa roupa : roupasAdicionadas) {
                trajeDao.cadastrarRoupaNoTraje(roupa, traje);
            }
            listarNaTabela();
            roupaTrajeAdd.dispose();
        } catch (Exception e) {
            JPane.show.STRING("AVISO!", "Preencha todos os campos corretamente!");
        }
    }

    public void lerTraje() {
        try {
            if (traje != null) {
                jtfDesconto.setText(String.valueOf(traje.getDesconto()));
                jtfNomeTraje.setText(String.valueOf(traje.getNome()));
                btnCriarTraje.setEnabled(false);
                TrajeDao trajeDao = new TrajeDao();
                for (Roupa roupa : trajeDao.listarRoupas(traje)) {
                    roupasAdicionadas.add(roupa);
                    AdicionarRoupaNaTable(roupa);
                }
            }
        } catch (Exception e) {
        }
    }
}
