/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.dao.CategoriaDao;
import model.dao.ClienteDao;
import model.dao.EnderecoDao;
import model.vo.Categoria;
import model.vo.Endereco;
import util.JPane;
import util.SelectOptions;
import util.Validar;
import view.CriarCategoria;

public class CategoriaController {

    private CategoriaDao categoriaDao;
    private CriarCategoria categoriaTela;
    private JTable tableCategoria;

    public CategoriaController(JTable table) {
        this.tableCategoria = table;
    }

    List<Categoria> categorias = new ArrayList<>();

    public List<Categoria> listarCategoria() {
        categoriaDao = new CategoriaDao();
        categorias = categoriaDao.listar();
        return categorias;
    }

    public void listarCategoriaNaTabela() {
        DefaultTableModel model = (DefaultTableModel) tableCategoria.getModel();
        int qtd = model.getRowCount();
        for (int i = 0; qtd > i; i++) {
            model.removeRow(qtd - i - 1);
        }
        model = (DefaultTableModel) tableCategoria.getModel();
        for (Categoria categoria1 : listarCategoria()) {
            Object[] linha = {
                categoria1.getIdCategoria(),
                categoria1.getNome()
            };
            model.addRow(linha);
        }
    }

    public void adicionarCategoria() {
        try {
            Categoria categoria = new Categoria();
            CategoriaDao categoriaDao = new CategoriaDao();
            String nome = JPane.input.STRING("Insira", "Digite o nome da categoria");
            Validar.continuar(nome);
            categoria.setNome(nome);
            categoriaDao.cadastrar(categoria);
            listarCategoriaNaTabela();
        } catch (Exception e) {
            JPane.show.STRING("AVISO", "Por favor digite corretamente todos os dados!");
        }
    }

    public void fechar() {
        categoriaTela.dispose();
    }

    public void editarCategoria() {
        try {
            if (tableCategoria.getSelectedRow() >= 0) {
                Categoria categoria = this.categorias.get(tableCategoria.getSelectedRow());
                CategoriaDao categoriaDao = new CategoriaDao();
                List<Categoria> categorias = categoriaDao.listar();
                try {
                    String nome = JPane.input.STRING("Insira", "Digite o nome da categoria");
                    Validar.continuar(nome);
                    categoria.setNome(nome);
                    categoriaDao.alterar(categoria);
                    listarCategoriaNaTabela();
                } catch (Exception e) {

                }
            } else {
                JPane.show.STRING("AVISO!", "Para editar, escolha uma linha!");
            }
        } catch (Exception e) {
        }
    }

    public void excluirCategoria() {
        try {
            if (tableCategoria.getSelectedRow() >= 0) {
                Categoria categoria = this.categorias.get(tableCategoria.getSelectedRow());
                CategoriaDao categoriaDao = new CategoriaDao();
                List<Categoria> categorias = categoriaDao.listar();
                try {

                    categoria.setAtivo(0);
                    categoriaDao.alterar(categoria);
                    listarCategoriaNaTabela();
                } catch (Exception e) {

                }
            } else {
                JPane.show.STRING("AVISO!", "Para editar, escolha uma linha!");
            }
        } catch (Exception e) {
        }
    }
}
//        if (tableCategoria.getSelectedRow() >= 0) {
//            CategoriaDao categoriaDao = new CategoriaDao();
//            List<Categoria> categorias = categoriaDao.listar();
//            try {
//                categoriaDao.deletar(categorias.get(table.getSelectedRow()).getIdCategoria());
//                listarCategoriaNaTabela();
//            } catch (Exception e) {
//
//            }
//        } else {
//            JPane.show.STRING("AVISO!", "Para excluir, escolha uma linha!");
//        }
//
//    }
