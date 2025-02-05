package com.example.direitoafelicidade;

import android.content.Intent;
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
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;

import modelDominio.Artigo;

public class TelaArtigos extends AppCompatActivity {

    RecyclerView rvArtigos;
    ArtigoAdapter artigoAdapter;
    InformacoesApp informacoesApp;

    RequestQueue requestQueue;
    ArrayList<Artigo> artigos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_artigos);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rvArtigos = findViewById(R.id.rvArtigos);
        requestQueue = Volley.newRequestQueue(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        informacoesApp = (InformacoesApp) getApplicationContext();

        artigos = new ArrayList<>();

        Intent it = getIntent();
        String filtroCOdTematica = it.getStringExtra("codTematica");

        WebServiceController webServiceController = new WebServiceController(TelaArtigos.this);
        webServiceController.carregaArtigos(filtroCOdTematica, new WebServiceController.VolleyResponseListner() {
            @Override
            public void onResponse(Object response) {
                artigos = (ArrayList<Artigo>) response;
                Log.d("vrauu", artigos.toString());


                if (artigos != null) {
                    Log.d("ahammm", artigos.toString());
                    artigoAdapter = new ArtigoAdapter(artigos, trataCliqueItem);
                    rvArtigos.setLayoutManager(new LinearLayoutManager(TelaArtigos.this));
                    rvArtigos.setItemAnimator(new DefaultItemAnimator());
                    rvArtigos.setAdapter(artigoAdapter);
                }
            }

            @Override
            public void onError(String message) {

            }
        });
        //carregaArtigos();

    }

    /*
    public void carregaArtigos()
    {
        stringRequest = new StringRequest(Request.Method.POST, urlWebServicesDesenvolvimento,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Busca Artigos", response);

                        try
                        {
                            JSONObject jsonObject = new JSONObject(response);

                            Log.d("Busca Artigos", jsonObject.toString());

                            boolean isErro = jsonObject.getBoolean("erro");

                            if(isErro)
                            {
                                Toast.makeText(getApplicationContext(),"Dados inválidos",Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                JsonController jsonController = new JsonController();
                                listaArtigos = jsonController.obtemArtigos(jsonObject,response);
                                Log.d("Teste"," Lista de Artigos depois de chamar a fução controller " + listaArtigos);
                                // Nessa lista acima tem dados

                                if (listaArtigos != null)
                                {
                                    Log.d("Teste se entrou no IF", "Lista de artigos dentro do IF: " + listaArtigos);
                                    artigoAdapter = new ArtigoAdapter(listaArtigos, trataCliqueItem);
                                    rvArtigos.setLayoutManager(new LinearLayoutManager(TelaArtigos.this));
                                    rvArtigos.setItemAnimator(new DefaultItemAnimator());
                                    rvArtigos.setAdapter(artigoAdapter);
                                }
                            }
                        }
                        catch(Exception e)
                        {
                            Log.d("Teste","Exceção no método carregaArtigos() " + e.getMessage());


                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("LogLogin",error.getMessage());
            }
        }){

        };
        requestQueue.add(stringRequest);

    }*/


    ArtigoAdapter.ArtigoOnClickListener trataCliqueItem = new ArtigoAdapter.ArtigoOnClickListener() {
        @Override
        public void onClickArtigo(View view, int position) {
            // Aqui o usuário deve ser redirecionado pra outra página que contem as informações do site clicado
            Artigo artigo = artigos.get(position);

            Intent it = new Intent(TelaArtigos.this, ArtigoDetalhadoActivity.class);
            it.putExtra("artigo",artigo);
            startActivity(it);

        }
    };

}
