package com.example.direitoafelicidade;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import modelDominio.Tematica;

public class CategoriaActivity extends AppCompatActivity {
    Button bYoutube, bAplicativos, bFilmes, bSeries, bLivros, bArtigos, bPaginaWeb, bEventos;
    InformacoesApp informacoesApp;
    String codTematica;

    StringRequest stringRequest;
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bYoutube = findViewById(R.id.bYoutube);
        bFilmes = findViewById(R.id.bFilmes);
        bSeries = findViewById(R.id.bSeries);
        bLivros = findViewById(R.id.bLivros);
        bEventos = findViewById(R.id.bEventos);
        bArtigos = findViewById(R.id.bArtigos);
        bAplicativos = findViewById(R.id.bAplicativos);
        bPaginaWeb = findViewById(R.id.bPaginaWeb);
        informacoesApp = (InformacoesApp) getApplicationContext();

        Intent it = getIntent();



        if (it.hasExtra("tematica")) {

            Tematica tematica = (Tematica) it.getSerializableExtra("tematica");
            codTematica = String.valueOf(tematica.getCodTematica());
            Log.d("tematicaCategoria", codTematica);

        } else {

            codTematica = (String) it.getStringExtra("tematicaVazia");

        }



//        Toast.makeText(this, "aqui o user log" + informacoesApp.getUsuarioLogado().toString(), Toast.LENGTH_SHORT).show();
        bYoutube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent novaTela = new Intent(CategoriaActivity.this, TelaYoutubeActivity.class);
                novaTela.putExtra("codTematica", codTematica);
                startActivity(novaTela);
            }
        });

        bFilmes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent novaTela = new Intent(CategoriaActivity.this, TelaFilmesActivity.class);
                novaTela.putExtra("codTematica", codTematica);
                startActivity(novaTela);
            }
        });

        bEventos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent novaTela = new Intent(CategoriaActivity.this, TelaEventosActivity.class);
                novaTela.putExtra("codTematica", codTematica);
                startActivity(novaTela);
            }
        });

        bSeries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent novaTela = new Intent(CategoriaActivity.this, TelaSeries.class);
                novaTela.putExtra("codTematica", codTematica);
                startActivity(novaTela);
            }
        });

        bLivros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent novaTela = new Intent(CategoriaActivity.this, TelaLivros.class);
                novaTela.putExtra("codTematica", codTematica);
                startActivity(novaTela);
            }
        });

        bArtigos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent novaTela = new Intent(CategoriaActivity.this, TelaArtigos.class);
                novaTela.putExtra("codTematica", codTematica);
                startActivity(novaTela);
            }
        });

        bAplicativos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent novaTela = new Intent(CategoriaActivity.this, TelaAplicativosActivity.class);
                novaTela.putExtra("codTematica", codTematica);
                startActivity(novaTela);
            }
        });

        bPaginaWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent novaTela = new Intent (CategoriaActivity.this, TelaSitesActivity.class);
                novaTela.putExtra("codTematica", codTematica);
                startActivity(novaTela);
            }
        }
        );


    }


}
