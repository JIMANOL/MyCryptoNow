package com.example.mycryptonow.ui.bienvenidausuario;

import android.app.Activity;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mycryptonow.db.Realtime;
import com.example.mycryptonow.interfaces.Respuesta;
import com.example.mycryptonow.models.Datum;
import com.example.mycryptonow.models.MiCrypto;

import java.util.ArrayList;

public class BienvenidaViewModel extends ViewModel {
    private MutableLiveData<ArrayList<ArrayList<Datum>>> listaDatos = new MutableLiveData<>();
    private MutableLiveData<ArrayList<MiCrypto>> listaMisCryptos = new MutableLiveData<>();
    private Realtime database = new Realtime();

    public MutableLiveData<ArrayList<ArrayList<Datum>>> getListaDatos() {
        return listaDatos;
    }

    public MutableLiveData<ArrayList<MiCrypto>> getListaMisCryptos() {
        return listaMisCryptos;
    }

    public void obtenerListaCryptos(Activity activity){
        database.obtenerListaCryptos(activity, new Respuesta() {
            @Override
            public void respuesta(Object respuesta) {
                if(respuesta != null){
                    listaDatos.setValue((ArrayList<ArrayList<Datum>>) respuesta);
                }
            }
        });
    }

    public void obtenerListaMisCryptos(Activity activity){
        database.obtenerListaMisCryptos(activity, new Respuesta() {
            @Override
            public void respuesta(Object respuesta) {
                if(respuesta != null){
                    listaMisCryptos.setValue((ArrayList<MiCrypto>) respuesta);
                }
            }
        });
    }
}