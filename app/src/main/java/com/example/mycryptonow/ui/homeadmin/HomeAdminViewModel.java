package com.example.mycryptonow.ui.homeadmin;

import android.app.Activity;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mycryptonow.db.Realtime;
import com.example.mycryptonow.interfaces.Respuesta;
import com.example.mycryptonow.models.ControlCreditosCMC;
import com.example.mycryptonow.models.Datum;

public class HomeAdminViewModel extends ViewModel {
    private MutableLiveData<ControlCreditosCMC> informacion = new MutableLiveData<>();
    private Realtime database = new Realtime();

    public MutableLiveData<ControlCreditosCMC> getInformacion() {
        return informacion;
    }

    public void agregarInformacionCryptos(Datum informacion, Activity activity){
        database.agregarCryptoInformacion(informacion, activity, new Respuesta() {
            @Override
            public void respuesta(Object respuesta) {

            }
        });
    }

    public void obtenerControlCryptos(Activity activity){
        database.obtenerControlControlCreditos(activity, new Respuesta() {
            @Override
            public void respuesta(Object respuesta) {
                if(respuesta != null ){
                    informacion.setValue((ControlCreditosCMC) respuesta);
                }
            }
        });
    }

    public void actualizarControlCryptos(ControlCreditosCMC controlCreditosCMC, Activity activity){
        database.agregarControlControlCreditos(controlCreditosCMC, activity, new Respuesta() {
            @Override
            public void respuesta(Object respuesta) {
                if(respuesta != null ){
                    obtenerControlCryptos(activity);
                    Toast.makeText(activity.getBaseContext(), "Se actualizo correctamen el contador",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}