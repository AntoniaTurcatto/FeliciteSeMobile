package com.example.direitoafelicidade;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RegistrarEmocaoActivity extends AppCompatActivity {
    Button btnRegistraEmocao, btnVoltar;
    TextView tvRadiante, tvFeliz, tvNormal, tvTriste, tvMuitoMal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_emocao);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


       btnRegistraEmocao = findViewById(R.id.btnRegistraEmocao);
       btnVoltar = findViewById(R.id.btnVoltar);
       tvRadiante = findViewById(R.id.tvRadiante);
        tvFeliz = findViewById(R.id.tvFeliz);
       tvNormal = findViewById(R.id.tvNormal);
        tvTriste = findViewById(R.id.tvTriste);
        tvMuitoMal = findViewById(R.id.tvMuitoMal);

    }

}
