package com.example.mycryptonow.ui.listamiscryptos;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.mycryptonow.R;

public class ListaMisCryptosViewHolder {
    private TextView tvTipo;
    private TextView tvDireccion;
    private TextView tvNombre;
    private TextView tvCantidad;

    public ListaMisCryptosViewHolder(@NonNull View itemView) {
        //super(itemView);
        tvTipo = itemView.findViewById(R.id.tvmicryptoTipo);
        tvDireccion = itemView.findViewById(R.id.tvRankcmcListaCryptos);
        tvNombre = itemView.findViewById(R.id.tvNombreListaCryptos);
        tvCantidad = itemView.findViewById(R.id.tvSimboloListaCryptos);

    }
}
