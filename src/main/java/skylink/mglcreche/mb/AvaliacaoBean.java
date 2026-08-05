/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package skylink.mglcreche.mb;

import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import skylink.mglcreche.dao.AlunoDAO;
import skylink.mglcreche.dao.AvaliacaoDAO;
import skylink.mglcreche.dao.MatriculaDAO;
import skylink.mglcreche.modelo.Aluno;
import skylink.mglcreche.modelo.Avaliacao;
import skylink.mglcreche.modelo.Disciplina;
import skylink.mglcreche.modelo.Matricula;
import skylink.mglcreche.modelo.Profissional;
import skylink.mglcreche.modelo.TipoAvaliacao;
import skylink.mglcreche.modelo.Trimestre;
import skylink.mglcreche.modelo.Turma;

@Named(value = "avaliacaoBean")
@SessionScoped
public class AvaliacaoBean implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(AvaliacaoBean.class.getName());
    @Inject
    FacesContext facesContext;

    private Disciplina disciplina = new Disciplina();
    private Trimestre trimestre = new Trimestre();
    private Profissional profissional = new Profissional();
    private TipoAvaliacao tipoAvaliacao = new TipoAvaliacao();
    private Turma turma = new Turma();
    private Aluno aluno = new Aluno();
    private Avaliacao avaliacao = new Avaliacao();
    private List<Avaliacao> avalicaoAlunos = new ArrayList<>();
    private AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();
    private AlunoDAO alunoDAO = new AlunoDAO();
    private List<Aluno> alunosDaTurma = new ArrayList<>();

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public Trimestre getTrimestre() {
        return trimestre;
    }

    public void setTrimestre(Trimestre trimestre) {
        this.trimestre = trimestre;
    }

    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }

    public TipoAvaliacao getTipoAvaliacao() {
        return tipoAvaliacao;
    }

    public void setTipoAvaliacao(TipoAvaliacao tipoAvaliacao) {
        this.tipoAvaliacao = tipoAvaliacao;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Avaliacao getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
    }

    public List<Avaliacao> getAvalicaoAlunos() {
        return avalicaoAlunos;
    }

    public void setAvalicaoAlunos(List<Avaliacao> avalicaoAlunos) {
        this.avalicaoAlunos = avalicaoAlunos;
    }

    public void alunosEncontrados(List<Aluno> alunos) {
        for (Aluno alunoEncontrado : alunos) {
            addicionarAlunoAvaliacoes(alunoEncontrado);
        }

    }

    public void addicionarAlunoAvaliacoes(Aluno aluno) {
        this.avalicaoAlunos.add(new Avaliacao(aluno));
    }

    public void carregarEstudantesDaTurma(Integer idTurma) {
        alunosDaTurma = alunoDAO.findAllAlunosTurma(idTurma);
        alunosEncontrados(alunosDaTurma);

    }

    public void registarNotas(List<Avaliacao> itens) {
        boolean flag=false;
        for (Avaliacao item : itens) {

            item.setTurma(avaliacao.getTurma());
            item.setDisciplina(avaliacao.getDisciplina());
            item.setTipoAvaliacao(avaliacao.getTipoAvaliacao());
            item.setTrimestre(avaliacao.getTrimestre());
            item.setProfissional(avaliacao.getProfissional());
            item.setDataAplicacao(avaliacao.getDataAplicacao());
            item.setAluno(item.getAluno());
            item.setNotaAvaliacao(item.getNotaAvaliacao());
           flag=avaliacaoDAO.save(item);
        }
        if(flag==true){
         itens.clear();

        FacesMessage info = new FacesMessage(FacesMessage.SEVERITY_INFO, "Notas da turma N.º" + avaliacao.getTurma().getCodigoTurma() + " - Dados guardados com sucesso ", "");
        facesContext.addMessage("msg", info);
        facesContext.getExternalContext().getFlash().setKeepMessages(true);
        //  imprimirReciboInscricao(numeroInscricao);
        }else{
        
         FacesMessage info = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Notas da turma N.º" + avaliacao.getTurma().getCodigoTurma() + " - Erro ao guardar dados ", "");
        facesContext.addMessage("msg", info);
        facesContext.getExternalContext().getFlash().setKeepMessages(true);
        }
       

    }

    public String save() {
        LOGGER.log(Level.INFO, "saving tipo avalicao@{0}", tipoAvaliacao);
        if (this.avaliacao.getIdAvaliacao() == null) {
            avaliacaoDAO.save(avaliacao);
            avaliacao = new Avaliacao();
            FacesMessage info = new FacesMessage("Dados guardados com sucesso!");
            facesContext.addMessage(null, info);
        } else {
            avaliacaoDAO.update(avaliacao);
            avaliacao = new Avaliacao();
            FacesMessage info = new FacesMessage("Dados actualizados com sucesso!");
            facesContext.addMessage(null, info);

        }

        return "lista_tipo_avaliacao.xhtml?faces-redirect=true";
    }

    public String eliminar() {
        avaliacaoDAO.delete(avaliacao);
        avaliacao = new Avaliacao();
        return "lista_tipo_avaliacao?faces-redirect=true";
    }

    public String prepararEditar() {
        return "tipo_avaliacao_editar";
    }

}
