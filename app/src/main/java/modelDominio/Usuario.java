package modelDominio;

import java.io.Serializable;

public class Usuario implements Serializable {
    private static final long serialVersionUID = 123456789L;
    private int codUsuario;
    private String nomeUsuario;
    private int generoUsuario;
    private int tipoUsuario;
    private String emailUsuario;
    private String senhaUsuario;
    private String matriculaEstudante;
    private String matriculaServidor;
    private String cargoServidorString;
    private String siapeServidorString;



    public Usuario(int codUsuario, String nomeUsuario, int generoUsuario, int tipoUsuario, String emailUsuario, String senhaUsuario) {
        this.codUsuario = codUsuario;
        this.nomeUsuario = nomeUsuario;
        this.generoUsuario = generoUsuario;
        this.tipoUsuario = tipoUsuario;
        this.emailUsuario = emailUsuario;
        this.senhaUsuario = senhaUsuario;

    }

    public Usuario(String nomeUsuario, int generoUsuario, int tipoUsuario, String emailUsuario, String senhaUsuario) {
        this.nomeUsuario = nomeUsuario;
        this.generoUsuario = generoUsuario;
        this.tipoUsuario = tipoUsuario;
        this.emailUsuario = emailUsuario;
        this.senhaUsuario = senhaUsuario;
    }

    public Usuario(String emailUsuario, String senhaUsuario) {
        this.emailUsuario = emailUsuario;
        this.senhaUsuario = senhaUsuario;
    }



    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getCodUsuario() {
        return codUsuario;
    }
    public void setCodUsuario(int codUsuario) {
        this.codUsuario = codUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }
    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public int getGeneroUsuario() {
        return generoUsuario;
    }
    public void setGeneroUsuario(int generoUsuario) {
        this.generoUsuario = generoUsuario;
    }

    public int getTipoUsuario() {
        return tipoUsuario;
    }
    public void setTipoUsuario(int tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }
    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getSenhaUsuario() {
        return senhaUsuario;
    }
    public void setSenhaUsuario(String senhaUsuario) {
        this.senhaUsuario = senhaUsuario;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "codUsuario=" + codUsuario +
                ", nomeUsuario='" + nomeUsuario + '\'' +
                ", generoUsuario=" + generoUsuario +
                ", tipoUsuario=" + tipoUsuario +
                ", senhaUsuario='" + senhaUsuario + '\'' +
                ", emailUsuario='" + emailUsuario + '\'' +
                '}';
    }
}
