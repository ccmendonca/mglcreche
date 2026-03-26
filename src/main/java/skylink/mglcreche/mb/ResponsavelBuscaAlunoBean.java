package skylink.mglcreche.mb;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.sql.Date; 
import java.util.ArrayList;
import java.util.List;
import skylink.mglcreche.dao.ResponsavelBuscaAlunoDAO;
import skylink.mglcreche.modelo.ResponsavelBuscaAluno;

@Named(value = "responsavelBuscaAlunoBean")
@SessionScoped
public class ResponsavelBuscaAlunoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private ResponsavelBuscaAluno responsavel;
    private ResponsavelBuscaAlunoDAO responsavelDAO;
    private List<ResponsavelBuscaAluno> listaResponsaveis;

    public ResponsavelBuscaAlunoBean() {
    }

    @PostConstruct
    public void init() {
        responsavel = new ResponsavelBuscaAluno();
        responsavelDAO = new ResponsavelBuscaAlunoDAO();
        listaResponsaveis = new ArrayList<>();
        listar();
    }

    public void salvar() {
        if (responsavel.getDataRegistoResponsavel() == null) {

            responsavel.setDataRegistoResponsavel(new Date(System.currentTimeMillis()));
        }

        if (responsavelDAO.save(responsavel)) {
            novo();
            listar();
        }
    }

    public void atualizar() {
        if (responsavelDAO.update(responsavel)) {
            listar();
            novo(); 
        }
    }

    public void eliminar() {
        if (responsavelDAO.delete(responsavel)) {
            novo();
            listar();
        }
    }

    public void listar() {
        listaResponsaveis = responsavelDAO.findAll();
    }

    public void novo() {
        responsavel = new ResponsavelBuscaAluno();
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

    public void setListaResponsaveis(List<ResponsavelBuscaAluno> listaResponsaveis) {
        this.listaResponsaveis = listaResponsaveis;
    }
}