package skylink.mglcreche.mb;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import skylink.mglcreche.dao.PropinaDAO;
import skylink.mglcreche.modelo.Propina;

@Named(value = "propinaMBean")
@ViewScoped
public class PropinaMBean implements Serializable {

    private Propina propina = new Propina();
    private PropinaDAO propinaDAO = new PropinaDAO();
    @Inject
    FacesContext facesContext;

    @PostConstruct
    public void init() {

    }

    public void registarPropina() {
        if (propinaDAO.save(propina)) {
            FacesMessage info = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Propina criada com sucesso!");
            facesContext.addMessage(null, info);
        } else {
            FacesMessage info = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro ao criada com sucesso!");
            facesContext.addMessage(null, info);
        }

    }

}
