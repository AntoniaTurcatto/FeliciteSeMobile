package modelDominio;

import java.io.Serializable;

public class Tematica implements Serializable {
    private static final long serialVersionUID = 123456789L;
    private int codTematica;
    private String nomeTematica;
    private String descricaoTematica;
    private Byte imagemTematica;

    public Tematica(int codTematica, String nomeTematica) {
        this.codTematica = codTematica;
        this.nomeTematica = nomeTematica;
    }

    public Tematica(int codTematica, String nomeTematica, String descricaoTematica, Byte imagemTematica) {
        this.codTematica = codTematica;
        this.nomeTematica = nomeTematica;
        this.descricaoTematica = descricaoTematica;
        this.imagemTematica = imagemTematica;
    }

    public int getCodTematica() {
        return codTematica;
    }

    public void setCodTematica(int codTematica) {
        this.codTematica = codTematica;
    }

    public String getNomeTematica() {
        return nomeTematica;
    }

    public void setNomeTematica(String nomeTematica) {
        this.nomeTematica = nomeTematica;
    }

    public String getDescricaoTematica() {
        return descricaoTematica;
    }

    public void setDescricaoTematica(String descricaoTematica) {
        this.descricaoTematica = descricaoTematica;
    }

    public Byte getImagemTematica() {
        return imagemTematica;
    }

    public void setImagemTematica(Byte imagemTematica) {
        this.imagemTematica = imagemTematica;
    }

    @Override
    public String toString() {
        return "Tematica{" +
                "codTematica=" + codTematica +
                ", nomeTematica=" + nomeTematica +
                ", descricaoTematica=" + descricaoTematica +
                ", imagemTematica=" + imagemTematica +
                '}';
    }
}
