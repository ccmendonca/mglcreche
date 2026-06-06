
package skylink.mglcreche.modelo;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @Henriques
 */
public class Periodo implements Serializable {
    
    private Integer idPeriodo;
    private String descricaoPeriodo;


public Periodo (){

}    

    public Periodo(Integer idPeriodo, String descricaoPeriodo) {
        this.idPeriodo = idPeriodo;
        this.descricaoPeriodo = descricaoPeriodo;
    }

    public Integer getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(Integer idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    public String getDescricaoPeriodo() {
        return descricaoPeriodo;
    }

    public void setDescricaoPeriodo(String descricaoPeriodo) {
        this.descricaoPeriodo = descricaoPeriodo;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.idPeriodo);
        hash = 23 * hash + Objects.hashCode(this.descricaoPeriodo);
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
        final Periodo other = (Periodo) obj;
        if (!Objects.equals(this.descricaoPeriodo, other.descricaoPeriodo)) {
            return false;
        }
        return Objects.equals(this.idPeriodo, other.idPeriodo);
    }

    @Override
    public String toString() {
           return String.format("%s[idPeriodo=%d]", getClass().getSimpleName(), getIdPeriodo());
    }
   
    
    
}
