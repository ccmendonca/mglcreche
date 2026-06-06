package skylink.mglcreche.mb;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import skylink.mglcreche.dao.AlocacaoProfessorDAO;
import skylink.mglcreche.dao.AnoLectivoDAO;
import skylink.mglcreche.dao.TurmaDAO;
import skylink.mglcreche.modelo.AlocacaoProfessor;
import skylink.mglcreche.modelo.AnoLectivo;
import skylink.mglcreche.modelo.Turma;

/**
 *
 *  Henriques
 */
@Named(value = "alocacaoProfessorMBean")
@SessionScoped
public class AlocacaoProfessorMBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private AlocacaoProfessorDAO alocacaoProfessorDAO;
    private List<AlocacaoProfessor> alocacoes = new ArrayList<>();
    private String dado;

    private DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
    private List<Disciplina> disciplinas = new ArrayList<>();

    private TurmaDAO turmaDAO = new TurmaDAO();
    private List<Turma> turmas = new ArrayList<>();

    private AnoLectivoDAO anoLectivoDAO = new AnoLectivoDAO();
    private List<AnoLectivo> anosLectivos = new ArrayList<>();

    private TrimestreDAO trimestreDAO = new TrimestreDAO();
    private List<Trimestre> trimestres = new ArrayList<>();

    private List<AlocacaoProfessor> pesquisado = new ArrayList<>();
    private AlocacaoProfessor selecionado;
    private AlocacaoProfessor alocacaoProfessor;

    @PostConstruct
    public void inicializar() {
        try {
            alocacoes = alocacaoProfessorDAO.findAll();
            disciplinas = disciplinaDAO.findAll();
            turmas = turmaDAO.findAll();
            anosLectivos = anoLectivoDAO.findAll();
            trimestres = trimestreDAO.findAll();
            alocacaoProfessor = new AlocacaoProfessor();
        } catch (Exception e) {
            throw new RuntimeException("Erro crítico ao inicializar as listas de alocação de professores", e);
        }
    }

 public void findParameter() {
        pesquisado = alocacaoProfessorDAO.findByParameter(dado);
    }
    
    public String update() throws Exception {
        alocacaoProfessorDAO.update(alocacaoProfessor);
        alocacaoProfessor = new AlocacaoProfessor();
        return "lista_alocacoes.faces?faces-redirect=true";
    }

    public String selectAlocacao(AlocacaoProfessor ap) {
        this.alocacaoProfessor = ap;
        return "editar_alocacao";
    }
    
    public String selectHistorico(AlocacaoProfessor ap) {
        this.alocacaoProfessor = ap;
        return "historico_alocacao";
    }

    public AlocacaoProfessorDAO getAlocacaoProfessorDAO() {
        return alocacaoProfessorDAO;
    }

    public void setAlocacaoProfessorDAO(AlocacaoProfessorDAO alocacaoProfessorDAO) {
        this.alocacaoProfessorDAO = alocacaoProfessorDAO;
    }

    public List<AlocacaoProfessor> getAlocacoes() {
        return alocacoes;
    }

    public void setAlocacoes(List<AlocacaoProfessor> alocacoes) {
        this.alocacoes = alocacoes;
    }

    public String getDado() {
        return dado;
    }

    public void setDado(String dado) {
        this.dado = dado;
    }

    public DisciplinaDAO getDisciplinaDAO() {
        return disciplinaDAO;
    }

    public void setDisciplinaDAO(DisciplinaDAO disciplinaDAO) {
        this.disciplinaDAO = disciplinaDAO;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
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

    public AnoLectivoDAO getAnoLectivoDAO() {
        return anoLectivoDAO;
    }

    public void setAnoLectivoDAO(AnoLectivoDAO anoLectivoDAO) {
        this.anoLectivoDAO = anoLectivoDAO;
    }

    public List<AnoLectivo> getAnosLectivos() {
        return anosLectivos;
    }

    public void setAnosLectivos(List<AnoLectivo> anosLectivos) {
        this.anosLectivos = anosLectivos;
    }

    public TrimestreDAO getTrimestreDAO() {
        return trimestreDAO;
    }

    public void setTrimestreDAO(TrimestreDAO trimestreDAO) {
        this.trimestreDAO = trimestreDAO;
    }

    public List<Trimestre> getTrimestres() {
        return trimestres;
    }

    public void setTrimestres(List<Trimestre> trimestres) {
        this.trimestres = trimestres;
    }

    public List<AlocacaoProfessor> getPesquisado() {
        return pesquisado;
    }

    public void setPesquisado(List<AlocacaoProfessor> pesquisado) {
        this.pesquisado = pesquisado;
    }

    public AlocacaoProfessor getSelecionado() {
        return selecionado;
    }

    public void setSelecionado(AlocacaoProfessor selecionado) {
        this.selecionado = selecionado;
    }

    public AlocacaoProfessor getAlocacaoProfessor() {
        return alocacaoProfessor;
    }

    public void setAlocacaoProfessor(AlocacaoProfessor alocacaoProfessor) {
        this.alocacaoProfessor = alocacaoProfessor;
    }

}