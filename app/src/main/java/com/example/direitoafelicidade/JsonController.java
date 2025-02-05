package com.example.direitoafelicidade;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import modelDominio.Aplicativo;
import modelDominio.Artigo;
import modelDominio.CanalYoutube;
import modelDominio.Evento;
import modelDominio.Livro;
import modelDominio.PaginaWeb;
import modelDominio.Filme;
import modelDominio.Serie;
import modelDominio.Tematica;
import modelDominio.Usuario;

public class JsonController {
    static  Usuario usuarioLogado = null;




    public static Usuario obtemUsuarioLogado(JSONObject jsonObject) {

        Log.d("Retorno", "jsonObject " + jsonObject.toString());


        try
        {



                String codUsuarioString = jsonObject.getString("codUsuario");
                int codUsuarioInt = Integer.parseInt(codUsuarioString);

                String nomeUsuarioString = jsonObject.getString("nomeUsuario");
                String generoUsuarioString = jsonObject.getString("generoUsuario");
                int generoUsuario = Integer.parseInt(generoUsuarioString);

                String tipoUsuarioString = jsonObject.getString("tipoUsuario");
                int tipoUsuarioInt = Integer.parseInt(tipoUsuarioString);

                String emailUsuarioString = jsonObject.getString("emailUsuario");

                String senhaUsuarioString = jsonObject.getString("senhaUsuario");
                String matriculaEstudanteString = jsonObject.getString("matriculaEstudante");
                String matriculaServidorString = jsonObject.getString("matriculaServidor");

                String cargoServidorString = jsonObject.getString("cargoServidor");
                String siapeServidorString = jsonObject.getString("siapeServidor");

                 usuarioLogado = new Usuario(codUsuarioInt, nomeUsuarioString, generoUsuario, tipoUsuarioInt, emailUsuarioString, senhaUsuarioString);
                Log.d("json", usuarioLogado +"");


            return usuarioLogado;
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            Log.d("Teste try", "Erro JSON: " + e.getMessage());
            return null;
        }
    }



    public static ArrayList<PaginaWeb> obtemPaginasWeb(JSONObject jsonObject, String response ) {

        Log.d("Retorno", "jsonObject " + jsonObject.toString());
        Log.d("Retorno", "Response " + response);

        try
        {
            ArrayList<PaginaWeb> listaPaginasWeb = new ArrayList<>();

            JSONObject jsonConsulta = new JSONObject();
            jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("site");


            for(int x=0; x<jsonArray.length(); x++)
            {

                jsonConsulta = jsonArray.getJSONObject(x);


                String codConteudoString = jsonConsulta.getString("codConteudo");
                int codConteudo = Integer.parseInt(codConteudoString);
                String nomeConteudo = jsonConsulta.getString("nomeConteudo");
                String descricaoConteudo = jsonConsulta.getString("descricaoConteudo");
                String descricaoIndicacao = jsonConsulta.getString("descricaoIndicacao");

                String linkPagina = jsonConsulta.getString("linkPagina");
                String autorPagina = jsonConsulta.getString("autorPagina");

                JSONArray listaTematicasJsonArray = jsonConsulta.getJSONArray("tematica");
                //ArrayList<Tematica> listaTematicaArrayList = new ArrayList<>();

                ArrayList<Tematica> listaTematicas = trataTematicas(jsonConsulta);
                /*
                for (int i = 0; i < listaTematicasJsonArray.length(); i++) {
                    JSONObject tematicaJsonObject = listaTematicasJsonArray.getJSONObject(i);
                    String codTematcaString = tematicaJsonObject.getString("codTematica");
                    int codtematicaInt = Integer.parseInt(codTematcaString);

                    String nomeTematca = tematicaJsonObject.getString("nomeTematica");
                    Tematica tematica = new Tematica(codtematicaInt, nomeTematca);

                    listaTematicaArrayList.add(tematica);

                }
                Log.d("listaTematicasArray",  listaTematicaArrayList.toString());
                */
                PaginaWeb paginaWeb = new PaginaWeb(codConteudo, nomeConteudo, descricaoConteudo, descricaoIndicacao, linkPagina, autorPagina, listaTematicas);
                listaPaginasWeb.add(paginaWeb);
                Log.d("Teste", "Teste se está criando objeto certo " + paginaWeb.toString());
            }
            Log.d("Teste", "Teste se está adicionando na lista " + listaPaginasWeb);
            return listaPaginasWeb;
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            Log.d("Teste try", "Erro JSON: " + e.getMessage());
            return null;
        }
    }



