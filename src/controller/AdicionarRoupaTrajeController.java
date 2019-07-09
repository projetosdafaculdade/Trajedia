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
import view.AdicionarRoupaTraje;

public class AdicionarRoupaTrajeController {

    private javax.swing.JToggleButton btnAdicionar;
    private javax.swing.JButton btnAdicionarRoupa;
    private javax.swing.JButton btnFechar;
    private javax.swing.JButton btnRemoverRoupa;
    private javax.swing.JTable tableAdicionarRoupaTraje;
    private javax.swing.JTextField jtfDesconto;
    private javax.swing.JTextField jtfNomeTraje;
    private view.AdicionarRoupaTraje adicionarRoupaTraje;
    private List<RoupaTraje> roupaTrajes = new ArrayList<>();
    private Traje traje;

    public AdicionarRoupaTrajeController() {
    }

    public AdicionarRoupaTrajeController(JToggleButton btnAdicionar, JButton btnAdicionarRoupa, JButton btnFechar, JButton btnRemoverRoupa, JTable tableAdicionarRoupaTraje, JTextField jtfDesconto, JTextField jtfNomeTraje, AdicionarRoupaTraje adicionarRoupaTraje) {
        this.btnAdicionarRoupa = btnAdicionarRoupa;
        this.btnFechar = btnFechar;
        this.btnRemoverRoupa = btnRemoverRoupa;
        this.tableAdicionarRoupaTraje = tableAdicionarRoupaTraje;
        this.jtfDesconto = jtfDesconto;
        this.jtfNomeTraje = jtfNomeTraje;
        this.adicionarRoupaTraje = adicionarRoupaTraje;
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
        if (traje != null) {
            RoupaTraje roupaTraje = new RoupaTraje();
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
            roupaTraje.setIdRoupa(roupas.get(selectOptions.getIndice()).getIdRoupa());
            listarNaTabela();
            listarNaTabela();
        } else {
            JPane.show.STRING("Aviso!", "Primeiro crie o traje");
        }

    }

    public void removerRoupa() {
        if (traje != null) {
            if (tableAdicionarRoupaTraje.getSelectedRow() >= 0) {
                DefaultTableModel model = (DefaultTableModel) tableAdicionarRoupaTraje.getModel();
                int linhaSelecionada = tableAdicionarRoupaTraje.getSelectedRow();
                roupaDao = new RoupaDao();
                roupaDao.deletar(roupas.get(linhaSelecionada).getIdRoupa());
                JPane.show.STRING("Alerta!", roupas.get(linhaSelecionada).getNome() + " foi excluída!");
                listarNaTabela();
            } else {
                JPane.show.STRING("AVISO!", "Para excluir, escolha uma linha!");
            }
        } else {
            JPane.show.STRING("Aviso!", "Primeiro crie o traje");
        }
    }

    public void fechar() {
        adicionarRoupaTraje.dispose();
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
        TrajeDao trajeDao = new TrajeDao();
        Traje trajeCriar = new Traje();
        trajeCriar.setNome(jtfNomeTraje.getText());
        trajeCriar.setDesconto(Integer.parseInt(jtfDesconto.getText()));
        trajeDao.cadastrar(trajeCriar);
        
    }
}
