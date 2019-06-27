package controller;

import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.dao.RoupaDao;
import model.vo.Roupa;
import util.JPane;

public class RoupaController {

    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnFechar;
    private javax.swing.JButton btnRemover;
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JTable tableRoupa;

    public RoupaController(JButton btnEditar, JButton btnFechar, JButton btnRemover, JButton btnAdicionar, JTable tableRoupa) {
        this.btnEditar = btnEditar;
        this.btnFechar = btnFechar;
        this.btnRemover = btnRemover;
        this.btnAdicionar = btnAdicionar;
        this.tableRoupa = tableRoupa;
    }

    private RoupaDao roupaDao;

    public RoupaController() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Roupa> listarRoupas() {
        roupaDao = new RoupaDao();
        return roupaDao.listar();
    }

    public void listarNaTabelaInicial() {
        for (model.vo.Roupa roupa : listarRoupas()) {
            DefaultTableModel model = (DefaultTableModel) tableRoupa.getModel();

            Object[] linha = {
                roupa.getIdCategoria(),
                roupa.getNome(),
                roupa.getVlr(),
                roupa.getIdCategoria()
            };
            model.addRow(linha);
        };
    }

    public void adicionarRoupa() {
        try {
            System.out.println(JPane.input.STRING("Inserir", "Digite o nome da roupa"));
            System.out.println(JPane.input.STRING("Inserir", "Apelido da roupa"));
            System.out.println(JPane.input.STRING("Inserir", "Qualquer outra coisa vai"));
        } catch (Exception e) {
        }

    }
}
