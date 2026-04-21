
package skylink.mglcreche.mb;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import skylink.mglcreche.dao.ClasseDAO;
import skylink.mglcreche.modelo.Classe;

/**
 * «claudiomendonca»
 */

@Named(value = "classeMBean")
@SessionScoped
public class ClasseMBean implements Serializable {
    
    
    private ClasseDAO classeDAO = new ClasseDAO();
    private List<Classe> classes = new ArrayList<>();
    
    @PostConstruct
    public void inicializar(){
        
        classes = classeDAO.findAll();
    }
    public ClasseMBean() {
    }

    public ClasseDAO getClasseDAO() {
        return classeDAO;
    }

    public void setClasseDAO(ClasseDAO classeDAO) {
        this.classeDAO = classeDAO;
    }

    public List<Classe> getClasses() {
        return classes;
    }

    public void setClasses(List<Classe> classes) {
        this.classes = classes;
    }
    
    
}
