package modelDominio;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.ArrayList;

public class Livro extends Conteudo implements Serializable {
    private static final long serialVersionUID = 123456789L;
    private String editoraLivro;
    //private int capaLivro;
    private Bitmap capaLivro;
    private int anoLivro;
    private int paginasLivro;
    private String autorLivro;
    private String generoLivro;

    public Livro(int codConteudo, String nomeConteudo, String descricaoConteudo,
                 String descricaoIndicacao, ArrayList tematicas,
                 String editoraLivro, Bitmap capaLivro, int anoLivro, int paginasLivro, String autorLivro, String generoLivro) {
        super(codConteudo, nomeConteudo, descricaoConteudo, descricaoIndicacao, tematicas);
        this.editoraLivro = editoraLivro;
        this.capaLivro = capaLivro;
        this.anoLivro = anoLivro;
        this.paginasLivro = paginasLivro;
        this.autorLivro = autorLivro;
        this.generoLivro = generoLivro;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getEditoraLivro() {
        return editoraLivro;
    }

    public void setEditoraLivro(String editoraLivro) {
        this.editoraLivro = editoraLivro;
    }

    public Bitmap getCapaLivro() {
        return capaLivro;
    }

    public void setCapaLivro(Bitmap capaLivro) {
        this.capaLivro = capaLivro;
    }

    public int getAnoLivro() {
        return anoLivro;
    }

    public void setAnoLivro(int anoLivro) {
        this.anoLivro = anoLivro;
    }

    public int getPaginasLivro() {
        return paginasLivro;
    }

    public void setPaginasLivro(int paginasLivro) {
        this.paginasLivro = paginasLivro;
    }

    public String getAutorLivro() {
        return autorLivro;
    }

    public void setAutorLivro(String autorLivro) {
        this.autorLivro = autorLivro;
    }

    public String getGeneroLivro() {
        return generoLivro;
    }

    public void setGeneroLivro(String generoLivro) {
        this.generoLivro = generoLivro;
    }

    public String toString() {
        return "Livro{" +
                "  codConteudo='" + getCodConteudo() + '\'' +
                ", nomeConteudo='" + getNomeConteudo() + '\'' +
                ", descricaoConteudo='" + getDescricaoConteudo() + '\'' +
                ", descricaoIndicacao='" + getDescricaoIndicacao() + '\'' +
                ", Tematicas' " + getTematicas() + '\'' +
                ", editoraLivro='" + editoraLivro + '\'' +
                ", capaLivro='" + capaLivro + '\'' +
                ", anoLivro='" + anoLivro + '\'' +
                ", paginasLivro='" + paginasLivro + '\'' +
                ", autorLivro='" + autorLivro + '\'' +
                ", generoLivro='" + generoLivro + '\'' +
                '}';
    }
}
