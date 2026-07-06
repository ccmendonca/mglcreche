
package skylink.mglcreche.mb;

import jakarta.annotation.PostConstruct;
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
 * «claudiomendonca
 */

@Named(value = "profissionalMBean")
@SessionScoped
public class ProfissionalMBean implements Serializable {

    @Inject
    private transient ProfissionalDAO profissionalDAO;
    private MunicipioDAO municipioDao = new MunicipioDAO();
    private CategoriaProfissionalDAO categoriaProfissionalDAO = new CategoriaProfissionalDAO();
    private List<CategoriaProfissional> categorias = new ArrayList<>();
    private List<Profissional> profissionais;
    private List<Profissional> pesquisado;
    private Profissional selecionado;
    private String name;
    private Profissional profissional = new Profissional();
    private Municipio municipio = new Municipio();
    private List<Municipio> municipios = new ArrayList<>();
    private String dataDeNascimento;
    private List<Sexo> sexos = new ArrayList<>();
    private Sexo sexo = new Sexo();
    private SexoDAO sexoDAO = new SexoDAO();
    private CategoriaProfissional categoriaProfissional = new CategoriaProfissional();

    @PostConstruct
    public void init() {
        profissionais = profissionalDAO.findAll();
        sexos = sexoDAO.findAll();
        municipios = municipioDao.findAll();
        categorias = categoriaProfissionalDAO.findAll();
        profissional = new Profissional();
    }

    public String editar() {
        profissionalDAO.update(profissional);
        profissional = new Profissional();
        return "lista_profissionais.faces?faces-redirect=true";
    }

    public Profissional getSelecionado() {
        return selecionado;
    }

    public void setSelecionado(Profissional selecionado) {
        this.selecionado = selecionado;
    }

    public void findByOptional() {
        pesquisado = profissionalDAO.aleatory(name);
        //PrimeFaces.current().ajax().update("");
    }

    public String selecionar(Profissional prof) {
        profissional = prof;
        return "editar_profissionais";
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

    public CategoriaProfissionalDAO getCategoriaProfissionalDAO() {
        return categoriaProfissionalDAO;
    }

    public void setCategoriaProfissionalDAO(CategoriaProfissionalDAO categoriaProfissionalDAO) {
        this.categoriaProfissionalDAO = categoriaProfissionalDAO;
    }

    public List<CategoriaProfissional> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<CategoriaProfissional> categorias) {
        this.categorias = categorias;
    }

    public List<Profissional> getProfissionais() {
        return profissionais;
    }

    public void setProfissionais(List<Profissional> profissionais) {
        this.profissionais = profissionais;
    }

    public List<Profissional> getPesquisado() {
        return pesquisado;
    }

    public void setPesquisado(List<Profissional> pesquisado) {
        this.pesquisado = pesquisado;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<Municipio> getMunicipios() {
        return municipios;
    }

    public void setMunicipios(List<Municipio> municipios) {
        this.municipios = municipios;
    }

    public String getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(String dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public List<Sexo> getSexos() {
        return sexos;
    }

    public void setSexos(List<Sexo> sexos) {
        this.sexos = sexos;
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
    
}
