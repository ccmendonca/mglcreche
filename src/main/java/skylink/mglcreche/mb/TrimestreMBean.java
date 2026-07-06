package skylink.mglcreche.mb;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import skylink.mglcreche.dao.TrimestreDAO;
import skylink.mglcreche.modelo.Trimestre;

/**
 *
 * @Henriques
 */
@Named(value = "trimestreMBean")
@SessionScoped
public class TrimestreMBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private TrimestreDAO trimestreDAO = new TrimestreDAO();
    private List<Trimestre> trimestres = new ArrayList<>();
    private Trimestre trimestre = new Trimestre();

    @Inject
    FacesContext facesContext;

    @PostConstruct
    public void inicializar() {
        trimestres = trimestreDAO.findAll();
    }

    public TrimestreMBean() {
    }

    public TrimestreDAO getTrimestreDAO() {
        return trimestreDAO;
    }

    public void setTrimestreDAO(TrimestreDAO trimestreDAO) {
        this.trimestreDAO = trimestreDAO;
    }

    public List<Trimestre> getTrimestres() {
        return trimestres;
    }

    public void setTrimestres(List<Trimestre> trimestres) {
        this.trimestres = trimestres;
    }

    public Trimestre getTrimestre() {
        return trimestre;
    }

    public void setTrimestre(Trimestre trimestre) {
        this.trimestre = trimestre;
    }

    public void save() {
        if (trimestreDAO.save(trimestre)) {
            FacesMessage info = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Trimestre registado com sucesso!");
            facesContext.addMessage(null, info);
            
            trimestres = trimestreDAO.findAll();
            trimestre = new Trimestre();
        } else {
            FacesMessage info = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Erro ao registar");
            facesContext.addMessage(null, info);
        }
    }
}