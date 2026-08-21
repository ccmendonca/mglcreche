package skylink.mglcreche.mb;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ActionEvent;
import jakarta.inject.Named;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import skylink.mglcreche.dao.FormaPagamentoDAO;
import skylink.mglcreche.dao.MatriculaDAO;
import skylink.mglcreche.dao.TurmaDAO;
import skylink.mglcreche.modelo.FormaPagamento;
import skylink.mglcreche.modelo.Matricula;
import skylink.mglcreche.modelo.Turma;

@Named(value = "matriculaMBean")
@SessionScoped
public class MatriculaMBean implements Serializable {

    private Matricula matricula = new Matricula();
    private MatriculaDAO matriculaDAO = new MatriculaDAO();
    private List<Matricula> matriculas = new ArrayList<>();
    private List<Matricula> matriculasEntreDatas = new ArrayList<>();
    private Turma turma = new Turma();
    private TurmaDAO turmaDAO = new TurmaDAO();
    private List<Turma> turmas = new ArrayList<>();
    private FormaPagamentoDAO formaPagamentoDAO = new FormaPagamentoDAO();
    private List<FormaPagamento> formaPagamentos = new ArrayList();
    private FormaPagamento formapagamento = new FormaPagamento();
    private Date dataInicio, dataFim;
    @Inject
    FacesContext facesContext;
    @Inject
    GestorImpressao gestorImpressao;

    @PostConstruct
    public void inicializar() {
        formaPagamentos = formaPagamentoDAO.findAll();
        turmas = turmaDAO.findAll();
        matriculasEntreDatas = matriculaDAO.findAllMatriculasEntreDatas();
    }

    public void registarMatricula() {
        if (matriculaDAO.save(matricula)) {
            FacesMessage info = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Matrícula criada com sucesso!");
            facesContext.addMessage(null, info);
        } else {
            FacesMessage info = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro ao crir matrícula!");
            facesContext.addMessage(null, info);
        }

    }

    public List<Matricula> getMatriculasEntreDatas() {
        return matriculasEntreDatas;
    }

    public void setMatriculasEntreDatas(List<Matricula> matriculasEntreDatas) {
        this.matriculasEntreDatas = matriculasEntreDatas;
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }

    public MatriculaDAO getMatriculaDAO() {
        return matriculaDAO;
    }

    public void setMatriculaDAO(MatriculaDAO matriculaDAO) {
        this.matriculaDAO = matriculaDAO;
    }

    public List<Matricula> getMatriculas() {
        return matriculas;
    }

    public void setMatriculas(List<Matricula> matriculas) {
        this.matriculas = matriculas;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public TurmaDAO getTurmaDAO() {
        return turmaDAO;
    }

    public void setTurmaDAO(TurmaDAO turmaDAO) {
        this.turmaDAO = turmaDAO;
    }

    public List<Turma> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }

    public FacesContext getFacesContext() {
        return facesContext;
    }

    public void setFacesContext(FacesContext facesContext) {
        this.facesContext = facesContext;
    }

    public FormaPagamentoDAO getFormaPagamentoDAO() {
        return formaPagamentoDAO;
    }

    public void setFormaPagamentoDAO(FormaPagamentoDAO formaPagamentoDAO) {
        this.formaPagamentoDAO = formaPagamentoDAO;
    }

    public List<FormaPagamento> getFormaPagamentos() {
        return formaPagamentos;
    }

    public void setFormaPagamentos(List<FormaPagamento> formaPagamentos) {
        this.formaPagamentos = formaPagamentos;
    }

    public FormaPagamento getFormapagamento() {
        return formapagamento;
    }

    public void setFormapagamento(FormaPagamento formapagamento) {
        this.formapagamento = formapagamento;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public String imprimirDiarioInscricoes() {
        String relatorio = "inscricoes_diario.jasper";
        HashMap parametros = new HashMap();
        parametros.put("dataInicio", dataInicio);
        parametros.put("dataFim", dataFim);
        //  gestorImpressao.visualizarPDF(relatorio, parametros);
        return null;
    }

    public void listaDiariosInscricoes(ActionEvent event) {

        //   inscricaos = inscricaoService.findInscricoesEntreDatasDiario(dataInicio, dataFim);
    }

    public void imprimirReciboMatricula(Integer idMatricula) {
        String relatorio = "recibo_matricula.jasper";
        HashMap parametros = new HashMap();

        parametros.put("NUMERO_RECIBO", idMatricula);
       gestorImpressao.imprimirPDF(relatorio, parametros);
        

    }

    public void downloadReciboMatricula(Long idIscricao) {
        String relatorio = "recibo_inscricao_download.jasper";
        HashMap parametros = new HashMap();
        Integer numeroRecibo = idIscricao.intValue();
        parametros.put("NUMERO_RECIBO", numeroRecibo);
        //   gestorImpressao.downloadPDF(relatorio, parametros, numeroRecibo);

    }

}