    //Método que trata o resultado da consutla JSON, transforamndo em objetos e adicionando na lista
    public static ArrayList<Livro> obtemLivros(JSONObject jsonObject, String response ) {

        Log.d("Retorno", "jsonObject " + jsonObject.toString());
        Log.d("Retorno", "Response " + response);

        try
        {
            ArrayList<Livro> listaLivros = new ArrayList<>();
            JSONObject jsonConsulta = new JSONObject();
            jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("livros");

            for(int x=0; x<jsonArray.length(); x++)
            {
                jsonConsulta = jsonArray.getJSONObject(x);
                String codConteudoString = jsonConsulta.getString("codConteudo");
                int codConteudo = Integer.parseInt(codConteudoString);
                String nomeConteudo = jsonConsulta.getString("nomeConteudo");
                String descricaoConteudo = jsonConsulta.getString("descricaoConteudo");
                String descricaoIndicacao = jsonConsulta.getString("descricaoIndicacao");


                Bitmap capaBitMap = trataImagens(jsonConsulta.getString("capaLivro"));
                /*
                String capaLivroString = jsonConsulta.getString("capaLivro");

                Log.d("capa" , capaLivroString);

                // decodifica a string em uma matriz de bytes
                byte[] decodedString = Base64.decode(capaLivroString, Base64.DEFAULT);

                // converte a matriz de bytes em um bitmap
                Bitmap imagemBitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

                 */



                String editoraLivro = jsonConsulta.getString("editoraLivro");


                String anoLivroString = jsonConsulta.getString("anoLivro");
                int anoLivro = Integer.parseInt(anoLivroString);
                String paginasLivroString = jsonConsulta.getString("paginasLivro");
                int paginasLivro = Integer.parseInt(paginasLivroString);
                String autorLivro = jsonConsulta.getString("autorLivro");
                String generoLivro = jsonConsulta.getString("generoLivro");

                //ArrayList<Tematica> tematicasArray = new ArrayList<>();

                JSONArray tematicas = jsonConsulta.getJSONArray("tematica");
                Log.d("aaaaa", "bbbbb" + tematicas);

                ArrayList<Tematica> listaTematicas = trataTematicas(jsonConsulta);
                /*
                for (int i = 0; i < tematicas.length(); i++) {

                    JSONObject tematicaJson = tematicas.getJSONObject(i);
                    String nomeTematica = tematicaJson.getString("nomeTematica");
                    String codTematicaString = tematicaJson.getString("codTematica");
                    int codTematicaInt = Integer.parseInt(codConteudoString);

                    Tematica tematica = new Tematica(codTematicaInt, nomeTematica);
                    tematicasArray.add(tematica);

                }

                 */


                Log.d("Teste", "listaaa" + listaTematicas);
                Livro livro = new Livro(codConteudo, nomeConteudo, descricaoConteudo, descricaoIndicacao, listaTematicas, editoraLivro, capaBitMap, anoLivro,paginasLivro,autorLivro,generoLivro);
                listaLivros.add(livro);
                Log.d("Teste", "criou livro" + livro);
            }
            Log.d("Teste", "Teste se está adicionando na lista " + listaLivros);
            return listaLivros;
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            Log.d("Teste try", "Erro JSON: " + e.getMessage());
            return null;
        }
    }



