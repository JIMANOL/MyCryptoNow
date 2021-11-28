package com.example.mycryptonow.ui.detallecrypto;

import android.graphics.Bitmap;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mycryptonow.db.GaleriaFireBase;
import com.example.mycryptonow.interfaces.Respuesta;

public class DetalleCryptoViewModel extends ViewModel {
    private GaleriaFireBase galeria = new GaleriaFireBase();

    public void subirImagen(Bitmap imagen, String nombreImagen, Respuesta respuesta){
        galeria.subirImagen(imagen,nombreImagen,respuesta);
    }

}