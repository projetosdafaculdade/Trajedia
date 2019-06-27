package factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class Conexao {

    private final static String URL = "jdbc:mysql://localhost:3306/TRAJEDIA";
    private final static String USER = "root";
    private final static String PASS = "";
    private static Connection conexao;

    public static Connection getConexao() {
        if (conexao == null) {
            try {
                conexao = DriverManager.getConnection(URL, USER, PASS);
                System.out.println("Conectou...");
            } catch (SQLException ex) {
                System.out.println("DEU ERRO!");
                System.out.println(ex.getMessage());
            }
        }
        return conexao;
    }

}
