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

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

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

        if(it != null && it.hasExtra("filepath"))
        {
            int codConteudo = it.getIntExtra("codConteudo",0);
            String nomeConteudo = it.getStringExtra("nomeConteudo");
            String descConteudo = it.getStringExtra("descConteudo");
            String descIndi = it.getStringExtra("descIndi");
            String link = it.getStringExtra("link");
            ArrayList<Tematica> tematicas = (ArrayList<Tematica>) it.getSerializableExtra("tematicas");
            String filepath = it.getStringExtra("filepath");

            byte[] img = {0};
            File file = new File(filepath);
            try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))){
                img = new byte[(int)file.length()];
                bis.read(img);

            }catch (IOException e){
                e.printStackTrace();
            }

            CanalYoutube canalYoutube = new CanalYoutube(codConteudo,
                    nomeConteudo,
                    descConteudo,
                    descIndi,
                    link,
                    tematicas,
                    img);
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
