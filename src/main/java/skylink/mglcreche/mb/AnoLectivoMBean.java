/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package skylink.mglcreche.mb;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import skylink.mglcreche.dao.AnoLectivoDAO;
import skylink.mglcreche.modelo.AnoLectivo;
import skylink.mglcreche.modelo.MesPropina;

/**
 *
 * @author root
 */
@Named(value = "anoLectivoMBean")
@ViewScoped
public class AnoLectivoMBean implements Serializable {

    
    private AnoLectivo anoLectivo = new AnoLectivo();
    private AnoLectivoDAO anoLectivoDAO = new AnoLectivoDAO();
    private List<AnoLectivo> anosLectivos = new ArrayList();
    
     @PostConstruct
    public void init() {
        anosLectivos = anoLectivoDAO.findAll();  
       
    }

    public AnoLectivo getAnoLectivo() {
        return anoLectivo;
    }

    public void setAnoLectivo(AnoLectivo anoLectivo) {
        this.anoLectivo = anoLectivo;
    }

    public List<AnoLectivo> getAnosLectivos() {
        return anosLectivos;
    }

    public void setAnosLectivos(List<AnoLectivo> anosLectivos) {
        this.anosLectivos = anosLectivos;
    }
    
    
    
    
    public String save() {
        if (anoLectivoDAO.save(anoLectivo)) {
            anoLectivo = new AnoLectivo();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso ao Guarda", "Sucesso ao guardar os dados"));
            return "mes_propina_novo?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao Guardar", "Erro ao guardar os dados"));
            return null;
        }
    }

    public String eliminar() {
        anoLectivoDAO.delete(anoLectivo);
        anoLectivo = new AnoLectivo();
        return "mes_propina_novo_lista?faces-redirect=true";
    }

    public String prepararEditar() {
        return "mes_propina_editar";
    }

    public String goToEditarProduto(MesPropina mesPropina) {

        this.anoLectivo = anoLectivoDAO.findById(mesPropina.getIdMesPropina());

        return "mes_propina_editar?faces-redirect=true";
    }

    public String editar() {
        if (anoLectivoDAO.update(anoLectivo)) {
            anoLectivo = new AnoLectivo();
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