    public static ArrayList<Aplicativo> obtemAplicativos(JSONObject jsonObject, String response ) {

        Log.d("Retorno", "jsonObject " + jsonObject.toString());
        Log.d("Retorno", "Response " + response);

        try
        {
            ArrayList<Aplicativo> listaAplicativos = new ArrayList<>();
            JSONObject jsonConsulta = new JSONObject();
            jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("aplicativo");

            for(int x=0; x<jsonArray.length(); x++)
            {
                jsonConsulta = jsonArray.getJSONObject(x);
                String codConteudoString = jsonConsulta.getString("codConteudo");
                int codConteudo = Integer.parseInt(codConteudoString);
                String nomeConteudo = jsonConsulta.getString("nomeConteudo");
                String descricaoConteudo = jsonConsulta.getString("descricaoConteudo");
                String descricaoIndicacao = jsonConsulta.getString("descricaoIndicacao");


                Bitmap logoBitMap = trataImagens(jsonConsulta.getString("logoAplicativo"));
                /*
                String logoAplicativoString = jsonConsulta.getString("logoAplicativo");
                // decodifica a string em uma matriz de bytes
                byte[] decodedString = Base64.decode(logoAplicativoString, Base64.DEFAULT);

                // converte a matriz de bytes em um bitmap
                Bitmap imagemBitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

                 */

                String linkAplicativo = jsonConsulta.getString("linkAplicativo");
                String desenvolvedorAplicativo = jsonConsulta.getString("desenvolvedoresAplicativo");
                int gratisAplicativo = Integer.parseInt(jsonConsulta.getString("gratisAplicativo"));

                //JSONArray listaTematicaJsonArray = jsonConsulta.getJSONArray("tematica");
                //Log.d("jsonarray", "jsonarray" + listaTematicaJsonArray);
                //ArrayList<Tematica> arrayList = new ArrayList<>();
                ArrayList<Tematica> listaTematicas = trataTematicas(jsonConsulta);
                /*
                for (int i = 0; i < listaTematicaJsonArray.length(); i++) {

                    JSONObject tematicaJsonObject = listaTematicaJsonArray.getJSONObject(i);
                    int codTematica = Integer.parseInt(tematicaJsonObject.getString("codTematica"));
                    String nomeTematica = tematicaJsonObject.getString("nomeTematica");
                    Tematica tematica = new Tematica(codTematica, nomeTematica);
                    arrayList.add(tematica);
                }

                 */




                Aplicativo app = new Aplicativo(codConteudo, nomeConteudo, descricaoConteudo, descricaoIndicacao, linkAplicativo, logoBitMap, desenvolvedorAplicativo,gratisAplicativo, listaTematicas);
                listaAplicativos.add(app);
            }
            Log.d("Teste", "Teste se está adicionando na lista " + listaAplicativos);
            return listaAplicativos;
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            Log.d("Teste try", "Erro JSON: " + e.getMessage());
            return null;
        }
    }

