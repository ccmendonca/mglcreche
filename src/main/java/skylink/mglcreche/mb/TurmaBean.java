package skylink.mglcreche.mb;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import skylink.mglcreche.modelo.Turma;
import skylink.mglcreche.modelo.AnoLectivo;
import skylink.mglcreche.modelo.Classe;
import skylink.mglcreche.dao.TurmaDAO;
import skylink.mglcreche.dao.AnoLectivoDAO;
import skylink.mglcreche.dao.ClasseDAO;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @Henriques
 */
@Named(value = "turmaBean")
@ViewScoped
public class TurmaBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Turma turma;
    private Turma turmaSelecionada;
    private List<Turma> turmas;
    private List<AnoLectivo> anosLectivos;
    private List<Classe> classes;
    private TurmaDAO turmaDAO;
    private AnoLectivoDAO anoLectivoDAO;
    private ClasseDAO classeDAO;

    private String filtroDescricao;

    public TurmaBean() {
        turmaDAO = new TurmaDAO();
        anoLectivoDAO = new AnoLectivoDAO();
        classeDAO = new ClasseDAO();
        turma = new Turma();
        turmas = new ArrayList<>();
        anosLectivos = new ArrayList<>();
        classes = new ArrayList<>();
        novo();
        carregarTurmas();
        carregarAnosLectivos();
        carregarClasses();
    }

    public void novo() {
        turma = new Turma();
        turma.setAnoLectivo(new AnoLectivo());
        turma.setClasse(new Classe());
        turma.setActiva(true);
        turma.setNumeroMaximo(30);
        turma.setDataRegisto(new Date());
        turmaSelecionada = null;
    }

    public void salvar() {
        if (turma == null) {
            addMensagem(FacesMessage.SEVERITY_WARN, "Aviso", "Nenhuma turma para salvar.");
            return;
        }

        boolean sucesso;

        if (turma.getIdTurma() == null) {
            sucesso = turmaDAO.save(turma);
            if (sucesso) {
                addMensagem(FacesMessage.SEVERITY_INFO, "Sucesso", "Turma cadastrada com sucesso!");
            } else {
                addMensagem(FacesMessage.SEVERITY_ERROR, "Erro", "Erro ao cadastrar turma!");
            }
        } else {
            sucesso = turmaDAO.update(turma);
            if (sucesso) {
                addMensagem(FacesMessage.SEVERITY_INFO, "Sucesso", "Turma actualizada com sucesso!");
            } else {
                addMensagem(FacesMessage.SEVERITY_ERROR, "Erro", "Erro ao actualizar turma!");
            }
        }

        if (sucesso) {
            novo();
            carregarTurmas();
        }
    }

    public void excluir() {
        if (turmaSelecionada == null) {
            addMensagem(FacesMessage.SEVERITY_WARN, "Aviso", "Seleccione uma turma para excluir.");
            return;
        }

        boolean sucesso = turmaDAO.delete(turmaSelecionada);
        if (sucesso) {
            addMensagem(FacesMessage.SEVERITY_INFO, "Sucesso", "Turma eliminada com sucesso!");
            carregarTurmas();
            novo();
        } else {
            addMensagem(FacesMessage.SEVERITY_ERROR, "Erro", "Erro ao eliminar turma!");
        }
    }

   public String editar() {
    if (turmaSelecionada == null) {
        addMensagem(FacesMessage.SEVERITY_WARN, "Aviso", "Seleccione uma turma para editar.");
        return null;
    }
    this.turma = turmaSelecionada;
    return "/turma/cadastro_turma?faces-redirect=true";
}

    public void carregarTurmas() {
        turmas = turmaDAO.listar();
    }

    public void carregarAnosLectivos() {
        anosLectivos = anoLectivoDAO.findAll();
    }

    public void carregarClasses() {
        classes = classeDAO.findAll();
    }

    public void pesquisar() {
        if (filtroDescricao != null && !filtroDescricao.trim().isEmpty()) {
            turmas = turmaDAO.buscarPorDescricao(filtroDescricao.trim());
        } else {
            carregarTurmas();
        }
    }

    public void limparPesquisa() {
        filtroDescricao = null;
        carregarTurmas();
    }

    private void addMensagem(FacesMessage.Severity severity, String resumo, String detalhe) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(severity, resumo, detalhe));
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

    public List<AnoLectivo> getAnosLectivos() {
        return anosLectivos;
    }

    public void setAnosLectivos(List<AnoLectivo> anosLectivos) {
        this.anosLectivos = anosLectivos;
    }

    public List<Classe> getClasses() {
        return classes;
    }

    public void setClasses(List<Classe> classes) {
        this.classes = classes;
    }

    public String getFiltroDescricao() {
        return filtroDescricao;
    }

    public void setFiltroDescricao(String filtroDescricao) {
        this.filtroDescricao = filtroDescricao;
    }
    
}
