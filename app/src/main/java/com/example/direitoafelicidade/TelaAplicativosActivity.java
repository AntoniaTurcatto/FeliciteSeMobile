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
import android.widget.Toast;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import modelDominio.Aplicativo;

public class TelaAplicativosActivity extends AppCompatActivity {

    RecyclerView rvAplicativos;
    AplicativoAdapter aplicativoAdapter;
    ArrayList<Aplicativo> listaAplicativos;

    String urlWebServicesDesenvolvimento = "http://192.168.0.106/projetoAndroid/getAplicativos.php"; // O número deve ser o IPV4 de cada um
    StringRequest stringRequest;
    RequestQueue requestQueue;
    InformacoesApp informacoesApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_aplicativos);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rvAplicativos = findViewById(R.id.rvAplicativos);
        requestQueue = Volley.newRequestQueue(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        informacoesApp = (InformacoesApp) getApplicationContext();
        listaAplicativos = new ArrayList<>();

        Intent it = getIntent();
        String filtroCodTematica = it.getStringExtra("codTematica");

        Log.d("Teste"," Chegou aqui!");

        WebServiceController webServiceController = new WebServiceController(TelaAplicativosActivity.this);
        webServiceController.carregaAplicativos(filtroCodTematica, new WebServiceController.VolleyResponseListner() {
            @Override
            public void onResponse(Object response) {
                listaAplicativos = (ArrayList<Aplicativo>) response;

                if (listaAplicativos != null) {
                    Log.d("ahammm", listaAplicativos.toString());
                    aplicativoAdapter = new AplicativoAdapter(listaAplicativos, trataCliqueItem);
                    rvAplicativos.setLayoutManager(new LinearLayoutManager(TelaAplicativosActivity.this));
                    rvAplicativos.setItemAnimator(new DefaultItemAnimator());
                    rvAplicativos.setAdapter(aplicativoAdapter);
                } else {
                    Toast.makeText(TelaAplicativosActivity.this, "Erro", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onError(String message) {

            }
        });
    };




    AplicativoAdapter.AplicativoOnClickListener trataCliqueItem = new AplicativoAdapter.AplicativoOnClickListener() {
        @Override
        public void onClickAplicativo(View view, int position) {
            // Aqui o usuário deve ser redirecionado pra outra página que contem as informações do site clicado
            Aplicativo app = listaAplicativos.get(position);

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            app.getLogoAplicativo().compress(Bitmap.CompressFormat.JPEG, 100, stream);
            byte[] imgByte = stream.toByteArray();
            File file = new File(getApplicationContext().getCacheDir(), "tempfile");
            try (FileOutputStream fos = new FileOutputStream(file)){

                 fos.write(imgByte);

            } catch (IOException e){
                e.printStackTrace();
            }


            Intent it = new Intent(TelaAplicativosActivity.this, AplicativoDetalhadoActivity.class);
            it.putExtra("codConteudo", app.getCodConteudo());
            it.putExtra("nomeConteudo", app.getNomeConteudo());
            it.putExtra("descConteudo",app.getDescricaoConteudo());
            it.putExtra("descIndi",app.getDescricaoIndicacao());
            it.putExtra("link",app.getLinkAplicativo());
            it.putExtra("filepath", file.getAbsolutePath());
            it.putExtra("dev",app.getDesenvolvedorAplicativo());
            it.putExtra("gratis",app.getGratisAplicativo());
            it.putExtra("tematicas",app.getTematicas());
            //it.putExtra("aplicativo",aplicativo);
            startActivity(it);

        }
    };
}
