package com.example.direitoafelicidade;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;
import java.util.ArrayList;
import modelDominio.PaginaWeb;

public class TelaSitesActivity extends AppCompatActivity {

    RecyclerView rvSites;
    SiteAdapter siteAdapter;
    ArrayList<PaginaWeb> paginaWebs;


    InformacoesApp informacoesApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_sites);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rvSites = findViewById(R.id.rvSites);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        informacoesApp = (InformacoesApp) getApplicationContext();
        paginaWebs = new ArrayList<>();

        Intent it = getIntent();
        String filtroCodTematica = it.getStringExtra("codTematica");

        WebServiceController webServiceController = new WebServiceController(TelaSitesActivity.this);
        webServiceController.carregaSites(filtroCodTematica, new WebServiceController.VolleyResponseListner() {
            @Override
            public void onResponse(Object response) {
                paginaWebs = (ArrayList<PaginaWeb>) response;

                if (paginaWebs != null){
                    Log.d("aaa", paginaWebs.toString());
                    SiteAdapter siteAdapter = new SiteAdapter(paginaWebs, trataCliqueItem);
                    rvSites.setLayoutManager(new LinearLayoutManager(TelaSitesActivity.this));
                    rvSites.setItemAnimator(new DefaultItemAnimator());
                    rvSites.setAdapter(siteAdapter);
                }
            }

            @Override
            public void onError(String message) {

            }
        });







    }




    SiteAdapter.PaginaWebOnClickListener trataCliqueItem = new SiteAdapter.PaginaWebOnClickListener() {
        @Override
        public void onClickPaginaWeb(View view, int position) {
            // Aqui o usuário deve ser redirecionado pra outra página que contem as informações do site clicado
            PaginaWeb pw = paginaWebs.get(position);
            Log.d("Posição: ", paginaWebs.get(position).toString());

            Intent it = new Intent(TelaSitesActivity.this, Site_Detalhado_Activity.class);
            it.putExtra("site",pw);
            startActivity(it);

        }
    };

}
