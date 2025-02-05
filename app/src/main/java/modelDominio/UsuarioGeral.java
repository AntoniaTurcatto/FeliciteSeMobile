package modelDominio;

import java.io.Serializable;

public class UsuarioGeral extends Usuario implements Serializable {
    private static final long serialVersionUID = 123456789L;

    public UsuarioGeral(int codUsuario, String nomeUsuario, int generoUsuario, int tipoUsuario, String senhaUsuario, String emailUsuario) {
        super(codUsuario, nomeUsuario, generoUsuario, tipoUsuario, senhaUsuario, emailUsuario);
    }

    @Override
    public String toString() {
        return super.toString()+"UsuarioGeral{}";
    }
}
