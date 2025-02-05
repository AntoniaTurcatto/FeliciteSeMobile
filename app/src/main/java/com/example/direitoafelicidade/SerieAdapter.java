package com.example.direitoafelicidade;

import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import modelDominio.Artigo;
import modelDominio.Serie;
import modelDominio.Tematica;

public class SerieAdapter extends RecyclerView.Adapter<SerieAdapter.MyViewHolder>  {

    private List<Serie> listaSerie;

    public SerieAdapter.SerieOnClickListener serieOnClickListener;

    public SerieAdapter(List<Serie> listaSerie, SerieAdapter.SerieOnClickListener serieOnClickListener) {
        this.listaSerie = listaSerie;
        this.serieOnClickListener = serieOnClickListener;
    }

    @Override
    public SerieAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_row_series, parent, false);

        return new SerieAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final SerieAdapter.MyViewHolder holder, final int position) {


        Serie serie = listaSerie.get(position);
        Log.d("TesteNoAdapter",serie.toString());

        holder.tvNomeSerie.setText( serie.getNomeConteudo());

        holder.tvSinopseSerie.setText(serie.getSinopseSerie());
        holder.ivCapaSerie.setImageBitmap(serie.getCapaSerie());

        ArrayList<Tematica> tematicas = serie.getTematicas();
        Log.d("TesteNoAdapterLista",tematicas.toString());



        String concatenar = "";
        for (int i = 0; i < tematicas.size(); i++) {
            Tematica tematica = tematicas.get(i);
            Log.d("TesteNoAdapterOO",tematica.toString());

            if (i == (tematicas.size() - 1)) {
                concatenar = concatenar + tematica.getNomeTematica() + ".";
                Log.d("TesteNoAdapterCC",concatenar);
            } else {
                concatenar = concatenar + tematica.getNomeTematica() + ", ";
                Log.d("TesteNoAdapterCC",concatenar);
            }


        }
        holder.tvTematicaSerie.setText(concatenar);


        // clique no item do cliente
        if (serieOnClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    serieOnClickListener.onClickSerie(holder.itemView,position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return listaSerie.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvNomeSerie,tvSinopseSerie, tvTematicaSerie;
        ImageView ivCapaSerie;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvNomeSerie = (TextView)itemView.findViewById(R.id.tvNomeSerie);
            tvSinopseSerie =(TextView)itemView.findViewById(R.id.tvSinopseSerie);
            ivCapaSerie = (ImageView) itemView.findViewById(R.id.ivCapaSerie);
            tvTematicaSerie = (TextView)itemView.findViewById(R.id.tvTematicaSerie);


        }
    }

    public interface SerieOnClickListener {
        public void onClickSerie(View view, int position);
    }



}
