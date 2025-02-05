package modelDominio;

import java.io.Serializable;

public class Servidor extends Usuario implements Serializable {
    private static final long serialVersionUID = 123456789L;
    private String matriculaServidor;
    private String cargoServidor;
    private int SIAPE;

    public Servidor(int codUsuario, String nomeUsuario, int generoUsuario, int tipoUsuario, String senhaUsuario, String emailUsuario, String matriculaServidor, String cargoServidor, int SIAPE) {
        super(codUsuario, nomeUsuario, generoUsuario, tipoUsuario, senhaUsuario, emailUsuario);
        this.matriculaServidor = matriculaServidor;
        this.cargoServidor = cargoServidor;
        this.SIAPE = SIAPE;

    }

    public String getMatriculaServidor() {
        return matriculaServidor;
    }

    public void setMatriculaServidor(String matriculaServidor) {
        this.matriculaServidor = matriculaServidor;
    }

    public String getCargoServidor() {
        return cargoServidor;
    }

    public void setCargoServidor(String cargoServidor) {
        this.cargoServidor = cargoServidor;
    }

    public int getSIAPE() {
        return SIAPE;
    }

    public void setSIAPE(int SIAPE) {
        this.SIAPE = SIAPE;
    }

    @Override
    public String toString() {
        return super.toString()+"Servidor{" +
                "matriculaServidor='" + matriculaServidor + '\'' +
                ", cargoServidor='" + cargoServidor + '\'' +
                ", SIAPE=" + SIAPE +
                '}';
    }
}
