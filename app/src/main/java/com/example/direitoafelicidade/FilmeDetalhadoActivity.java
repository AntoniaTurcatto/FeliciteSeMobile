package com.example.direitoafelicidade;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.widget.ImageView;
import android.widget.TextView;
import modelDominio.Filme;
import modelDominio.Tematica;

public class FilmeDetalhadoActivity extends AppCompatActivity {

    TextView tvDetalhadoNomeFilme, tvDetalhadoAnoFilme, tvDetalhadoDuracaoFilme, tvDetalhadoSinopseFilme, tvDetalhadoTematicaFilme, tvDetalhadoIndicacaoFilme, tvDetalhadoPlataformasFilme;
    ImageView ivDetalhadoCapaFilme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_filme_detalhado);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvDetalhadoNomeFilme = findViewById(R.id.tvDetalhadoNomeFilme);
        tvDetalhadoAnoFilme = findViewById(R.id.tvDetalhadoAnoFilme);
        tvDetalhadoDuracaoFilme = findViewById(R.id.tvDetalhadoDuracaoFilme);
        tvDetalhadoSinopseFilme = findViewById(R.id.tvDetalhadoSinopseFilme);
        tvDetalhadoTematicaFilme = findViewById(R.id.tvDetalhadoTematicaFilme);
        tvDetalhadoIndicacaoFilme = findViewById(R.id.tvDetalhadoIndicacaoFilme);
        tvDetalhadoPlataformasFilme = findViewById(R.id.tvDetalhadoPlataformasFilme);
        ivDetalhadoCapaFilme = findViewById(R.id.ivDetalhadoCapaFilme);

        Intent it = getIntent();

        if(it != null && it.hasExtra("filmeImgByte"))
        {
            Filme filme = (Filme) it.getSerializableExtra("filmeImgByte");
            tvDetalhadoNomeFilme.setText(filme.getNomeConteudo());
            tvDetalhadoAnoFilme.setText(String.valueOf(filme.getAnoLancamentoFilme()));
            tvDetalhadoDuracaoFilme.setText(String.valueOf(filme.getDuracaoFilme()));
            tvDetalhadoSinopseFilme.setText(filme.getSinopseFilme());

            tvDetalhadoIndicacaoFilme.setText(filme.getDescricaoIndicacao());
            tvDetalhadoPlataformasFilme.setText(filme.getPlataformaFilme());


            String nomeTematica = "";

            for (int i = 0; i < filme.getTematicas().size(); i++) {
                Tematica tematica = filme.getTematicas().get(i);

                nomeTematica = nomeTematica + tematica.getNomeTematica() + "\n";

            }

            tvDetalhadoTematicaFilme.setText(nomeTematica);
            byte[] bytes = filme.getCapaFilmeByte();
            Bitmap imagemBitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

            ivDetalhadoCapaFilme.setImageBitmap(imagemBitmap);
        }

    }

}
