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
import modelDominio.Serie;

public class CadastroSerie extends AppCompatActivity {
    private EditText duracaoSerie, anoSerie, plataformaSerie, sinopseSerie;
    private Button bSalvar, bEscolherImagem;
    private ImageView imagemSerie;
    private byte[] imagemByte;

    private static final int PICK_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_serie);

        duracaoSerie = findViewById(R.id.temporadasSerie);
        anoSerie = findViewById(R.id.anoSerie);
        plataformaSerie = findViewById(R.id.plataformaSerie);
        bSalvar = findViewById(R.id.bCadastrarSerie);
        bEscolherImagem = findViewById(R.id.bAdicionarImagem);
        imagemSerie = findViewById(R.id.imagemSerie);
        sinopseSerie = findViewById(R.id.sinopseSerie);

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
                if (duracaoSerie.getText().toString().isEmpty()) {
                    duracaoSerie.setError("Insira a duração");
                    duracaoSerie.requestFocus();
                    return;
                }

                /*
                (int codConteudo, String nomeConteudo, String descricaoConteudo,
                 String descricaoIndicacao, byte[] capaSerieByte, String sinopseSerie,
                 int temporadaSerie, int anoLancamentoSerie, String plataformaSerie, ArrayList tematicas) {
                 */
                Serie serie = new Serie(
                        conteudo.getCodConteudo(),
                        conteudo.getNomeConteudo(),
                        conteudo.getDescricaoConteudo(),
                        conteudo.getDescricaoIndicacao(),
                        imagemByte,
                        sinopseSerie.getText().toString(),
                        Integer.parseInt(duracaoSerie.getText().toString()),
                        Integer.parseInt(anoSerie.getText().toString()),
                        plataformaSerie.getText().toString(),
                        conteudo.getTematicas()
                );

                WebServiceController web = new WebServiceController(CadastroSerie.this);
                web.sugerir(serie, 8, new WebServiceController.VolleyResponseListner() {
                    @Override
                    public void onResponse(Object response) {
                        boolean sucesso = (boolean)response;
                        if(sucesso){
                            Toast.makeText(CadastroSerie.this, "Série sugerida com sucesso!", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(CadastroSerie.this, "Erro ao sugerir série", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(String message) {
                        String errorMessage = (message == null) ? "Erro desconhecido" : message;
                        Log.e("sugerir", "erro no VolleyResponseListner no CadastroSerie: "+errorMessage);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(CadastroSerie.this, "Erro ao sugerir serie", Toast.LENGTH_SHORT).show();
                            }
                        });
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
                imagemSerie.setImageBitmap(bitmap);
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
