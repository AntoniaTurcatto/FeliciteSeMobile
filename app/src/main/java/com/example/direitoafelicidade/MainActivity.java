package com.example.direitoafelicidade;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnLogin, btnCadastro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(clickLogin);

        btnCadastro = findViewById(R.id.btnCadastro);
        btnCadastro.setOnClickListener(clickCadastro);
    }

    View.OnClickListener clickLogin = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            Intent novaTela = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(novaTela);
            finish();
        }
    };

    View.OnClickListener clickCadastro = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent novaTela = new Intent (MainActivity.this, CadastroActivity.class);
            startActivity(novaTela);

        }
    };
}
