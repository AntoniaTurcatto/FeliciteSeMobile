package com.example.direitoafelicidade;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import modelDominio.Livro;

public class LivrosDetalhadoActivity extends AppCompatActivity {

    TextView tvDetalhadoNomeLivro, tvDetalhadoAutorLivro, tvDetalhadoAnoLivro, tvDetalhadoGeneroLivro, tvDetalhadoPaginasLivro, tvDetalhadoDescricaoLivro, tvDetalhadoTematicaLivro, tvDetalhadoIndicacaoLivro;
    ImageView ivDetalhadoCapaLivro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_livros_detalhado);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tvDetalhadoNomeLivro = findViewById(R.id.tvDetalhadoNomeLivro);
        tvDetalhadoAutorLivro = findViewById(R.id.tvDetalhadoAutorLivro);
        tvDetalhadoAnoLivro = findViewById(R.id.tvDetalhadoAnoLivro);
        tvDetalhadoGeneroLivro = findViewById(R.id.tvDetalhadoGeneroLivro);
        tvDetalhadoPaginasLivro = findViewById(R.id.tvDetalhadoPaginasLivro);
        tvDetalhadoDescricaoLivro = findViewById(R.id.tvDetalhadoDescricaoLivro);
        tvDetalhadoTematicaLivro = findViewById(R.id.tvDetalhadoTematicaLivro);
        tvDetalhadoIndicacaoLivro = findViewById(R.id.tvDetalhadoIndicacaoLivro);
        ivDetalhadoCapaLivro = findViewById(R.id.ivDetalhadoCapaLivro);

        Intent it = getIntent();

        if(it != null /*&& it.hasExtra("livro")*/)
        {
            Log.d("det", it.getStringExtra("nomeLivro") );
            tvDetalhadoNomeLivro.setText(it.getStringExtra("nomeLivro"));
            tvDetalhadoAutorLivro.setText(it.getStringExtra("autorLivro"));
            tvDetalhadoAnoLivro.setText(it.getStringExtra("anoLivro"));

            tvDetalhadoGeneroLivro.setText(it.getStringExtra("generoLivro"));
            tvDetalhadoPaginasLivro.setText(it.getStringExtra("paginasLivro"));
            tvDetalhadoDescricaoLivro.setText(it.getStringExtra("descricaoConteudoLivro"));
            tvDetalhadoIndicacaoLivro.setText(it.getStringExtra("descricaoIndicacao"));
            tvDetalhadoTematicaLivro.setText(it.getStringExtra("tematicasLivro"));

            byte[] imagemByte = it.getByteArrayExtra("imagemLivro");
            Bitmap imagemBitmap = BitmapFactory.decodeByteArray(imagemByte, 0, imagemByte.length);

            ivDetalhadoCapaLivro.setImageBitmap(imagemBitmap);
            /*
            Livro livro = (Livro) it.getSerializableExtra("livro");
            Log.d("agoraDetalhe", livro.toString());
            tvNomeLivro.setText(livro.getNomeConteudo());

            tvAutorLivro.setText(livro.getAutorLivro());
            tvAnoLivro.setText(String.valueOf(livro.getAnoLivro()));
            tvGeneroLivro.setText(livro.getGeneroLivro());
            tvPaginasLivro.setText(String.valueOf(livro.getPaginasLivro()));
            tvDescricao.setText(livro.getDescricaoConteudo());
            tvTematica.setText(String.valueOf(livro.getTematicas()));
            tvIndicacao.setText(livro.getDescricaoIndicacao());
            ivCapaLivro.setImageBitmap(livro.getCapaLivro());

             */
        }
    }

}
