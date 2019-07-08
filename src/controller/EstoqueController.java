package controller;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.dao.CategoriaDao;
import model.dao.EstoqueDao;
import model.dao.RoupaDao;
import model.vo.Categoria;
import model.vo.Estoque;
import model.vo.Roupa;
import util.JPane;
import util.SelectOptions;
import util.Validar;

public class EstoqueController {

    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnFechar;
    private javax.swing.JButton btnRemover;
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JTable tableEstoque;
    private view.Estoque estoque;

    public EstoqueController(view.Estoque estoque, JButton btnEditar, JButton btnFechar, JButton btnRemover, JButton btnAdicionar, JTable tableEstoque) {
        this.btnEditar = btnEditar;
        this.btnFechar = btnFechar;
        this.btnRemover = btnRemover;
        this.btnAdicionar = btnAdicionar;
        this.tableEstoque = tableEstoque;
        this.estoque = estoque;
    }

    private EstoqueDao estoqueDao;
    List<Estoque> estoques = new ArrayList<>();

    public List<Estoque> listarEstoques() {
        estoqueDao = new EstoqueDao();
        estoques = estoqueDao.listar();
        return estoqueDao.listar();

    }

    public void listarNaTabela() {
        DefaultTableModel model = (DefaultTableModel) tableEstoque.getModel();
        int qtd = model.getRowCount();
        for (int i = 0; qtd > i; i++) {
            model.removeRow(qtd - i - 1);
        }
        model = (DefaultTableModel) tableEstoque.getModel();
        for (model.vo.Estoque estoque : listarEstoques()) {
            Object[] linha = {
                estoque.getRoupa().getNome(),
                estoque.getRoupa().getVlr(),
                estoque.getRoupa().getCategoria().getNome(),
                estoque.getQuantidade()

            };
            model.addRow(linha);
        }
    }

    public void adicionarEstoque() {
        Estoque estoque = new Estoque();
        RoupaDao roupaDao = new RoupaDao();
        List<Roupa> roupas = roupaDao.listar();
        //DEFININDO UMA ROUPA
        SelectOptions selectOptions = new SelectOptions();
        for (Roupa roupa : roupas) {
            selectOptions.adicionar(roupa.getNome());
        }
        selectOptions.setTitulo("Selecione uma Roupa");
        selectOptions.instanciar(selectOptions);
        selectOptions.getTitulo();
        Validar.continuar(selectOptions.getRetorno());
        //DEFININDO quantidade
        int qtd = JPane.input.INT("Inserir", "Digite a quantidade que deseja adicionar:");
        Validar.continuar(qtd);
        //SETANDO E CADASTRANDO
        estoque.setRoupa(roupas.get(selectOptions.getIndice()));
        estoque.setQuantidade(qtd);
        estoqueDao.cadastrar(estoque);
        listarNaTabela();

    }

    public void removerEstoque() {
        if (tableEstoque.getSelectedRow() >= 0) {
            DefaultTableModel model = (DefaultTableModel) tableEstoque.getModel();
            int linhaSelecionada = tableEstoque.getSelectedRow();
            //DEFININDO quantidade
            int qtd = JPane.input.INT("Inserir", "Digite a quantidade que deseja remover:");
            Validar.continuar(qtd);
            //SETANDO E CADASTRANDO
            estoques.get(linhaSelecionada).setQuantidade(-qtd);
            estoqueDao = new EstoqueDao();
            estoqueDao.cadastrar(estoques.get(linhaSelecionada));
            listarNaTabela();
        } else {
            JPane.show.STRING("AVISO!", "Para remover, escolha uma linha!");
        }
    }

    public void fechar() {
        estoque.dispose();
    }

    public void editarEstoque() {
        if (tableEstoque.getSelectedRow() >= 0) {
            Estoque estoque = this.estoques.get(tableEstoque.getSelectedRow());
            try {
                DefaultTableModel model = (DefaultTableModel) tableEstoque.getModel();
                int linhaSelecionada = tableEstoque.getSelectedRow();
                RoupaDao roupaDao = new RoupaDao();
                List<Roupa> roupas = roupaDao.listar();
                //DEFININDO UMA ROUPA
                SelectOptions selectOptions = new SelectOptions();
                for (Roupa roupa : roupas) {
                    selectOptions.adicionar(roupa.getNome());
                }
                selectOptions.setTitulo("Selecione uma Roupa");
                selectOptions.instanciar(selectOptions);
                selectOptions.getTitulo();
                Validar.continuar(selectOptions.getRetorno());
                //DEFININDO quantidade
                int qtd = JPane.input.INT("Inserir", "Digite a quantidade que deseja definir:");
                Validar.continuar(qtd);
                //SETANDO E CADASTRANDO
                estoque.setRoupa(roupas.get(selectOptions.getIndice()));
                estoque.setQuantidade(qtd-estoques.get(linhaSelecionada).getQuantidade());
                estoque.setIdEstoque(estoques.get(linhaSelecionada).getIdEstoque());
                estoqueDao.cadastrar(estoque);
                listarNaTabela();
            } catch (Exception e) {

            }
        } else {
            JPane.show.STRING("AVISO!", "Para editar, escolha uma linha!");
        }
    }
}
