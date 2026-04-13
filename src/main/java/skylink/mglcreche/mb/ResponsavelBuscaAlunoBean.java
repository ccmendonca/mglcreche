package skylink.mglcreche.mb;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import skylink.mglcreche.dao.MunicipioDAO;
import skylink.mglcreche.dao.ResponsavelBuscaAlunoDAO;
import skylink.mglcreche.dao.SexoDAO;
import skylink.mglcreche.modelo.Municipio;
import skylink.mglcreche.modelo.ResponsavelBuscaAluno;
import skylink.mglcreche.modelo.Sexo;

@Named("responsavelBuscaAlunoBean")
@ViewScoped
public class ResponsavelBuscaAlunoBean implements Serializable {

    private ResponsavelBuscaAluno responsavel;
    private ResponsavelBuscaAlunoDAO responsavelDAO;
    private List<ResponsavelBuscaAluno> listaResponsaveis; 
    private String nomeResponsavel;
    private List<Sexo> listaSexos;
    private List<Municipio> listaMunicipios;
    private SexoDAO sexoDAO;
    private MunicipioDAO municipioDAO;

    @PostConstruct
    public void init() {
        responsavelDAO = new ResponsavelBuscaAlunoDAO();
        sexoDAO = new SexoDAO();
        municipioDAO = new MunicipioDAO();
        listaSexos = sexoDAO.findAll();
        listaMunicipios = municipioDAO.findAll();

      
        Map<String, Object> session = FacesContext.getCurrentInstance()
                .getExternalContext().getSessionMap();

        ResponsavelBuscaAluno editando = (ResponsavelBuscaAluno) session.get("responsavelEditando");

        if (editando != null) {
         
            this.responsavel = editando;
            session.remove("responsavelEditando");
        } else {
          
            this.responsavel = new ResponsavelBuscaAluno();
        }
    }

    
    public void pesquisa() {
        listaResponsaveis = responsavelDAO.buscar(nomeResponsavel);
    }


    public String editar(ResponsavelBuscaAluno r) {
    FacesContext.getCurrentInstance()
        .getExternalContext()
        .getSessionMap()
        .put("responsavelEditando", r);

    return "/responsavelbusca/editar_responsavel_busca.xhtml?faces-redirect=true";
}

    public String salvar() {
        String telefone = responsavel.getTelefoneResponsavel();
        if (telefone != null) {
            telefone = telefone.replaceAll("[^0-9]", "");
            responsavel.setTelefoneResponsavel(telefone);
        }

        Sexo sexoSelecionado = listaSexos.stream()
                .filter(s -> s.getIdSexo().equals(responsavel.getIdSexo()))
                .findFirst()
                .orElse(null);

        if (sexoSelecionado != null) {
            responsavel.setSexoResponsavel(sexoSelecionado.getDescricaoSexo());
        }

        if (responsavelDAO.save(responsavel)) {
            responsavel = new ResponsavelBuscaAluno();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Dados guardados"));
            return "/responsavelbusca/index_responsavel_busca_aluno.xhtml?faces-redirect=true";
        }

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Falha ao guardar dados"));
        return null;
    }

    public void atualizar() {
        String telefone = responsavel.getTelefoneResponsavel();
        if (telefone != null) {
            telefone = telefone.replaceAll("[^0-9]", "");
            responsavel.setTelefoneResponsavel(telefone);
        }

        Sexo sexoSelecionado = listaSexos.stream()
                .filter(s -> s.getIdSexo().equals(responsavel.getIdSexo()))
                .findFirst()
                .orElse(null);

        if (sexoSelecionado != null) {
            responsavel.setSexoResponsavel(sexoSelecionado.getDescricaoSexo());
        }

        if (responsavelDAO.update(responsavel)) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Atualizado com sucesso"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Falha ao atualizar"));
        }
    }

    public void eliminar(ResponsavelBuscaAluno r) {
        if (responsavelDAO.delete(r)) {
            listaResponsaveis.remove(r);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Eliminado com sucesso"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Falha ao eliminar"));
        }
    }

    public void novo() {
        responsavel = new ResponsavelBuscaAluno();
    }

    
    public ResponsavelBuscaAluno getResponsavel() { return responsavel; }
    public void setResponsavel(ResponsavelBuscaAluno responsavel) { this.responsavel = responsavel; }

    public List<ResponsavelBuscaAluno> getListaResponsaveis() { return listaResponsaveis; }
    public void setListaResponsaveis(List<ResponsavelBuscaAluno> l) { this.listaResponsaveis = l; }

    public String getNomeResponsavel() { return nomeResponsavel; }
    public void setNomeResponsavel(String nomeResponsavel) { this.nomeResponsavel = nomeResponsavel; }

    public List<Sexo> getListaSexos() { return listaSexos; }
    public void setListaSexos(List<Sexo> listaSexos) { this.listaSexos = listaSexos; }

    public List<Municipio> getListaMunicipios() { return listaMunicipios; }
    public void setListaMunicipios(List<Municipio> listaMunicipios) { this.listaMunicipios = listaMunicipios; }
}
