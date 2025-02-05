package com.example.direitoafelicidade;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import modelDominio.Usuario;


public class CadastroActivity extends AppCompatActivity {


    Button btnCadastro;
    EditText edtConfirmarSenha, edtSenha, edtEmail, edtNome;
    RadioGroup rgSexo, rgAluno;
    RadioButton rbFem, rbMasc, rbOutro, rbSim, rbNao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
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



        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!edtNome.getText().toString().equals(""))
                {
                    if(rbMasc.isChecked() || rbFem.isChecked() || rbOutro.isChecked())
                    {
                        if(rbSim.isChecked() || rbNao.isChecked())
                        {
                            if(!edtEmail.getText().toString().equals(""))
                            {
                                if(!edtSenha.getText().toString().equals(""))
                                {
                                    String nome = edtNome.getText().toString();
                                    int sexo;
                                    if(rbMasc.isChecked())
                                    {
                                        sexo = 1;
                                    }
                                    else if(rbFem.isChecked())
                                    {
                                        sexo = 2;
                                    }
                                    else
                                    {
                                        sexo = 3;
                                    }
                                    int ifParticipante;
                                    if(rbSim.isChecked())
                                    {
                                        ifParticipante = 1;
                                    }
                                    else
                                    {
                                        ifParticipante = 2;
                                    }

                                    String email = edtEmail.getText().toString();
                                    String senha = edtSenha.getText().toString();

                                    Usuario usuario = new Usuario(nome, sexo, ifParticipante, email, senha);

                                    Log.d("Criou usuario? + ", usuario.toString());
                                    WebServiceController webServiceController = new WebServiceController(CadastroActivity.this);
                                    webServiceController.cadastrarUsuario(usuario, new WebServiceController.VolleyResponseListner() {
                                        @Override
                                        public void onResponse(Object response) {
                                            boolean erro = (boolean) response;
                                            if(erro) {
                                                Toast.makeText(CadastroActivity.this, "Ocorreu alguma falha!", Toast.LENGTH_SHORT).show();
                                            } else {
                                                Toast.makeText(CadastroActivity.this, "Cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                                                Intent it = new Intent(CadastroActivity.this, MainActivity.class);
                                                startActivity(it);
                                            }
                                        }

                                        @Override
                                        public void onError(String message) {

                                        }
                                    });
                                    /*Toast.makeText(getApplicationContext(), "Usuário cadastrado", Toast.LENGTH_LONG).show();
                                    Intent novaTela = new Intent(CadastroActivity.this, CategoriaActivity.class);
                                    startActivity(novaTela);
                                    finish();*/
                                }
                            }
                        }
                    }
                }
            }
        });




    }





}
