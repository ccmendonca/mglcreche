/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package skylink.mglcreche.mb;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import skylink.mglcreche.dao.DisciplinaDAO;
import skylink.mglcreche.modelo.Disciplina;

/**
 *
 * @author root
 */
@Named(value = "disciplinaBean")
@RequestScoped
public class DisciplinaBean {
 
    private DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
    private List<Disciplina> disciplinas = new ArrayList<>();
    private Disciplina disciplina = new Disciplina();
     @Inject
    FacesContext facesContext;
    
    @PostConstruct
    public void inicializar(){
        
        disciplinas = disciplinaDAO.findAll();
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }
   

  
 
    
      public void save() {
        if (disciplinaDAO.save(disciplina)) {
            FacesMessage info = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Discisplina registada com sucesso!");
            facesContext.addMessage(null, info);
        } else {
            FacesMessage info = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Erro ao resgistar");
            facesContext.addMessage(null, info);
        }

    }
    
}
