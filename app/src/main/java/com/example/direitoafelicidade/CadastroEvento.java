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
import modelDominio.Evento;

public class CadastroEvento extends AppCompatActivity {
    private EditText dataEvento, localEvento, responsavelEvento;
    private Button bSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_evento);

        dataEvento = findViewById(R.id.dataEvento);
        localEvento = findViewById(R.id.localEvento);
        responsavelEvento = findViewById(R.id.responsavelEvento);
        bSalvar = findViewById(R.id.bCadastrarEvento);

        Intent it = getIntent();
        Bundle parametrosRecebidos = it.getExtras();
        final Conteudo conteudo = (Conteudo) parametrosRecebidos.getSerializable("Conteudo");

        bSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dataEvento.getText().toString().isEmpty()) {
                    dataEvento.setError("Insira a data");
                    dataEvento.requestFocus();
                    return;
                }
                if (localEvento.getText().toString().isEmpty()) {
                    localEvento.setError("Insira o local");
                    localEvento.requestFocus();
                    return;
                }
                if (responsavelEvento.getText().toString().isEmpty()) {
                    responsavelEvento.setError("Insira o responsável");
                    responsavelEvento.requestFocus();
                    return;
                }

                Evento evento = new Evento(
                        conteudo.getCodConteudo(),
                        conteudo.getNomeConteudo(),
                        conteudo.getDescricaoConteudo(),
                        conteudo.getDescricaoIndicacao(),
                        conteudo.getTematicas(),
                        dataEvento.getText().toString(),
                        localEvento.getText().toString(),
                        responsavelEvento.getText().toString()
                );

                WebServiceController web = new WebServiceController(CadastroEvento.this);
                web.sugerir(evento, 4, new WebServiceController.VolleyResponseListner() {
                    @Override
                    public void onResponse(Object response) {
                        boolean sucesso = (boolean)response;
                        if (sucesso){
                            Toast.makeText(CadastroEvento.this, "Evento sugerido com sucesso!", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(CadastroEvento.this, "Erro ao sugerir evento", Toast.LENGTH_SHORT).show();
                        }
                        
                    }

                    @Override
                    public void onError(String message) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(CadastroEvento.this, "Erro ao sugerir evento", Toast.LENGTH_SHORT).show();
                            }
                        });
                        String errorMessage = (message == null) ? "Erro desconhecido" : message;
                        Log.e("sugerir", "erro no VolleyResponseListner no CadastroEvento: "+errorMessage);
                    }
                });
            }
        });
    }
}
