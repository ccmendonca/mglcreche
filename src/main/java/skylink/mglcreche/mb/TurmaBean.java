package skylink.mglcreche.mb;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import skylink.mglcreche.modelo.Turma;
import skylink.mglcreche.dao.TurmaDAO;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

@Named( value= "turmaBean")
@ViewScoped
public class TurmaBean implements Serializable {
    
    private Turma turma;
    private Turma turmaSelecionada;
    private List<Turma> turmas;
    private TurmaDAO turmaDAO;
    
    private String filtroDescricao;
    private Integer filtroAnoLectivo;
    private Integer filtroClasse;
    private Boolean filtroActiva;
    
    public TurmaBean() {
        turmaDAO = new TurmaDAO();
        turma = new Turma();
        turma.setActiva(true);
        turma.setNumeroMaximo(30);
        turma.setDataCriacao(new Date());
        turmas = new ArrayList<>();
        carregarTurmas();
    }
        
    public void salvar() {
        boolean sucesso;
        
        if (turma.getIdTurma() == null) {
            sucesso = turmaDAO.save(turma);
            if (sucesso) {
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Turma cadastrada com sucesso!"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro ao cadastrar turma!"));
            }
        } else {
            sucesso = turmaDAO.update(turma);
            if (sucesso) {
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Turma atualizada com sucesso!"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro ao atualizar turma!"));
            }
        }
        
        if (sucesso) {
            limpar();
            carregarTurmas();
        }
    }
    
    public void excluir() {
        if (turmaSelecionada != null) {
            boolean sucesso = turmaDAO.delete(turmaSelecionada);
            if (sucesso) {
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Turma excluída com sucesso!"));
                carregarTurmas();
            } else {
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro ao excluir turma!"));
            }
        }
    }
    
    public void editar() {
        if (turmaSelecionada != null) {
            this.turma = turmaSelecionada;
        }
    }
    
    public void limpar() {
        turma = new Turma();
        turma.setActiva(true);
        turma.setNumeroMaximo(30);
        turma.setDataCriacao(new Date());
        turmaSelecionada = null;
    }
    
    public void carregarTurmas() {
        turmas = turmaDAO.listar();
    }
    
    public void pesquisar() {
        if (filtroDescricao != null && !filtroDescricao.trim().isEmpty()) {
            turmas = turmaDAO.buscarPorDescricao(filtroDescricao);
        } else {
            turmas = turmaDAO.listar();
        }
    }
    
    public void limparPesquisa() {
        filtroDescricao = null;
        filtroAnoLectivo = null;
        filtroClasse = null;
        filtroActiva = null;
        carregarTurmas();
    }
        
    public Turma getTurma() {
        return turma;
    }
    
    public void setTurma(Turma turma) {
        this.turma = turma;
    }
    
    public Turma getTurmaSelecionada() {
        return turmaSelecionada;
    }
    
    public void setTurmaSelecionada(Turma turmaSelecionada) {
        this.turmaSelecionada = turmaSelecionada;
    }
    
    public List<Turma> getTurmas() {
        return turmas;
    }
    
    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }
    
    public String getFiltroDescricao() {
        return filtroDescricao;
    }
    
    public void setFiltroDescricao(String filtroDescricao) {
        this.filtroDescricao = filtroDescricao;
    }
    
    public Integer getFiltroAnoLectivo() {
        return filtroAnoLectivo;
    }
    
    public void setFiltroAnoLectivo(Integer filtroAnoLectivo) {
        this.filtroAnoLectivo = filtroAnoLectivo;
    }
    
    public Integer getFiltroClasse() {
        return filtroClasse;
    }
    
    public void setFiltroClasse(Integer filtroClasse) {
        this.filtroClasse = filtroClasse;
    }
    
    public Boolean getFiltroActiva() {
        return filtroActiva;
    }
    
    public void setFiltroActiva(Boolean filtroActiva) {
        this.filtroActiva = filtroActiva;
    }
}