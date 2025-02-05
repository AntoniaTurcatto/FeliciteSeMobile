package com.example.direitoafelicidade;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.widget.TextView;

import com.example.direitoafelicidade.R;

import modelDominio.PaginaWeb;
import modelDominio.Tematica;

public class Site_Detalhado_Activity extends AppCompatActivity {

    TextView tvNomeSite, tvLinkSite, tvAutorSite, tvDescricao, tvTematica, tvIndicacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site_detalhado);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvNomeSite = findViewById(R.id.tvNomeSite);
        tvAutorSite = findViewById(R.id.tvAutorSite);
        tvDescricao = findViewById(R.id.tvDescricao);
        tvTematica = findViewById(R.id.tvTematica);
        tvIndicacao = findViewById(R.id.tvIndicacao);
        tvLinkSite = findViewById(R.id.tvLinkSite);

        Intent it = getIntent();

        if(it != null && it.hasExtra("site"))
        {
            PaginaWeb pw = (PaginaWeb) it.getSerializableExtra("site");
            tvNomeSite.setText(pw.getNomeConteudo());
            tvLinkSite.setText(pw.getLinkPagina());
            tvAutorSite.setText(pw.getAutorPagina());
            tvDescricao.setText(pw.getDescricaoConteudo());

            String nomeTematica = "";

            for (int i = 0; i < pw.getTematicas().size(); i++) {
                Tematica tematica = pw.getTematicas().get(i);

                nomeTematica = nomeTematica + tematica.getNomeTematica() + "\n";

            }

            tvTematica.setText(nomeTematica);

            tvIndicacao.setText(pw.getDescricaoIndicacao());
        }

    }

}
