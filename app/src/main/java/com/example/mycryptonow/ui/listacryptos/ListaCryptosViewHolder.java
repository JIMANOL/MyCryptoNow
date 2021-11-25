package com.example.mycryptonow.ui.listacryptos;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycryptonow.R;
import com.example.mycryptonow.models.Datum;

public class ListaCryptosViewHolder extends RecyclerView.ViewHolder {
    private TextView tvID;
    private TextView tvRank;

    public ListaCryptosViewHolder(@NonNull View itemView) {
        super(itemView);
        tvID = itemView.findViewById(R.id.tvIDListaCryptos);
        tvRank = itemView.findViewById(R.id.tvRankcmcListaCryptos);
    }

    public void  setIfo(Datum crypto){
        tvID.setText(String.valueOf(crypto.getId()));
        tvRank.setText(String.valueOf(crypto.getCmcRank()));
    }
}
