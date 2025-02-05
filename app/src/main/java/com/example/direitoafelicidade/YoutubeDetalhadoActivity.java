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
import modelDominio.CanalYoutube;
import modelDominio.Tematica;

public class YoutubeDetalhadoActivity extends AppCompatActivity {

    TextView  tvDetalhadoNomeCanal, tvDetalhadoDescricaoCanal, tvDetalhadoIndicacaoCanal, tvDetalhadoTematicaCanal, tvDetalhadoLinkCanal;
    ImageView ivDetalhadoCapaCanal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_youtube_detalhado);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tvDetalhadoNomeCanal = findViewById(R.id.tvDetalhadoNomeCanal);
        tvDetalhadoDescricaoCanal = findViewById(R.id.tvDetalhadoDescricaoCanal);
        tvDetalhadoIndicacaoCanal = findViewById(R.id.tvDetalhadoIndicacaoCanal);
        tvDetalhadoTematicaCanal = findViewById(R.id.tvDetalhadoTematicaCanal);
        tvDetalhadoLinkCanal = findViewById(R.id.tvDetalhadoLinkCanal);
        ivDetalhadoCapaCanal = findViewById(R.id.ivDetalhadoCapaCanal);


        Intent it = getIntent();

        if(it != null && it.hasExtra("canal"))
        {
            CanalYoutube canalYoutube = (CanalYoutube) it.getSerializableExtra("canal");
            tvDetalhadoNomeCanal.setText(canalYoutube.getNomeConteudo());
            Log.d("DentroDetalhado", canalYoutube.getLinkCanal());
            tvDetalhadoDescricaoCanal.setText(canalYoutube.getDescricaoConteudo());
            tvDetalhadoIndicacaoCanal.setText(canalYoutube.getDescricaoIndicacao());

            tvDetalhadoLinkCanal.setText(canalYoutube.getLinkCanal());

            String nomeTematica = "";

            for (int i = 0; i < canalYoutube.getTematicas().size(); i++) {
                Tematica tematica = canalYoutube.getTematicas().get(i);

                nomeTematica = nomeTematica + tematica.getNomeTematica() + "\n";

            }

            tvDetalhadoTematicaCanal.setText(nomeTematica);

            byte[] imagemByte = canalYoutube.getCapaCanalByte();
            Bitmap imagemBitmap = BitmapFactory.decodeByteArray(imagemByte, 0, imagemByte.length);

            ivDetalhadoCapaCanal.setImageBitmap(imagemBitmap);
        }

    }

}
