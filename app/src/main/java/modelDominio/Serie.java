package modelDominio;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.ArrayList;

public class Serie extends Conteudo implements Serializable {
    private static final long serialVersionUID = 123456789L;
    private Bitmap capaSerie;
    private byte[] capaSerieByte;
    private String sinopseSerie;
    private int temporadaSerie;
    private int anoLancamentoSerie;
    private String plataformaSerie;

    public Serie(int codConteudo, String nomeConteudo, String descricaoConteudo,
                 String descricaoIndicacao, Bitmap capaSerie, String sinopseSerie, int temporadaSerie, int anoLancamentoSerie, String plataformaSerie, ArrayList tematicas) {
        super(codConteudo, nomeConteudo, descricaoConteudo, descricaoIndicacao, tematicas);
        this.capaSerie = capaSerie;
        this.sinopseSerie = sinopseSerie;
        this.temporadaSerie = temporadaSerie;
        this.anoLancamentoSerie = anoLancamentoSerie;
        this.plataformaSerie = plataformaSerie;
    }

    public Serie(int codConteudo, String nomeConteudo, String descricaoConteudo,
                 String descricaoIndicacao, byte[] capaSerieByte, String sinopseSerie, int temporadaSerie, int anoLancamentoSerie, String plataformaSerie, ArrayList tematicas) {
        super(codConteudo, nomeConteudo, descricaoConteudo, descricaoIndicacao, tematicas);
        this.capaSerieByte = capaSerieByte;
        this.sinopseSerie = sinopseSerie;
        this.temporadaSerie = temporadaSerie;
        this.anoLancamentoSerie = anoLancamentoSerie;
        this.plataformaSerie = plataformaSerie;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Bitmap getCapaSerie() {
        return capaSerie;
    }

    public void setCapaSerie(Bitmap capaSerie) {
        this.capaSerie = capaSerie;
    }

    public byte[] getCapaSerieByte() {
        return capaSerieByte;
    }

    public void setCapaSerieByte(byte[] capaSerieByte) {
        this.capaSerieByte = capaSerieByte;
    }

    public String getSinopseSerie() {
        return sinopseSerie;
    }

    public void setSinopseSerie(String sinopseSerie) {
        this.sinopseSerie = sinopseSerie;
    }



    public int getTemporadaSerie() {
        return temporadaSerie;
    }

    public void setTemporadaSerie(int temporadaSerie) {
        this.temporadaSerie = temporadaSerie;
    }

    public int getAnoLancamentoSerie() {
        return anoLancamentoSerie;
    }

    public void setAnoLancamentoSerie(int anoLancamentoSerie) {
        this.anoLancamentoSerie = anoLancamentoSerie;
    }

    public String getPlataformaSerie() {
        return plataformaSerie;
    }

    public void setPlataformaSerie(String plataformaSerie) {
        this.plataformaSerie = plataformaSerie;
    }


    @Override
    public String toString() {
        return "Serie{" +
                "  codConteudo='" + getCodConteudo() + '\'' +
                ", nomeConteudo='" + getNomeConteudo() + '\'' +
                ", descricaoConteudo='" + getDescricaoConteudo() + '\'' +
                ", descricaoIndicacao='" + getDescricaoIndicacao() + '\'' +
                ", Tematicas' " + getTematicas() + '\'' +
                "capaSerie=" + capaSerie +
                ", sinopseSerie='" + sinopseSerie + '\'' +

                ", temporadaSerie=" + temporadaSerie +
                ", anoLancamentoSerie=" + anoLancamentoSerie +
                ", plataformaSerie='" + plataformaSerie + '\'' +
                '}';
    }
}
