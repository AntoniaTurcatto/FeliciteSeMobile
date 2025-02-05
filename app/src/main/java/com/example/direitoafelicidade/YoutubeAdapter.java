package com.example.direitoafelicidade;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONObject;

import java.util.ArrayList;
import modelDominio.CanalYoutube;
import modelDominio.Conteudo;
import modelDominio.Tematica;

public class YoutubeAdapter extends RecyclerView.Adapter<YoutubeAdapter.MyViewHolder> {
    private ArrayList<CanalYoutube> listaCanais;
    private YoutubeOnClickListener YoutubeOnClickListener;

    public YoutubeAdapter(ArrayList<CanalYoutube> listaCanais, YoutubeOnClickListener YoutubeOnClickListener) {
        this.listaCanais = listaCanais;
        this.YoutubeOnClickListener = YoutubeOnClickListener;
    }

    @Override
    public YoutubeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_row_youtube, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final YoutubeAdapter.MyViewHolder holder, final int position) {


        CanalYoutube canal = listaCanais.get(position);
        Log.d("YoutubeAdapter", canal.toString());
        holder.ivCapaCanal.setImageBitmap(canal.getCapaCanal());
        holder.tvNomeCanal.setText(canal.getNomeConteudo());
        holder.tvDescricaoCanal.setText(canal.getDescricaoConteudo());

        ArrayList<Tematica> arrayList = canal.getTematicas();

        Log.d("YoutubeAdapter", arrayList.toString());


        String nomeTematica = "";
        for (int i = 0; i < arrayList.size(); i++) {
            if(i == (arrayList.size() - 1)) {
                Tematica tematicaObjeto = arrayList.get(i);
                nomeTematica = nomeTematica + tematicaObjeto.getNomeTematica() + ".";
            } else {
                Tematica tematicaObjeto = arrayList.get(i);
                nomeTematica = nomeTematica + tematicaObjeto.getNomeTematica() + ", ";
            }


        }

        holder.tvTematicaCanal.setText(nomeTematica);






        // clique no item do cliente
        if (YoutubeOnClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    YoutubeOnClickListener.onClickYoutube(holder.itemView,position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return listaCanais.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvNomeCanal, tvDescricaoCanal, tvTematicaCanal;
        ImageView ivCapaCanal;

        public MyViewHolder(View itemView) {
            super(itemView);
            ivCapaCanal =  itemView.findViewById(R.id.ivCapaCanal);
            tvNomeCanal = itemView.findViewById(R.id.tvNomeCanal);
            tvDescricaoCanal = itemView.findViewById(R.id.tvDescricaoCanal);
            tvTematicaCanal = itemView.findViewById(R.id.tvTematicaCanal);


        }
    }

    public interface YoutubeOnClickListener {
        public void onClickYoutube(View view, int position);
    }

}
