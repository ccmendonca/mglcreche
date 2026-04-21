
package skylink.mglcreche.mb;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import skylink.mglcreche.dao.MatriculaDAO;
import skylink.mglcreche.dao.TurmaDAO;
import skylink.mglcreche.modelo.Matricula;
import skylink.mglcreche.modelo.Turma;

/**
 * «claudiomendonca»
 */

@Named(value = "matriculaMBean")
@ViewScoped
public class MatriculaMBean implements Serializable {

    private Matricula matricula = new Matricula();
    private MatriculaDAO matriculaDAO = new MatriculaDAO();
    private List<Matricula> matriculas = new ArrayList<>();
    private Turma turma = new Turma();
    private TurmaDAO turmaDAO = new TurmaDAO();
    private List<Turma> turmas = new ArrayList<>();
    
    @Inject
    FacesContext facesContext;
    
    @PostConstruct
    public void inicializar(){
        
        turmas = turmaDAO.listar();
    }
    
    public void registarMatricula() {
        if (matriculaDAO.save(matricula)) {
            FacesMessage info = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Matrícula criada com sucesso!");
            facesContext.addMessage(null, info);
        } else {
            FacesMessage info = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro ao crir matrícula!");
            facesContext.addMessage(null, info);
        }

    }

    public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }

    public MatriculaDAO getMatriculaDAO() {
        return matriculaDAO;
    }

    public void setMatriculaDAO(MatriculaDAO matriculaDAO) {
        this.matriculaDAO = matriculaDAO;
    }

    public List<Matricula> getMatriculas() {
        return matriculas;
    }

    public void setMatriculas(List<Matricula> matriculas) {
        this.matriculas = matriculas;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public TurmaDAO getTurmaDAO() {
        return turmaDAO;
    }

    public void setTurmaDAO(TurmaDAO turmaDAO) {
        this.turmaDAO = turmaDAO;
    }

    public List<Turma> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }

    public FacesContext getFacesContext() {
        return facesContext;
    }

    public void setFacesContext(FacesContext facesContext) {
        this.facesContext = facesContext;
    }
}
