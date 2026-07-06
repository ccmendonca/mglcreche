
package skylink.mglcreche.mb;

import jakarta.inject.Named;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import skylink.mglcreche.bdutil.ConnectionDB;

/**
 * «claudiomendonca»
 */

@ApplicationScoped
public class GestorImpressao {

  @Inject
  ConnectionDB connectionDB;
  FacesContext facesContext;
  JasperPrint jasperPrint;
  Connection conn;
  
  private String getPathPastaAplicacaoFaces(){
      String separador = System.getProperty("file.separator");
      String pasta = separador + "WEB-INF" + separador + "relatorios" + separador;
      String raizAplicacao = FacesContext.getCurrentInstance().getExternalContext().getRealPath(separador);
      return raizAplicacao+pasta;
  }
  
  private void prepararRelatorio(String relatorio, HashMap hashMap){
      
      conn = connectionDB.getConnection();
      try {
          facesContext = FacesContext.getCurrentInstance();
          facesContext.responseComplete();
          jasperPrint = JasperFillManager.fillReport(getPathPastaAplicacaoFaces() + relatorio, hashMap, conn);
      } catch (JRException jRException) {
          System.out.printf("Relatorio nao localizado" + jRException.getMessage());
      }
  }
  public String imprimirPDF(String relatorio, HashMap parametros){
      try {
          prepararRelatorio(relatorio, parametros);
          HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
          try 
              (ServletOutputStream outputStream = response.getOutputStream()){
              JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
              JasperPrintManager.printReport(jasperPrint, false);
              outputStream.flush();
          }
          } catch (JRException | IOException ex) {
              System.out.println("Erro - >>> " + ex.getMessage());
          }
      FacesContext.getCurrentInstance().responseComplete();
      FacesContext.getCurrentInstance().responseComplete();
      
      return null;

    }
}
