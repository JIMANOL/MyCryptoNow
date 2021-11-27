package com.example.mycryptonow.ui.listacryptos;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycryptonow.R;
import com.example.mycryptonow.models.Datum;

import java.util.ArrayList;
import java.util.Comparator;

public class ListaCryptosAdapter extends RecyclerView.Adapter<ListaCryptosViewHolder> {
    private ArrayList<ArrayList<Datum>> datosLista = new ArrayList<>();

    @NonNull
    @Override
    public ListaCryptosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ListaCryptosViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_crypto_info,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ListaCryptosViewHolder holder, int position) {
        datosLista.sort(new Comparator<ArrayList<Datum>>() {
            @Override
            public int compare(ArrayList<Datum> data, ArrayList<Datum> t1) {
                return data.get(data.size()-1).getCmcRank().compareTo(t1.get(t1.size()-1).getCmcRank());
            }
        });

        ArrayList<Datum> lista = datosLista.get(position);

        holder.setIfo(lista);
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
