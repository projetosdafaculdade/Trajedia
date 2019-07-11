package controller;

;

import javax.swing.JTextField;
import model.dao.FuncionarioDao;
import model.vo.Funcionario;
import util.JPane;
import view.Inicial;
import view.Login;



public class LoginController {

    private javax.swing.JTextField jtfSenha;
    private javax.swing.JTextField jtfUsuario;
    private Login login;

    public LoginController() {
    }

    public LoginController(Login login, JTextField jtfSenha, JTextField jtfUsuario) {
        this.jtfSenha = jtfSenha;
        this.jtfUsuario = jtfUsuario;
        this.login = login;
    }

    FuncionarioDao dao;

    public void logar() {
        dao = new FuncionarioDao();
        Funcionario funcionario = new Funcionario();
        funcionario.setSenha(jtfSenha.getText());
        funcionario.setUsuario(jtfUsuario.getText());
        Funcionario funcionarioDoBanco = dao.login(funcionario);
        if (funcionarioDoBanco.getSenha() != null
                && funcionarioDoBanco.getUsuario() != null) {
            Inicial inicial = new Inicial(funcionarioDoBanco.getIdFuncionario());
            inicial.setVisible(true);
            login.dispose();

        } else {
            JPane.show.STRING("AVISO!", "Login ou senha incorreto!");
        }
    }
}
