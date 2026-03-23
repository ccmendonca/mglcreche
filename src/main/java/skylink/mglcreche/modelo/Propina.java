
package skylink.mglcreche.modelo;

import java.util.Date;
import java.util.Objects;


public class Propina {
    private Integer idPropina;
    private Date dataPagamento;
    private Aluno aluno;

    public Propina() {
    }

    public Propina(Integer idPropina, Date dataPagamento, Aluno aluno) {
        this.idPropina = idPropina;
        this.dataPagamento = dataPagamento;
        this.aluno = aluno;
    }

    public Integer getIdPropina() {
        return idPropina;
    }

    public void setIdPropina(Integer idPropina) {
        this.idPropina = idPropina;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.idPropina);
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
        final Propina other = (Propina) obj;
        return Objects.equals(this.idPropina, other.idPropina);
    }
    
}
