package com.example.direitoafelicidade;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import modelDominio.Evento;

public class TelaEventosActivity extends AppCompatActivity {
    RecyclerView rvEventos;
    ArrayList<Evento> listaEventos;
    EventoAdapter eventoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_eventos);
        rvEventos = findViewById(R.id.rvEventos);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listaEventos = new ArrayList<>();

        Intent it = getIntent();
        String filtroCodTematica = it.getStringExtra("codTematica");

        WebServiceController webServiceController = new WebServiceController(TelaEventosActivity.this);
        webServiceController.carregaEventos(filtroCodTematica, new WebServiceController.VolleyResponseListner() {
            @Override
            public void onResponse(Object response) {
                listaEventos = (ArrayList<Evento>) response;

                if (listaEventos != null) {
                    eventoAdapter = new EventoAdapter(listaEventos, trataCliqueItem);
                    rvEventos.setLayoutManager(new LinearLayoutManager(TelaEventosActivity.this));
                    rvEventos.setItemAnimator(new DefaultItemAnimator());
                    rvEventos.setAdapter(eventoAdapter);
                } else {
                    Toast.makeText(TelaEventosActivity.this, "Erro", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onError(String message) {

            }
        });
    };




    EventoAdapter.EventoOnClickListener trataCliqueItem = new EventoAdapter.EventoOnClickListener() {
        @Override
        public void onClickEvento(View view, int position) {
            // Aqui o usuário deve ser redirecionado pra outra página que contem as informações do evento clicado
            Evento evento = listaEventos.get(position);


            Intent it = new Intent(TelaEventosActivity.this, EventosDetalhadoActivity.class);
            it.putExtra("evento",evento);
            startActivity(it);

        }
    };
}