package com.example.direitoafelicidade;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import modelDominio.Livro;

public class CadastroLivro extends AppCompatActivity {
    private EditText editoraLivro, anoLivro, paginasLivro, autorLivro, generoLivro;
    private Button bSalvar, bEscolherImagem;
    private ImageView imagemLivro;
    private byte[] imagemByte;

    private static final int PICK_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_livro);

        editoraLivro = findViewById(R.id.editoraLivro);
        anoLivro = findViewById(R.id.anoLivro);
        paginasLivro = findViewById(R.id.paginasLivro);
        autorLivro = findViewById(R.id.autorLivro);
        generoLivro = findViewById(R.id.generoLivro);
        bSalvar = findViewById(R.id.bCadastrarLivro);
        bEscolherImagem = findViewById(R.id.bAdicionarImagem);
        imagemLivro = findViewById(R.id.imagemLivro);

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
                if (editoraLivro.getText().toString().isEmpty()) {
                    editoraLivro.setError("Insira a editora");
                    editoraLivro.requestFocus();
                    return;
                }

                /*

                (int codConteudo, String nomeConteudo, String descricaoConteudo,
                 String descricaoIndicacao, ArrayList tematicas,
                 String editoraLivro, Bitmap capaLivro, int anoLivro, int paginasLivro,
                 String autorLivro, String generoLivro)

                 */

                Livro livro = new Livro(
                        conteudo.getCodConteudo(),
                        conteudo.getNomeConteudo(),
                        conteudo.getDescricaoConteudo(),
                        conteudo.getDescricaoIndicacao(),
                        conteudo.getTematicas(),
                        editoraLivro.getText().toString(),
                        BitmapFactory.decodeByteArray(imagemByte, 0, imagemByte.length),
                        Integer.parseInt(anoLivro.getText().toString()),
                        Integer.parseInt(paginasLivro.getText().toString()),
                        autorLivro.getText().toString(),
                        generoLivro.getText().toString()
                );
                WebServiceController web = new WebServiceController(CadastroLivro.this);
                web.sugerir(livro, 6, new WebServiceController.VolleyResponseListner() {
                    @Override
                    public void onResponse(Object response) {
                        boolean sucesso = (boolean)response;
                        if(sucesso){
                            Toast.makeText(CadastroLivro.this, "Livro sugerido com sucesso!", Toast.LENGTH_SHORT).show();
                            finish();
                        }else {
                            Toast.makeText(CadastroLivro.this, "Erro ao sugerir livro", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(String message) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(CadastroLivro.this, "Erro ao sugerir livro", Toast.LENGTH_SHORT).show();
                            }
                        });
                        String errorMessage = (message == null) ? "Erro desconhecido" : message;
                        Log.e("sugerir", "erro no VolleyResponseListner no CadastroLivro: "+errorMessage);
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
                imagemLivro.setImageBitmap(bitmap);
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
