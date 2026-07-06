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

import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import skylink.mglcreche.dao.MatriculaDAO;
import skylink.mglcreche.modelo.Matricula;

@Named(value = "turmaBean")
@ViewScoped
public class TurmaBean implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(TurmaBean.class.getName());

    private static final long serialVersionUID = 1L;
    @Inject
    FacesContext facesContext;
    private Turma turma = new Turma();
    private Turma turmaSelecionada;
    private List<Turma> turmas;
    private Matricula TurmaComEstudantes = new Matricula();
    private List<Matricula> aluntosDaTurma = new ArrayList<>();
    private List<Turma> porAnoLectivoturmas = new ArrayList<>();
    private List<AnoLectivo> anosLectivos;
    private List<Classe> classes = new ArrayList<>();
    private List<Sala> salas = new ArrayList<>();
    private TurmaDAO turmaDAO = new TurmaDAO();
    private MatriculaDAO matriculaDAO = new MatriculaDAO();
    private AnoLectivoDAO anoLectivoDAO = new AnoLectivoDAO();
    private ClasseDAO classeDAO;
    private SalaDAO salaDAO;
    private Matricula matricula = new Matricula();
    private String filtroDescricao;
    private Integer idTurma;

    @PostConstruct
    public void inicializar() {
        // salas = salaDAO.findAll();
        //anosLectivos = anoLectivoDAO.findAll();
        //  classes = classeDAO.findAll();
        //  turmas = turmaDAO.findAll();
        //   anosLectivos = anoLectivoDAO.findAll();
        // classes = classeDAO.findAll();
        // salas = salaDAO.findAll();
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

    public Matricula getTurmaComEstudantes() {
        return TurmaComEstudantes;
    }

    public void setTurmaComEstudantes(Matricula TurmaComEstudantes) {
        this.TurmaComEstudantes = TurmaComEstudantes;
    }

    public List<Matricula> getAluntosDaTurma() {
        return aluntosDaTurma;
    }

    public void setAluntosDaTurma(List<Matricula> aluntosDaTurma) {
        this.aluntosDaTurma = aluntosDaTurma;
    }

    public Integer getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(Integer idTurma) {
        this.idTurma = idTurma;
    }

   
    
    
    
    
    public String salvar() {
        LOGGER.log(Level.INFO, "saving task@{0}", turma);
        Integer numeroTurma = turmaDAO.buscaUltimaTurmaCriada();
        numeroTurma = numeroTurma + 1;
        StringBuilder codigoTurma = new StringBuilder();
        codigoTurma.append(numeroTurma).append(turma.getAnoLectivo().getDescricaoAnoLectivo()).append(turma.getSala().getDescricaoSala());
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>Codigo da Turma" + codigoTurma.toString());
        if (this.turma.getIdTurma() == null) {
            turma.setCodigoTurma(codigoTurma.toString());
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

    public void carregarTurmaEstudantes() {
        TurmaComEstudantes = matriculaDAO.findTurmaComMatriculados(idTurma);
        aluntosDaTurma = matriculaDAO.findAllAlunosDaTurma(idTurma);

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

    public List<Turma> getPorAnoLectivoturmas() {
        return porAnoLectivoturmas;
    }

    public void setPorAnoLectivoturmas(List<Turma> porAnoLectivoturmas) {
        this.porAnoLectivoturmas = porAnoLectivoturmas;
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

    public void carregarTurmasPorAnoLectivo(AnoLectivo anoLectivo) {
        this.porAnoLectivoturmas = this.turmaDAO.buscarPorAnoLectivo(anoLectivo.getIdAnoLectivo());
    }

    private void addMensagem(FacesMessage.Severity SEVERITY_WARN, String titulo, String message) {
        FacesMessage info = new FacesMessage(titulo, message);
        info.setSeverity(SEVERITY_WARN);
        facesContext.addMessage(null, info);
    }

}
