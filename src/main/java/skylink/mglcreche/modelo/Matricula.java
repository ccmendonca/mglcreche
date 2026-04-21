
package skylink.mglcreche.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @«claudiomendonca»
 */

public class Matricula  implements Serializable {
    
    public static final long serialVersionUID = 1L;
    
    private Integer idMatricula;
    private Date dataMatricula;
    private Aluno aluno;
    private Turma turma;

    public Integer getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(Integer idMatricula) {
        this.idMatricula = idMatricula;
    }

    public Date getDataMatricula() {
        return dataMatricula;
    }

    public void setDataMatricula(Date dataMatricula) {
        this.dataMatricula = dataMatricula;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.idMatricula);
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
        final Matricula other = (Matricula) obj;
        return Objects.equals(this.idMatricula, other.idMatricula);
    }
    
}
