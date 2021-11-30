package com.example.mycryptonow.ui.listamiscryptos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycryptonow.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ListaMisCryptosAdapter extends RecyclerView.Adapter<ListaMisCryptosAdapter.ViewHolderMisCryptos> {

    //ArrayList<ListaMisCryptos> listaMisCryptos;
    //public ListaMisCryptosAdapter(ArrayList<ListaMisCryptos> listaMisCryptos) {}


    @NonNull
    @NotNull
    @Override
    public ViewHolderMisCryptos onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_micrypto,null,false);
        return new ViewHolderMisCryptos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolderMisCryptos holder, int position) {
        //holder.tvTipo.setText(listaMisCryptos.get(position).getTipo());
        //holder.tvDireccion.setText(listaMisCryptos.get(position).getDireccion());

       // holder.tvCantidad.setText(listaMisCryptos.get(position).getCantidad());
        //holder.tvNombre.setText(listaMisCryptos.get(position).getNombre());

    }

    @Override
    public int getItemCount() {
        return 0;
        //listaMisCryptos.size();
    }

    public class ViewHolderMisCryptos extends RecyclerView.ViewHolder {

        TextView tvTipo,tvDireccion,tvNombre,tvCantidad;



        public ViewHolderMisCryptos(@NonNull @NotNull View itemView) {
            super(itemView);

            tvTipo=(TextView) itemView.findViewById(R.id.tvmicryptoTipo);
            tvDireccion=(TextView) itemView.findViewById(R.id.tvmicryptoDireccion);
            tvNombre=(TextView) itemView.findViewById(R.id.tvmicryptoNombre);
            tvCantidad=(TextView) itemView.findViewById(R.id.tvmicryptoCantidad);



        }
    }
}
