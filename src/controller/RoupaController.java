package controller;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.dao.CategoriaDao;
import model.dao.RoupaDao;
import model.vo.Categoria;
import model.vo.Roupa;
import util.JPane;
import util.SelectOptions;
import util.Validar;

public class RoupaController {

    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnFechar;
    private javax.swing.JButton btnRemover;
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JTable tableRoupa;
    private view.Roupa roupa;

    public RoupaController(view.Roupa roupa, JButton btnEditar, JButton btnFechar, JButton btnRemover, JButton btnAdicionar, JTable tableRoupa) {
        this.btnEditar = btnEditar;
        this.btnFechar = btnFechar;
        this.btnRemover = btnRemover;
        this.btnAdicionar = btnAdicionar;
        this.tableRoupa = tableRoupa;
        this.roupa = roupa;
    }

    private RoupaDao roupaDao;
    List<Roupa> roupas = new ArrayList<>();

    public List<Roupa> listarRoupas() {
        roupaDao = new RoupaDao();
        roupas = roupaDao.listar();
        return roupaDao.listar();

    }

    public void listarNaTabela() {
        DefaultTableModel model = (DefaultTableModel) tableRoupa.getModel();
        int qtd = model.getRowCount();
        for (int i = 0; qtd > i; i++) {
            model.removeRow(qtd - i - 1);
        }
        model = (DefaultTableModel) tableRoupa.getModel();
        for (model.vo.Roupa roupa : listarRoupas()) {
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
            Roupa roupa = new Roupa();
            CategoriaDao categoriaDao = new CategoriaDao();
            List<Categoria> categorias = categoriaDao.listar();

            //DEFININDO NOME
            String nome = JPane.input.STRING("Inserir", "Digite o nome da roupa:");
            Validar.continuar(nome);
            //DEFININDO VALOR
            double vlr = JPane.input.DOUBLE("Inserir", "Digite o preço da roupa:");
            Validar.continuar(vlr);
            //DEFININDO CATEGORIA
            SelectOptions selectOptions = new SelectOptions();
            for (Categoria categoria : categorias) {
                selectOptions.adicionar(categoria.getNome());
            }
            selectOptions.setTitulo("Selecione uma Categoria");
            selectOptions.instanciar(selectOptions);
            selectOptions.getTitulo();
            Validar.continuar(selectOptions.getRetorno());
            //SETANDO E CADASTRANDO
            roupa.setCategoria(categorias.get(selectOptions.getIndice()));
            roupa.setNome(nome);
            roupa.setVlr(vlr);
            roupaDao.cadastrar(roupa);
            listarNaTabela();

        } catch (Exception e) {
        }
    }

    public void removerRoupa() {
        try {
            if (tableRoupa.getSelectedRow() >= 0) {
                DefaultTableModel model = (DefaultTableModel) tableRoupa.getModel();
                int linhaSelecionada = tableRoupa.getSelectedRow();
                roupaDao = new RoupaDao();
                roupaDao.deletar(roupas.get(linhaSelecionada).getIdRoupa());
                JPane.show.STRING("Alerta!", roupas.get(linhaSelecionada).getNome() + " foi excluída!");
                listarNaTabela();
            } else {
                JPane.show.STRING("AVISO!", "Para excluir, escolha uma linha!");
            }

        } catch (Exception e) {
        }
    }

    public void fechar() {
        roupa.dispose();
    }

    public void editarRoupa() {
        if (tableRoupa.getSelectedRow() >= 0) {
            Roupa roupa = this.roupas.get(tableRoupa.getSelectedRow());
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
}
