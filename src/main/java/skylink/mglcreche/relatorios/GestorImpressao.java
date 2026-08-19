package skylink.mglcreche.relatorios;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import skylink.mglcreche.bdutil.ConnectionDB;

@ApplicationScoped
public class GestorImpressao {

    FacesContext facesContext;
    JasperPrint jasperPrint;
    Connection conn;

    public String imprimirPDF(String relatorio, HashMap paramentos) {
        try {

            prepararRelatorio(relatorio, paramentos);

            HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
            /*
             response.setContentType("application/pdf");
   response.addHeader("Content-Disposition", "attachment;filename=recibo.pdf");
            response.addHeader("Content-Disposition", "inline;filename=recibo.pdf");
             */

            try (ServletOutputStream outputStream = response.getOutputStream()) {
                //Esta instrução visualiza o relatório
                JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
                //Esta manda o relatório direitamente para a impressora
                JasperPrintManager.printReport(jasperPrint, true);

                outputStream.flush();

            }
        } catch (JRException | IOException ex) {
            System.out.println("Erro - >>>>" + ex.getMessage());

        }
        FacesContext.getCurrentInstance().responseComplete();
        FacesContext.getCurrentInstance().responseComplete();

        return null;
    }

    public String visualizarPDF(String relatorio, HashMap paramentos) {
        try {

            prepararRelatorio(relatorio, paramentos);

            HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();

            try (ServletOutputStream outputStream = response.getOutputStream()) {
                //Esta instrução visualiza o relatório
                JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

                outputStream.flush();
                return "";
            }
        } catch (JRException | IOException ex) {
            System.out.println("Erro - >>>>" + ex.getMessage());

        }
        FacesContext.getCurrentInstance().responseComplete();
        FacesContext.getCurrentInstance().responseComplete();

        return "";
    }

    public String downloadPDF(String relatorio, HashMap paramentos, Integer numeroDocumento) {
        try {

            prepararRelatorio(relatorio, paramentos);

            HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();

            response.setContentType("application/pdf");
            response.addHeader("Content-Disposition", "attachment;filename=recibo_N" + numeroDocumento + ".pdf");
            /*
            response.addHeader("Content-Disposition", "inline;filename=recibo.pdf");
             */

            try (ServletOutputStream outputStream = response.getOutputStream()) {
                //Esta instrução visualiza o relatório
                JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
                //Esta manda o relatório direitamente para a impressora
                JasperPrintManager.printReport(jasperPrint, true);

                outputStream.flush();

            }
        } catch (JRException | IOException ex) {
            System.out.println("Erro - >>>>" + ex.getMessage());

        }
        FacesContext.getCurrentInstance().responseComplete();
        FacesContext.getCurrentInstance().responseComplete();

        return null;
    }

    private void prepararRelatorio(String relatorio, HashMap hashMap) {
        conn = ConnectionDB.getConnection();
        try {
            facesContext = FacesContext.getCurrentInstance();
            facesContext.responseComplete();
            jasperPrint = JasperFillManager.fillReport(getPathPastaAplicacaoJSF() + relatorio, hashMap, conn);
        } catch (JRException jRException) {
            System.err.printf(" Relatorio nao localizado \n" + jRException.getMessage());
            System.err.printf(" Relatorio nao localizado \n" + jRException.getLocalizedMessage());
        }
    }

    private String getPathPastaAplicacaoJSF() {
        String separador = System.getProperty("file.separator");
        String pasta = separador + "WEB-INF" + separador + "relatorios" + separador;
        String raizAplicacao = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
        return raizAplicacao + pasta;
    }

}
