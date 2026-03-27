
package skylink.mglcreche.mb;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import skylink.mglcreche.dao.AlunoDAO;
import skylink.mglcreche.dao.MesPropinaDAO;
import skylink.mglcreche.modelo.Aluno;
import skylink.mglcreche.modelo.MesPropina;

@Named(value = "mesPropinaMBean")
@SessionScoped
public class MesPropinaMBean implements Serializable {

    MesPropinaDAO mesPropinaDAO = new MesPropinaDAO();
    private List<MesPropina> mesPropinas = new ArrayList();
    private MesPropina mesPropina = new MesPropina();
    private List<Aluno> alunosPesquisados;
    private String nomeAluno ;
    private Aluno aluno = new Aluno();
     private AlunoDAO alunoDAO = new AlunoDAO();
    @PostConstruct
    public void inicializar() {

        mesPropinas = mesPropinaDAO.findAll();

    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    
    
    public List<Aluno> getAlunosPesquisados() {
        return alunosPesquisados;
    }

    public void setAlunosPesquisados(List<Aluno> alunosPesquisados) {
        this.alunosPesquisados = alunosPesquisados;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    
    
    public List<MesPropina> getMesPropinas() {
        return mesPropinas;
    }

    public void setMesPropinas(List<MesPropina> mesPropinas) {
        this.mesPropinas = mesPropinas;
    }

    public MesPropina getMesPropina() {
        return mesPropina;
    }

    public void setMesPropina(MesPropina mesPropina) {
        this.mesPropina = mesPropina;
    }
    
     public void pesquisaAluno() {
        alunosPesquisados = alunoDAO.aleatoryAlunos(nomeAluno);

    }
    
    

    public String save() {
        if (mesPropinaDAO.save(mesPropina)) {
            mesPropina = new MesPropina();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso ao Guarda", "Sucesso ao guardar os dados"));
            return "mes_propina_novo?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao Guardar", "Erro ao guardar os dados"));
            return null;
        }
    }

    public String eliminar() {
        mesPropinaDAO.delete(mesPropina);
        mesPropina = new MesPropina();
        return "mes_propina_novo_lista?faces-redirect=true";
    }

    public String prepararEditar() {
        return "mes_propina_editar";
    }

    public String goToEditarProduto(MesPropina mesPropina) {

        this.mesPropina = mesPropinaDAO.findById(mesPropina.getIdMesPropina());

        return "mes_propina_editar?faces-redirect=true";
    }

    public String editar() {
        if (mesPropinaDAO.update(mesPropina)) {
            mesPropina = new MesPropina();
            addMessage(FacesMessage.SEVERITY_INFO, "Editar", "Dados do produto alterados com sucesso");
            return "mes_propina_lista?faces-redirect=true";
        } else {
            addMessage(FacesMessage.SEVERITY_ERROR, "Erro ao guardar", "Erro ao guardar os dados");
            return null;
        }
    }

    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }
}