    public static ArrayList<Artigo> obtemArtigos(JSONObject jsonObject, String response ) {

        Log.d("Retorno", "jsonObject " + jsonObject.toString());
        Log.d("Retorno", "Response " + response);

        try
        {
            ArrayList<Artigo> listaArtigos = new ArrayList<>();
            JSONObject jsonConsulta = new JSONObject();
            jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("artigo");
            String s;

            for(int x=0; x<jsonArray.length(); x++)
            {
                jsonConsulta = jsonArray.getJSONObject(x);

                Log.d("Retorno", "vamosVer " + jsonConsulta);

                String codConteudoString = jsonConsulta.getString("codConteudo");
                int codConteudo = Integer.parseInt(codConteudoString);
                String nomeConteudo = jsonConsulta.getString("nomeConteudo");
                String descricaoConteudo = jsonConsulta.getString("descricaoConteudo");
                String descricaoIndicacao = jsonConsulta.getString("descricaoIndicacao");
                //String tematicaConteudoString = jsonConsulta.getString("tematicaConteudo");
                //int tematicaConteudo = Integer.parseInt(tematicaConteudoString);
                //String listaTematicaConteudoString = jsonConsulta.getString("listaTematicaConteudo");
                //int listaTematicaConteudo = Integer.parseInt(listaTematicaConteudoString);

                
                //ArrayList<Tematica> tematicas = (ArrayList<Tematica>) ("tematica");

                String linkArtigo = jsonConsulta.getString("linkArtigo");
                String resumoArtigo = jsonConsulta.getString("resumoArtigo");
                int anoPublicacaoArtigo = Integer.parseInt(jsonConsulta.getString("anoPublicacao"));

                String autorArtigo = jsonConsulta.getString("autorArtigo");
                ArrayList<Tematica> listaTematicas = trataTematicas(jsonConsulta);
                //JSONArray lista = jsonConsulta.getJSONArray("tematica");
                //Log.d("jsonarray", "jsonarray" + lista);
                //ArrayList<Tematica> arrayList = new ArrayList<>();
                /*
                int a = lista.length();



                    for (int i = 0; i < lista.length(); i++) {



                        JSONObject cod = lista.getJSONObject(i);
                        s = cod.getString("nomeTematica");
                        Tematica tematicas = new Tematica(1, s);
                        arrayList.add(tematicas);
                    }




                /*JSONArray listaTematicasJsonArray = jsonConsulta.getJSONArray("tematica");
                ArrayList<Tematica> listaTematicaArrayList = new ArrayList<>();

                for (int i = 0; i < listaTematicasJsonArray.length(); i++) {
                    JSONObject tematicaJsonObject = listaTematicasJsonArray.getJSONObject(i);
                    String codTematcaString = tematicaJsonObject.getString("codTematica");
                    int codtematicaInt = Integer.parseInt(codTematcaString);

                    String nomeTematca = tematicaJsonObject.getString("nomeTematica");
                    Tematica tematica = new Tematica(codtematicaInt, nomeTematca);

                    listaTematicaArrayList.add(tematica);

                }*/







                Artigo artigo = new Artigo(codConteudo, nomeConteudo, descricaoConteudo, descricaoIndicacao, listaTematicas,  linkArtigo, resumoArtigo,anoPublicacaoArtigo,autorArtigo);
                Log.d("TesteArtigos", "mostra o artigo criado agr " + artigo);
                listaArtigos.add(artigo);
            }

            return listaArtigos;
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            Log.d("Teste try", "Erro JSON: " + e.getMessage());
            return null;
        }
    }




    public static ArrayList<Filme> obtemFilmes(JSONObject jsonObject, String response ) {

        Log.d("Retorno", "jsonObject " + jsonObject.toString());
        Log.d("Retorno", "Response " + response);

        try
        {
            ArrayList<Filme> listaFilmes = new ArrayList<>();
            JSONObject jsonConsulta = new JSONObject();
            jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("filme");

            for(int x=0; x<jsonArray.length(); x++)
            {
                jsonConsulta = jsonArray.getJSONObject(x);
                String codConteudoString = jsonConsulta.getString("codConteudo");
                int codConteudo = Integer.parseInt(codConteudoString);
                String nomeConteudo = jsonConsulta.getString("nomeConteudo");
                String descricaoConteudo = jsonConsulta.getString("descricaoConteudo");
                String descricaoIndicacao = jsonConsulta.getString("descricaoIndicacao");

                //chamando a funcao que lida com as tematicas
                ArrayList<Tematica> listaTematicas = trataTematicas(jsonConsulta);
                //chamando a funcao que lida com a imagem
                Bitmap capaBitMap = trataImagens(jsonConsulta.getString("capaFilme"));


                String sinopseFilme = jsonConsulta.getString("sinopseFilme");
                int duracaoFilme = Integer.parseInt(jsonConsulta.getString("duracaoFilme"));
                int anoLancamentoFilme = Integer.parseInt(jsonConsulta.getString("anoLancamentoFilme"));
                String plataformasFilme = jsonConsulta.getString("plataformaFilme");



                Filme filme = new Filme(codConteudo, nomeConteudo, descricaoConteudo, descricaoIndicacao, capaBitMap, sinopseFilme,duracaoFilme, anoLancamentoFilme, plataformasFilme, listaTematicas);
                listaFilmes.add(filme);
            }
            Log.d("Teste", "Teste se está adicionando na lista " + listaFilmes);
            return listaFilmes;
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            Log.d("Teste try", "Erro JSON: " + e.getMessage());
            return null;
        }
    }

