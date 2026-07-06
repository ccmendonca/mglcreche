
package skylink.mglcreche.modelo;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @Henriques
 */
public class Municipio implements Serializable {

    private Integer idMunicipio;
    private String nomeMunicipio;
    private Integer idProvincia;

    public Municipio() {
    }

    public Municipio(Integer idMunicipio, String nomeMunicipio, Integer idProvincia) {
        this.idMunicipio = idMunicipio;
        this.nomeMunicipio = nomeMunicipio;
        this.idProvincia = idProvincia;
    }

    public Integer getIdMunicipio() { return idMunicipio; }
    public void setIdMunicipio(Integer idMunicipio) { this.idMunicipio = idMunicipio; }

    public String getNomeMunicipio() { return nomeMunicipio; }
    public void setNomeMunicipio(String nomeMunicipio) { this.nomeMunicipio = nomeMunicipio; }

    public Integer getIdProvincia() { return idProvincia; }
    public void setIdProvincia(Integer idProvincia) { this.idProvincia = idProvincia; }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.idMunicipio);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final Municipio other = (Municipio) obj;
        return Objects.equals(this.idMunicipio, other.idMunicipio);
    }

    @Override
    public String toString() {
     return String.format("%s[idMunicipio=%d]", getClass().getSimpleName(), getIdMunicipio());   
    }
}