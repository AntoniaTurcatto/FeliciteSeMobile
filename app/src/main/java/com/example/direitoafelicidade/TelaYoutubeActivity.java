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
import modelDominio.CanalYoutube;

public class TelaYoutubeActivity extends AppCompatActivity {

    RecyclerView rvYoutube;
    YoutubeAdapter youtubeAdapter;
    ArrayList<CanalYoutube> canais;

    RequestQueue requestQueue;
    InformacoesApp informacoesApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_youtube);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rvYoutube = findViewById(R.id.rvYoutube);
        requestQueue = Volley.newRequestQueue(this);



        informacoesApp = (InformacoesApp) getApplicationContext();
        canais = new ArrayList<>();


        Intent it = getIntent();

        String filtroCodTematica = it.getStringExtra("codTematica");

        Log.d("codTematica = ", filtroCodTematica);



        WebServiceController webServiceController = new WebServiceController(TelaYoutubeActivity.this);
        webServiceController.carregaCanais(filtroCodTematica, new WebServiceController.VolleyResponseListner() {
            @Override
            public void onResponse(Object response) {
                canais = (ArrayList<CanalYoutube>) response;
                if (canais != null) {
                    Log.d("aaa", canais.toString());
                    YoutubeAdapter youtubeAdapter = new YoutubeAdapter(canais, trataCliqueItem);
                    rvYoutube.setLayoutManager(new LinearLayoutManager(TelaYoutubeActivity.this));
                    rvYoutube.setItemAnimator(new DefaultItemAnimator());
                    rvYoutube.setAdapter(youtubeAdapter);
                }
            }

            @Override
            public void onError(String message) {

            }
        });
    }




    YoutubeAdapter.YoutubeOnClickListener trataCliqueItem = new YoutubeAdapter.YoutubeOnClickListener() {
        @Override
        public void onClickYoutube(View view, int position) {
            // Aqui o usuário deve ser redirecionado pra outra página que contem as informações do site clicado
            CanalYoutube canalYoutube = canais.get(position);
            Log.d("Posição: ", canais.get(position).toString());

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            canalYoutube.getCapaCanal().compress(Bitmap.CompressFormat.JPEG, 100, stream);
            byte[] img = stream.toByteArray();
            File file = new File(getApplicationContext().getCacheDir(), "filepath");
            try(FileOutputStream fos = new FileOutputStream(file)){
                fos.write(img);
            }catch (IOException e){
                e.printStackTrace();
            }

            Intent it = new Intent(TelaYoutubeActivity.this, YoutubeDetalhadoActivity.class);
            //it.putExtra("canal",canalYoutubeComImgByte);
            it.putExtra("codConteudo", canalYoutube.getCodConteudo());
            it.putExtra("nomeConteudo", canalYoutube.getNomeConteudo());
            it.putExtra("descConteudo", canalYoutube.getDescricaoConteudo());
            it.putExtra("descIndi", canalYoutube.getDescricaoIndicacao());
            it.putExtra("link", canalYoutube.getLinkCanal());
            it.putExtra("tematicas", canalYoutube.getTematicas());
            it.putExtra("filepath", file.getAbsolutePath());
            startActivity(it);

        }
    };
}
