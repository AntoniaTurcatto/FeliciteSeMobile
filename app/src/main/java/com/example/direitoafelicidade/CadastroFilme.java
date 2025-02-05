package com.example.direitoafelicidade;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;

import modelDominio.Conteudo;
import modelDominio.Filme;

public class CadastroFilme extends AppCompatActivity {
    private EditText sinopseFilme, duracaoFilme, anoFilme, plataformaFilme;
    private Button bSalvar, bEscolherImagem;
    private ImageView imagemFilme;
    private byte[] imagemByte;

    private static final int PICK_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_filme);

        sinopseFilme = findViewById(R.id.sinopseFilme);
        duracaoFilme = findViewById(R.id.duracaoFilme);
        anoFilme = findViewById(R.id.anoFilme);
        plataformaFilme = findViewById(R.id.plataformaFilme);
        bSalvar = findViewById(R.id.bCadastrarFilme);
        bEscolherImagem = findViewById(R.id.bAdicionarImagem);
        imagemFilme = findViewById(R.id.imagemFilme);

        Intent it = getIntent();
        Bundle parametrosRecebidos = it.getExtras();
        final Conteudo conteudo = (Conteudo) parametrosRecebidos.getSerializable("Conteudo");

        bEscolherImagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, PICK_IMAGE);
            }
        });

        bSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sinopseFilme.getText().toString().isEmpty()) {
                    sinopseFilme.setError("Insira a sinopse");
                    sinopseFilme.requestFocus();
                    return;
                }


                /*

                (int codConteudo, String nomeConteudo, String descricaoConteudo,
                 String descricaoIndicacao,
                 byte[] capaFilmeByte, String sinopseFilme, int duracaoFilme, int anoLancamentoFilme,
                 String plataformaFilme, ArrayList tematicas)
                 */
                Filme filme = new Filme(
                        conteudo.getCodConteudo(),
                        conteudo.getNomeConteudo(),
                        conteudo.getDescricaoConteudo(),
                        conteudo.getDescricaoIndicacao(),
                        imagemByte,
                        sinopseFilme.getText().toString(),
                        Integer.parseInt(duracaoFilme.getText().toString()),
                        Integer.parseInt(anoFilme.getText().toString()),
                        plataformaFilme.getText().toString(),
                        conteudo.getTematicas()
                );

                WebServiceController web = new WebServiceController(CadastroFilme.this);
                web.sugerir(filme, 5, new WebServiceController.VolleyResponseListner() {
                    @Override
                    public void onResponse(Object response) {
                        boolean sucesso = (boolean)response;
                        if (sucesso){
                            Toast.makeText(CadastroFilme.this, "Filme sugerido com sucesso!", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(CadastroFilme.this, "Erro ao sugerir filme", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(String message) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(CadastroFilme.this, "Erro ao sugerir filme", Toast.LENGTH_SHORT).show();
                            }
                        });
                        String errorMessage = (message == null) ? "Erro desconhecido" : message;
                        Log.e("sugerir", "erro no VolleyResponseListner no CadastroFilme: "+errorMessage);
                    }
                });
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                imagemFilme.setImageBitmap(bitmap);
                imagemByte = convertBitmapToByteArray(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private byte[] convertBitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }
}
