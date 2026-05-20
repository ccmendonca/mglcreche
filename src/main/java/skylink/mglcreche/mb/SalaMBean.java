package skylink.mglcreche.mb;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import skylink.mglcreche.dao.SalaDAO;
import skylink.mglcreche.modelo.Sala;

/**
 *
 * @author Henriques
 */
@Named(value = "salaMBean")
@ViewScoped
public class SalaMBean implements Serializable {

    private List<Sala> salas;
    private SalaDAO salaDAO = new SalaDAO();
    
    public SalaMBean() {
    }

    @PostConstruct
    public void inicializar() {
        
        salas = salaDAO.findAll();
    }
    public List<Sala> getSalas() {
        return salas;
    }

    public void setSalas(List<Sala> salas) {
        this.salas = salas;
    }

    public SalaDAO getSalaDAO() {
        return salaDAO;
    }

    public void setSalaDAO(SalaDAO salaDAO) {
        this.salaDAO = salaDAO;
    }
}