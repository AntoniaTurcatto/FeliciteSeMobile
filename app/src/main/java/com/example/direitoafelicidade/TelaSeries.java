package com.example.direitoafelicidade;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;


import android.view.View;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import modelDominio.Livro;
import modelDominio.Serie;
import modelDominio.Tematica;

public class TelaSeries extends AppCompatActivity {
    RecyclerView rvSeries;
    ArrayList<Serie> listaSeries;
    SerieAdapter serieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_series);
        rvSeries = findViewById(R.id.rvSeries);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listaSeries = new ArrayList<>();

        Intent it = getIntent();
        String filtroCodTematica = it.getStringExtra("codTematica");

        WebServiceController webServiceController = new WebServiceController(TelaSeries.this);
        webServiceController.carregaSeries(filtroCodTematica, new WebServiceController.VolleyResponseListner() {
            @Override
            public void onResponse(Object response) {
                listaSeries = (ArrayList<Serie>) response;

                if (listaSeries != null) {
                    serieAdapter = new SerieAdapter(listaSeries, trataCliqueItem);
                    rvSeries.setLayoutManager(new LinearLayoutManager(TelaSeries.this));
                    rvSeries.setItemAnimator(new DefaultItemAnimator());
                    rvSeries.setAdapter(serieAdapter);
                } else {
                    Toast.makeText(TelaSeries.this, "Nao voltou serie nenhuma", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(String message) {

            }
        });
    }


    SerieAdapter.SerieOnClickListener trataCliqueItem = new SerieAdapter.SerieOnClickListener() {
        @Override
        public void onClickSerie(View view, int position) {
            Serie serie = listaSeries.get(position);

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            serie.getCapaSerie().compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] capaSerieByte = stream.toByteArray();

            Serie serieImagemByte = new Serie(serie.getCodConteudo(), serie.getNomeConteudo(), serie.getDescricaoConteudo(), serie.getDescricaoIndicacao(), capaSerieByte, serie.getSinopseSerie(), serie.getTemporadaSerie(), serie.getAnoLancamentoSerie(), serie.getPlataformaSerie(), serie.getTematicas());

            Intent it = new Intent(TelaSeries.this, SerieDetalhadaActivity.class);
            it.putExtra("serie", serieImagemByte);
            startActivity(it);

        }
    };



}
