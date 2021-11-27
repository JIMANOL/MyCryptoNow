package com.example.mycryptonow.ui.listacryptos;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycryptonow.R;
import com.example.mycryptonow.models.Datum;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class ListaCryptosViewHolder extends RecyclerView.ViewHolder {
    private DecimalFormat decimalFormat = new DecimalFormat("#00.00");
    private NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
    private TextView tvID;
    private TextView tvRank;
    private TextView tvNombre;
    private TextView tvSimbolo;
    private TextView tvPrecio;
    private TextView tvCap;
    private TextView tvVariacion1;
    private TextView tvVariacion24;
    private CardView cvCart;

    public ListaCryptosViewHolder(@NonNull View itemView) {
        super(itemView);
        tvID = itemView.findViewById(R.id.tvIDListaCryptos);
        tvRank = itemView.findViewById(R.id.tvRankcmcListaCryptos);
        tvNombre = itemView.findViewById(R.id.tvNombreListaCryptos);
        tvSimbolo = itemView.findViewById(R.id.tvSimboloListaCryptos);
        tvPrecio = itemView.findViewById(R.id.tvPrecioListaCryptos);
        tvCap = itemView.findViewById(R.id.tvCapMarketListaCryptos);
        tvVariacion1 = itemView.findViewById(R.id.tvVariacion1hListaCryptos);
        tvVariacion24 = itemView.findViewById(R.id.tvVariacion24hListaCryptos);
        cvCart = itemView.findViewById(R.id.cvCart);
    }

    public void  setIfo(ArrayList<Datum> cryptos){
        Datum crypto = cryptos.get(cryptos.size()-1);

        tvID.setText(String.valueOf(crypto.getId()));
        tvRank.setText(String.valueOf(crypto.getCmcRank()));
        tvNombre.setText(crypto.getName());
        tvSimbolo.setText(crypto.getSymbol());
        tvPrecio.setText(numberFormat.format(crypto.getQuote().getMxn().getPrice()));
        tvCap.setText(numberFormat.format(crypto.getQuote().getMxn().getMarketCapitalizacion()));
        tvVariacion1.setText(decimalFormat.format(crypto.getQuote().getMxn().getPercentChange1h()) +" %");
        tvVariacion24.setText(decimalFormat.format(crypto.getQuote().getMxn().getPercentChange24h())+" %");

        if (crypto.getQuote().getMxn().getPercentChange1h() == 0){
            tvVariacion1.setTextColor(Color.WHITE);
        }else if(crypto.getQuote().getMxn().getPercentChange1h() > 0){
            tvVariacion1.setTextColor(Color.GREEN);
        }else{
            tvVariacion1.setTextColor(Color.RED);
        }

        if (crypto.getQuote().getMxn().getPercentChange24h() == 0){
            tvVariacion24.setTextColor(Color.WHITE);
        }else if(crypto.getQuote().getMxn().getPercentChange24h() > 0){
            tvVariacion24.setTextColor(Color.GREEN);
        }else{
            tvVariacion24.setTextColor(Color.RED);
        }

        cvCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("historial", cryptos);
                Navigation.findNavController(view).navigate(R.id.nav_detalle_crypto,bundle);
            }
        });
    }
}
