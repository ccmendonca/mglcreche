/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package skylink.mglcreche.mb;

import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import skylink.mglcreche.dao.AvaliacaoDAO;
import skylink.mglcreche.modelo.Aluno;
import skylink.mglcreche.modelo.Avaliacao;
import skylink.mglcreche.modelo.Disciplina;
import skylink.mglcreche.modelo.Profissional;
import skylink.mglcreche.modelo.TipoAvaliacao;
import skylink.mglcreche.modelo.Trimestre;
import skylink.mglcreche.modelo.Turma;


@Named(value = "avaliacaoBean")
@SessionScoped
public class AvaliacaoBean implements Serializable {
      private static final Logger LOGGER = Logger.getLogger(AvaliacaoBean.class.getName());
    @Inject
    FacesContext facesContext;

    private Disciplina  disciplina = new Disciplina();
    private Trimestre trimestre = new Trimestre();
    private Profissional profissional = new Profissional();
    private TipoAvaliacao tipoAvaliacao = new TipoAvaliacao();
    private Turma turma = new Turma();
    private Aluno aluno = new Aluno();
    private Avaliacao avaliacao = new Avaliacao();
    private AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public Trimestre getTrimestre() {
        return trimestre;
    }

    public void setTrimestre(Trimestre trimestre) {
        this.trimestre = trimestre;
    }

    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }

    public TipoAvaliacao getTipoAvaliacao() {
        return tipoAvaliacao;
    }

    public void setTipoAvaliacao(TipoAvaliacao tipoAvaliacao) {
        this.tipoAvaliacao = tipoAvaliacao;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Avaliacao getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
    }
    
    
    
    
     public String save() {
        LOGGER.log(Level.INFO, "saving tipo avalicao@{0}", tipoAvaliacao);
        if (this.avaliacao.getIdAvaliacao()== null) {
            avaliacaoDAO.save(avaliacao);
            tipoAvaliacao = new TipoAvaliacao();
            FacesMessage info = new FacesMessage("Dados guardados com sucesso!");
            facesContext.addMessage(null, info);
        } else {
            avaliacaoDAO.update(avaliacao);
            tipoAvaliacao = new TipoAvaliacao();
            FacesMessage info = new FacesMessage("Dados actualizados com sucesso!");
            facesContext.addMessage(null, info);

        }

        return "lista_tipo_avaliacao.xhtml?faces-redirect=true";
    }

    public String eliminar() {
        avaliacaoDAO.delete(avaliacao);
        tipoAvaliacao = new TipoAvaliacao();
        return "lista_tipo_avaliacao?faces-redirect=true";
    }

    public String prepararEditar() {
        return "tipo_avaliacao_editar";
    }

    
    
}
