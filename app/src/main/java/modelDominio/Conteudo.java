package modelDominio;

import java.io.Serializable;
import java.util.ArrayList;

public class Conteudo implements Serializable {
    private static final long serialVersionUID = 123456789L;
    private int codConteudo;
    private String nomeConteudo;
    private String descricaoConteudo;
    private String descricaoIndicacao;
    private int tematicaConteudo;
    private int listaTematicaConteudo;
    private ArrayList<Tematica> tematicas;
    private int aprovado = 0;

    public Conteudo(int codConteudo, String nomeConteudo, String descricaoConteudo, String descricaoIndicacao, int aprovado, ArrayList<Tematica> tematicas) {
        this.codConteudo = codConteudo;
        this.nomeConteudo = nomeConteudo;
        this.descricaoConteudo = descricaoConteudo;
        this.descricaoIndicacao = descricaoIndicacao;
        this.aprovado = aprovado;
        this.tematicas = tematicas;
    }

    public Conteudo(int codConteudo, String nomeConteudo, String descricaoConteudo, String descricaoIndicacao, int tematicaConteudo) {
        this.codConteudo = codConteudo;
        this.nomeConteudo = nomeConteudo;
        this.descricaoConteudo = descricaoConteudo;
        this.descricaoIndicacao = descricaoIndicacao;
        this.tematicaConteudo = tematicaConteudo;

    }

    public Conteudo(int codConteudo, String nomeConteudo, String descricaoConteudo, String descricaoIndicacao, ArrayList tematicas) {
        this.codConteudo = codConteudo;
        this.nomeConteudo = nomeConteudo;
        this.descricaoConteudo = descricaoConteudo;
        this.descricaoIndicacao = descricaoIndicacao;
        this.tematicas = tematicas;

    }
    public Conteudo(int codConteudo, String nomeConteudo, String descricaoConteudo, String descricaoIndicacao, int tematicaConteudo, int listaTematicaConteudo) {
        this.codConteudo = codConteudo;
        this.nomeConteudo = nomeConteudo;
        this.descricaoConteudo = descricaoConteudo;
        this.descricaoIndicacao = descricaoIndicacao;
        this.tematicaConteudo = tematicaConteudo;
        this.listaTematicaConteudo = listaTematicaConteudo;

    }

    public Conteudo(String nomeConteudo, String descricaoConteudo, String descricaoIndicacao, int tematicaConteudo) {
        this.nomeConteudo = nomeConteudo;
        this.descricaoConteudo = descricaoConteudo;
        this.descricaoIndicacao = descricaoIndicacao;
        this.tematicaConteudo = tematicaConteudo;

    }

    public int getAprovado() {
        return aprovado;
    }

    public void setAprovado(int aprovado) {
        this.aprovado = aprovado;
    }

    public Conteudo(int codConteudo) {
        this.codConteudo = codConteudo;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getCodConteudo() {
        return codConteudo;
    }

    public void setCodConteudo(int codConteudo) {
        this.codConteudo = codConteudo;
    }

    public String getNomeConteudo() {
        return nomeConteudo;
    }

    public void setNomeConteudo(String nomeConteudo) {
        this.nomeConteudo = nomeConteudo;
    }

    public String getDescricaoConteudo() {
        return descricaoConteudo;
    }

    public void setDescricaoConteudo(String descricaoConteudo) {
        this.descricaoConteudo = descricaoConteudo;
    }

    public String getDescricaoIndicacao() {
        return descricaoIndicacao;
    }

    public void setDescricaoIndicacao(String descricaoIndicacao) {
        this.descricaoIndicacao = descricaoIndicacao;
    }

    public int getTematicaConteudo() {
        return tematicaConteudo;
    }

    public void setTematicaConteudo(int tematicaConteudo) {
        this.tematicaConteudo = tematicaConteudo;
    }

    public int getListaTematicaConteudo() {
        return listaTematicaConteudo;
    }

    public void setListaTematicaConteudo(int listaTematicaConteudo) {
        this.listaTematicaConteudo = listaTematicaConteudo;
    }

    public ArrayList<Tematica> getTematicas() {
        return tematicas;
    }

    public void setTematicas(ArrayList<Tematica> tematicas) {
        this.tematicas = tematicas;
    }

    @Override
    public String toString() {
        return "Conteudo{" +
                "codConteudo=" + codConteudo +
                ", nomeConteudo='" + nomeConteudo + '\'' +
                ", descricaoConteudo='" + descricaoConteudo + '\'' +
                ", descricaoIndicacao='" + descricaoIndicacao + '\'' +
                ", tematicaConteudo=" + tematicaConteudo;
    }
}
