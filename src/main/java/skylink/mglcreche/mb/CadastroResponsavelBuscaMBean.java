package skylink.mglcreche.mb;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import skylink.mglcreche.dao.AlunoDAO;
import skylink.mglcreche.dao.AnoLectivoDAO;
import skylink.mglcreche.dao.CadastroResponsavelBuscaDAO;
import skylink.mglcreche.dao.GrauParentescoDAO;
import skylink.mglcreche.dao.ResponsavelBuscaAlunoDAO;
import skylink.mglcreche.modelo.Aluno;
import skylink.mglcreche.modelo.AnoLectivo;
import skylink.mglcreche.modelo.CadastroResponsavelBusca;
import skylink.mglcreche.modelo.GrauParentesco;
import skylink.mglcreche.modelo.ResponsavelBuscaAluno;

/**
 * Managed Bean responsável pela gestão do Cadastro de Responsável de Busca.
 *
 * @author Henriques
 */
@Named("cadastroResponsavelBuscaBean")
@ViewScoped
public class CadastroResponsavelBuscaMBean implements Serializable {

    private static final long serialVersionUID = 1L;

    
    private CadastroResponsavelBusca cadastro;

    
    private CadastroResponsavelBuscaDAO cadastroDAO;
    private AlunoDAO alunoDAO;
    private GrauParentescoDAO grauParentescoDAO;
    private ResponsavelBuscaAlunoDAO responsavelBuscaAlunoDAO;
    private AnoLectivoDAO anoLectivoDAO;

    
    private List<CadastroResponsavelBusca> listaCadastros;
    private List<CadastroResponsavelBusca> listaFiltrada;   
    private List<Aluno> listaAlunos;
    private List<GrauParentesco> listaGrausParentesco;
    private List<ResponsavelBuscaAluno> listaResponsaveisBusca;
    private List<AnoLectivo> listaAnosLectivos;

    
    private String nomeAluno;

    
    @PostConstruct
    public void init() {
        cadastroDAO = new CadastroResponsavelBuscaDAO();
        alunoDAO = new AlunoDAO();
        grauParentescoDAO = new GrauParentescoDAO();
        responsavelBuscaAlunoDAO = new ResponsavelBuscaAlunoDAO();
        anoLectivoDAO = new AnoLectivoDAO();

        try {
            listaAlunos = alunoDAO.findAll();
            listaGrausParentesco = grauParentescoDAO.findAll();
            listaResponsaveisBusca = responsavelBuscaAlunoDAO.findAll();
            listaAnosLectivos = anoLectivoDAO.findAll();

            listaCadastros = cadastroDAO.findAll();

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Erro", "Falha ao carregar dados iniciais: " + e.getMessage()));
        }

        Map<String, Object> session = FacesContext.getCurrentInstance()
                .getExternalContext().getSessionMap();

        CadastroResponsavelBusca editando =
                (CadastroResponsavelBusca) session.get("cadastroResponsavelBuscaEditando");

        if (editando != null) {
            this.cadastro = editando;
            session.remove("cadastroResponsavelBuscaEditando");
        } else {
            this.cadastro = new CadastroResponsavelBusca();
        }
    }

    public void pesquisa() {
        try {
            listaCadastros = cadastroDAO.findAll();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Erro", "Falha ao pesquisar: " + e.getMessage()));
        }
    }

    public String editar(CadastroResponsavelBusca c) {
        FacesContext.getCurrentInstance()
                .getExternalContext()
                .getSessionMap()
                .put("cadastroResponsavelBuscaEditando", c);

        return "/responsavelbusca/editar_cadastro_responsavel_busca.xhtml?faces-redirect=true";
    }

    public String salvar() {
        try {
            if (cadastroDAO.save(cadastro)) {
                cadastro = new CadastroResponsavelBusca();
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "Sucesso", "Dados guardados"));
                return "/responsavelbusca/lista_cadastro_responsavel_busca.xhtml?faces-redirect=true";
            }

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Erro", "Falha ao guardar dados"));
            return null;

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Erro", "Falha ao guardar: " + e.getMessage()));
            return null;
        }
    }

    
    public String atualizar() {
        try {
            if (cadastroDAO.actualizar(cadastro)) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "Sucesso", "Atualizado com sucesso"));
                return "/responsavelbusca/lista_cadastro_responsavel_busca.xhtml?faces-redirect=true";
            }

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Erro", "Falha ao atualizar"));
            return null;

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Erro", "Falha ao atualizar: " + e.getMessage()));
            return null;
        }
    }

    
    public void eliminar(CadastroResponsavelBusca c) {
        try {
            if (cadastroDAO.delete(c.getIdResponsavelBusca())) {
                if (listaCadastros != null) {
                    listaCadastros.remove(c);
                }
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "Sucesso", "Eliminado com sucesso"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                "Erro", "Falha ao eliminar"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Erro", "Falha ao eliminar: " + e.getMessage()));
        }
    }

    
    public void novo() {
        cadastro = new CadastroResponsavelBusca();
    }

   
    public CadastroResponsavelBusca getCadastro() {
        return cadastro;
    }

    public void setCadastro(CadastroResponsavelBusca cadastro) {
        this.cadastro = cadastro;
    }

    public List<CadastroResponsavelBusca> getListaCadastros() {
        return listaCadastros;
    }

    public void setListaCadastros(List<CadastroResponsavelBusca> listaCadastros) {
        this.listaCadastros = listaCadastros;
    }

    public List<CadastroResponsavelBusca> getListaFiltrada() {
        return listaFiltrada;
    }

    public void setListaFiltrada(List<CadastroResponsavelBusca> listaFiltrada) {
        this.listaFiltrada = listaFiltrada;
    }

    public List<Aluno> getListaAlunos() {
        return listaAlunos;
    }

    public void setListaAlunos(List<Aluno> listaAlunos) {
        this.listaAlunos = listaAlunos;
    }

    public List<GrauParentesco> getListaGrausParentesco() {
        return listaGrausParentesco;
    }

    public void setListaGrausParentesco(List<GrauParentesco> listaGrausParentesco) {
        this.listaGrausParentesco = listaGrausParentesco;
    }

    public List<ResponsavelBuscaAluno> getListaResponsaveisBusca() {
        return listaResponsaveisBusca;
    }

    public void setListaResponsaveisBusca(List<ResponsavelBuscaAluno> listaResponsaveisBusca) {
        this.listaResponsaveisBusca = listaResponsaveisBusca;
    }

    public List<AnoLectivo> getListaAnosLectivos() {
        return listaAnosLectivos;
    }

    public void setListaAnosLectivos(List<AnoLectivo> listaAnosLectivos) {
        this.listaAnosLectivos = listaAnosLectivos;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }
}