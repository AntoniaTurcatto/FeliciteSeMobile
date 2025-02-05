package com.example.direitoafelicidade;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.util.ArrayList;

import modelDominio.Conteudo;
import modelDominio.Tematica;

public class CadastroConteudo extends AppCompatActivity {
        ArrayList<Tematica> listaTematicas;
        ArrayList<Tematica> listaTematicasSelecionadas;
        String[] tematicas;
        boolean[] tematicasSelecionadas;
        private AlertDialog alerta;
        Spinner spinnerTipo;
        EditText sTematicaConteudos;
        EditText nomeConteudo, descricaoConteudo, motivoIndicacao;
        Button bContinuar, bCancelar;



        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_cadastro_conteudo);
            listaTematicasSelecionadas = new ArrayList<>();
            spinnerTipo = findViewById(R.id.tipoConteudo);
            sTematicaConteudos = findViewById(R.id.emocaoConteudo);
            nomeConteudo = findViewById(R.id.nomeConteudo);
            descricaoConteudo = findViewById(R.id.descricaoConteudo);
            motivoIndicacao = findViewById(R.id.indicacaoConteudo);
            bContinuar = findViewById(R.id.bCadastrarConteudo);
            bCancelar = findViewById(R.id.bCancelar);
            tematicasSelecionadas = new boolean[]{false, false, false, false, false, false};


            String[] conteudo = {"Aplicativo", "Artigo", "Canal Youtube", "Evento", "Filme", "Livro", "Pagina Web", "Serie"};
            ArrayAdapter<String> adapterTipo = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, conteudo);
            adapterTipo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerTipo.setAdapter(adapterTipo);

            //tematicas:{"Ansiedade", "Organização Pessoal", "Preocupação com o futuro", "Insônia", "Procrastinação", "Desânimo"};


            WebServiceController controller = new WebServiceController(this);

            controller.carregaTematicas(new WebServiceController.VolleyResponseListner() {
                @Override
                public void onResponse(Object response) {
                    listaTematicas = (ArrayList<Tematica>) response;
                    Log.d("listaTematicas", listaTematicas.toString());
                    if(listaTematicas!= null){
                        tematicas = new String[listaTematicas.size()];
                        for(int i = 0; i < tematicas.length; i++){
                            tematicas[i] = listaTematicas.get(i).getNomeTematica();
                        }
                    }

                    ArrayAdapter<String> adapterEmocoes = new ArrayAdapter<String>(CadastroConteudo.this, android.R.layout.simple_spinner_item, tematicas);
                    adapterEmocoes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    sTematicaConteudos.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            abrirDialog();
                        }
                    });


                    bContinuar.setOnClickListener(new View.OnClickListener() {
                                                      @Override
                                                      public void onClick(View v) {
                                                          //verificando se selecionou algum
                                                          if (!nomeConteudo.getText().toString().equals("")) {
                                                              if (!descricaoConteudo.getText().toString().equals("")) {
                                                                  if (!motivoIndicacao.getText().toString().equals("")) {
                                                                      if (spinnerTipo.getSelectedItemPosition() >=0) {
                                                                          if (!listaTematicasSelecionadas.isEmpty()) {

                                                                              String nome = nomeConteudo.getText().toString();
                                                                              String descricao = descricaoConteudo.getText().toString();
                                                                              String motivo = motivoIndicacao.getText().toString();
                                                                              String tipo = spinnerTipo.getSelectedItem().toString();

                                                                              //Tematica tematica = listaTematicas.get(sTematicaConteudos.getSelectedItemPosition());
                                                                              //listaTematicasSelecionadas.add(tematica);

                                                                        /*

                                                                        (int codConteudo, String nomeConteudo, String descricaoConteudo, String descricaoIndicacao,
                                                                        int aprovado, ArrayList<Tematica> tematicas)

                                                                         */
                                                                              Conteudo conteudo = new Conteudo(-1,nome, descricao, motivo, 0, listaTematicasSelecionadas);

                                                                              Log.d("listaTematicas", listaTematicasSelecionadas.toString());
                                                                              if(spinnerTipo.getSelectedItem().toString().equalsIgnoreCase("Aplicativo")) {
                                                                                  Intent it = new Intent(CadastroConteudo.this, CadastroAplicativo.class);
                                                                                  Bundle parametros = new Bundle();
                                                                                  parametros.putSerializable("Conteudo", conteudo);

                                                                                  it.putExtras(parametros);

                                                                                  startActivity(it);
                                                                              } else if(spinnerTipo.getSelectedItem().toString().equalsIgnoreCase("Artigo")) {
                                                                                  Intent it = new Intent(CadastroConteudo.this, CadastroArtigo.class);
                                                                                  Bundle parametros = new Bundle();
                                                                                  parametros.putSerializable("Conteudo", conteudo);

                                                                                  it.putExtras(parametros);

                                                                                  startActivity(it);
                                                                              } else if(spinnerTipo.getSelectedItem().toString().equalsIgnoreCase("Canal Youtube")) {
                                                                                  Intent it = new Intent(CadastroConteudo.this, CadastroCanalYoutube.class);
                                                                                  Bundle parametros = new Bundle();
                                                                                  parametros.putSerializable("Conteudo", conteudo);

                                                                                  it.putExtras(parametros);

                                                                                  startActivity(it);
                                                                              } else if(spinnerTipo.getSelectedItem().toString().equalsIgnoreCase("Evento")) {
                                                                                  Intent it = new Intent(CadastroConteudo.this, CadastroEvento.class);
                                                                                  Bundle parametros = new Bundle();
                                                                                  parametros.putSerializable("Conteudo", conteudo);

                                                                                  it.putExtras(parametros);

                                                                                  startActivity(it);
                                                                              } else if(spinnerTipo.getSelectedItem().toString().equalsIgnoreCase("Filme")) {
                                                                                  Intent it = new Intent(CadastroConteudo.this, CadastroFilme.class);
                                                                                  Bundle parametros = new Bundle();
                                                                                  parametros.putSerializable("Conteudo", conteudo);

                                                                                  it.putExtras(parametros);

                                                                                  startActivity(it);
                                                                              } else if(spinnerTipo.getSelectedItem().toString().equalsIgnoreCase("Pagina Web")) {
                                                                                  Intent it = new Intent(CadastroConteudo.this, CadastroPaginaWeb.class);
                                                                                  Bundle parametros = new Bundle();
                                                                                  parametros.putSerializable("Conteudo", conteudo);

                                                                                  it.putExtras(parametros);

                                                                                  startActivity(it);
                                                                              } else if(spinnerTipo.getSelectedItem().toString().equalsIgnoreCase("Serie")) {
                                                                                  Intent it = new Intent(CadastroConteudo.this, CadastroSerie.class);
                                                                                  Bundle parametros = new Bundle();
                                                                                  parametros.putSerializable("Conteudo", conteudo);

                                                                                  it.putExtras(parametros);

                                                                                  startActivity(it);
                                                                              } else if (spinnerTipo.getSelectedItem().toString().equalsIgnoreCase("Livro")){
                                                                                  Intent it = new Intent(CadastroConteudo.this, CadastroLivro.class);
                                                                                  Bundle parametros = new Bundle();
                                                                                  parametros.putSerializable("Conteudo", conteudo);

                                                                                  it.putExtras(parametros);

                                                                                  startActivity(it);
                                                                              }


                                                                          } else {
                                                                              Toast.makeText(CadastroConteudo.this, "Selecione uma ou mais temáticas", Toast.LENGTH_SHORT).show();
                                                                          }
                                                                      } else {
                                                                          Toast.makeText(CadastroConteudo.this, "Selecione um tipo de conteúdo", Toast.LENGTH_SHORT).show();
                                                                      }
                                                                  } else {
                                                                      motivoIndicacao.setError("Preencha o campo");
                                                                      motivoIndicacao.requestFocus();
                                                                  }
                                                              } else {
                                                                  descricaoConteudo.setError("Preencha o campo");
                                                                  descricaoConteudo.requestFocus();
                                                              }
                                                          } else {
                                                              nomeConteudo.setError("Preencha o campo");
                                                              nomeConteudo.requestFocus();
                                                          }
                                                      }

                                                  }
                    );
                    bCancelar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            limpaCampos();
                        }
                    });

                }

                @Override
                public void onError(String message) {

                }
            });



    }

    public void limpaCampos() {
        nomeConteudo.setText("");
        descricaoConteudo.setText("");
        motivoIndicacao.setText("");

    }

    private void abrirDialog(){
        //cria o gerador do alert dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Temáticas");

        builder.setMultiChoiceItems(tematicas, tematicasSelecionadas, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int posicao, boolean isChecked) {
                tematicasSelecionadas[posicao] = isChecked;
            }
        });

        builder.setPositiveButton("Positivo", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                sTematicaConteudos.setText("");
                listaTematicasSelecionadas.clear();
                for (int i = 0; i < listaTematicas.size(); i++){
                    if (tematicasSelecionadas[i]){

                        listaTematicasSelecionadas.add(listaTematicas.get(i));
                        if(!sTematicaConteudos.getText().toString().equals("")){
                            sTematicaConteudos.setText(sTematicaConteudos.getText().toString() + ", ");
                        }
                        sTematicaConteudos.setText(sTematicaConteudos.getText().toString() + listaTematicas.get(i).getNomeTematica());


                    } else {
                        listaTematicasSelecionadas.remove(listaTematicas.get(i));
                    }
                }

                if (sTematicaConteudos.getText().toString().equals("")){
                    sTematicaConteudos.setText("Selecione...");
                }
                Log.d("listaTematicasAtual", listaTematicasSelecionadas.toString());
            }
        });
        //define um botão como negativo.
        builder.setNegativeButton("Negativo", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                alerta.dismiss();
                Log.d("listaTematicasAtual", listaTematicasSelecionadas.toString());
            }
        });

        alerta = builder.create();
        alerta.show();
    }
}
