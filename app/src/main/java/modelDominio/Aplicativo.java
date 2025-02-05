package modelDominio;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.ArrayList;

public class Aplicativo extends Conteudo implements Serializable {
    private static final long serialVersionUID = 123456789L;
    private Bitmap logoAplicativo;
    private byte[] logoAplicativoByte;
    private String linkAplicativo;
    private String desenvolvedorAplicativo;
    private int gratisAplicativo;

    public Aplicativo(int codConteudo, String nomeConteudo, String descricaoConteudo,
                      String descricaoIndicacao,
                      String linkAplicativo, Bitmap logoAplicativo, String desenvolvedorAplicativo, int gratisAplicativo, ArrayList tematicas) {
        super(codConteudo, nomeConteudo, descricaoConteudo, descricaoIndicacao, tematicas);
        this.logoAplicativo = logoAplicativo;
        this.linkAplicativo = linkAplicativo;
        this.desenvolvedorAplicativo = desenvolvedorAplicativo;
        this.gratisAplicativo = gratisAplicativo;
    }

    public Aplicativo(int codConteudo, String nomeConteudo, String descricaoConteudo,
                      String descricaoIndicacao,
                      String linkAplicativo, byte[] logoAplicativoByte, String desenvolvedorAplicativo, int gratisAplicativo, ArrayList tematicas) {
        super(codConteudo, nomeConteudo, descricaoConteudo, descricaoIndicacao, tematicas);
        this.logoAplicativoByte = logoAplicativoByte;
        this.linkAplicativo = linkAplicativo;
        this.desenvolvedorAplicativo = desenvolvedorAplicativo;
        this.gratisAplicativo = gratisAplicativo;
    }

    public byte[] getLogoAplicativoByte() {
        return logoAplicativoByte;
    }

    public void setLogoAplicativoByte(byte[] logoAplicativoByte) {
        this.logoAplicativoByte = logoAplicativoByte;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Bitmap getLogoAplicativo() {
        return logoAplicativo;
    }

    public void setLogoAplicativo(Bitmap logoAplicativo) {
        this.logoAplicativo = logoAplicativo;
    }

    public String getLinkAplicativo() {
        return linkAplicativo;
    }

    public void setLinkAplicativo(String linkAplicativo) {
        this.linkAplicativo = linkAplicativo;
    }

    public String getDesenvolvedorAplicativo() {
        return desenvolvedorAplicativo;
    }

    public void setDesenvolvedorAplicativo(String desenvolvedorAplicativo) {
        this.desenvolvedorAplicativo = desenvolvedorAplicativo;
    }

    public int getGratisAplicativo() {
        return gratisAplicativo;
    }

    public void setGratisAplicativo(int gratisAplicativo) {
        this.gratisAplicativo = gratisAplicativo;
    }

    @Override
    public String toString() {
        return "Aplicativo{" +
                "  codConteudo='" + getCodConteudo() + '\'' +
                ", nomeConteudo='" + getNomeConteudo() + '\'' +
                ", descricaoConteudo='" + getDescricaoConteudo() + '\'' +
                ", descricaoIndicacao='" + getDescricaoIndicacao() + '\'' +

                ", listaTematicaConteudo='" + getTematicas() + '\'' +
                "logoAplicativo=" + logoAplicativo +
                ", linkAplicativo=" + linkAplicativo +
                ", desenvolvedorAplicativo='" + desenvolvedorAplicativo + '\'' +
                ", gratisAplicativo=" + gratisAplicativo +
                '}';
    }
}
