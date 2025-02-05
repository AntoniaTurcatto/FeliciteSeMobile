package modelDominio;

import java.io.Serializable;
import java.util.ArrayList;

public class PaginaWeb extends Conteudo implements Serializable {
    private static final long serialVersionUID = 123456789L;
    private String linkPagina;
    private String autorPagina;

    public PaginaWeb(int codConteudo, String nomeConteudo, String descricaoConteudo,
                     String descricaoIndicacao,
                     String linkPagina, String autorPagina, ArrayList tematicas) {
            super(codConteudo, nomeConteudo, descricaoConteudo,
                    descricaoIndicacao, tematicas);
            this.linkPagina = linkPagina;
            this.autorPagina = autorPagina;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getLinkPagina() {
        return linkPagina;
    }

    public void setLinkPagina(String linkPagina) {
        this.linkPagina = linkPagina;
    }

    public String getAutorPagina() {
        return autorPagina;
    }

    public void setAutorPagina(String autorPagina) {
        this.autorPagina = autorPagina;
    }

    @Override
    public String toString() {
        return "PaginaWeb{" +
                "  codConteudo='" + getCodConteudo() + '\'' +
                ", nomeConteudo='" + getNomeConteudo() + '\'' +
                ", descricaoConteudo='" + getDescricaoConteudo() + '\'' +
                ", descricaoIndicacao='" + getDescricaoIndicacao() + '\'' +
                ", tematica(s)='" + getTematicas() + '\'' +

                ", linkPagina='" + linkPagina + '\'' +
                ", autorPagina='" + autorPagina + '\'' +
                '}';
    }
}