    public static ArrayList<Serie> obtemSeries(JSONObject jsonObject, String response ) {

        Log.d("Retorno", "jsonObject " + jsonObject.toString());
        Log.d("Retorno", "Response " + response);

        try
        {
            ArrayList<Serie> listaSeries = new ArrayList<>();
            JSONObject jsonConsulta = new JSONObject();
            jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("series");

            for(int x=0; x<jsonArray.length(); x++)
            {
                jsonConsulta = jsonArray.getJSONObject(x);
                String codConteudoString = jsonConsulta.getString("codConteudo");
                int codConteudo = Integer.parseInt(codConteudoString);
                String nomeConteudo = jsonConsulta.getString("nomeConteudo");
                String descricaoConteudo = jsonConsulta.getString("descricaoConteudo");
                String descricaoIndicacao = jsonConsulta.getString("descricaoIndicacao");

                //chamando a funcao que lida com as tematicas
                ArrayList<Tematica> listaTematicas = trataTematicas(jsonConsulta);
                //chamando a funcao que lida com a imagem
                Bitmap capaBitMap = trataImagens(jsonConsulta.getString("capaSerie"));


                String sinopseSerie = jsonConsulta.getString("sinopseSerie");
                int temporadaSerie = Integer.parseInt(jsonConsulta.getString("temporadaSerie"));
                int anoLancamentoSerie = Integer.parseInt(jsonConsulta.getString("anoLancamentoSerie"));
                String plataformasFilme = jsonConsulta.getString("plataformaSerie");



                Serie serie = new Serie(codConteudo, nomeConteudo, descricaoConteudo, descricaoIndicacao, capaBitMap, sinopseSerie,temporadaSerie, anoLancamentoSerie, plataformasFilme, listaTematicas);
                listaSeries.add(serie);
            }
            Log.d("Teste", "Teste se está adicionando na lista " + listaSeries);
            return listaSeries;
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            Log.d("Teste try", "Erro JSON: " + e.getMessage());
            return null;
        }
    }


    public static ArrayList<Evento> obtemEventos(JSONObject jsonObject, String response ) {

        Log.d("Retorno", "jsonObject " + jsonObject.toString());
        Log.d("Retorno", "Response " + response);

        try
        {
            ArrayList<Evento> listaEventos = new ArrayList<>();
            JSONObject jsonConsulta = new JSONObject();
            jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("eventos");

            for(int x=0; x<jsonArray.length(); x++)
            {
                jsonConsulta = jsonArray.getJSONObject(x);
                String codConteudoString = jsonConsulta.getString("codConteudo");
                int codConteudo = Integer.parseInt(codConteudoString);
                String nomeConteudo = jsonConsulta.getString("nomeConteudo");
                String descricaoConteudo = jsonConsulta.getString("descricaoConteudo");
                String descricaoIndicacao = jsonConsulta.getString("descricaoIndicacao");

                //chamando a funcao que lida com as tematicas
                ArrayList<Tematica> listaTematicas = trataTematicas(jsonConsulta);

                // AVISO ------------------------------- Consultar em que formato vem a data do banco
                String dataEvento = jsonConsulta.getString("dataEvento");
                String localEvento = jsonConsulta.getString("localEvento");
                String responsavelEvento = jsonConsulta.getString("responsavelEvento");






                Evento evento = new Evento(codConteudo, nomeConteudo, descricaoConteudo, descricaoIndicacao, listaTematicas, dataEvento, localEvento,responsavelEvento);
                listaEventos.add(evento);
            }
            Log.d("Teste", "Teste se está adicionando na lista " + listaEventos);
            return listaEventos;
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            Log.d("Teste try", "Erro JSON: " + e.getMessage());
            return null;
        }
    }



