package controller;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.bo.EnderecoBO;
import model.dao.CategoriaDao;
import model.dao.EnderecoDao;
import model.dao.EnderecoDaoServer;
import model.dao.RoupaDao;
import model.vo.Categoria;
import model.vo.Endereco;
import model.vo.EnderecoServer;
import model.vo.Roupa;
import util.ConverterEstado;
import util.JPane;
import util.SelectOptions;
import util.Validar;

public class EnderecoController {

    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnFechar;
    private javax.swing.JButton btnRemover;
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JTable tableEndereco;
    private view.Endereco endereco;

    public EnderecoController(view.Endereco endereco, JButton btnEditar, JButton btnFechar, JButton btnRemover, JButton btnAdicionar, JTable tableEndereco) {
        this.btnEditar = btnEditar;
        this.btnFechar = btnFechar;
        this.btnRemover = btnRemover;
        this.btnAdicionar = btnAdicionar;
        this.tableEndereco = tableEndereco;
        this.endereco = endereco;
    }

    private EnderecoDao enderecoDao;
    List<Endereco> enderecos = new ArrayList<>();

    public List<Endereco> listarEnderecos() {
        enderecoDao = new EnderecoDao();
        enderecos = enderecoDao.listar();
        return enderecoDao.listar();

    }

    public void listarNaTabela() {
        DefaultTableModel model = (DefaultTableModel) tableEndereco.getModel();
        int qtd = model.getRowCount();
        for (int i = 0; qtd > i; i++) {
            model.removeRow(qtd - i - 1);
        }
        model = (DefaultTableModel) tableEndereco.getModel();
        for (model.vo.Endereco endereco : listarEnderecos()) {
            Object[] linha = {
                endereco.getIdEndereco(),
                endereco.getPais(),
                endereco.getUf(),
                endereco.getEstado(),
                endereco.getCidade(),
                endereco.getBairro(),
                endereco.getRua(),
                endereco.getNumero()
            };
            model.addRow(linha);
        }
    }

    public void adicionarEndereco() {
        Endereco endereco = new Endereco();
        enderecoDao = new EnderecoDao();
        SelectOptions options = new SelectOptions();
        options.setTitulo("Como deseja adicionar o endereço:");
        options.adicionar("CEP (Automático)");
        options.adicionar("Manualmente");
        options.instanciar(options);
        options.getTitulo();
        if (options.getRetorno().equals("Manualmente")) {
            //DEFININDO PAIS
            String pais = JPane.input.STRING("Inserir", "Digite o país:");
            Validar.continuar(pais);
            //DEFININDO ESTADO
            String estado = JPane.input.STRING("Inserir", "Digite o estado:");
            Validar.continuar(estado);
            //DEFININDO UF
            String uf = JPane.input.STRING("Inserir", "Digite o UF:");
            Validar.continuar(uf);
            //DEFININDO CIDADE
            String cidade = JPane.input.STRING("Inserir", "Digite a cidade:");
            Validar.continuar(cidade);
            //DEFININDO BAIRRO
            String bairro = JPane.input.STRING("Inserir", "Digite o bairro:");
            Validar.continuar(bairro);
            //DEFININDO RUA
            String rua = JPane.input.STRING("Inserir", "Digite a rua:");
            Validar.continuar(rua);
            //DEFININDO RUA
            String numero = JPane.input.STRING("Inserir", "Digite o número:");
            Validar.continuar(numero);
            //SETANDO E CADASTRANDO
            endereco.setPais(pais);
            endereco.setUf(uf);
            endereco.setEstado(estado);
            endereco.setCidade(cidade);
            endereco.setBairro(bairro);
            endereco.setRua(rua);
            endereco.setNumero(numero);
            enderecoDao.cadastrar(endereco);
            listarNaTabela();
        } else {
            //DEFININDO CEP
            EnderecoServer e = null;
            EnderecoServer enderecoServer = null;
            EnderecoBO enderecoBO = null;
            while (e == null || e.getLocalidade() == null) {
                String cep = JPane.input.STRING("Inserir", "Digite o cep:");
                Validar.continuar(cep);
                enderecoServer = new EnderecoServer();
                enderecoServer.setCep(cep);
                enderecoBO = new EnderecoBO(enderecoServer);
                cep = enderecoBO.removeHifenCep();
                e = EnderecoDaoServer.getEndereco(cep); //Resgata o endereço do servidor
                if(e == null || e.getLocalidade() == null){
                    JPane.show.STRING("AVISO!", "Erro, CEP inválido!");
                }
            }
            if (enderecoBO.validarTamanhoCep()) {
                endereco.setBairro(e.getBairro());
                endereco.setCidade(e.getLocalidade());
                endereco.setNumero(e.getComplemento());
                endereco.setPais(ConverterEstado.receberPais(e.getUf()));
                endereco.setUf(e.getUf());
                endereco.setEstado(ConverterEstado.porUF(e.getUf()));
                endereco.setRua(e.getLogradouro());
                //DEFININDO NÚMERO
                String numero = JPane.input.STRING("Inserir", "Digite o número:");
                Validar.continuar(numero);
                endereco.setNumero(numero);
                enderecoDao.cadastrar(endereco);
                listarNaTabela();
            }
        }
    }

