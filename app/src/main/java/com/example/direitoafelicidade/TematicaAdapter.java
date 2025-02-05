package com.example.direitoafelicidade;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import modelDominio.CanalYoutube;
import modelDominio.Tematica;

public class TematicaAdapter extends RecyclerView.Adapter<TematicaAdapter.MyViewHolder> {
    private ArrayList<Tematica> listaTematicas;
    private TematicaAdapter.TematicaOnClickListener tematicaOnClickListener;

    public TematicaAdapter(ArrayList<Tematica> listaTematicas, TematicaAdapter.TematicaOnClickListener tematicaOnClickListener) {
        this.listaTematicas = listaTematicas;
        this.tematicaOnClickListener = tematicaOnClickListener;
    }

    @Override
    public TematicaAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_row_tematicas, parent, false);

        return new TematicaAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final TematicaAdapter.MyViewHolder holder, final int position) {


        Tematica tematica = listaTematicas.get(position);
        Log.d("tematicaAdapter", tematica.toString());
        holder.tvFiltrarNomeTematica.setText(tematica.getNomeTematica());


        // clique no item do cliente
        if (tematicaOnClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tematicaOnClickListener.onClickTematica(holder.itemView,position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return listaTematicas.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvFiltrarNomeTematica;
        public MyViewHolder(View itemView) {
            super(itemView);
            tvFiltrarNomeTematica =  itemView.findViewById(R.id.tvFiltrarNomeTematica);


        }
    }

    public interface TematicaOnClickListener {
        public void onClickTematica(View view, int position);
    }

}
