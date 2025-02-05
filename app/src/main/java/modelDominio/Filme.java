package modelDominio;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.ArrayList;

public class Filme extends Conteudo implements Serializable {
    private static final long serialVersionUID = 123456789L;
    private Bitmap capaFilme;
    private byte[] capaFilmeByte;
    private String sinopseFilme;
    private int duracaoFilme;
    private int anoLancamentoFilme;
    private String plataformaFilme;

    public Filme(int codConteudo, String nomeConteudo, String descricaoConteudo,
                 String descricaoIndicacao,
                 Bitmap capaFilme, String sinopseFilme, int duracaoFilme, int anoLancamentoFilme,
                 String plataformaFilme, ArrayList tematicas) {
        super(codConteudo, nomeConteudo, descricaoConteudo, descricaoIndicacao, tematicas);
        this.capaFilme = capaFilme;
        this.sinopseFilme = sinopseFilme;
        this.duracaoFilme = duracaoFilme;
        this.anoLancamentoFilme = anoLancamentoFilme;

        this.plataformaFilme = plataformaFilme;
    }

    public Filme(int codConteudo, String nomeConteudo, String descricaoConteudo,
                 String descricaoIndicacao,
                 byte[] capaFilmeByte, String sinopseFilme, int duracaoFilme, int anoLancamentoFilme,
                 String plataformaFilme, ArrayList tematicas) {
        super(codConteudo, nomeConteudo, descricaoConteudo, descricaoIndicacao, tematicas);
        this.capaFilmeByte = capaFilmeByte;
        this.sinopseFilme = sinopseFilme;
        this.duracaoFilme = duracaoFilme;
        this.anoLancamentoFilme = anoLancamentoFilme;

        this.plataformaFilme = plataformaFilme;
    }


    public byte[] getCapaFilmeByte() {
        return capaFilmeByte;
    }

    public void setCapaFilmeByte(byte[] capaFilmeByte) {
        this.capaFilmeByte = capaFilmeByte;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Bitmap getCapaFilme() {
        return capaFilme;
    }

    public void setCapaFilme(Bitmap capaFilme) {
        this.capaFilme = capaFilme;
    }

    public String getSinopseFilme() {
        return sinopseFilme;
    }

    public void setSinopseFilme(String sinopseFilme) {
        this.sinopseFilme = sinopseFilme;
    }

    public int getDuracaoFilme() {
        return duracaoFilme;
    }

    public void setDuracaoFilme(int duracaoFilme) {
        this.duracaoFilme = duracaoFilme;
    }

    public int getAnoLancamentoFilme() {
        return anoLancamentoFilme;
    }

    public void setAnoLancamentoFilme(int anoLancamentoFilme) {
        this.anoLancamentoFilme = anoLancamentoFilme;
    }


    public String getPlataformaFilme() {
        return plataformaFilme;
    }

    public void setPlataformaFilme(String plataformaFilme) {
        this.plataformaFilme = plataformaFilme;
    }

    @Override
    public String toString() {
        return "Filme{" +
                "capaFilme=" + capaFilme +
                ", sinopseFilme='" + sinopseFilme + '\'' +
                ", duracaoFilme=" + duracaoFilme +
                ", anoLancamentoFilme=" + anoLancamentoFilme +

                ", plataformaFilme='" + plataformaFilme + '\'' +
                '}';
    }
}
