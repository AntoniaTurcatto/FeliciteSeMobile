package com.example.direitoafelicidade;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import modelDominio.Livro;
import modelDominio.Tematica;

public class LivroAdapter  extends RecyclerView.Adapter<LivroAdapter.MyViewHolder> {

    private List<Livro> listaLivros;
    public LivroAdapter.LivroOnClickListener LivroOnClickListener;

    public LivroAdapter(List<Livro> listaLivros, LivroAdapter.LivroOnClickListener LivroOnClickListener) {
        this.listaLivros = listaLivros;
        this.LivroOnClickListener = LivroOnClickListener;
    }

    @Override
    public LivroAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_row_livros, parent, false);

        return new LivroAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final LivroAdapter.MyViewHolder holder, final int position) {


        Livro livro = listaLivros.get(position);

        holder.ivCapaLivro.setImageBitmap(livro.getCapaLivro());
        holder.tvNomeLivro.setText(livro.getNomeConteudo());
        holder.tvAutorLivro.setText(livro.getAutorLivro());
        holder.tvGeneroLivro.setText(livro.getGeneroLivro());



        ArrayList<Tematica> tematicas = livro.getTematicas();
        Log.d("TesteNoAdapterLista",tematicas.toString());

        String nomeTematica = "";


        for (int i = 0; i < tematicas.size(); i++) {
            Tematica tematica = tematicas.get(i);
            Log.d("TesteNoAdapterOO",tematica.toString());

            if(i == (tematicas.size() - 1)) {
                nomeTematica = nomeTematica + tematica.getNomeTematica() + ".";
            } else {
                nomeTematica = nomeTematica + tematica.getNomeTematica() + ", ";
                Log.d("TesteNoAdapterCC",nomeTematica);
            }


        }

        holder.tvTematicaLivro.setText(nomeTematica);


        // clique no item do cliente
        if (LivroOnClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LivroOnClickListener.onClickLivro(holder.itemView,position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return listaLivros.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvNomeLivro,tvAutorLivro,tvGeneroLivro, tvTematicaLivro;
        ImageView ivCapaLivro;


        public MyViewHolder(View itemView) {
            super(itemView);
            ivCapaLivro = itemView.findViewById(R.id.ivCapaLivro);
            tvNomeLivro = itemView.findViewById(R.id.tvNomeLivro);
            tvAutorLivro = itemView.findViewById(R.id.tvAutorLivro);
            tvGeneroLivro = itemView.findViewById(R.id.tvGeneroLivro);
            tvTematicaLivro = itemView.findViewById(R.id.tvTematicaLivro);

        }
    }

    public interface LivroOnClickListener {
        public void onClickLivro(View view, int position);
    }


}
