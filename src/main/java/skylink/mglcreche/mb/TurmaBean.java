package skylink.mglcreche.mb;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import skylink.mglcreche.modelo.Turma;
import skylink.mglcreche.modelo.AnoLectivo;
import skylink.mglcreche.modelo.Classe;
import skylink.mglcreche.modelo.Sala;
import skylink.mglcreche.dao.TurmaDAO;
import skylink.mglcreche.dao.AnoLectivoDAO;
import skylink.mglcreche.dao.ClasseDAO;
import skylink.mglcreche.dao.SalaDAO;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import skylink.mglcreche.modelo.Matricula;

/**
 * Henriques
 *
 * @Henriques
 */
@Named(value = "turmaBean")
@ViewScoped
public class TurmaBean implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(TurmaBean.class.getName());

    private static final long serialVersionUID = 1L;
    @Inject
    FacesContext facesContext;
    private Turma turma;
    private Turma turmaSelecionada;
    private List<Turma> turmas;
    private List<AnoLectivo> anosLectivos;
    private List<Classe> classes;
    private List<Sala> salas;
    private final TurmaDAO turmaDAO;
    private final AnoLectivoDAO anoLectivoDAO;
    private final ClasseDAO classeDAO;
    private final SalaDAO salaDAO;
    private Matricula matricula = new Matricula();
    private String filtroDescricao;

    @PostConstruct
    public void inicializar() {
        salas = salaDAO.findAll();
        anosLectivos = anoLectivoDAO.findAll();
        classes = classeDAO.findAll();
        turmas = turmaDAO.findAll();
    }

    public TurmaBean() {
        turmaDAO = new TurmaDAO();
        anoLectivoDAO = new AnoLectivoDAO();
        classeDAO = new ClasseDAO();
        salaDAO = new SalaDAO();
        turma = new Turma();
        turmas = new ArrayList<>();
        anosLectivos = new ArrayList<>();
        classes = new ArrayList<>();
        salas = new ArrayList<>();

    }

    private void carregarCombos() {
        try {
            anosLectivos = anoLectivoDAO.findAll();
            classes = classeDAO.findAll();
            salas = salaDAO.findAll();
        } catch (Exception e) {
            addMensagem(FacesMessage.SEVERITY_ERROR, "Erro", "Erro ao carregar dados auxiliares: " + e.getMessage());
        }
    }

    public void pesquisar() {
        if (filtroDescricao != null && !filtroDescricao.trim().isEmpty()) {
            turmas = turmaDAO.buscarPorDescricao(filtroDescricao.trim());
            if (turmas.isEmpty()) {
                addMensagem(FacesMessage.SEVERITY_INFO, "Informação", "Nenhuma turma encontrada com essa descrição.");
                boolean sucesso;

                if (turma.getIdTurma() == null) {
                    sucesso = turmaDAO.save(turma);
                    if (sucesso) {
                        addMensagem(FacesMessage.SEVERITY_INFO, "Sucesso", "Turma cadastrada com sucesso!");
                    } else {
                        addMensagem(FacesMessage.SEVERITY_ERROR, "Erro", "Erro ao cadastrar turma! Verifique o console.");
                    }
                } else {

                    addMensagem(FacesMessage.SEVERITY_WARN, "Atenção", "Informe uma descrição para pesquisar.");
                    turmas = new ArrayList<>();
                }
            }
        }
    }

    public void limparPesquisa() {
        this.filtroDescricao = null;
        this.turmas = new ArrayList<>();
        this.turmaSelecionada = null;
    }

    /*
    public void salvar() {
        if (turma == null) {
            return;
        }

        boolean sucesso;
        if (turma.getIdTurma() == null) {
            sucesso = turmaDAO.save(turma);
        } else {
            sucesso = turmaDAO.update(turma);
        }

        if (sucesso) {
            addMensagem(FacesMessage.SEVERITY_INFO, "Sucesso", "Operação realizada com sucesso!");

        } else {
            addMensagem(FacesMessage.SEVERITY_ERROR, "Erro", "Erro ao processar operação no banco de dados.");
        }
    }
     */
    public String salvar() {
        LOGGER.log(Level.INFO, "saving task@{0}", turma);
        if (this.turma.getIdTurma() == null) {
            turmaDAO.save(turma);
            addMensagem(FacesMessage.SEVERITY_INFO, "Sucesso", "Turma registada com sucesso!");
            return "cadastro_turmas.faces?faces-redirect=true";
        } else if (this.turma.getIdTurma() != null) {
            turmaDAO.update(turma);
            addMensagem(FacesMessage.SEVERITY_INFO, "Sucesso", "Turma actualizada com sucesso!");
            return "cadastro_turmas.faces?faces-redirect=true";
        } else {
            addMensagem(FacesMessage.SEVERITY_ERROR, "Erro", "Erro ao processar operação no banco de dados.");
            return null;
        }

    }

    public void excluir() {
        if (turmaSelecionada == null) {
            addMensagem(FacesMessage.SEVERITY_WARN, "Aviso", "Selecione uma turma na tabela.");
            return;
        }

        if (turmaDAO.delete(turmaSelecionada)) {
            addMensagem(FacesMessage.SEVERITY_INFO, "Sucesso", "Turma removida.");
            turmas.remove(turmaSelecionada);
            turmaSelecionada = null;
        } else {
            addMensagem(FacesMessage.SEVERITY_ERROR, "Erro", "Não foi possível excluir a turma.");
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
        turmas = turmaDAO.findAll();
    }

    public void carregarAnosLectivos() {
        anosLectivos = anoLectivoDAO.findAll();
    }

    public void carregarClasses() {
        classes = classeDAO.findAll();
    }

    public void carregarSalas() {

        salas = salaDAO.findAll();
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

    public List<Sala> getSalas() {
        return salas;
    }

    public void setSalas(List<Sala> salas) {
        this.salas = salas;
    }

    public String getFiltroDescricao() {
        return filtroDescricao;
    }

    public void setFiltroDescricao(String filtroDescricao) {
        this.filtroDescricao = filtroDescricao;
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }

    private void addMensagem(FacesMessage.Severity SEVERITY_WARN, String titulo, String message) {
        FacesMessage info = new FacesMessage(titulo, message);
        info.setSeverity(SEVERITY_WARN);
        facesContext.addMessage(null, info);
    }

}
