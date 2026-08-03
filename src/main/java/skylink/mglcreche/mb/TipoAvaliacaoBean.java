/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package skylink.mglcreche.mb;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import skylink.mglcreche.dao.TipoAvaliacaoDAO;
import skylink.mglcreche.modelo.TipoAvaliacao;

/**
 *
 * @author root
 */
@Named(value = "tipoAvaliacaoBean")
@RequestScoped
public class TipoAvaliacaoBean {

    private static final Logger LOGGER = Logger.getLogger(TipoAvaliacaoBean.class.getName());
    @Inject
    FacesContext facesContext;

    private TipoAvaliacao tipoAvaliacao = new TipoAvaliacao();
    private TipoAvaliacaoDAO tipoAvaliacaoDAO = new TipoAvaliacaoDAO();
    private List<TipoAvaliacao> tipoAvaliacaos = new ArrayList();

    @PostConstruct
    public void init() {
        tipoAvaliacaos = tipoAvaliacaoDAO.findAll();
    }

    public TipoAvaliacao getTipoAvaliacao() {
        return tipoAvaliacao;
    }

    public void setTipoAvaliacao(TipoAvaliacao tipoAvaliacao) {
        this.tipoAvaliacao = tipoAvaliacao;
    }

    public List<TipoAvaliacao> getTipoAvaliacaos() {
        return tipoAvaliacaos;
    }

    public void setTipoAvaliacaos(List<TipoAvaliacao> tipoAvaliacaos) {
        this.tipoAvaliacaos = tipoAvaliacaos;
    }

    public String save() {
        LOGGER.log(Level.INFO, "saving tipo avalicao@{0}", tipoAvaliacao);
        if (this.tipoAvaliacao.getIdTipoAvaliacao() == null) {
            tipoAvaliacaoDAO.save(tipoAvaliacao);
            tipoAvaliacao = new TipoAvaliacao();
            FacesMessage info = new FacesMessage("Dados guardados com sucesso!");
            facesContext.addMessage(null, info);
        } else {
            tipoAvaliacaoDAO.update(tipoAvaliacao);
            tipoAvaliacao = new TipoAvaliacao();
            FacesMessage info = new FacesMessage("Dados actualizados com sucesso!");
            facesContext.addMessage(null, info);

        }

        return "lista_tipo_avaliacao.xhtml?faces-redirect=true";
    }

    public String eliminar() {
        tipoAvaliacaoDAO.delete(tipoAvaliacao);
        tipoAvaliacao = new TipoAvaliacao();
        return "lista_tipo_avaliacao?faces-redirect=true";
    }

    public String prepararEditar() {
        return "tipo_avaliacao_editar";
    }

  

}
