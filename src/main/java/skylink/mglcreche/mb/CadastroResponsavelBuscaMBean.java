package skylink.mglcreche.mb;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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
 * @author Henriques
 */
@Named(value = "cadastroResponsavelBuscaBean")
@SessionScoped
public class CadastroResponsavelBuscaMBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final String PAGINA_LISTA =
            "/responsavelbusca/lista_cadastro_responsavel_busca.xhtml?faces-redirect=true";
    private static final String PAGINA_EDITAR =
            "/responsavelbusca/editar_cadastro_responsavel_busca.xhtml?faces-redirect=true";

    private CadastroResponsavelBusca cadastro;
    private List<CadastroResponsavelBusca> listaCadastros;
    private List<CadastroResponsavelBusca> listaFiltrada;
    private List<Aluno> listaAlunos;
    private List<GrauParentesco> listaGrausParentesco;
    private List<ResponsavelBuscaAluno> listaResponsaveisBusca;
    private List<AnoLectivo> listaAnosLectivos;

    private String nomeAluno;

    @PostConstruct
    public void init() {
        try {
            GrauParentescoDAO grauParentescoDAO = new GrauParentescoDAO();
            ResponsavelBuscaAlunoDAO responsavelBuscaAlunoDAO = new ResponsavelBuscaAlunoDAO();
            AnoLectivoDAO anoLectivoDAO = new AnoLectivoDAO();

            listaGrausParentesco = grauParentescoDAO.findAll();
            listaResponsaveisBusca = responsavelBuscaAlunoDAO.findAll();
            listaAnosLectivos = anoLectivoDAO.findAll();

            // Tabela inicia vazia
            listaCadastros = new ArrayList<>();
            listaFiltrada = new ArrayList<>();

        } catch (Exception e) {
            addErro("Falha ao carregar dados iniciais: " + e.getMessage());
        }

        Map<String, Object> session = FacesContext.getCurrentInstance()
                .getExternalContext().getSessionMap();

        CadastroResponsavelBusca editando =
                (CadastroResponsavelBusca) session.get("cadastroResponsavelBuscaEditando");

        if (editando != null) {
            this.cadastro = editando;
            session.remove("cadastroResponsavelBuscaEditando");
        } else if (this.cadastro == null) {
            this.cadastro = new CadastroResponsavelBusca();
        }
    }

    public void pesquisa() {
        try {
            CadastroResponsavelBuscaDAO dao = new CadastroResponsavelBuscaDAO();
            List<CadastroResponsavelBusca> todos = dao.findAll();

            if (nomeAluno != null && !nomeAluno.trim().isEmpty()) {
                String filtro = nomeAluno.trim().toLowerCase(Locale.ROOT);
                List<CadastroResponsavelBusca> filtrados = new ArrayList<>();
                for (CadastroResponsavelBusca c : todos) {
                    if (c.getAluno() != null
                            && c.getAluno().getNomeAluno() != null
                            && c.getAluno().getNomeAluno()
                                    .toLowerCase(Locale.ROOT)
                                    .contains(filtro)) {
                        filtrados.add(c);
                    }
                }
                listaCadastros = filtrados;
            } else {
                listaCadastros = new ArrayList<>();
            }
            listaFiltrada = new ArrayList<>(listaCadastros);

        } catch (Exception e) {
            addErro("Falha ao pesquisar: " + e.getMessage());
        }
    }

    public void limpar() {
        this.nomeAluno = null;
        this.listaCadastros = new ArrayList<>();
        this.listaFiltrada = new ArrayList<>();
    }

    public String editar(CadastroResponsavelBusca c) {
        if (c == null) {
            addErro("Registro inválido para edição.");
            return null;
        }
        this.cadastro = c;
        FacesContext.getCurrentInstance()
                .getExternalContext()
                .getSessionMap()
                .put("cadastroResponsavelBuscaEditando", c);
        return PAGINA_EDITAR;
    }

    public String salvar() {
        try {
            CadastroResponsavelBuscaDAO dao = new CadastroResponsavelBuscaDAO();
            if (dao.save(cadastro)) {
                cadastro = new CadastroResponsavelBusca();
                recarregarLista();
                addInfo("Dados guardados com sucesso.");
                return PAGINA_LISTA;
            }
            addErro("Falha ao guardar dados.");
            return null;

        } catch (Exception e) {
            addErro("Falha ao guardar: " + e.getMessage());
            return null;
        }
    }

    public String atualizar() {
        try {
            CadastroResponsavelBuscaDAO dao = new CadastroResponsavelBuscaDAO();
            if (dao.actualizar(cadastro)) {
                cadastro = new CadastroResponsavelBusca();
                recarregarLista();
                addInfo("Atualizado com sucesso.");
                return PAGINA_LISTA;
            }
            addErro("Falha ao atualizar.");
            return null;

        } catch (Exception e) {
            addErro("Falha ao atualizar: " + e.getMessage());
            return null;
        }
    }

    public void eliminar(CadastroResponsavelBusca c) {
        try {
            if (c == null || c.getIdResponsavelBusca() == null) {
                addErro("Registro inválido para eliminação.");
                return;
            }
            CadastroResponsavelBuscaDAO dao = new CadastroResponsavelBuscaDAO();
            if (dao.delete(c.getIdResponsavelBusca())) {
                if (listaCadastros != null) {
                    listaCadastros.remove(c);
                }
                if (listaFiltrada != null) {
                    listaFiltrada.remove(c);
                }
                addInfo("Eliminado com sucesso.");
            } else {
                addErro("Falha ao eliminar.");
            }
        } catch (Exception e) {
            addErro("Falha ao eliminar: " + e.getMessage());
        }
    }

    public void novo() {
        cadastro = new CadastroResponsavelBusca();
    }

    private void recarregarLista() {
        try {
            CadastroResponsavelBuscaDAO dao = new CadastroResponsavelBuscaDAO();
            listaCadastros = dao.findAll();
            listaFiltrada = new ArrayList<>(listaCadastros);
        } catch (Exception e) {
            addErro("Falha ao recarregar lista: " + e.getMessage());
        }
    }

    private void addInfo(String msg) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", msg));
    }

    private void addErro(String msg) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", msg));
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