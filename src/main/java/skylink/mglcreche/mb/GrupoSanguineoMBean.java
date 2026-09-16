
package skylink.mglcreche.mb;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import skylink.mglcreche.dao.ClasseDAO;
import skylink.mglcreche.dao.GrupoSanguineoDAO;
import skylink.mglcreche.modelo.Classe;
import skylink.mglcreche.modelo.GrupoSanguineo;

@Named(value = "grupoSanguineoMBean")
@RequestScoped
public class GrupoSanguineoMBean {

    
    private GrupoSanguineoDAO grupoSanguineoDAO = new GrupoSanguineoDAO();
    private List<GrupoSanguineo> grupoSanguineos = new ArrayList<>();
    private GrupoSanguineo grupoSanguineo = new GrupoSanguineo();
     @Inject
    FacesContext facesContext;
    
    @PostConstruct
    public void inicializar(){
       
        grupoSanguineos = grupoSanguineoDAO.findAll();
    }

    public List<GrupoSanguineo> getGrupoSanguineos() {
        return grupoSanguineos;
    }

    public void setGrupoSanguineos(List<GrupoSanguineo> grupoSanguineos) {
        this.grupoSanguineos = grupoSanguineos;
    }

    public GrupoSanguineo getGrupoSanguineo() {
        return grupoSanguineo;
    }

    public void setGrupoSanguineo(GrupoSanguineo grupoSanguineo) {
        this.grupoSanguineo = grupoSanguineo;
    }
    
    
    
    
}
