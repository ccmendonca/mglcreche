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
    private String nomeResponsavel;

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public void setNomeResponsavel(String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }

    @PostConstruct
    public void init() {
        responsavel = new ResponsavelBuscaAluno();
        responsavelDAO = new ResponsavelBuscaAlunoDAO();
    }

    public void listar(String nomeResponsavel) {
        listaResponsaveis = responsavelDAO.listar(nomeResponsavel);
    }
    
    public String salvar() {
        String numero = responsavel.getTelefoneResponsavel().substring(5);
        String[] divs = numero.strip().split("-");
        String novoNumero = String.join("", divs);
        responsavel.setTelefoneResponsavel(novoNumero);
        if (responsavelDAO.save(responsavel)) {

            responsavel = new ResponsavelBuscaAluno();

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Dados guardados"));

            return "/responsavelbusca/index_responsavel_busca_aluno.xhtml?faces-redirect=true";
        }

        return null;
    }

    public void atualizar() {
        if (responsavelDAO.update(responsavel)) {

        }
    }

    public String editar(ResponsavelBuscaAluno r) {
        this.responsavel = r;
        return "cadastroResponsavel?faces-redirect=true";
    }

    public void eliminar() {
        if (responsavelDAO.delete(responsavel)) {

        }
    }

    public ResponsavelBuscaAluno getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(ResponsavelBuscaAluno responsavel) {
        this.responsavel = responsavel;
    }

    
    public List<ResponsavelBuscaAluno> getListaResponsaveis() {
    if (listaResponsaveis == null) {
        listaResponsaveis = new java.util.ArrayList<>();
    }
    return listaResponsaveis;
}

    public void pesquisa() {
        System.out.println("Dados pesquisado com Sucessso...");
        listar(nomeResponsavel);
    }
    
    public void limpar() {
    responsavel = new ResponsavelBuscaAluno();
}
    public void limparLista() {
    listaResponsaveis = new java.util.ArrayList<>();
}
}
