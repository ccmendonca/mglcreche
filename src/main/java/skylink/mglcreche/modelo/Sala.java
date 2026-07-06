package skylink.mglcreche.modelo;

import java.util.Objects;

/**
 *
 * @author Henriques
 */
public class Sala {
    
    private Integer idSala;
    private String descricaoSala;
    
    public Sala(){
    }

    public Sala(Integer idSala, String descricaoSala) {
        this.idSala = idSala;
        this.descricaoSala = descricaoSala;
    }

    public Integer getIdSala() {
        return idSala;
    }

    public void setIdSala(Integer idSala) {
        this.idSala = idSala;
    }

    public String getDescricaoSala() {
        return descricaoSala;
    }

    public void setDescricaoSala(String descricaoSala) {
        this.descricaoSala = descricaoSala;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.idSala);
        hash = 67 * hash + Objects.hashCode(this.descricaoSala);
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
        final Sala other = (Sala) obj;
        if (!Objects.equals(this.descricaoSala, other.descricaoSala)) {
            return false;
        }
        return Objects.equals(this.idSala, other.idSala);
    }
    
}
