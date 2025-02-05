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

import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import modelDominio.Livro;
import modelDominio.Tematica;

public class FiltrarTematicasActivity extends AppCompatActivity {
    Button bVisualizarTodosConteudos, bSugerirConteudo;
    RecyclerView rvTematicas;
    TematicaAdapter tematicaAdapter;
    ArrayList<Tematica> listaTematicas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtrar_tematicas);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        bVisualizarTodosConteudos = findViewById(R.id.bVisualizarTodosConteudos);
        rvTematicas = findViewById(R.id.rvTematicas);
        bSugerirConteudo = findViewById(R.id.bSugerirConteudo);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bVisualizarTodosConteudos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(FiltrarTematicasActivity.this, CategoriaActivity.class);
                it.putExtra("tematicaVazia", String.valueOf(0));

                startActivity(it);
            }
        });

        bSugerirConteudo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(FiltrarTematicasActivity.this, CadastroConteudo.class);
                startActivity(it);
            }
        });

        listaTematicas = new ArrayList<>();

        WebServiceController webServiceController = new WebServiceController(FiltrarTematicasActivity.this);
        webServiceController.carregaTematicas(new WebServiceController.VolleyResponseListner() {
            @Override
            public void onResponse(Object response) {
                listaTematicas = (ArrayList<Tematica>) response;

                if (listaTematicas != null) {

                    Log.d("ahammm", listaTematicas.toString());
                    tematicaAdapter = new TematicaAdapter(listaTematicas, trataCliqueItem);
                    rvTematicas.setLayoutManager(new LinearLayoutManager(FiltrarTematicasActivity.this));
                    rvTematicas.setItemAnimator(new DefaultItemAnimator());
                    rvTematicas.setAdapter(tematicaAdapter);


                } else {

                }
            }

            @Override
            public void onError(String message) {

            }
        });
    }


    TematicaAdapter.TematicaOnClickListener trataCliqueItem = new TematicaAdapter.TematicaOnClickListener() {

        @Override
        public void onClickTematica(View view, int position) {
            // Aqui o usuário deve ser redirecionado pra outra página que contem as informações do site clicado
            Tematica tematica = listaTematicas.get(position);
            Log.d("passarProxTela", tematica.toString());

            Intent it = new Intent(FiltrarTematicasActivity.this, CategoriaActivity.class);
            it.putExtra("tematica", tematica);

            startActivity(it);

        }
    };

}
