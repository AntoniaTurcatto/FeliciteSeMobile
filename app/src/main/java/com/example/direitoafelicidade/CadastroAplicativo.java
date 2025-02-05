package com.example.direitoafelicidade;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;

import modelDominio.Aplicativo;
import modelDominio.Conteudo;

public class CadastroAplicativo extends AppCompatActivity {
    private EditText linkApp, desenvolvedorApp;
    private RadioButton opSim, opNao;
    private Button bSalvar, bEscolherImagem;
    private ImageView imagemApp;
    private byte[] imagemByte;
    private Aplicativo app;
    private boolean sugeriu;

    private static final int PICK_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_aplicativo);

        linkApp = findViewById(R.id.linkAplicativo);
        desenvolvedorApp = findViewById(R.id.desenvolvedorAplicativo);
        opSim = findViewById(R.id.rbSim);
        opNao = findViewById(R.id.rbNao);
        bSalvar = findViewById(R.id.bCadastrarAplicativo);
        bEscolherImagem = findViewById(R.id.bAdicionarImagem);
        imagemApp = findViewById(R.id.imagemAplicativo);

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
                if (linkApp.getText().toString().isEmpty()) {
                    linkApp.setError("Insira o link");
                    linkApp.requestFocus();
                    return;
                }
                if (desenvolvedorApp.getText().toString().isEmpty()) {
                    desenvolvedorApp.setError("Insira o desenvolvedor");
                    desenvolvedorApp.requestFocus();
                    return;
                }

                int gratis = opSim.isChecked() ? 1 : 0;


                /*

                (int codConteudo, String nomeConteudo, String descricaoConteudo,
                      String descricaoIndicacao,
                      String linkAplicativo, byte[] logoAplicativoByte, String desenvolvedorAplicativo,
                      int gratisAplicativo, ArrayList tematicas) {

                 */
                app = new Aplicativo(
                        conteudo.getCodConteudo(),
                        conteudo.getNomeConteudo(),
                        conteudo.getDescricaoConteudo(),
                        conteudo.getDescricaoIndicacao(),
                        linkApp.getText().toString(),
                        imagemByte,
                        desenvolvedorApp.getText().toString(),
                        gratis,
                        conteudo.getTematicas()

                );

                WebServiceController wbc = new WebServiceController(CadastroAplicativo.this);
                wbc.sugerir(app, 1, new WebServiceController.VolleyResponseListner() {
                    @Override
                    public void onResponse(Object response) {
                        sugeriu = (boolean)response;
                        if(sugeriu){
                            Toast.makeText(CadastroAplicativo.this, "Aplicativo sugerido com sucesso!", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(CadastroAplicativo.this, "Erro ao sugerir aplicativo", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(String message) {
                        sugeriu = false;
                        String errorMessage = (message == null) ? "Erro desconhecido" : message;
                        Log.e("sugerir", "erro no VolleyResponseListner no CadastroAplicativo: "+errorMessage);
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
                imagemApp.setImageBitmap(bitmap);
                imagemByte = convertBitmapToByteArray(bitmap);
                String base64String = Base64.encodeToString(imagemByte, Base64.NO_WRAP);
                Log.d("debugImagem byte: ", base64String);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(data == null){
            Log.d("debugImagem", "imagem null");
        }

    }

    private byte[] convertBitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }
}
