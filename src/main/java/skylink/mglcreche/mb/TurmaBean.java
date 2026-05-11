package skylink.mglcreche.mb;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
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

/**
 * Henriques
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
    private List<Sala> salas; 

    
    private final TurmaDAO turmaDAO;
    private final AnoLectivoDAO anoLectivoDAO;
    private final ClasseDAO classeDAO;
    private final SalaDAO salaDAO; 

    private String filtroDescricao;

    public TurmaBean() {
        turmaDAO = new TurmaDAO();
        anoLectivoDAO = new AnoLectivoDAO();
        classeDAO = new ClasseDAO();
        salaDAO = new SalaDAO(); 

        turmas = new ArrayList<>();
        anosLectivos = new ArrayList<>();
        classes = new ArrayList<>();
        salas = new ArrayList<>();


        novo();
        carregarCombos();
    }

    
    public void novo() {
        turma = new Turma();
        turma.setAnoLectivo(new AnoLectivo());
        turma.setClasse(new Classe());
        turma.setSala(new Sala()); 
        turma.setActiva(true);
        turma.setNumeroMaximo(30);
        turma.setDataRegisto(new Date());
        turmaSelecionada = null;
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
            }
        } else {

            addMensagem(FacesMessage.SEVERITY_WARN, "Atenção", "Informe uma descrição para pesquisar.");
            turmas = new ArrayList<>(); 
        }
    }

   
    public void limparPesquisa() {
        this.filtroDescricao = null;
        this.turmas = new ArrayList<>();
        this.turmaSelecionada = null;
    }

    public void salvar() {
        if (turma == null) return;

        boolean sucesso;
        if (turma.getIdTurma() == null) {
            sucesso = turmaDAO.save(turma);
        } else {
            sucesso = turmaDAO.update(turma);
        }

        if (sucesso) {
            addMensagem(FacesMessage.SEVERITY_INFO, "Sucesso", "Operação realizada com sucesso!");
            novo();
            
        } else {
            addMensagem(FacesMessage.SEVERITY_ERROR, "Erro", "Erro ao processar operação no banco de dados.");
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

    private void addMensagem(FacesMessage.Severity severity, String resumo, String detalhe) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, resumo, detalhe));
    }

    // Getters e Setters
    public Turma getTurma() { return turma; }
    public void setTurma(Turma turma) { this.turma = turma; }

    public Turma getTurmaSelecionada() { return turmaSelecionada; }
    public void setTurmaSelecionada(Turma turmaSelecionada) { this.turmaSelecionada = turmaSelecionada; }

    public List<Turma> getTurmas() { return turmas; }
    public List<AnoLectivo> getAnosLectivos() { return anosLectivos; }
    public List<Classe> getClasses() { return classes; }
    public List<Sala> getSalas() { return salas; } 

    public String getFiltroDescricao() { return filtroDescricao; }
    public void setFiltroDescricao(String filtroDescricao) { this.filtroDescricao = filtroDescricao; }
}