package skylink.mglcreche.mb;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import skylink.mglcreche.dao.FormaPagamentoDAO;
import skylink.mglcreche.dao.PropinaDAO;
import skylink.mglcreche.modelo.FormaPagamento;
import skylink.mglcreche.modelo.Propina;

@Named(value = "propinaMBean")
@SessionScoped
public class PropinaMBean implements Serializable {

    private Propina propina = new Propina();
    private List<Propina> propinas = new ArrayList<>();
    private PropinaDAO propinaDAO = new PropinaDAO();
    private FormaPagamentoDAO formaPagamentoDAO = new FormaPagamentoDAO();
    private List<FormaPagamento> formaPagamentos = new ArrayList();
    @Inject
    FacesContext facesContext;

    @PostConstruct
    public void init() {
        formaPagamentos = formaPagamentoDAO.findAll();
        propinas = propinaDAO.findAll();
    }

    public Propina getPropina() {
        return propina;
    }

    public void setPropina(Propina propina) {
        this.propina = propina;
    }

    public List<FormaPagamento> getFormaPagamentos() {
        return formaPagamentos;
    }

    public void setFormaPagamentos(List<FormaPagamento> formaPagamentos) {
        this.formaPagamentos = formaPagamentos;
    }

    public List<Propina> getPropinas() {
        return propinas;
    }

    public void setPropinas(List<Propina> propinas) {
        this.propinas = propinas;
    }

    
    
    
    public void registarPropina() {
        if (propinaDAO.save(propina)) {
            FacesMessage info = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Propina registada com sucesso!");
            facesContext.addMessage(null, info);
        } else {
            FacesMessage info = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Erro ao resgistar com sucesso!");
            facesContext.addMessage(null, info);
        }

    }

}
