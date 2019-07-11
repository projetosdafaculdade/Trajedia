package controller;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.table.DefaultTableModel;
import model.dao.TrajeDao;
import model.vo.Traje;
import util.JPane;
import view.RoupaTrajeAdd;

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
                traje.getDesconto(),
                traje.getValorTraje()
            };
            model.addRow(linha);
        }
    }

    public void adicionarTraje() {
        RoupaTrajeAdd adicionarRoupaTraje = new RoupaTrajeAdd(null, true, null);
        adicionarRoupaTraje.setVisible(true);
        listarNaTabela();
    }

    public void fechar() {
        traje.dispose();
    }

    public void editarTraje() {
        if (tableTraje.getSelectedRow() >= 0) {
            RoupaTrajeAdd adicionarRoupaTraje = new RoupaTrajeAdd(null, true, trajes.get(tableTraje.getSelectedRow()));
            adicionarRoupaTraje.setVisible(true);
            listarNaTabela();
        } else {
            JPane.show.STRING("AVISO!", "Para editar, escolha uma linha!");
        }
    }
}