    public void removerEndereco() {
        if (tableEndereco.getSelectedRow() >= 0) {
            DefaultTableModel model = (DefaultTableModel) tableEndereco.getModel();
            int linhaSelecionada = tableEndereco.getSelectedRow();
            enderecoDao = new EnderecoDao();
            enderecoDao.deletar(enderecos.get(linhaSelecionada).getIdEndereco());
            JPane.show.STRING("Alerta!", "Endereço com ID:" + enderecos.get(linhaSelecionada).getIdEndereco() + " foi excluída!");
            listarNaTabela();
        } else {
            JPane.show.STRING("AVISO!", "Para excluir, escolha uma linha!");
        }
    }

    public void fechar() {
        endereco.dispose();
    }

    public void editarEndereco() {
        if (tableEndereco.getSelectedRow() >= 0) {
            CategoriaDao categoriaDao = new CategoriaDao();
            List<Categoria> categorias = categoriaDao.listar();
            try {
                Endereco endereco = new Endereco();
                enderecoDao = new EnderecoDao();
                SelectOptions options = new SelectOptions();
                options.setTitulo("Como deseja adicionar o endereço:");
                options.adicionar("CEP (Automático)");
                options.adicionar("Manualmente");
                options.instanciar(options);
                options.getTitulo();
                if (options.getRetorno().equals("Manualmente")) {
                    //DEFININDO PAIS
                    String pais = JPane.input.STRING("Inserir", "Digite o país:");
                    Validar.continuar(pais);
                    //DEFININDO ESTADO
                    String estado = JPane.input.STRING("Inserir", "Digite o estado:");
                    Validar.continuar(estado);
                    //DEFININDO UF
                    String uf = JPane.input.STRING("Inserir", "Digite o UF:");
                    Validar.continuar(uf);
                    //DEFININDO CIDADE
                    String cidade = JPane.input.STRING("Inserir", "Digite a cidade:");
                    Validar.continuar(cidade);
                    //DEFININDO BAIRRO
                    String bairro = JPane.input.STRING("Inserir", "Digite o bairro:");
                    Validar.continuar(bairro);
                    //DEFININDO RUA
                    String rua = JPane.input.STRING("Inserir", "Digite a rua:");
                    Validar.continuar(rua);
                    //DEFININDO RUA
                    String numero = JPane.input.STRING("Inserir", "Digite o número:");
                    Validar.continuar(numero);
                    //SETANDO E CADASTRANDO
                    endereco.setIdEndereco(enderecos.get(tableEndereco.getSelectedRow()).getIdEndereco());
                    endereco.setPais(pais);
                    endereco.setUf(uf);
                    endereco.setEstado(estado);
                    endereco.setCidade(cidade);
                    endereco.setBairro(bairro);
                    endereco.setRua(rua);
                    endereco.setNumero(numero);
                    enderecoDao.alterar(endereco);
                    listarNaTabela();
                } else {
                    System.out.println("INFORME O CEP");
                }
            } catch (Exception e) {

            }
        } else {
            JPane.show.STRING("AVISO!", "Para editar, escolha uma linha!");
        }
    }
}
