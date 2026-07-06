package skylink.mglcreche.modelo;

import java.io.Serializable;
import java.util.Objects;

public class MesPropina implements Serializable {

    private Integer idMesPropina;
    private String descricaoMesPropina;
    private Double valorMesPropina;

    public MesPropina() {
    }

    public MesPropina(Integer idMesPropina, String descricaoMesPropina, Double valorMesPropina) {
        this.idMesPropina = idMesPropina;
        this.descricaoMesPropina = descricaoMesPropina;
        this.valorMesPropina = valorMesPropina;
    }

    public Integer getIdMesPropina() {
        return idMesPropina;
    }

    public void setIdMesPropina(Integer idMesPropina) {
        this.idMesPropina = idMesPropina;
    }

    public String getDescricaoMesPropina() {
        return descricaoMesPropina;
    }

    public void setDescricaoMesPropina(String descricaoMesPropina) {
        this.descricaoMesPropina = descricaoMesPropina;
    }

    public Double getValorMesPropina() {
        return valorMesPropina;
    }

    public void setValorMesPropina(Double valorMesPropina) {
        this.valorMesPropina = valorMesPropina;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.idMesPropina);
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
        final MesPropina other = (MesPropina) obj;
        return Objects.equals(this.idMesPropina, other.idMesPropina);
    }

}
