package util;

import java.util.Date;
import model.dao.SistemaDao;

public class Obter {

 
    public static Date DataAtual() {
           SistemaDao sistemaDao = new SistemaDao();
        return sistemaDao.obterDataSistema();
    }
}
