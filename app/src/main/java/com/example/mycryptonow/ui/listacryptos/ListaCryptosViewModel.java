package com.example.mycryptonow.ui.listacryptos;

import android.app.Activity;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mycryptonow.db.Realtime;
import com.example.mycryptonow.interfaces.Respuesta;
import com.example.mycryptonow.models.Datum;

import java.util.ArrayList;

public class ListaCryptosViewModel extends ViewModel {
    MutableLiveData<ArrayList<ArrayList<Datum>>> info = new MutableLiveData<>();
    private Realtime database = new Realtime();

    public MutableLiveData<ArrayList<ArrayList<Datum>>> getInfo() {
        return info;
    }

    public void buscarCryptos(Activity activity){
        database.obtenerListaCryptos(activity, new Respuesta() {
            @Override
            public void respuesta(Object respuesta) {
                info.setValue((ArrayList<ArrayList<Datum>>) respuesta);
            }
        });
    }

}