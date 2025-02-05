package com.example.direitoafelicidade;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.direitoafelicidade.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import modelDominio.Filme;
import modelDominio.Livro;


public class TelaFilmesActivity extends AppCompatActivity {

    RecyclerView rvFilmes;
    FilmeAdapter filmeAdapter;
    ArrayList<Filme> listaFilmes;


    InformacoesApp informacoesApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_filmes);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rvFilmes = findViewById(R.id.rvFilmes);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        informacoesApp = (InformacoesApp) getApplicationContext();
        listaFilmes = new ArrayList<>();

        Intent it = getIntent();
        String filtroCodtematica = it.getStringExtra("codTematica");

        WebServiceController webServiceController = new WebServiceController(TelaFilmesActivity.this);
        webServiceController.carregaFilmes(filtroCodtematica, new WebServiceController.VolleyResponseListner() {
            @Override
            public void onResponse(Object response) {
                listaFilmes = (ArrayList<Filme>) response;
                Log.d("vrauu", listaFilmes.toString());


                if (listaFilmes != null) {

                    filmeAdapter = new FilmeAdapter(listaFilmes, trataCliqueItem);
                    rvFilmes.setLayoutManager(new LinearLayoutManager(TelaFilmesActivity.this));
                    rvFilmes.setItemAnimator(new DefaultItemAnimator());
                    rvFilmes.setAdapter(filmeAdapter);
                }
            }

            @Override
            public void onError(String message) {

            }
        });
    }




    FilmeAdapter.FilmeOnClickListener trataCliqueItem = new FilmeAdapter.FilmeOnClickListener() {
        @Override
        public void onClickFilme(View view, int position) {
            // Aqui o usuário deve ser redirecionado pra outra página que contem as informações do site clicado
            Filme filme = listaFilmes.get(position);
            Log.d("Posição: ", listaFilmes.get(position).toString());


            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            filme.getCapaFilme().compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] img = stream.toByteArray();

            Filme filmeImgByte = new Filme(filme.getCodConteudo(), filme.getNomeConteudo(), filme.getDescricaoConteudo(), filme.getDescricaoIndicacao(), img, filme.getSinopseFilme(), filme.getDuracaoFilme(), filme.getAnoLancamentoFilme(), filme.getPlataformaFilme(), filme.getTematicas());

            Intent it = new Intent(TelaFilmesActivity.this, FilmeDetalhadoActivity.class);


            it.putExtra("filmeImgByte", filmeImgByte);
            startActivity(it);

        }
    };

}
