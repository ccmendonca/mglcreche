
package skylink.mglcreche.modelo;

import java.io.Serializable;
import java.util.Objects;

/**
 * «claudiomendonca»
 */

public class Servico implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idServico;
    private String descricaoServico;
    private Double precoServico;

    public Integer getIdServico() {
        return idServico;
    }

    public void setIdServico(Integer idServico) {
        this.idServico = idServico;
    }

    public String getDescricaoServico() {
        return descricaoServico;
    }

    public void setDescricaoServico(String descricaoServico) {
        this.descricaoServico = descricaoServico;
    }

    public Double getPrecoServico() {
        return precoServico;
    }

    public void setPrecoServico(Double precoServico) {
        this.precoServico = precoServico;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.idServico);
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
        final Servico other = (Servico) obj;
        return Objects.equals(this.idServico, other.idServico);
    }

    @Override
    public String toString() {
        return "Servico{" + "idServico=" + idServico + ", descricaoServico=" + descricaoServico + ", precoServico=" + precoServico + '}';
    }
    
}
