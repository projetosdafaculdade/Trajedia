package util;

import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showInputDialog;

public class JPane {

    public static class show {

        public static void STRING(String titulo, String mensagem) {
            JOptionPane.showMessageDialog(null, mensagem, titulo, JOptionPane.DEFAULT_OPTION);
        }
    }

    public static class input {

        private static String gerarJOptionPane(String titulo, String mensagem) {
            return showInputDialog(null, mensagem, titulo, JOptionPane.DEFAULT_OPTION);
        }

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

        public static Double DOUBLE(String titulo, String mensagem) {
            String var = gerarJOptionPane(titulo, mensagem);
            if (var != null) {
                while (!Validar.DOUBLE(var)) {
                    var = gerarJOptionPane(titulo, "Valor inválido. " + mensagem);
                    if (var == null) {
                        break;
                    }
                }
                return Double.parseDouble(var.replaceAll(",", "."));
            } else {
                return null;
            }
        }

        public static Integer INT(String titulo, String mensagem) {
            String var = gerarJOptionPane(titulo, mensagem);
            if (var != null) {
                while (!Validar.INT(var)) {
                    var = gerarJOptionPane(titulo, "Valor inválido. " + mensagem);
                    if (var == null) {
                        break;
                    }
                }
                return Integer.parseInt(var.replaceAll(",", ""));
            } else {
                return null;
            }
        }

        public static int String(String inserir, String digite_o_CPF_do_cliente) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

}
