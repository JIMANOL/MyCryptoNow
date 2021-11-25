package com.example.mycryptonow.ui.listacryptos;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycryptonow.R;
import com.example.mycryptonow.models.Datum;

import java.util.ArrayList;

public class ListaCryptosAdapter extends RecyclerView.Adapter<ListaCryptosViewHolder> {
    private ArrayList<ArrayList<Datum>> datosLista = new ArrayList<>();

    @NonNull
    @Override
    public ListaCryptosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ListaCryptosViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_crypto_info,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ListaCryptosViewHolder holder, int position) {
        int posicionDato =datosLista.get(position).size()-1;
        holder.setIfo(datosLista.get(position).get(posicionDato));
    }

    @Override
    public int getItemCount() {
        return datosLista.size();
    }

    public void setData(ArrayList<ArrayList<Datum>> listaDatos){
        this.datosLista=listaDatos;
        notifyDataSetChanged();
    }
}
