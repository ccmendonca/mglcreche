
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
import skylink.mglcreche.dao.MatriculaDAO;
import skylink.mglcreche.dao.MatriculaDetalheDAO;
import skylink.mglcreche.modelo.Matricula;
import skylink.mglcreche.modelo.MatriculaDetalhe;
import skylink.mglcreche.modelo.Servico;

/**
 * «claudiomendonca»
 */

@Named(value = "cestoMatriculaDetalheMBean")
@SessionScoped
public class CestoMatriculaDetalheMBean implements Serializable {

    private MatriculaDAO matriculaDAO = new MatriculaDAO();
    private Matricula matricula = new Matricula();
    private Servico servico = new Servico();
    private MatriculaDetalheDAO MatriculadetalheDAO = new MatriculaDetalheDAO();
    private List<MatriculaDetalhe> cesto = new ArrayList<>();
    
    @Inject
    MatriculaMBean matriculaMBean;
    @Inject
    FacesContext facesContext;
    
    private int verificaExistenciaServico(Servico servico) {
        for (int v = 0; v < this.cesto.size(); v++) {
            if (this.cesto.get(v).getServico().getIdServico() == servico.getIdServico()) {
                return v;
            }
        }
        return -1;

    }

    public String adicionarServicoCesto(Servico servico) {
        int index = verificaExistenciaServico(servico);
        if (index == -1) {
            this.cesto.add(new MatriculaDetalhe(servico, servico.getPrecoServico()));
        } else {
            FacesMessage info = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Serviço já colocado no cesto!");
            facesContext.addMessage(null, info);
        }
        return null;
    }

    
     public long totalAPagar() {
        long t = 0L;
        Iterator<MatriculaDetalhe> iterator = this.cesto.iterator();
        while (iterator.hasNext()) {
            MatriculaDetalhe next = iterator.next();
            t = (long) ((double) t + (double) 1 * next.getPrecoServico());
        }

        return t;
    }
    
     public String registarMatricula(List<MatriculaDetalhe> itens) {

        matriculaMBean.registarMatricula();
        //busca a ultima factura registada
        Matricula matriculaActual = matriculaDAO.buscaUltimaMatricula();

        //percorre o carrinho e regista cada item
        for (MatriculaDetalhe item : itens) {
            item.setMatricula(matriculaActual);
            item.setServico(item.getServico());
            item.setPrecoServico(item.getPrecoServico());
           
            matriculaDAO.save(matricula);
        }
        cesto.clear();

        FacesMessage info = new FacesMessage(FacesMessage.SEVERITY_INFO, "Matrícula N.º" + matriculaActual.getIdMatricula() + " - Dados guardados com sucesso ", "");
        facesContext.addMessage("msg", info);
        facesContext.getExternalContext().getFlash().setKeepMessages(true);
        return "/matriculas/matriculas_selecionar_aluno?faces-redirect=true";

    }

    public MatriculaDAO getMatriculaDAO() {
        return matriculaDAO;
    }

    public void setMatriculaDAO(MatriculaDAO matriculaDAO) {
        this.matriculaDAO = matriculaDAO;
    }

    public MatriculaDetalheDAO getMatriculadetalheDAO() {
        return MatriculadetalheDAO;
    }

    public void setMatriculadetalheDAO(MatriculaDetalheDAO MatriculadetalheDAO) {
        this.MatriculadetalheDAO = MatriculadetalheDAO;
    }

    public List<MatriculaDetalhe> getCesto() {
        return cesto;
    }

    public void setCesto(List<MatriculaDetalhe> cesto) {
        this.cesto = cesto;
    }

    public MatriculaMBean getMatriculaMBean() {
        return matriculaMBean;
    }

    public void setMatriculaMBean(MatriculaMBean matriculaMBean) {
        this.matriculaMBean = matriculaMBean;
    }

    public FacesContext getFacesContext() {
        return facesContext;
    }

    public void setFacesContext(FacesContext facesContext) {
        this.facesContext = facesContext;
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }
}
