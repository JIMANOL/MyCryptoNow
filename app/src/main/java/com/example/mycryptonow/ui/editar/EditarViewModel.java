package com.example.mycryptonow.ui.editar;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mycryptonow.db.Realtime;
import com.example.mycryptonow.interfaces.Respuesta;
import com.example.mycryptonow.models.MiCrypto;

public class EditarViewModel extends ViewModel {
    private MutableLiveData<MiCrypto> miCryptoMutableLiveData = new MutableLiveData<MiCrypto>();
    private Realtime database= new Realtime();

    public MutableLiveData getCrypto(){
        return miCryptoMutableLiveData;
    }

     public void editarCrypto(MiCrypto miCrypto){

       database.actualizarMiCrypto(miCrypto,new Respuesta() {
            @Override
            public void respuesta(Object respuesta) {
                if (respuesta!=null){
                    miCryptoMutableLiveData.setValue(new MiCrypto());
                }else{miCryptoMutableLiveData.setValue(null);}

            }
        });

    }
}