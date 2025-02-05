package modelDominio;

import java.io.Serializable;
import java.util.ArrayList;

public class Artigo extends Conteudo implements Serializable  {
    private static final long serialVersionUID = 123456789L;
    private String linkArtigo;
    private String resumoArtigo;
    private int anoPublicacaoArtigo;
    private String autorArtigo;

    public Artigo(int codConteudo, String nomeConteudo, String descricaoConteudo,
                  String descricaoIndicacao, int tematicaConteudo,
                  String linkArtigo, String resumoArtigo, int anoPublicacaoArtigo, String autorArtigo) {
        super(codConteudo, nomeConteudo, descricaoConteudo,
                descricaoIndicacao, tematicaConteudo);
        this.linkArtigo = linkArtigo;
        this.resumoArtigo = resumoArtigo;
        this.anoPublicacaoArtigo = anoPublicacaoArtigo;
        this.autorArtigo = autorArtigo;
    }

    public Artigo(int codConteudo, String nomeConteudo, String descricaoConteudo,
                  String descricaoIndicacao, ArrayList tematicas,
                  String linkArtigo, String resumoArtigo, int anoPublicacaoArtigo, String autorArtigo) {
        super(codConteudo, nomeConteudo, descricaoConteudo,
                descricaoIndicacao, tematicas);
        this.linkArtigo = linkArtigo;
        this.resumoArtigo = resumoArtigo;
        this.anoPublicacaoArtigo = anoPublicacaoArtigo;
        this.autorArtigo = autorArtigo;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getLinkArtigo() {
        return linkArtigo;
    }

    public void setLinkArtigo(String linkArtigo) {
        this.linkArtigo = linkArtigo;
    }

    public String getResumoArtigo() {
        return resumoArtigo;
    }

    public void setResumoArtigo(String resumoArtigo) {
        this.resumoArtigo = resumoArtigo;
    }

    public int getAnoPublicacaoArtigo() {
        return anoPublicacaoArtigo;
    }

    public void setAnoPublicacaoArtigo(int anoPublicacaoArtigo) {
        this.anoPublicacaoArtigo = anoPublicacaoArtigo;
    }

    public String getAutorArtigo() {
        return autorArtigo;
    }

    public void setAutorArtigo(String autorArtigo) {
        this.autorArtigo = autorArtigo;
    }

    @Override
    public String toString() {
        return "Artigo{" +
                "  codConteudo='" + getCodConteudo() + '\'' +
                ", nomeConteudo='" + getNomeConteudo() + '\'' +
                ", descricaoConteudo='" + getDescricaoConteudo() + '\'' +
                ", descricaoIndicacao='" + getDescricaoIndicacao() + '\'' +
                ", ='"  + '\'' +
                ", listaTematicaConteudo='" + getTematicas() + '\'' +
                ", linkArtigo='" + linkArtigo + '\'' +
                ", resumoArtigo='" + resumoArtigo + '\'' +
                ", anoPublicacao=" + anoPublicacaoArtigo +
                ", autorArtigo='" + autorArtigo + '\'' +
                '}';
    }
}
