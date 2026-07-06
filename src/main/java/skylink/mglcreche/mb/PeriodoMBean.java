package skylink.mglcreche.bean;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import skylink.mglcreche.dao.PeriodoDAO;
import skylink.mglcreche.modelo.Periodo;

/**
 *
 * @Henriques
 */
@Named(value = "periodoMBean")
@SessionScoped
public class PeriodoMBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Periodo periodo;
    private List<Periodo> periodos;
    private PeriodoDAO periodoDAO;

    @PostConstruct
    public void init() {
        periodoDAO = new PeriodoDAO();
        limpar();
        
        try {
            listarTodos();
        } catch (SQLException e) {
            throw new RuntimeException("Falha crítica ao inicializar períodos", e);
        }
    }

    public void limpar() {
        periodo = new Periodo();
    }

    public void listarTodos() throws SQLException {
        periodos = periodoDAO.findAll();
    }

    public void salvar() throws Exception {
        if (periodo.getIdPeriodo() == null) {
            adicionarMensagemSucesso("Período cadastrado com sucesso!");
        } else {

            adicionarMensagemSucesso("Período atualizado com sucesso!");
        }
        limpar();
        listarTodos(); 
    }

    public void excluir(Periodo p) throws Exception {
        adicionarMensagemSucesso("Período excluído com sucesso!");
        listarTodos(); 
    }

    public void prepararEditar(Periodo p) {
        this.periodo = p;
    }

    private void adicionarMensagemSucesso(String mensagem) {
        FacesContext.getCurrentInstance().addMessage(null, 
            new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", mensagem));
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    public List<Periodo> getPeriodos() {
        return periodos;
    }

    public void setPeriodos(List<Periodo> periodos) {
        this.periodos = periodos;
    }
}