package controller;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.table.DefaultTableModel;
import model.dao.CategoriaDao;
import model.dao.TrajeDao;
import model.vo.Categoria;
import model.vo.Traje;
import util.JPane;
import util.SelectOptions;
import util.Validar;
import view.AdicionarRoupaTraje;

public class TrajeController {

    private javax.swing.JToggleButton btnAdicionar;
    private javax.swing.JToggleButton btnEditar;
    private javax.swing.JButton btnFechar;
    private javax.swing.JToggleButton btnRemover;
    private javax.swing.JTable tableTraje;
    private view.Traje traje;

    public TrajeController(JToggleButton btnAdicionar, JToggleButton btnEditar, JButton btnFechar, JToggleButton btnRemover, JTable tableTraje, view.Traje traje) {
        this.btnAdicionar = btnAdicionar;
        this.btnEditar = btnEditar;
        this.btnFechar = btnFechar;
        this.btnRemover = btnRemover;
        this.tableTraje = tableTraje;
        this.traje = traje;
    }

    private TrajeDao trajeDao;
    List<Traje> trajes = new ArrayList<>();

    public List<Traje> listarTrajes() {
        trajeDao = new TrajeDao();
        trajes = trajeDao.listar();
        return trajeDao.listar();

    }

    public void listarNaTabela() {
        DefaultTableModel model = (DefaultTableModel) tableTraje.getModel();
        int qtd = model.getRowCount();
        for (int i = 0; qtd > i; i++) {
            model.removeRow(qtd - i - 1);
        }
        model = (DefaultTableModel) tableTraje.getModel();
        for (model.vo.Traje traje : listarTrajes()) {
            Object[] linha = {
                traje.getIdTraje(),
                traje.getNome(),
                traje.getDesconto()
            };
            model.addRow(linha);
        }
    }

    public void adicionarTraje() {
        AdicionarRoupaTraje adicionarRoupaTraje = new AdicionarRoupaTraje();
        adicionarRoupaTraje.setVisible(true);
        listarTrajes();
    }

    public void fechar() {
        traje.dispose();
    }

    public void editarTraje() {
        if (tableTraje.getSelectedRow() >= 0) {
            AdicionarRoupaTraje adicionarRoupaTraje = new AdicionarRoupaTraje();
            adicionarRoupaTraje.setVisible(true);
            listarTrajes();
        } else {
            JPane.show.STRING("AVISO!", "Para editar, escolha uma linha!");
        }
    }
}
