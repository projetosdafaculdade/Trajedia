package model.dao;

import factory.Dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class SistemaDao extends Dao {

    public Date obterDataSistema() {
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement("SELECT NOW() FROM DUAL");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                return new Date(rs.getDate(1).getTime());
            }
            return null;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