    public static ArrayList<CanalYoutube> obtemCanaisYoutube(JSONObject jsonObject, String response ) {

        Log.d("Retorno", "jsonObject " + jsonObject.toString());
        Log.d("Retorno", "Response " + response);

        try
        {
            ArrayList<CanalYoutube> listaCanais = new ArrayList<>();
            JSONObject jsonConsulta = new JSONObject();
            jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("canal");

            for(int x=0; x<jsonArray.length(); x++)
            {
                jsonConsulta = jsonArray.getJSONObject(x);
                String codConteudoString = jsonConsulta.getString("codConteudo");
                int codConteudo = Integer.parseInt(codConteudoString);
                String nomeConteudo = jsonConsulta.getString("nomeConteudo");
                String descricaoConteudo = jsonConsulta.getString("descricaoConteudo");
                String descricaoIndicacao = jsonConsulta.getString("descricaoIndicacao");

                ArrayList<Tematica> listaTematicas = trataTematicas(jsonConsulta);

                /*
                JSONArray listaTematicasJson = jsonConsulta.getJSONArray("tematica");
                Log.d("jsonarray", "jsonarray" + listaTematicasJson);
                ArrayList<Tematica> arrayList = new ArrayList<>();





                for (int i = 0; i < listaTematicasJson.length(); i++) {



                    JSONObject tematicaJsonJSONObject = listaTematicasJson.getJSONObject(i);
                    String codTematica = tematicaJsonJSONObject.getString("codTematica");
                    String nomeTematica = tematicaJsonJSONObject.getString("nomeTematica");
                    Tematica tematicas = new Tematica(Integer.parseInt(codTematica), nomeTematica);
                    arrayList.add(tematicas);
                }

                 */


                String linkCanal = jsonConsulta.getString("linkCanal");


                Bitmap capaBitMap = trataImagens(jsonConsulta.getString("capaCanal"));
                /*
                String capaLivroString = jsonConsulta.getString("capaLivro");
                Log.d("capa" , capaLivroString);

                // decodifica a string em uma matriz de bytes
                byte[] decodedString = Base64.decode(capaLivroString, Base64.DEFAULT);

                // converte a matriz de bytes em um bitmap
                Bitmap imagemBitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

                 */




                CanalYoutube canalYoutube = new CanalYoutube(codConteudo, nomeConteudo, descricaoConteudo, descricaoIndicacao,linkCanal, listaTematicas, capaBitMap);
                listaCanais.add(canalYoutube);
            }
            Log.d("Teste", "Teste se está adicionando na lista " + listaCanais);
            return listaCanais;
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            Log.d("Teste try", "Erro JSON: " + e.getMessage());
            return null;
        }
    }

    public static ArrayList<Tematica> trataTematicas(JSONObject jsonObject) {

        ArrayList<Tematica> arrayList = new ArrayList<>();

        try {

            JSONArray listaTematicasJson = jsonObject.getJSONArray("tematica");
            Log.d("jsonarray", "jsonarray" + listaTematicasJson);

            for (int i = 0; i < listaTematicasJson.length(); i++) {

                JSONObject tematicaJsonJSONObject = listaTematicasJson.getJSONObject(i);
                String codTematica = tematicaJsonJSONObject.getString("codTematica");
                String nomeTematica = tematicaJsonJSONObject.getString("nomeTematica");
                Tematica tematicas = new Tematica(Integer.parseInt(codTematica), nomeTematica);
                arrayList.add(tematicas);
            }

        } catch (Exception ex) {

        }
        return arrayList;
    }

    public static boolean sugeriuComSucesso(JSONObject jsonObject){
        boolean sugerido = false;
        Log.d("sugerir Retorno", "jsonObject " + jsonObject.toString());
        try{

            sugerido = jsonObject.getBoolean("sucesso");

        } catch (Exception e){
            Log.e("sugerir erro", "erro ao obter boolean do JSONObject",e);
        }

        return sugerido;
    }

    public static Bitmap trataImagens (String imagemString) {

        Bitmap imagemBitmap = null;

        try {
            Log.d("estanaFunk", imagemString);


            // decodifica a string em uma matriz de bytes
            byte[] decodedString = Base64.decode(imagemString, Base64.DEFAULT);

            // converte a matriz de bytes em um bitmap
            imagemBitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        } catch (Exception ex) {

        }
        return imagemBitmap;
    }

}
