package util;

import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showInputDialog;

public class JPane {

    public static class input {

        public static String STRING(String titulo, String mensagem) {
            String var = gerarJOptionPane(titulo, mensagem);
            if (var != null) {
                while (Validar.STRING(var) == false) {
                    var = gerarJOptionPane(titulo, "Valor inválido. " + mensagem);
                    if (var == null) {
                        break;
                    }
                }
                return var;
            } else {
                return null;
            }
        }

        private static String gerarJOptionPane(String titulo, String mensagem) {
            return showInputDialog(null, mensagem, titulo, JOptionPane.DEFAULT_OPTION);
        }

        public static Double DOUBLE(String titulo, String mensagem) {
            String var = gerarJOptionPane(titulo, mensagem);
            if (var != null) {
                while (var.replaceAll("[^0-9].", "").length() <= 0) {
                    var = gerarJOptionPane(titulo, "Valor inválido. " + mensagem);
                    if (var == null) {
                        break;
                    }
                }
            }
            return Double.parseDouble(var);
        }

    }

    public static class select {

        public static String gerarJOptionPane(String titulo, Object mensagem,Object[] obj) {
       String retorno = new String();
        JOptionPane.showOptionDialog(null, mensagem, titulo,0, 0, null, obj,obj[0]);
            return null;
        }
    };

}
