package skylink.mglcreche.modelo;

import java.util.Objects;

public class AnoLectivo {

    private Integer idAnoLectivo;
    private String descricaoAnoLectivo;

    public AnoLectivo() {
    }

    public AnoLectivo(Integer idAnoLectivo, String descricaoAnoLectivo) {
        this.idAnoLectivo = idAnoLectivo;
        this.descricaoAnoLectivo = descricaoAnoLectivo;
    }

    public Integer getIdAnoLectivo() {
        return idAnoLectivo;
    }

    public void setIdAnoLectivo(Integer idAnoLectivo) {
        this.idAnoLectivo = idAnoLectivo;
    }

    public String getDescricaoAnoLectivo() {
        return descricaoAnoLectivo;
    }

    public void setDescricaoAnoLectivo(String descricaoAnoLectivo) {
        this.descricaoAnoLectivo = descricaoAnoLectivo;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.idAnoLectivo);
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
        final AnoLectivo other = (AnoLectivo) obj;
        return Objects.equals(this.idAnoLectivo, other.idAnoLectivo);
    }

    @Override
    public String toString() {
        return "AnoLectivo{" + "descricaoAnoLectivo=" + descricaoAnoLectivo + '}';
    }

}
