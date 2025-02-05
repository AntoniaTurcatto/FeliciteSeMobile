package modelDominio;

import java.io.Serializable;
import java.util.Date;

public class Emocao implements Serializable {
    private static final long serialVersionUID = 123456789L;
    private int codEmocao;
    private int codUsuario;
    private int tipoEmocao;
    private Date dataEmocao;

    public Emocao(int codEmocao, int codUsuario, int tipoEmocao, Date dataEmocao) {
        this.codEmocao = codEmocao;
        this.codUsuario = codUsuario;
        this.tipoEmocao = tipoEmocao;
        this.dataEmocao = dataEmocao;
    }

    public Emocao(int tipoEmocao, Date dataEmocao) {
        this.tipoEmocao = tipoEmocao;
        this.dataEmocao = dataEmocao;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getCodEmocao() {
        return codEmocao;
    }

    public void setCodEmocao(int codEmocao) {
        this.codEmocao = codEmocao;
    }

    public int getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(int codUsuario) {
        this.codUsuario = codUsuario;
    }

    public int getTipoEmocao() {
        return tipoEmocao;
    }

    public void setTipoEmocao(int tipoEmocao) {
        this.tipoEmocao = tipoEmocao;
    }

    public Date getDataEmocao() {
        return dataEmocao;
    }

    public void setDataEmocao(Date dataEmocao) {
        this.dataEmocao = dataEmocao;
    }

    @Override
    public String toString() {
        return "Emocao{" +
                "codEmocao=" + codEmocao +
                ", codUsuario=" + codUsuario +
                ", tipoEmocao=" + tipoEmocao +
                ", dataEmocao=" + dataEmocao +
                '}';
    }
}
