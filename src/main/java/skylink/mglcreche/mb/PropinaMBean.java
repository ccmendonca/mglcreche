package skylink.mglcreche.mb;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import skylink.mglcreche.dao.FormaPagamentoDAO;
import skylink.mglcreche.dao.PropinaDAO;
import skylink.mglcreche.modelo.FormaPagamento;
import skylink.mglcreche.modelo.Propina;

@Named(value = "propinaMBean")
@ViewScoped
public class PropinaMBean implements Serializable {

    private Propina propina = new Propina();
    private PropinaDAO propinaDAO = new PropinaDAO();
    private FormaPagamentoDAO formaPagamentoDAO = new FormaPagamentoDAO();
    private List<FormaPagamento> formaPagamentos = new ArrayList();
    @Inject
    FacesContext facesContext;

    @PostConstruct
    public void init() {
        formaPagamentos = formaPagamentoDAO.findAll();
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

    
    
    
    public void registarPropina() {
        if (propinaDAO.save(propina)) {
            FacesMessage info = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Propina criada com sucesso!");
            facesContext.addMessage(null, info);
        } else {
            FacesMessage info = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro ao criada com sucesso!");
            facesContext.addMessage(null, info);
        }

    }

}
