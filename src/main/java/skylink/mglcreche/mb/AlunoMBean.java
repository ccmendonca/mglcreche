
package skylink.mglcreche.mb;

/**
 * «claudiomendonca»
 */

import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import skylink.mglcreche.dao.AlunoDAO;
import skylink.mglcreche.dao.MunicipioDAO;
import skylink.mglcreche.dao.SexoDAO;
import skylink.mglcreche.modelo.Aluno;
import skylink.mglcreche.modelo.Municipio;
import skylink.mglcreche.modelo.Sexo;

@Named(value = "alunoMBean")
@SessionScoped
public class AlunoMBean implements Serializable {

   @Inject
    private AlunoDAO alunoDAO;
    private List<Aluno> alunos = new ArrayList<>();
    private String dado;
    private SexoDAO sexoDAO = new SexoDAO();
    private List<Sexo> sexos = new ArrayList<>();
    private MunicipioDAO municipioDAO = new MunicipioDAO();
    private List<Municipio> municipios = new ArrayList<>();
    private List<Aluno> pesquisado = new ArrayList<>();
    private String dataNascimento;
    private Aluno selecionado;
    private Aluno aluno;

    @PostConstruct
    public void inicializar() {
        alunos = alunoDAO.findAll();
        aluno = new Aluno();
    }

    public void findParameter() {
        pesquisado = alunoDAO.aleatoryAlunos(dado);
    }
    
    public String update() {
        alunoDAO.update(aluno);
        aluno = new Aluno();
        return "lista_alunos.faces?faces-redirect=true";
    }

    public String selectAluno(Aluno a) {
        aluno = a;
        return "editar_aluno";
    }
    
    public String selectEstudante(Aluno estudante){
        aluno = estudante;
        return "historico_aluno";
    }

    public AlunoDAO getAlunoDAO() {
        return alunoDAO;
    }

    public void setAlunoDAO(AlunoDAO alunoDAO) {
        this.alunoDAO = alunoDAO;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public String getDado() {
        return dado;
    }

    public void setDado(String dado) {
        this.dado = dado;
    }

    public List<Aluno> getPesquisado() {
        return pesquisado;
    }

    public void setPesquisado(List<Aluno> pesquisado) {
        this.pesquisado = pesquisado;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Aluno getSelecionado() {
        return selecionado;
    }

    public void setSelecionado(Aluno selecionado) {
        this.selecionado = selecionado;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public SexoDAO getSexoDAO() {
        return sexoDAO;
    }

    public void setSexoDAO(SexoDAO sexoDAO) {
        this.sexoDAO = sexoDAO;
    }

    public List<Sexo> getSexos() {
        return sexos;
    }

    public void setSexos(List<Sexo> sexos) {
        this.sexos = sexos;
    }

    public MunicipioDAO getMunicipioDAO() {
        return municipioDAO;
    }

    public void setMunicipioDAO(MunicipioDAO municipioDAO) {
        this.municipioDAO = municipioDAO;
    }

    public List<Municipio> getMunicipios() {
        return municipios;
    }

    public void setMunicipios(List<Municipio> municipios) {
        this.municipios = municipios;
    }
    
    
}
