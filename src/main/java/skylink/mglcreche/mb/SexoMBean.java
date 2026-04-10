package skylink.mglcreche.mb;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import skylink.mglcreche.dao.SexoDAO;
import skylink.mglcreche.modelo.Sexo;


/**
 * @Henriques
 */
@Named(value = "sexoMBean")
@ViewScoped
public class SexoMBean implements Serializable {

    private Sexo sexo = new Sexo();
    private SexoDAO sexoDAO = new SexoDAO();
    private List<Sexo> sexos;
    
    @Inject
    FacesContext facesContext;

    @PostConstruct
    public void init() {
        listarSexos();
    }


    public void listarSexos() {
        sexos = sexoDAO.findAll();
    }
}