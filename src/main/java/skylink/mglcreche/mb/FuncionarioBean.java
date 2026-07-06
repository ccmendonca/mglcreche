
package skylink.mglcreche.mb;

import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.event.ActionEvent;
import jakarta.inject.Inject;
import java.util.HashMap;

/**
 * «claudiomendonca»
 */
@Named(value = "funcionarioBean")
@RequestScoped
public class FuncionarioBean {
    
    @Inject
    GestorImpressao gestorImpressao;
    
    public void imprimirListaGeralFuncionarios(ActionEvent event){
        String relatorio = "funcionario.jasper";
        HashMap parametros = new HashMap();
        //parametros.put("Título", "Gestão de Creche");
       /// parametros.put("Autor", "Skylink");
        gestorImpressao.imprimirPDF(relatorio, parametros);
    }
    
}
