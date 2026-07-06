package skylink.mglcreche.modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @Henriques
 */
public class AlocacaoProfessor implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idAlocacaoProfessor;
    private Integer CargaHorariaSemanal;
    private Integer idProfissional;
   // private Disciplina disciplina;
    private Turma turma;
    private AnoLectivo anoLectivo;
    private Trimestre trimestre;
    
    private LocalDate dataAlocacao;
    private LocalDateTime dataRegisto;
    
    public AlocacaoProfessor(){
    
    }

    public AlocacaoProfessor(Integer idAlocacaoProfessor, Integer CargaHorariaSemanal, Integer idProfissional, 
    //                         Disciplina disciplina, Turma turma, AnoLectivo anoLectivo, Trimestre trimestre,
                             LocalDate dataAlocacao, LocalDateTime dataRegisto) {
        this.idAlocacaoProfessor = idAlocacaoProfessor;
        this.CargaHorariaSemanal = CargaHorariaSemanal;
        this.idProfissional = idProfissional;
  //      this.disciplina = disciplina;
        this.turma = turma;
        this.anoLectivo = anoLectivo;
        this.trimestre = trimestre;
        this.dataAlocacao = dataAlocacao;
        this.dataRegisto = dataRegisto;
    }

    public Integer getIdAlocacaoProfessor() {
        return idAlocacaoProfessor;
    }

    public void setIdAlocacaoProfessor(Integer idAlocacaoProfessor) {
        this.idAlocacaoProfessor = idAlocacaoProfessor;
    }

    public Integer getCargaHorariaSemanal() {
        return CargaHorariaSemanal;
    }

    public void setCargaHorariaSemanal(Integer CargaHorariaSemanal) {
        this.CargaHorariaSemanal = CargaHorariaSemanal;
    }

    public Integer getIdProfissional() {
        return idProfissional;
    }

    public void setIdProfissional(Integer idProfissional) {
        this.idProfissional = idProfissional;
    }

  
    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public AnoLectivo getAnoLectivo() {
        return anoLectivo;
    }

    public void setAnoLectivo(AnoLectivo anoLectivo) {
        this.anoLectivo = anoLectivo;
    }

    public Trimestre getTrimestre() {
        return trimestre;
    }

    public void setTrimestre(Trimestre trimestre) {
        this.trimestre = trimestre;
    }

    public LocalDate getDataAlocacao() {
        return dataAlocacao;
    }

    public void setDataAlocacao(LocalDate dataAlocacao) {
        this.dataAlocacao = dataAlocacao;
    }

    public LocalDateTime getDataRegisto() {
        return dataRegisto;
    }

    public void setDataRegisto(LocalDateTime dataRegisto) {
        this.dataRegisto = dataRegisto;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + Objects.hashCode(this.idAlocacaoProfessor);
        hash = 73 * hash + Objects.hashCode(this.CargaHorariaSemanal);
        hash = 73 * hash + Objects.hashCode(this.idProfissional);
       // hash = 73 * hash + Objects.hashCode(this.disciplina);
        hash = 73 * hash + Objects.hashCode(this.turma);
        hash = 73 * hash + Objects.hashCode(this.anoLectivo);
        hash = 73 * hash + Objects.hashCode(this.trimestre);
        hash = 73 * hash + Objects.hashCode(this.dataAlocacao);
        hash = 73 * hash + Objects.hashCode(this.dataRegisto);
        return hash;
    }



    @Override
    public String toString() {
        return "AlocacaoProfessor{" + "idAlocacaoProfessor=" + idAlocacaoProfessor + ", CargaHorariaSemanal=" + CargaHorariaSemanal + ", idProfissional=" + idProfissional + ", disciplina="  + ", turma=" + turma + ", anoLectivo=" + anoLectivo + ", trimestre=" + trimestre + ", dataAlocacao=" + dataAlocacao + ", dataRegisto=" + dataRegisto + '}';
    }
}