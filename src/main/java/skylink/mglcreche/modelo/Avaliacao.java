/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package skylink.mglcreche.modelo;

import java.util.Date;
import java.util.Objects;

public class Avaliacao {

    private Integer idAvaliacao;
    private String descricaoAvaliacao;
    private Date dataAplicacao;
    private Double pesoAvaliacao;
    private Double notaAvaliacao;
    private Disciplina disciplina;
    private Trimestre trimestre;
    private Profissional profissional;
    private TipoAvaliacao tipoAvaliacao;
    private Turma turma;
    private Aluno aluno;
    private Date dataRegistoAvaliacao;

    public Avaliacao() {
    }

    public Avaliacao(Integer idAvaliacao, String descricaoAvaliacao, Date dataAplicacao, Double pesoAvaliacao, Double notaAvaliacao, Disciplina disciplina, Trimestre trimestre, Profissional profissional, TipoAvaliacao tipoAvaliacao, Turma turma, Aluno aluno, Date dataRegistoAvaliacao) {
        this.idAvaliacao = idAvaliacao;
        this.descricaoAvaliacao = descricaoAvaliacao;
        this.dataAplicacao = dataAplicacao;
        this.pesoAvaliacao = pesoAvaliacao;
        this.notaAvaliacao = notaAvaliacao;
        this.disciplina = disciplina;
        this.trimestre = trimestre;
        this.profissional = profissional;
        this.tipoAvaliacao = tipoAvaliacao;
        this.turma = turma;
        this.aluno = aluno;
        this.dataRegistoAvaliacao = dataRegistoAvaliacao;
    }

   

    public Integer getIdAvaliacao() {
        return idAvaliacao;
    }

    public void setIdAvaliacao(Integer idAvaliacao) {
        this.idAvaliacao = idAvaliacao;
    }

    public String getDescricaoAvaliacao() {
        return descricaoAvaliacao;
    }

    public void setDescricaoAvaliacao(String descricaoAvaliacao) {
        this.descricaoAvaliacao = descricaoAvaliacao;
    }

    public Date getDataAplicacao() {
        return dataAplicacao;
    }

    public void setDataAplicacao(Date dataAplicacao) {
        this.dataAplicacao = dataAplicacao;
    }

    public Double getPesoAvaliacao() {
        return pesoAvaliacao;
    }

    public void setPesoAvaliacao(Double pesoAvaliacao) {
        this.pesoAvaliacao = pesoAvaliacao;
    }

    public Double getNotaAvaliacao() {
        return notaAvaliacao;
    }

    public void setNotaAvaliacao(Double notaAvaliacao) {
        this.notaAvaliacao = notaAvaliacao;
    }

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

    public Date getDataRegistoAvaliacao() {
        return dataRegistoAvaliacao;
    }

    public void setDataRegistoAvaliacao(Date dataRegistoAvaliacao) {
        this.dataRegistoAvaliacao = dataRegistoAvaliacao;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.idAvaliacao);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Avaliacao other = (Avaliacao) obj;
        return Objects.equals(this.idAvaliacao, other.idAvaliacao);
    }

    @Override
    public String toString() {
        return "Avaliacao{" + "idAvaliacao=" + idAvaliacao + '}';
    }

    

    

}
