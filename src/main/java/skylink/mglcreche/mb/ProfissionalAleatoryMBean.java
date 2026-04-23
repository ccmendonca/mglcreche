
package skylink.mglcreche.mb;

import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import skylink.mglcreche.dao.CategoriaProfissionalDAO;
import skylink.mglcreche.dao.MunicipioDAO;
import skylink.mglcreche.dao.ProfissionalDAO;
import skylink.mglcreche.dao.SexoDAO;
import skylink.mglcreche.modelo.CategoriaProfissional;
import skylink.mglcreche.modelo.Municipio;
import skylink.mglcreche.modelo.Profissional;
import skylink.mglcreche.modelo.Sexo;

/**
 * «claudiomendonca»
 */

@Named(value = "profissionalAleatoryMBean")
@SessionScoped
public class ProfissionalAleatoryMBean implements Serializable {

    @Inject
    private transient ProfissionalDAO profissionalDAO;
    private MunicipioDAO municipioDao;
    private CategoriaProfissionalDAO categoriaProfissionalDao;
    private String dataNascimento;
    private List<Profissional> profissionais = new ArrayList<>();
    private String name;
    private List<Profissional> pesquisado = new ArrayList<>();
    private Profissional profissional = new Profissional();
    private Municipio municiio;
    private CategoriaProfissional categoriaProfissional;
    private Sexo sexo;
    private SexoDAO sexoDAO;

    public String adicionar(){
        profissionalDAO.save(profissional);
        profissional = new Profissional();
        return "lista_profissionais.faces?faces-redirect=true";
    }

    

    public CategoriaProfissionalDAO getCategoriaProfissionalDao() {
        return categoriaProfissionalDao;
    }

    public void setCategoriaProfissionalDao(CategoriaProfissionalDAO categoriaProfissionalDao) {
        this.categoriaProfissionalDao = categoriaProfissionalDao;
    }

    public MunicipioDAO getMunicipioDao() {
        return municipioDao;
    }

    public void setMunicipioDao(MunicipioDAO municipioDao) {
        this.municipioDao = municipioDao;
    }

    public Municipio getMuniciio() {
        return municiio;
    }

    public void setMuniciio(Municipio municiio) {
        this.municiio = municiio;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public SexoDAO getSexoDAO() {
        return sexoDAO;
    }

    public void setSexoDAO(SexoDAO sexoDAO) {
        this.sexoDAO = sexoDAO;
    }

    public CategoriaProfissional getCategoriaProfissional() {
        return categoriaProfissional;
    }

    public void setCategoriaProfissional(CategoriaProfissional categoriaProfissional) {
        this.categoriaProfissional = categoriaProfissional;
    }

    public void opcional() {
        pesquisado = profissionalDAO.aleatory(name);
        //PrimeFaces.current().ajax().update("");
    }

    public ProfissionalDAO getProfissionalDAO() {
        return profissionalDAO;
    }

    public List<Profissional> getPesquisado() {
        return pesquisado;
    }

    public void setPesquisado(List<Profissional> pesquisado) {
        this.pesquisado = pesquisado;
    }

    public void setProfissionalDAO(ProfissionalDAO profissionalDAO) {
        this.profissionalDAO = profissionalDAO;
    }

    public List<Profissional> getProfissionais() {
        return profissionais;
    }

    public void setProfissionais(List<Profissional> profissionais) {
        this.profissionais = profissionais;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String DataNascimento) {
        this.dataNascimento = DataNascimento;
    }

    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }

}
