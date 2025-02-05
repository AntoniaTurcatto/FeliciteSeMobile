package com.example.direitoafelicidade;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import modelDominio.Evento;
import modelDominio.Tematica;

public class EventosDetalhadoActivity extends AppCompatActivity {
    TextView tvNomeEvento, tvDescricaoEvento, tvIndicacaoEvento, tvDataEvento, tvLocalEvento, tvResponsavelEvento, tvTematicasEventoDetalhado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos_detalhado);
        tvNomeEvento = findViewById(R.id.tvNomeEvento);
        tvDescricaoEvento = findViewById(R.id.tvDescricaoEvento);
        tvIndicacaoEvento = findViewById(R.id.tvIndicacaoEvento);
        tvDataEvento = findViewById(R.id.tvDataEvento);
        tvLocalEvento = findViewById(R.id.tvLocalEvento);
        tvResponsavelEvento = findViewById(R.id.tvResponsavelEvento);
        tvTematicasEventoDetalhado = findViewById(R.id.tvTematicasEventoDetalhado);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent it = getIntent();

        if (it != null && it.hasExtra("evento")) {

            Evento evento = (Evento) it.getSerializableExtra("evento");
            tvNomeEvento.setText(evento.getNomeConteudo());
            tvDescricaoEvento.setText(evento.getDescricaoConteudo());
            tvIndicacaoEvento.setText(evento.getDescricaoIndicacao());
            tvDataEvento.setText(evento.getDataEvento());
            tvLocalEvento.setText(evento.getLocalEvento());
            tvResponsavelEvento.setText(evento.getResponsavelEvento());



            String guardaTematicas = "";

            for(int i = 0; i < evento.getTematicas().size(); i++) {

                if(i == (evento.getTematicas().size() - 1)) {
                    Tematica tematica =  evento.getTematicas().get(i);
                    guardaTematicas = guardaTematicas + tematica.getNomeTematica() + ".";
                } else {
                    Tematica tematica =  evento.getTematicas().get(i);
                    guardaTematicas = guardaTematicas + tematica.getNomeTematica() + ", ";
                }
            }

            tvTematicasEventoDetalhado.setText(guardaTematicas);

        } else {

        }
    }

}
