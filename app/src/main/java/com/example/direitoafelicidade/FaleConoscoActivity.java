package com.example.direitoafelicidade;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class FaleConoscoActivity extends AppCompatActivity {
    Button btnEnviar, btnCancela;
    RadioButton rbIdentificar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fale_conosco);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        btnEnviar = findViewById(R.id.btnEnviar);
        btnCancela = findViewById(R.id.btnCancela);
        rbIdentificar = findViewById(R.id.rbIdentificar);
    }

}
