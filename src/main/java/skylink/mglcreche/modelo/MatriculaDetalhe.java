
package skylink.mglcreche.modelo;

import java.io.Serializable;
import java.util.Objects;

/**
 * @«claudiomendonca»
 */

public class MatriculaDetalhe implements Serializable {
    
    public static final long SerialVersionUID = 1L;
    
    private Matricula matricula;
    private Double precoServico;
    private Integer quantidadeServico;
    private Servico servico;

    public MatriculaDetalhe(Servico servico, Double precoServico) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }

    public Double getPrecoServico() {
        return precoServico;
    }

    public void setPrecoServico(Double precoServico) {
        this.precoServico = precoServico;
    }

    public Integer getQuantidadeServico() {
        return quantidadeServico;
    }

    public void setQuantidadeServico(Integer quantidadeServico) {
        this.quantidadeServico = quantidadeServico;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.matricula);
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
        final MatriculaDetalhe other = (MatriculaDetalhe) obj;
        return Objects.equals(this.matricula, other.matricula);
    }

    
    
}
