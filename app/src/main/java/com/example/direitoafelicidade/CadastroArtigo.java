package com.example.direitoafelicidade;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import modelDominio.Artigo;
import modelDominio.Conteudo;

public class CadastroArtigo extends AppCompatActivity {
    private EditText linkArtigo, resumoArtigo, anoArtigo, autorArtigo;
    private Button bSalvar;
    private boolean sugeriu;
    private Artigo artigo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_artigo);

        linkArtigo = findViewById(R.id.linkArtigo);
        resumoArtigo = findViewById(R.id.resumoArtigo);
        anoArtigo = findViewById(R.id.anoArtigo);
        autorArtigo = findViewById(R.id.autorArtigo);
        bSalvar = findViewById(R.id.bCadastrarArtigo);

        Intent it = getIntent();
        Bundle parametrosRecebidos = it.getExtras();
        final Conteudo conteudo = (Conteudo) parametrosRecebidos.getSerializable("Conteudo");

        bSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (linkArtigo.getText().toString().isEmpty()) {
                    linkArtigo.setError("Insira o link");
                    linkArtigo.requestFocus();
                    return;
                }
                if (resumoArtigo.getText().toString().isEmpty()) {
                    resumoArtigo.setError("Insira o resumo");
                    resumoArtigo.requestFocus();
                    return;
                }
                if (anoArtigo.getText().toString().isEmpty()) {
                    anoArtigo.setError("Insira o ano");
                    anoArtigo.requestFocus();
                    return;
                }
                if (autorArtigo.getText().toString().isEmpty()) {
                    autorArtigo.setError("Insira o autor");
                    autorArtigo.requestFocus();
                    return;
                }

                /*
                (int codConteudo, String nomeConteudo, String descricaoConteudo,
                  String descricaoIndicacao, ArrayList tematicas,
                  String linkArtigo, String resumoArtigo, int anoPublicacaoArtigo, String autorArtigo)
                 */
                artigo = new Artigo(
                        conteudo.getCodConteudo(),
                        conteudo.getNomeConteudo(),
                        conteudo.getDescricaoConteudo(),
                        conteudo.getDescricaoIndicacao(),
                        conteudo.getTematicas(),
                        linkArtigo.getText().toString(),
                        resumoArtigo.getText().toString(),
                        Integer.parseInt(anoArtigo.getText().toString()),
                        autorArtigo.getText().toString()
                );

                WebServiceController wbc = new WebServiceController(CadastroArtigo.this);
                wbc.sugerir(artigo, 2, new WebServiceController.VolleyResponseListner() {
                    @Override
                    public void onResponse(Object response) {
                        sugeriu = (boolean)response;
                        if (sugeriu){
                            Toast.makeText(CadastroArtigo.this, "Artigo sugerido com sucesso", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(CadastroArtigo.this, "Erro ao sugerir artigo", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(String message) {
                        sugeriu = false;
                        String errorMessage = (message == null) ? "Erro desconhecido" : message;
                        Log.e("sugerir", "erro no VolleyResponseListner no CadastroArtigo: "+errorMessage);
                    }
                });

            }
        });
    }
}
