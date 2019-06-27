package util;

import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showInputDialog;

public class JPane {

    public static class input {

        public static String STRING(String titulo, String mensagem) {
            String var = gerarJOptionPane(titulo, mensagem);
            while (Validar.STRING(var) == false) {
                mensagem = "Valor inválido. " +mensagem;
                var = gerarJOptionPane(titulo, mensagem);
            }
            return var;
        }

        private static String gerarJOptionPane(String titulo, String mensagem) {
            return showInputDialog(null, mensagem, titulo, JOptionPane.DEFAULT_OPTION);
        }
    }
}
