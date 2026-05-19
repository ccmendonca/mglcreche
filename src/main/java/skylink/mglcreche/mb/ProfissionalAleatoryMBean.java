
package skylink.mglcreche.mb;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.view.ViewScoped;
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
@ViewScoped
public class ProfissionalAleatoryMBean implements Serializable {

    @Inject
    private transient ProfissionalDAO profissionalDAO;
    private MunicipioDAO municipioDao = new MunicipioDAO();
    private CategoriaProfissionalDAO categoriaProfissionalDao = new CategoriaProfissionalDAO();
    private String dataNascimento;
    private List<Profissional> profissionais = new ArrayList<>();
    private String name;
    private List<Profissional> pesquisado = new ArrayList<>();
    private Profissional profissional = new Profissional();
    private Municipio municipio;
    private CategoriaProfissional categoriaProfissional;
    private Sexo sexo;
    private SexoDAO sexoDAO = new SexoDAO();
    private List<CategoriaProfissional> categorias = new ArrayList<>();
    private List<Sexo> sexos = new ArrayList<>();
    private List<Municipio> municipios = new ArrayList<>();
    
    @PostConstruct
    public void inicializar(){
        profissionais = profissionalDAO.findAll();
        sexos = sexoDAO.findAll();
        municipios = municipioDao.findAll();
        categorias =categoriaProfissionalDao.findAll();
        profissional = new Profissional();
    }
    
    public String adicionar(){
        profissionalDAO.save(profissional);
        profissional = new Profissional();
        return "lista_profissionais.faces?faces-redirect=true";
    }

    public void opcional() {
        pesquisado = profissionalDAO.aleatory(name);
        //PrimeFaces.current().ajax().update("");
    }

    public ProfissionalDAO getProfissionalDAO() {
        return profissionalDAO;
    }

    public void setProfissionalDAO(ProfissionalDAO profissionalDAO) {
        this.profissionalDAO = profissionalDAO;
    }

    public MunicipioDAO getMunicipioDao() {
        return municipioDao;
    }

    public void setMunicipioDao(MunicipioDAO municipioDao) {
        this.municipioDao = municipioDao;
    }

    public CategoriaProfissionalDAO getCategoriaProfissionalDao() {
        return categoriaProfissionalDao;
    }

    public void setCategoriaProfissionalDao(CategoriaProfissionalDAO categoriaProfissionalDao) {
        this.categoriaProfissionalDao = categoriaProfissionalDao;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
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

    public List<Profissional> getPesquisado() {
        return pesquisado;
    }

    public void setPesquisado(List<Profissional> pesquisado) {
        this.pesquisado = pesquisado;
    }

    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public CategoriaProfissional getCategoriaProfissional() {
        return categoriaProfissional;
    }

    public void setCategoriaProfissional(CategoriaProfissional categoriaProfissional) {
        this.categoriaProfissional = categoriaProfissional;
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

    public List<CategoriaProfissional> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<CategoriaProfissional> categorias) {
        this.categorias = categorias;
    }

    public List<Sexo> getSexos() {
        return sexos;
    }

    public void setSexos(List<Sexo> sexos) {
        this.sexos = sexos;
    }

    public List<Municipio> getMunicipios() {
        return municipios;
    }

    public void setMunicipios(List<Municipio> municipios) {
        this.municipios = municipios;
    }

    
}
