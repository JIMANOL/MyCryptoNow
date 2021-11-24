package com.example.mycryptonow;

import android.app.Activity;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mycryptonow.db.Realtime;
import com.example.mycryptonow.interfaces.Respuesta;
import com.example.mycryptonow.models.CryptoCoinMarket;
import com.example.mycryptonow.models.Datum;

import java.util.ArrayList;

public class MainViewModel extends ViewModel {
    private MutableLiveData<CryptoCoinMarket> informacion = new MutableLiveData<>();
    private Realtime database = new Realtime();

    public MutableLiveData<CryptoCoinMarket> getInformacion() {
        return informacion;
    }

    public void agregarInformacionCryptos(Datum informacion, Activity activity){
        database.agregarCryptoInformacion(informacion, activity, new Respuesta() {
            @Override
            public void respuesta(Object respuesta) {

            }
        });

    }
}
