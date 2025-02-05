package modelDominio;

import java.io.Serializable;
import java.util.ArrayList;

public class Evento extends Conteudo implements Serializable {
    private static final long serialVersionUID = 123456789L;
    private String dataEvento;
    private String localEvento;
    private String responsavelEvento;

    public Evento(int codConteudo, String nomeConteudo, String descricaoConteudo, String descricaoIndicacao, ArrayList tematicas, String dataEvento, String localEvento, String responsavelEvento) {
        super(codConteudo, nomeConteudo, descricaoConteudo, descricaoIndicacao, tematicas);
        this.dataEvento = dataEvento;
        this.localEvento = localEvento;
        this.responsavelEvento = responsavelEvento;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(String dataEvento) {
        this.dataEvento = dataEvento;
    }

    public String getLocalEvento() {
        return localEvento;
    }

    public void setLocalEvento(String localEvento) {
        this.localEvento = localEvento;
    }

    public String getResponsavelEvento() {
        return responsavelEvento;
    }

    public void setResponsavelEvento(String responsavelEvento) {
        this.responsavelEvento = responsavelEvento;
    }

    @Override
    public String toString() {
        return "Evento{" +
                "  codConteudo='" + getCodConteudo() + '\'' +
                ", nomeConteudo='" + getNomeConteudo() + '\'' +
                ", descricaoConteudo='" + getDescricaoConteudo() + '\'' +
                ", descricaoIndicacao='" + getDescricaoIndicacao() + '\'' +
                "dataEvento=" + dataEvento +
                ", localEvento='" + localEvento + '\'' +
                ", responsavelEvento='" + responsavelEvento + '\'' +
                ", tematicas:='" + getTematicas() + '\'' +
                '}';
    }
}
