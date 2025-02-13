package com.example.direitoafelicidade;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.direitoafelicidade.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import modelDominio.Aplicativo;
import modelDominio.PaginaWeb;
import modelDominio.Tematica;

public class AplicativoDetalhadoActivity extends AppCompatActivity {

    TextView tvDetalhadoNomeAplicativo, tvDetalhadoDesenvolvedoresAplicativo, tvDetalhadoDescricaoAplicativo, tvDetalhadoLinkAplicativo, tvDetalhadoDescricaoIndicacaoAplicativo, tvDetalhadoTematicaAplicativo;
    ImageView ivDetalhadoLogoAplicativo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aplicativo_detalhado);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Log.d("DEBUG", "Entrou na Activity AplicativoDetalhadoActivity");
        tvDetalhadoNomeAplicativo = findViewById(R.id.tvDetalhadoNomeAplicativo);
        tvDetalhadoDesenvolvedoresAplicativo = findViewById(R.id.tvDetalhadoDesenvolvedoresAplicativo);
        tvDetalhadoDescricaoAplicativo = findViewById(R.id.tvDetalhadoDescricaoAplicativo);
        tvDetalhadoLinkAplicativo = findViewById(R.id.tvDetalhadoLinkAplicativo);
        tvDetalhadoDescricaoIndicacaoAplicativo = findViewById(R.id.tvDetalhadoDescricaoIndicacaoAplicativo);
        tvDetalhadoTematicaAplicativo = findViewById(R.id.tvDetalhadoTematicaAplicativo);
        ivDetalhadoLogoAplicativo = findViewById(R.id.ivDetalhadoLogoAplicativo);

        Intent it = getIntent();

        if(it != null && it.hasExtra("filepath"))
        {
            //Aplicativo app = (Aplicativo) it.getSerializableExtra("aplicativo");
            int codConteudo = it.getIntExtra("codConteudo",0);
            String nomeConteudo = it.getStringExtra("nomeConteudo");
            String descConteudo = it.getStringExtra("descConteudo");
            String descIndi = it.getStringExtra("descIndi");
            String link = it.getStringExtra("link");
            String dev = it.getStringExtra("dev");
            int gratis = it.getIntExtra("gratis", 1);
            ArrayList<Tematica> tematicas = (ArrayList<Tematica>) it.getSerializableExtra("tematicas");

            byte[] imagemByte={0};
            String filePath = getIntent().getStringExtra("filepath");
            Log.e("DEBUG - file path: ", filePath);

            File file = new File(filePath);

            if (filePath == null || filePath.isEmpty()) {
                Log.e("DEBUG", "O filePath está nulo ou vazio!");
            }

            try(FileInputStream fis = new FileInputStream(file);){
                imagemByte = new byte[(int) file.length()];
                fis.read(imagemByte);

                Aplicativo app = new Aplicativo(codConteudo, nomeConteudo, descConteudo, descIndi, link, imagemByte,  dev, gratis, tematicas);

                Bitmap imagemBitmap = BitmapFactory.decodeByteArray(imagemByte, 0, imagemByte.length);
                ivDetalhadoLogoAplicativo.setImageBitmap(imagemBitmap);
                tvDetalhadoNomeAplicativo.setText(app.getNomeConteudo());
                tvDetalhadoLinkAplicativo.setText(app.getLinkAplicativo());
                tvDetalhadoDesenvolvedoresAplicativo.setText(app.getDesenvolvedorAplicativo());
                tvDetalhadoDescricaoAplicativo.setText(app.getDescricaoConteudo());
                tvDetalhadoDescricaoIndicacaoAplicativo.setText(app.getDescricaoIndicacao());
                String nomeTematica = "";
                for (int i = 0; i < app.getTematicas().size(); i++) {
                    Tematica tematica = app.getTematicas().get(i);

                    nomeTematica = nomeTematica + tematica.getNomeTematica() + "\n";

                }
                tvDetalhadoTematicaAplicativo.setText(nomeTematica);

            }catch (IOException e){
                e.printStackTrace();
            }






        } else {
            Log.d("DEBUG", "Sem parametros");
        }

    }


    }


