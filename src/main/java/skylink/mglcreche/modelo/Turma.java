package skylink.mglcreche.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
/**
 *
 * @Henriques
 */
public class Turma implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idTurma;
    private String descricaoTurma;
    private AnoLectivo anoLectivo;
    private Classe classe;
    private Integer numeroMaximo;
    private Boolean activa;
    private Date dataRegisto;

    public Turma() {
    }

    public Turma(Integer idTurma, String descricaoTurma, AnoLectivo anoLectivo, 
                 Classe classe, Integer numeroMaximo, Boolean activa, Date dataRegisto) {
        this.idTurma = idTurma;
        this.descricaoTurma = descricaoTurma;
        this.anoLectivo = anoLectivo;
        this.classe = classe;
        this.numeroMaximo = numeroMaximo;
        this.activa = activa;
        this.dataRegisto = dataRegisto;
    }

    public Integer getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(Integer idTurma) {
        this.idTurma = idTurma;
    }

    public String getDescricaoTurma() {
        return descricaoTurma;
    }

    public void setDescricaoTurma(String descricaoTurma) {
        this.descricaoTurma = descricaoTurma;
    }

    public AnoLectivo getAnoLectivo() {
        return anoLectivo;
    }

    public void setAnoLectivo(AnoLectivo anoLectivo) {
        this.anoLectivo = anoLectivo;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public Integer getNumeroMaximo() {
        return numeroMaximo;
    }

    public void setNumeroMaximo(Integer numeroMaximo) {
        this.numeroMaximo = numeroMaximo;
    }

    public Boolean getActiva() {
        return activa;
    }

    public void setActiva(Boolean activa) {
        this.activa = activa;
    }

    public Date getDataRegisto() {
        return dataRegisto;
    }

    public void setDataRegisto(Date dataRegisto) {
        this.dataRegisto = dataRegisto;
    }

    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.idTurma);
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
        final Turma other = (Turma) obj;
        return Objects.equals(this.idTurma, other.idTurma);
    }

    @Override
    public String toString() {
        return "Turma{" + "descricaoTurma=" + descricaoTurma + '}';
    }
}