package com.example.felipesantos.organizzeclone.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;

import com.example.felipesantos.organizzeclone.R;
import com.example.felipesantos.organizzeclone.activity.PrincipalActivity;
import com.example.felipesantos.organizzeclone.config.ConfiguracaoFirebase;
import com.google.firebase.database.DatabaseReference;

public class Adapter extends RecyclerView.Adapter<Adapter.MyviewHolder> {


    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemLista = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_movimentos, viewGroup , false);

        return new MyviewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder myviewHolder, int i) {

        myviewHolder.categoria.setText("Categoria");
        myviewHolder.descricao.setText("Descrição");
        myviewHolder.valorMovimento.setText("0,00");

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class MyviewHolder extends RecyclerView.ViewHolder{

        TextView descricao;
        TextView categoria;
        TextView valorMovimento;

        DatabaseReference databaseReference = new ConfiguracaoFirebase().getFirebaseDatabase();

        public MyviewHolder(@NonNull View itemView) {
            super(itemView);

            descricao = itemView.findViewById(R.id.textDescricaoMovimento);
            categoria = itemView.findViewById(R.id.textCategoriaMovimento);
            valorMovimento = itemView.findViewById(R.id.textValorMovimento);
        }
    }
}
