package skylink.mglcreche.mb;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import skylink.mglcreche.dao.ResponsavelBuscaAlunoDAO;
import skylink.mglcreche.modelo.ResponsavelBuscaAluno;

@Named(value = "responsavelBean")
@SessionScoped
public class ResponsavelBuscaAlunoBean implements Serializable {

    private ResponsavelBuscaAluno responsavel;
    private ResponsavelBuscaAlunoDAO responsavelDAO;
    private List<ResponsavelBuscaAluno> listaResponsaveis;

    @PostConstruct
    public void init() {
        responsavel = new ResponsavelBuscaAluno();
        responsavelDAO = new ResponsavelBuscaAlunoDAO();
        listar();
    }

    public void listar() {
        listaResponsaveis = responsavelDAO.listar();
    }

    public String salvar() {
        if (responsavelDAO.save(responsavel)) {

            responsavel = new ResponsavelBuscaAluno();
            listar(); 

            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Dados guardados"));

            return "/responsavelbusca/index_responsavel_busca_aluno.xhtml?faces-redirect=true";
        }

        return null;
    }

    public void atualizar() {
        if (responsavelDAO.update(responsavel)) {
            listar();
        }
    }

    public void eliminar() {
        if (responsavelDAO.delete(responsavel)) {
            listar();
        }
    }

    public ResponsavelBuscaAluno getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(ResponsavelBuscaAluno responsavel) {
        this.responsavel = responsavel;
    }

    public List<ResponsavelBuscaAluno> getListaResponsaveis() {
        return listaResponsaveis;
    }
}