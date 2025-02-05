package modelDominio;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.ArrayList;

public class CanalYoutube extends Conteudo implements Serializable {
    private static final long serialVersionUID = 123456789L;
    private String linkCanal;

    private Bitmap capaCanal;
    private byte[] capaCanalByte;

    public CanalYoutube(int codConteudo, String nomeConteudo, String descricaoConteudo,
                        String descricaoIndicacao,
                        String linkCanal, ArrayList tematicas, Bitmap capaCanal) {
        super(codConteudo, nomeConteudo, descricaoConteudo, descricaoIndicacao, tematicas);
        this.linkCanal = linkCanal;

        this.capaCanal = capaCanal;
    }



    public CanalYoutube(int codConteudo, String nomeConteudo, String descricaoConteudo,
                        String descricaoIndicacao,
                        String linkCanal, ArrayList tematicas, byte[] capaCanal) {
        super(codConteudo, nomeConteudo, descricaoConteudo, descricaoIndicacao, tematicas);
        this.linkCanal = linkCanal;
        this.capaCanalByte = capaCanal;
    }



    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getLinkCanal() {
        return linkCanal;
    }

    public void setLinkCanal(String linkCanal) {
        this.linkCanal = linkCanal;
    }

    public Bitmap getCapaCanal() {
        return capaCanal;
    }

    public void setCapaCanal(Bitmap capaCanal) {
        this.capaCanal = capaCanal;
    }

    public byte[] getCapaCanalByte() {
        return capaCanalByte;
    }

    public void setCapaCanalByte(byte[] capaCanalByte) {
        this.capaCanalByte = capaCanalByte;
    }

    @Override
    public String toString() {
        return "CanalYoutube{" +
                "  codConteudo='" + getCodConteudo() + '\'' +
                ", nomeConteudo='" + getNomeConteudo() + '\'' +
                ", descricaoConteudo='" + getDescricaoConteudo() + '\'' +
                ", descricaoIndicacao='" + getDescricaoIndicacao() + '\'' +
                ", tematicaConteudo='" + getTematicaConteudo() + '\'' +
                ", listaTematicaConteudo='" + getListaTematicaConteudo() + '\'' +
                ", linkCanal='" + linkCanal + '\'' +
                ", capaCanal=" + capaCanal +
                '}';
    }
}
