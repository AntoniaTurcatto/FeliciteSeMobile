package com.example.direitoafelicidade;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import modelDominio.Serie;
import modelDominio.Tematica;

public class SerieDetalhadaActivity extends AppCompatActivity {
    TextView tvSerieDetalhadaNomeSerie, tvSerieDetalhadaDescricaoSerie, tvSerieDetalhadaDescricaoIndicacaoSerie, tvSerieDetalhadaSinopseSerie, tvSerieDetalhadaQuantidadeTemporadasSerie, tvSerieDetalhadaAnoLancamentoSerie, tvSerieDetalhadaTematicasSerie, tvSerieDetalhadaPlataformaSerie;
    ImageView ivSerieDetalhadaCapaSerie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serie_detalhada);
        Toolbar toolbar = findViewById(R.id.toolbar);
        tvSerieDetalhadaNomeSerie = findViewById(R.id.tvSerieDetalhadaNomeSerie);
        tvSerieDetalhadaDescricaoSerie = findViewById(R.id.tvSerieDetalhadaDescricaoSerie);
        tvSerieDetalhadaDescricaoIndicacaoSerie = findViewById(R.id.tvSerieDetalhadaDescricaoIndicacaoSerie);
        tvSerieDetalhadaSinopseSerie = findViewById(R.id.tvSerieDetalhadaSinopseSerie);
        tvSerieDetalhadaQuantidadeTemporadasSerie = findViewById(R.id.tvSerieDetalhadaQuantidadeTemporadasSerie);
        tvSerieDetalhadaAnoLancamentoSerie = findViewById(R.id.tvSerieDetalhadaAnoLancamentoSerie);
        tvSerieDetalhadaTematicasSerie = findViewById(R.id.tvSerieDetalhadaTematicasSerie);
        tvSerieDetalhadaPlataformaSerie = findViewById(R.id.tvSerieDetalhadaPlataformaSerie);
        ivSerieDetalhadaCapaSerie = findViewById(R.id.ivSerieDetalahaCapaSerie);

        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent it = getIntent();

        if(it != null ) {

            int codConteudo = it.getIntExtra("codConteudo",0);
            String nomeConteudo = it.getStringExtra("nomeConteudo");
            String descConteudo = it.getStringExtra("descConteudo");
            String sinopse = it.getStringExtra("sinopse");
            String descIndi = it.getStringExtra("descIndi");
            int duracao = it.getIntExtra("duracao",90);
            int ano = it.getIntExtra("ano", 0);
            String plat = it.getStringExtra("plat");
            ArrayList<Tematica> tematicas = (ArrayList<Tematica>)it.getSerializableExtra("tematicas");
            String filepath = it.getStringExtra("filepath");
            File file = new File(filepath);
            byte[] img = {0};
            try (FileInputStream fis = new FileInputStream(file)){
                img = new byte[(int)file.length()];
                fis.read(img);
            }catch (IOException e){
                e.printStackTrace();
            }

            Serie serie = new Serie(codConteudo,
                    nomeConteudo,
                    descConteudo,
                    descIndi,
                    img,
                    sinopse,
                    duracao,
                    ano,
                    plat,
                    tematicas);
            System.out.println(serie);

            tvSerieDetalhadaNomeSerie.setText(serie.getNomeConteudo());

            tvSerieDetalhadaDescricaoSerie.setText(serie.getDescricaoConteudo());

            tvSerieDetalhadaDescricaoIndicacaoSerie.setText(serie.getDescricaoIndicacao());

            tvSerieDetalhadaSinopseSerie.setText(serie.getSinopseSerie());

            tvSerieDetalhadaQuantidadeTemporadasSerie.setText(String.valueOf(serie.getTemporadaSerie()));

            tvSerieDetalhadaAnoLancamentoSerie.setText(String.valueOf(serie.getAnoLancamentoSerie()));

            //tratamento das tematicas
            String tematicasStringSerie = "";
            for(int i = 0; i < serie.getTematicas().size(); i++){
                Tematica tematica = serie.getTematicas().get(i);
                tematicasStringSerie = tematicasStringSerie + tematica.getNomeTematica() + "\n";
            }

            tvSerieDetalhadaTematicasSerie.setText(tematicasStringSerie);

            tvSerieDetalhadaPlataformaSerie.setText(serie.getPlataformaSerie());

            byte[] capaSerieByte = serie.getCapaSerieByte();
            Bitmap capaSerieBitmap = BitmapFactory.decodeByteArray(capaSerieByte, 0, capaSerieByte.length);

            ivSerieDetalhadaCapaSerie.setImageBitmap(capaSerieBitmap);







        }




    }

}
