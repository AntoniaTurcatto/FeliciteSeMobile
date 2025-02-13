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
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
            serie.getCapaSerie().compress(Bitmap.CompressFormat.JPEG, 100, stream);
            byte[] capaSerieByte = stream.toByteArray();
            File file = new File(getApplicationContext().getCacheDir(), "tempfile");
            try(FileOutputStream fos = new FileOutputStream(file)){
                fos.write(capaSerieByte);
                Log.d("tamanho do arquivo: ",String.valueOf(file.length()));
                stream.close();
            }catch (IOException e){
                e.printStackTrace();
            }

            Intent it = new Intent(TelaSeries.this, SerieDetalhadaActivity.class);

            it.putExtra("codConteudo",serie.getCodConteudo());
            it.putExtra("nomeConteudo", serie.getNomeConteudo());
            it.putExtra("descConteudo", serie.getDescricaoConteudo());
            it.putExtra("descIndi", serie.getDescricaoIndicacao());
            it.putExtra("filepath", file.getAbsolutePath());
            it.putExtra("sinopse", serie.getSinopseSerie());
            it.putExtra("duracao", serie.getTemporadaSerie());
            it.putExtra("ano", serie.getAnoLancamentoSerie());
            it.putExtra("plat", serie.getPlataformaSerie());
            it.putExtra("tematicas", serie.getTematicas());

            //it.putExtra("serie", serieImagemByte);
            startActivity(it);

        }
    };



}
