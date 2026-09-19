package skylink.mglcreche.modelo;

import java.util.Date;

/**
 *
 * @author Henriques
 */
public class CadastroResponsavelBusca {

    private Integer idResponsavelBusca;
    private Date dataRegisto;
    private Aluno aluno;
    private GrauParentesco grauParentesco;
    private ResponsavelBuscaAluno responsavelBuscaAluno;
    private AnoLectivo anoLectivo;

    public CadastroResponsavelBusca() {
    }

    
    public CadastroResponsavelBusca(Integer idResponsavelBusca, Date dataRegisto,
                                    Aluno aluno, GrauParentesco grauParentesco,
                                    ResponsavelBuscaAluno responsavelBuscaAluno,
                                    AnoLectivo anoLectivo) {
        this.idResponsavelBusca = idResponsavelBusca;
        this.dataRegisto = dataRegisto;
        this.aluno = aluno;
        this.grauParentesco = grauParentesco;
        this.responsavelBuscaAluno = responsavelBuscaAluno;
        this.anoLectivo = anoLectivo;
    }

    public Integer getIdResponsavelBusca() { return idResponsavelBusca; }
    public void setIdResponsavelBusca(Integer idResponsavelBusca) { this.idResponsavelBusca = idResponsavelBusca; }

    public Date getDataRegisto() { return dataRegisto; }
    public void setDataRegisto(Date dataRegisto) { this.dataRegisto = dataRegisto; }

    public Aluno getAluno() { return aluno; }
    public void setAluno(Aluno aluno) { this.aluno = aluno; }

    public GrauParentesco getGrauParentesco() { return grauParentesco; }
    public void setGrauParentesco(GrauParentesco grauParentesco) { this.grauParentesco = grauParentesco; }

    public ResponsavelBuscaAluno getResponsavelBuscaAluno() { return responsavelBuscaAluno; }
    public void setResponsavelBuscaAluno(ResponsavelBuscaAluno responsavelBuscaAluno) { this.responsavelBuscaAluno = responsavelBuscaAluno; }

    public AnoLectivo getAnoLectivo() { return anoLectivo; }
    public void setAnoLectivo(AnoLectivo anoLectivo) { this.anoLectivo = anoLectivo; }

    @Override
    public String toString() {
        return "CadastroResponsavelBusca{" +
                "idResponsavelBusca=" + idResponsavelBusca +
                ", dataRegisto=" + dataRegisto +
                ", aluno=" + aluno +
                ", grauParentesco=" + grauParentesco +
                ", responsavelBuscaAluno=" + responsavelBuscaAluno +
                ", anoLectivo=" + anoLectivo +
                '}';
    }
}