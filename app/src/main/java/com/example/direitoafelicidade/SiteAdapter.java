package com.example.direitoafelicidade;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import modelDominio.PaginaWeb;
import modelDominio.Tematica;

public class SiteAdapter extends RecyclerView.Adapter<SiteAdapter.MyViewHolder> {

    private List<PaginaWeb> listaPaginasWeb;
    public PaginaWebOnClickListener paginaWebOnClickListener;

    public SiteAdapter(List<PaginaWeb> listaPaginasWeb, PaginaWebOnClickListener paginaWebOnClickListener) {
        this.listaPaginasWeb = listaPaginasWeb;
        this.paginaWebOnClickListener = paginaWebOnClickListener;
    }

    @Override
    public SiteAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final SiteAdapter.MyViewHolder holder, final int position) {

        Log.d("bbbb", listaPaginasWeb.toString());
        PaginaWeb minhaPaginaWeb = listaPaginasWeb.get(position);
        Log.d("bbbb", minhaPaginaWeb.toString());
        holder.tvPaginaWebNome.setText(minhaPaginaWeb.getNomeConteudo());
        holder.tvAutorSite.setText(minhaPaginaWeb.getAutorPagina());

        holder.tvDescricaoSite.setText(minhaPaginaWeb.getDescricaoConteudo());

        ArrayList<Tematica> tematicasArrayList = minhaPaginaWeb.getTematicas();
        String nomeTematica = "";
        for (int i = 0; i < tematicasArrayList.size(); i++) {
            Tematica tematica = tematicasArrayList.get(i);
            nomeTematica = nomeTematica + tematica.getNomeTematica() + "\n";
        }

        holder.tvTematicasSite.setText(nomeTematica);



        /* CUIDADO: .setText() precisa sempre de String. Se for outro tipo de dado (sem concatenação), deve ser feita a conversão com o String.valueOf() */

        // clique no item do cliente
        if (paginaWebOnClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    paginaWebOnClickListener.onClickPaginaWeb(holder.itemView, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return listaPaginasWeb.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvPaginaWebNome, tvAutorSite, tvDescricaoSite, tvTematicasSite;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvPaginaWebNome = (TextView) itemView.findViewById(R.id.tvNomeSite);
            tvAutorSite = (TextView) itemView.findViewById(R.id.tvAutorSite);

            tvDescricaoSite = (TextView) itemView.findViewById(R.id.tvDescricao);
            tvTematicasSite = (TextView) itemView.findViewById(R.id.tvTematicasSite);

        }
    }

    public interface PaginaWebOnClickListener {
        public void onClickPaginaWeb(View view, int position);

    }
}
