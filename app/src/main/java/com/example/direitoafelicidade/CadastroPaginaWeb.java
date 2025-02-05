package com.example.direitoafelicidade;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import modelDominio.Conteudo;
import modelDominio.PaginaWeb;

public class CadastroPaginaWeb extends AppCompatActivity {
    private EditText linkPaginaWeb, autorPaginaWeb;
    private Button bSalvar;
    private boolean sugeriu;
    private PaginaWeb paginaWeb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pagina_web);

        linkPaginaWeb = findViewById(R.id.linkPaginaWeb);
        autorPaginaWeb = findViewById(R.id.autorPaginaWeb);
        bSalvar = findViewById(R.id.bCadastrarPaginaWeb);

        Intent it = getIntent();
        Bundle parametrosRecebidos = it.getExtras();
        final Conteudo conteudo = (Conteudo) parametrosRecebidos.getSerializable("Conteudo");

        bSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (linkPaginaWeb.getText().toString().isEmpty()) {
                    linkPaginaWeb.setError("Insira o link");
                    linkPaginaWeb.requestFocus();
                    return;
                }
                if (autorPaginaWeb.getText().toString().isEmpty()) {
                    autorPaginaWeb.setError("Insira o autor");
                    autorPaginaWeb.requestFocus();
                    return;
                }
                /*
                (int codConteudo, String nomeConteudo, String descricaoConteudo,
                     String descricaoIndicacao,
                     String linkPagina, String autorPagina, ArrayList tematicas)
                 */

                paginaWeb = new PaginaWeb(
                        conteudo.getCodConteudo(),
                        conteudo.getNomeConteudo(),
                        conteudo.getDescricaoConteudo(),
                        conteudo.getDescricaoIndicacao(),
                        linkPaginaWeb.getText().toString(),
                        autorPaginaWeb.getText().toString(),
                        conteudo.getTematicas()
                );

                //sugerindo pagina web

                WebServiceController controller = new WebServiceController(CadastroPaginaWeb.this);
                controller.sugerir(paginaWeb, 7, new WebServiceController.VolleyResponseListner() {
                    @Override
                    public void onResponse(Object response) {
                        sugeriu = (boolean)response;

                        if(sugeriu){
                            Toast.makeText(CadastroPaginaWeb.this, "Pagina Web sugerida com sucesso!", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(CadastroPaginaWeb.this, "Erro ao sugerir Pagina Web!", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onError(String message) {
                        sugeriu = false;
                        String errorMessage = (message == null) ? "Erro desconhecido" : message;
                        Log.e("sugerir", "erro no VolleyResponseListner no CadastroPaginaWeb: "+errorMessage);
                    }
                });

            }
        });
    }
}
