package skylink.mglcreche.mb;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import skylink.mglcreche.dao.AlunoDAO;
import skylink.mglcreche.dao.ServicoDAO;
import skylink.mglcreche.modelo.Aluno;
import skylink.mglcreche.modelo.Servico;

/**
 * «claudiomendonca»
 */
@Named(value = "servicoMBean")
@SessionScoped
public class ServicoMBean implements Serializable {

    private ServicoDAO servicoDAO = new ServicoDAO();
    private List<Servico> servicos = new ArrayList<>();
    private Servico servico = new Servico();
    private String nomeAluno;
    private List<Aluno> alunosPesquisados;
    private Aluno aluno = new Aluno();
    private AlunoDAO alunoDAO = new AlunoDAO();

    @Inject
    FacesContext facesContext;
    
    @PostConstruct
    public void inicializar() {

        servicos = servicoDAO.findAll();
    }
    
    public void pesquisaAluno() {
        alunosPesquisados = alunoDAO.aleatoryAlunos(nomeAluno);
    }

    public String save() {
        servicoDAO.save(servico);
        servico = new Servico();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso ao Guarda", "Sucesso ao guardar os dados"));
        return "todos_servicos?faces-redirect=true";
    }

    public String eliminar() {
        servicoDAO.delete(servico);
        servico = new Servico();
        return "todos_servicos?faces-redirect=true";
    }

    public String prepararEditar() {
        return "editar_servico";
    }

    public String goToEditar(Servico servico) {

        this.servico = (Servico) servicoDAO.findById(servico.getIdServico());

        return "editar_servicos?faces-redirect=true";
    }

    public String editar() {
        if (servicoDAO.update(servico)) {
            servico = new Servico();
            addMessage(FacesMessage.SEVERITY_INFO, "Editar", "Dados do produto alterados com sucesso");
            return "todos_servicos?faces-redirect=true";
        } else {
            addMessage(FacesMessage.SEVERITY_ERROR, "Erro ao guardar", "Erro ao guardar os dados");
            return null;
        }
    }

    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

    public ServicoDAO getServicoDAO() {
        return servicoDAO;
    }

    public void setServicoDAO(ServicoDAO servicoDAO) {
        this.servicoDAO = servicoDAO;
    }

    public List<Servico> getServicos() {
        return servicos;
    }

    public void setServicos(List<Servico> servicos) {
        this.servicos = servicos;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public FacesContext getFacesContext() {
        return facesContext;
    }

    public void setFacesContext(FacesContext facesContext) {
        this.facesContext = facesContext;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public List<Aluno> getAlunosPesquisados() {
        return alunosPesquisados;
    }

    public void setAlunosPesquisados(List<Aluno> alunosPesquisados) {
        this.alunosPesquisados = alunosPesquisados;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public AlunoDAO getAlunoDAO() {
        return alunoDAO;
    }

    public void setAlunoDAO(AlunoDAO alunoDAO) {
        this.alunoDAO = alunoDAO;
    }
}
