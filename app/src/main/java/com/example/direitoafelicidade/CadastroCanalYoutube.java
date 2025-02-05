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

import modelDominio.CanalYoutube;
import modelDominio.Conteudo;

public class CadastroCanalYoutube extends AppCompatActivity {
    private EditText linkCanal;
    private Button bSalvar, bEscolherImagem;
    private ImageView imagemCanal;
    private byte[] imagemByte;

    private static final int PICK_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_youtube);

        linkCanal = findViewById(R.id.linkCanalYoutube);
        bSalvar = findViewById(R.id.bCadastrarCanal);
        bEscolherImagem = findViewById(R.id.bAdicionarImagem);
        imagemCanal = findViewById(R.id.imagemCanalYoutube);

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
                if (linkCanal.getText().toString().isEmpty()) {
                    linkCanal.setError("Insira o link");
                    linkCanal.requestFocus();
                    return;
                }

                /*
                (int codConteudo, String nomeConteudo, String descricaoConteudo,
                        String descricaoIndicacao,
                        String linkCanal, ArrayList tematicas, byte[] capaCanal)
                 */
                CanalYoutube canal = new CanalYoutube(
                        conteudo.getCodConteudo(),
                        conteudo.getNomeConteudo(),
                        conteudo.getDescricaoConteudo(),
                        conteudo.getDescricaoIndicacao(),
                        linkCanal.getText().toString(),
                        conteudo.getTematicas(),
                        imagemByte
                );

                WebServiceController wbc = new WebServiceController(CadastroCanalYoutube.this);
                wbc.sugerir(canal, 3, new WebServiceController.VolleyResponseListner() {
                    @Override
                    public void onResponse(Object response) {
                        boolean sucesso = (boolean)response;
                        if(sucesso){
                            Toast.makeText(CadastroCanalYoutube.this, "Canal sugerido com sucesso!", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(CadastroCanalYoutube.this, "Erro ao sugerir canal do youtube", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(String message) {
                        String errorMessage = (message == null) ? "Erro desconhecido" : message;
                        Log.e("sugerir", "erro no VolleyResponseListner no CadastroCanalYoutube: "+errorMessage);
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
                imagemCanal.setImageBitmap(bitmap);
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
