package com.example.direitoafelicidade;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ConfiguracaoActivity extends AppCompatActivity {
    Button btnCadastro;
    EditText edtConfirmarSenha, edtSenha, edtEmail, edtNome;
    RadioGroup rgSexo, rgAluno;
    RadioButton rbFem, rbMasc, rbOutro, rbSim, rbNao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracao);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        btnCadastro = findViewById(R.id.btnCadastro);
        edtConfirmarSenha = findViewById(R.id.edtConfirmarSenha);
        edtNome = findViewById(R.id.edtNome);
        edtEmail = findViewById(R.id.edtEmail);
        edtSenha = findViewById(R.id.edtSenha);
        rgSexo = findViewById(R.id.rgSexo);
        rgAluno = findViewById(R.id.rgAluno);
        rbFem = findViewById(R.id.rbFem);
        rbMasc = findViewById(R.id.rbMasc);
        rbOutro = findViewById(R.id.rbOutro);
        rbSim = findViewById(R.id.rbSim);
        rbNao = findViewById(R.id.rbNao);
    }

}
