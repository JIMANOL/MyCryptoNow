package com.example.mycryptonow.ui.agregar;

import android.app.Activity;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mycryptonow.db.Realtime;
import com.example.mycryptonow.interfaces.Respuesta;
import com.example.mycryptonow.models.Datum;
import com.example.mycryptonow.models.MiCrypto;
import com.example.mycryptonow.models.Usuario;

public class AgregarViewModel extends ViewModel {

    private MutableLiveData<MiCrypto> miCryptoMutableLiveData = new MutableLiveData<MiCrypto>();
    private Realtime database= new Realtime();

    public MutableLiveData getCrypto(){
        return miCryptoMutableLiveData;
    }

    public void agregarCrypto(MiCrypto miCrypto, Activity activity){
       database.agregarMiCrypto(miCrypto, activity, new Respuesta() {
           @Override
           public void respuesta(Object respuesta) {
               if (respuesta!=null){
                   miCryptoMutableLiveData.setValue(new MiCrypto());
               }else{miCryptoMutableLiveData.setValue(null);}

           }
       });
    }





}