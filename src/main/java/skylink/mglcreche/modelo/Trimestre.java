package skylink.mglcreche.modelo;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @Henriques
 */
public class Trimestre implements Serializable {
    
    
    private static final long serialVersionUID = 1L;

    private Integer idTrimestre;
    private String descricaoTrimestre;
    
    
    public Trimestre(){
    
    }

    public Trimestre(Integer idTrimestre, String descricaoTrimestre) {
        this.idTrimestre = idTrimestre;
        this.descricaoTrimestre = descricaoTrimestre;
    }

    public Integer getIdTrimestre() {
        return idTrimestre;
    }

    public void setIdTrimestre(Integer idTrimestre) {
        this.idTrimestre = idTrimestre;
    }

    public String getDescricaoTrimestre() {
        return descricaoTrimestre;
    }

    public void setDescricaoTrimestre(String descricaoTrimestre) {
        this.descricaoTrimestre = descricaoTrimestre;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + Objects.hashCode(this.idTrimestre);
        hash = 11 * hash + Objects.hashCode(this.descricaoTrimestre);
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
        final Trimestre other = (Trimestre) obj;
        if (!Objects.equals(this.descricaoTrimestre, other.descricaoTrimestre)) {
            return false;
        }
        return Objects.equals(this.idTrimestre, other.idTrimestre);
    }

    @Override
    public String toString() {
        return "Trimestre{" + "idTrimestre=" + idTrimestre + ", descricaoTrimestre=" + descricaoTrimestre + '}';
    }
    
    
}
