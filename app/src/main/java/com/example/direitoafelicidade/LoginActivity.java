package com.example.direitoafelicidade;

import android.content.Intent;
import android.icu.text.IDNA;
import android.os.Bundle;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

import modelDominio.Usuario;

public class LoginActivity extends AppCompatActivity {
    Button btnEntrar;
    EditText edtUsuario, edtSenha;
    TextView tvCadastrese, tvEsqueciSenha;

    InformacoesApp informacoesApp;


    Usuario usuario = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);





        btnEntrar = findViewById(R.id.btnEntrar);
        edtUsuario = findViewById(R.id.edtUsuario);
        edtSenha = findViewById(R.id.edtSenha);
        tvEsqueciSenha = findViewById(R.id.tvEsqueciSenha);
        tvCadastrese = findViewById(R.id.tvCadastrese);
        informacoesApp = (InformacoesApp) getApplicationContext();



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean validado = true; //Testa se dados foram digitados

                if (edtUsuario.getText().length()==0)
                {
                    edtUsuario.setError("Campo Usuario Obrigatório");
                    edtUsuario.requestFocus();
                    validado = false;
                }
                if (edtSenha.getText().length()==0)
                {
                    edtSenha.setError("Campo Senha Obrigatório");
                    edtSenha.requestFocus();
                    validado = false;
                } if (validado)
                {
                    //Toast.makeText(getApplicationContext(),"Validando seus dados, espere...", Toast.LENGTH_SHORT).show();
                     usuario = new Usuario(edtUsuario.getText().toString(), edtSenha.getText().toString());

                    //WebServiceController webServiceController = new WebServiceController();




                    WebServiceController webServiceController = new WebServiceController(LoginActivity.this);
                    webServiceController.validarLogin(usuario, new WebServiceController.VolleyResponseListner() {
                        @Override
                        public void onResponse(Object response) {
                            Usuario usuarioLogado = (Usuario) response; //convertendo a resposta para objeto desejado (neste caso retor*no um boolean)
                            if (usuarioLogado != null) {
                                informacoesApp.setUsuarioLogado(usuarioLogado);
                                Intent it = new Intent(LoginActivity.this, FiltrarTematicasActivity.class);
                                startActivity(it);
                            } else {
                                Toast.makeText(LoginActivity.this, "Usuário ou senha inválido", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onError(String message) {

                        }
                    });

                    //webServiceController.setCallback(LoginActivity.this);
                    //int a = webServiceController.validarLogin( usuario);//Chama a função que pega o web service
                    //Toast.makeText(LoginActivity.this, "" + a, Toast.LENGTH_SHORT).show();
                    //validarLogin(usuario);






                }

            }
        });
    }





}

