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
import modelDominio.Tematica;

public class TelaLivros extends AppCompatActivity {

    RecyclerView rvLivros;
    LivroAdapter livroAdapter;
    InformacoesApp informacoesApp;
    ArrayList<Livro> listaLivros;
    StringRequest stringRequest;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_livros);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rvLivros = findViewById(R.id.rvLivros);



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        informacoesApp = (InformacoesApp) getApplicationContext();
        listaLivros = new ArrayList<>();

        Intent it = getIntent();
        String filtroCodTematica = it.getStringExtra("codTematica");

        WebServiceController webServiceController = new WebServiceController(TelaLivros.this);
        webServiceController.carregaLivros(filtroCodTematica, new WebServiceController.VolleyResponseListner() {
            @Override
            public void onResponse(Object response) {
                listaLivros = (ArrayList<Livro>) response;
                Log.d("lista livros", listaLivros.toString());


                if (listaLivros != null) {

                    livroAdapter = new LivroAdapter(listaLivros, trataCliqueItem);
                    rvLivros.setLayoutManager(new LinearLayoutManager(TelaLivros.this));
                    rvLivros.setItemAnimator(new DefaultItemAnimator());
                    rvLivros.setAdapter(livroAdapter);
                }
            }

            @Override
            public void onError(String message) {

            }
        });
    }



    LivroAdapter.LivroOnClickListener trataCliqueItem = new LivroAdapter.LivroOnClickListener() {

        @Override
        public void onClickLivro(View view, int position) {
            // Aqui o usuário deve ser redirecionado pra outra página que contem as informações do livro clicado
            Livro livro = listaLivros.get(position);
            Log.d("Detalhado", livro.toString());

            String teste = livro.getNomeConteudo();

            Intent it = new Intent(TelaLivros.this, LivrosDetalhadoActivity.class);
            it.putExtra("nomeLivro", livro.getNomeConteudo());

            it.putExtra("autorLivro", livro.getAutorLivro());
            String anoString = String.valueOf(livro.getAnoLivro());
            it.putExtra("anoLivro",  anoString);
            it.putExtra("generoLivro",  livro.getGeneroLivro());
            String paginasString = String.valueOf(livro.getPaginasLivro());
            it.putExtra("paginasLivro", paginasString);
            it.putExtra("descricaoConteudoLivro", livro.getDescricaoConteudo());
            it.putExtra("descricaoIndicacao", livro.getDescricaoIndicacao());

            String nomeTematica = "";

            for (int i = 0; i < livro.getTematicas().size(); i++) {
                Tematica tematica = livro.getTematicas().get(i);

                nomeTematica = nomeTematica + tematica.getNomeTematica() + "\n";

            }
            it.putExtra("tematicasLivro", nomeTematica);

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            livro.getCapaLivro().compress(Bitmap.CompressFormat.JPEG, 100, stream);
            byte[] img = stream.toByteArray();
            File file = new File(getApplicationContext().getCacheDir(), "tempfile");
            try (FileOutputStream fos = new FileOutputStream(file)){
                fos.write(img);
            } catch (IOException e){
                e.printStackTrace();
            }




            try {
                stream.close();
            } catch (IOException e){
                e.printStackTrace();
            }
            it.putExtra("imagemLivro", file.getAbsolutePath());

            startActivity(it);

        }
    };



}
