package com.example.direitoafelicidade;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


import modelDominio.Evento;
import modelDominio.Tematica;

public class EventoAdapter extends RecyclerView.Adapter<EventoAdapter.MyViewHolder> {

    private List<Evento> listaEventos;
    public EventoAdapter.EventoOnClickListener eventoOnClickListener;

    public EventoAdapter(List<Evento> listaEventos, EventoAdapter.EventoOnClickListener eventoOnClickListener) {
        this.listaEventos = listaEventos;
        this.eventoOnClickListener = eventoOnClickListener;
    }

    @Override
    public EventoAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_row_eventos, parent, false);

        return new EventoAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final EventoAdapter.MyViewHolder holder, final int position) {
        Evento evento = listaEventos.get(position);

        holder.tvNomeEvento.setText(evento.getNomeConteudo());
        holder.tvDescricaoEvento.setText(evento.getDescricaoConteudo());


        ArrayList<Tematica> tematicas = evento.getTematicas();
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
        holder.tvTematicasEvento.setText(concatenar);


        /* CUIDADO: .setText() precisa sempre de String. Se for outro tipo de dado (sem concatenação), deve ser feita a conversão com o String.valueOf() */

        // clique no item do cliente
        if (eventoOnClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    eventoOnClickListener.onClickEvento(holder.itemView, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return listaEventos.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvNomeEvento, tvDescricaoEvento, tvTematicasEvento;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvNomeEvento = (TextView) itemView.findViewById(R.id.tvNomeEvento);
            tvDescricaoEvento = (TextView) itemView.findViewById(R.id.tvDescricaoEvento);
            tvTematicasEvento = (TextView) itemView.findViewById(R.id.tvTematicasEvento);



        }
    }

    public interface EventoOnClickListener {
        public void onClickEvento(View view, int position);

    }
}
