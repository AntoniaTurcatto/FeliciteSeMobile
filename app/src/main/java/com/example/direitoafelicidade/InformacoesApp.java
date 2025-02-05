package com.example.direitoafelicidade;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

import modelDominio.Aplicativo;
import modelDominio.PaginaWeb;
import modelDominio.Usuario;

public class InformacoesApp extends Application {

    ArrayList<PaginaWeb> listaPaginasWeb;
    ArrayList<Aplicativo> listaAplicativos;
    Usuario usuarioLogado;

    @Override
    public void onCreate()
    {
        super.onCreate();
        listaPaginasWeb = new ArrayList<PaginaWeb>();
        listaAplicativos = new ArrayList<Aplicativo>();

    }

    public ArrayList<PaginaWeb> getListaPaginasWeb()
    {
        return listaPaginasWeb;
    }

    public ArrayList<Aplicativo> getListaAplicativos()
    {
        return listaAplicativos;
    }

    public void setListaPaginasWeb(ArrayList<PaginaWeb> listaPaginasWeb)
    {
        this.listaPaginasWeb = listaPaginasWeb;
    }

    public void setListaAplicativos(ArrayList<Aplicativo> listaAplicativos)
    {
        this.listaAplicativos = listaAplicativos;
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }
}
