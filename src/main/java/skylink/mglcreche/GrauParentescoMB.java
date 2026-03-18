/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package skylink.mglcreche;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import skylink.mglcreche.dao.GrauParentescoDAO;
import skylink.mglcreche.modelo.GrauParentesco;


@Named(value = "grauParentescoMB")
@ViewScoped
public class GrauParentescoMB implements Serializable {
    GrauParentesco grauParentesco = new GrauParentesco();
    
    GrauParentescoDAO grauParentescoDAO = new GrauParentescoDAO();

  

    List<GrauParentesco> grauParentescos = new ArrayList<>();
    
    @PostConstruct
    public void init() {
        grauParentescos = grauParentescoDAO.findAll();  
       
    }

    public GrauParentesco getGrauParentesco() {
        return grauParentesco;
    }

    public void setGrauParentesco(GrauParentesco grauParentesco) {
        this.grauParentesco = grauParentesco;
    }

    public List<GrauParentesco> getGrauParentescos() {
        return grauParentescos;
    }

    public void setGrauParentescos(List<GrauParentesco> grauParentescos) {
        this.grauParentescos = grauParentescos;
    }
    
    
    
    public String save() {
        if (grauParentescoDAO.save(grauParentesco)) {
            grauParentesco = new GrauParentesco();
             addMessage(FacesMessage.SEVERITY_ERROR, "guardar", "Sucesso ao guardar os dados");
            return "/pages/vendas/forma_pagamento_novo?faces-redirect=true";
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
