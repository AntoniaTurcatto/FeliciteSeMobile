package com.example.direitoafelicidade;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;

public class EmocaoActivity extends AppCompatActivity {
    Button btnAtribuirEmocao, btnVerJornada ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emocao);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        btnAtribuirEmocao = findViewById(R.id.btnAtribuirEmocao);
        btnVerJornada = findViewById(R.id.btnVerJornada);
    }

}
