
package skylink.mglcreche.mb;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.List;
import skylink.mglcreche.dao.CategoriaProfissionalDAO;
import skylink.mglcreche.modelo.CategoriaProfissional;

/**
 * «claudiomendonca»
 */

@Named(value = "categoriaProfissionalMBean")
@ViewScoped
public class CategoriaProfissionalMBean implements Serializable {

    @Inject
    private transient CategoriaProfissionalDAO categoriaProfissionalDAO;
    private List<CategoriaProfissional> catProfissionais;
    private List<CategoriaProfissional> pesquisado;
    private String name;
    private CategoriaProfissional categoriaProfissional = new CategoriaProfissional();

    public CategoriaProfissional getCategoriaProfissional() {
        return categoriaProfissional;
    }

    public void setCategoriaProfissional(CategoriaProfissional categoriaProfissional) {
        this.categoriaProfissional = categoriaProfissional;
    }

    @PostConstruct
    public void init() {
        catProfissionais = categoriaProfissionalDAO.findAll();
    }

    public String guardar() {
        categoriaProfissionalDAO.save(categoriaProfissional);
        categoriaProfissional = new CategoriaProfissional();
        return "categorias_profissionais.faces?faces-redirect=true";
    }

    public String listarPorNome() {
        pesquisado = categoriaProfissionalDAO.findByName(name);
        return null;
    }

    public CategoriaProfissionalDAO getCategoriaProfissionalDAO() {
        return categoriaProfissionalDAO;
    }

    public void setCategoriaProfissionalDAO(CategoriaProfissionalDAO categoriaProfissionalDAO) {
        this.categoriaProfissionalDAO = categoriaProfissionalDAO;
    }

    public List<CategoriaProfissional> getCatProfissionais() {
        return catProfissionais;
    }

    public void setCatProfissionais(List<CategoriaProfissional> catProfissionais) {
        this.catProfissionais = catProfissionais;
    }

    public List<CategoriaProfissional> getPesquisado() {
        return pesquisado;
    }

    public void setPesquisado(List<CategoriaProfissional> pesquisado) {
        this.pesquisado = pesquisado;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
