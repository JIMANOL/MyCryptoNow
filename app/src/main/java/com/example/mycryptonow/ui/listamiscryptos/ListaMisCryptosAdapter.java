package com.example.mycryptonow.ui.listamiscryptos;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycryptonow.R;
import com.example.mycryptonow.models.Datum;
import com.example.mycryptonow.models.MiCrypto;
import com.example.mycryptonow.ui.listacryptos.ListaCryptosViewHolder;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.MissingFormatArgumentException;

public class ListaMisCryptosAdapter extends RecyclerView.Adapter<ListaMisCryptosViewHolder> {
    private ArrayList<MiCrypto> datosLista = new ArrayList<>();
    private ListarMisCryptosViewModel modelo;

    @NonNull
    @Override
    public ListaMisCryptosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ListaMisCryptosViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_micrypto,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ListaMisCryptosViewHolder holder, int position) {
        MiCrypto miCrypto = datosLista.get(position);
        holder.setIfo(miCrypto,modelo);


    }

    @Override
    public int getItemCount() {
        return datosLista.size();
    }

    public void setDatosLista(ArrayList<MiCrypto> datosLista) {
        this.datosLista = datosLista;
        notifyDataSetChanged();
    }

    public void setModelo(ListarMisCryptosViewModel modelo) {
        this.modelo = modelo;
    }
}
