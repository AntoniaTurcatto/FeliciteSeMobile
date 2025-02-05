package com.example.direitoafelicidade;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import modelDominio.Artigo;
import modelDominio.Tematica;

public class ArtigoDetalhadoActivity extends AppCompatActivity {

    TextView tvNomeArtigo, tvAutorArtigo, tvAnoArtigo, tvDescricaoArtigo, tvIndicacaoArtigo, tvTematicaArtigo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artigo_detalhado);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvNomeArtigo = findViewById(R.id.tvNomeArtigo);
        tvAutorArtigo = findViewById(R.id.tvAutorArtigo);
        tvAnoArtigo = findViewById(R.id.tvAnoArtigo);
        tvDescricaoArtigo = findViewById(R.id.tvDescricaoArtigo);
        tvIndicacaoArtigo = findViewById(R.id.tvIndicacaoArtigo);
        tvTematicaArtigo = findViewById(R.id.tvTematicaArtigo);

        Intent it = getIntent();

        if(it != null && it.hasExtra("artigo"))
        {
            Artigo artigo = (Artigo) it.getSerializableExtra("artigo");
            tvNomeArtigo.setText(artigo.getNomeConteudo());
            tvAnoArtigo.setText(String.valueOf(artigo.getAnoPublicacaoArtigo()));
            tvAutorArtigo.setText(artigo.getAutorArtigo());
            tvDescricaoArtigo.setText(artigo.getDescricaoConteudo());
            tvIndicacaoArtigo.setText(artigo.getDescricaoIndicacao());

            String nomeTematica = "";

            for (int i = 0; i < artigo.getTematicas().size(); i++) {
                Tematica tematica = artigo.getTematicas().get(i);

                nomeTematica = nomeTematica + tematica.getNomeTematica() + "\n";

            }

            tvTematicaArtigo.setText(nomeTematica);
        }
    }

}
