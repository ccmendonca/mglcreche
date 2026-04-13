package skylink.mglcreche.mb;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import skylink.mglcreche.dao.SexoDAO;
import skylink.mglcreche.modelo.Sexo;

@Named(value = "sexoMBean")
@ViewScoped
public class SexoMBean implements Serializable {

    private List<Sexo> sexos;
    private SexoDAO sexoDAO = new SexoDAO();

    @PostConstruct
    public void init() {
        sexos = sexoDAO.findAll();
    }

    public List<Sexo> getSexos() {
        return sexos;
    }

    public void setSexos(List<Sexo> sexos) {
        this.sexos = sexos;
    }
}