package controller;

import factory.Conexao;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class RelatorioController {

    public void cliente() {
        JasperReport jReport = null;
        try {
            jReport = JasperCompileManager.compileReport("Reporter.jrxml");
        } catch (JRException ex) {
            Logger.getLogger(RelatorioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        JasperPrint jPrint = null;
        try {
            jPrint = JasperFillManager.fillReport(jReport, null, Conexao.getConexao());
        } catch (JRException ex) {
            Logger.getLogger(RelatorioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        JasperViewer.viewReport(jPrint, false);
    }
}
