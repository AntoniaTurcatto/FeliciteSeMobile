package com.example.direitoafelicidade;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RecuperarSenhaActivity extends AppCompatActivity {
    Button btnRecuperar, btnCancelar;
    EditText edtRecuperaSenha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_senha);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


       btnRecuperar = findViewById(R.id.btnRecuperar);
       btnCancelar = findViewById(R.id.btnCancelar);
       edtRecuperaSenha = findViewById(R.id.edtRecuperaSenha);

    }

}
