package skylink.mglcreche.mb;

import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import skylink.mglcreche.dao.PropinaDAO;
import skylink.mglcreche.dao.PropinaDetalheDAO;
import skylink.mglcreche.modelo.MesPropina;
import skylink.mglcreche.modelo.Propina;
import skylink.mglcreche.modelo.PropinaDetalhe;

@Named(value = "cestoPropinaDetalheBean")
@SessionScoped
public class CestoPropinaDetalheBean implements Serializable {

    private PropinaDAO propinaDAO = new PropinaDAO();
    private PropinaDetalheDAO propinaDetalheDAO = new PropinaDetalheDAO();
    private List<PropinaDetalhe> cesto = new ArrayList();
     @Inject
    PropinaMBean propinaMBean;
    @Inject
    FacesContext facesContext;

    public List<PropinaDetalhe> getCesto() {
        return cesto;
    }

    public void setCesto(List<PropinaDetalhe> cesto) {
        this.cesto = cesto;
    }
    
    
    
    
    
    

    private int verificaExistenciaMes(MesPropina mesPropina) {
        for (int i = 0; i < this.cesto.size(); i++) {
            if (this.cesto.get(i).getMesPropina().getIdMesPropina() == mesPropina.getIdMesPropina()) {
                return i;
            }
        }
        return -1;

    }

    public String addicionarMesCesto(MesPropina mesPropina) {
        int index = verificaExistenciaMes(mesPropina);
        if (index == -1) {
            this.cesto.add(new PropinaDetalhe(mesPropina, mesPropina.getValorMesPropina()));
        } else {
            FacesMessage info = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Mẽs já colocado no cesto!");
            facesContext.addMessage(null, info);
        }
        return null;
    }

    
     public long totalDaInscricao() {
        long s = 0L;
        Iterator<PropinaDetalhe> iterator = this.cesto.iterator();
        while (iterator.hasNext()) {
            PropinaDetalhe next = iterator.next();
            s = (long) ((double) s + (double) 1 * next.getValorPropina());
        }

        return s;
    }
    
     public String registarPropina(List<PropinaDetalhe> itens) {

        propinaMBean.registarPropina();
        //busca a ultima factura registada
        Propina proprinaActual = propinaDAO.buscaUltimaPropina();

        //percorre o carrinho e regista cada item
        for (PropinaDetalhe item : itens) {
            item.setPropina(proprinaActual);
            item.setMesPropina(item.getMesPropina());
            item.setValorPropina(item.getValorPropina());
           
            propinaDetalheDAO.save(item);
        }
        cesto.clear();

        FacesMessage info = new FacesMessage(FacesMessage.SEVERITY_INFO, "Propina N.º" + proprinaActual.getIdPropina() + " - Dados guardados com sucesso ", "");
        facesContext.addMessage("msg", info);
        facesContext.getExternalContext().getFlash().setKeepMessages(true);
        return "/propinas/propinas_pagamento?faces-redirect=true";

    }
    
    
}
