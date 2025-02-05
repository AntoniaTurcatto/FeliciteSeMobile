package modelDominio;

import java.io.Serializable;

public class Estudante extends Usuario implements Serializable {
    private static final long serialVersionUID = 123456789L;
    private int matriculaEstudante;

    public Estudante(int codUsuario, String nomeUsuario, int generoUsuario, int tipoUsuario,  String emailUsuario,String senhaUsuario, int matriculaEstudante) {
        super(codUsuario, nomeUsuario, generoUsuario, tipoUsuario, emailUsuario, senhaUsuario);
        this.matriculaEstudante = matriculaEstudante;
    }

    public int getMatriculaEstudante() {
        return matriculaEstudante;
    }

    public void setMatriculaEstudante(int matriculaEstudante) {
        this.matriculaEstudante = matriculaEstudante;
    }

    @Override
    public String toString() {
        return super.toString()+"Estudante{" +
                "matriculaEstudante=" + matriculaEstudante +
                '}';
    }
}
