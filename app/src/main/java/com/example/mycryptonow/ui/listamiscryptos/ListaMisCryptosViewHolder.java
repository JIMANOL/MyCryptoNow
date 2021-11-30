package com.example.mycryptonow.ui.listamiscryptos;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycryptonow.R;
import com.example.mycryptonow.models.Datum;
import com.example.mycryptonow.models.MiCrypto;

import java.util.ArrayList;

public class ListaMisCryptosViewHolder extends RecyclerView.ViewHolder {
    private TextView tvTipoCrypto;
    private TextView tvDireccion;
    private TextView tvCantidad;
    private TextView tvNombre;
    private Button btnEliminar,btnEditar;

    public ListaMisCryptosViewHolder(@NonNull View itemView) {
        super(itemView);
        tvTipoCrypto = itemView.findViewById(R.id.tvmicryptoTipo);
        tvDireccion = itemView.findViewById(R.id.tvmicryptoDireccion);
        tvCantidad = itemView.findViewById(R.id.tvmicryptoCantidad);
        tvNombre = itemView.findViewById(R.id.tvmicryptoNombre);
        btnEliminar = itemView.findViewById(R.id.btnMisCryptoEliminar);
        btnEditar = itemView.findViewById(R.id.btnMisCryptoEditar);

    }

    public void  setIfo(MiCrypto miCrypto, ListarMisCryptosViewModel modelo){
        tvTipoCrypto.setText(miCrypto.getNombreCrypto());
        tvDireccion.setText(miCrypto.getDireccionWallet());
        tvCantidad.setText(miCrypto.getCantidadCryptos());
        tvNombre.setText(miCrypto.getNombreBilletera());
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modelo.eliminarCrypto(miCrypto);
            }
        });

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Datum dato = new Datum();
                Bundle bundle = new Bundle();
                bundle.putSerializable("hola", dato);
                Navigation.findNavController(view).navigate(R.id.nav_editar_crypto,bundle);
            }
        });



    }
}
