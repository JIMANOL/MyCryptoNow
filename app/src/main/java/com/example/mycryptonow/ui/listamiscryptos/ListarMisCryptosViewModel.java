package com.example.mycryptonow.ui.listamiscryptos;

import android.app.Activity;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mycryptonow.db.Realtime;
import com.example.mycryptonow.interfaces.Respuesta;
import com.example.mycryptonow.models.Datum;
import com.example.mycryptonow.models.MiCrypto;

import java.lang.reflect.AccessibleObject;
import java.util.ArrayList;

public class ListarMisCryptosViewModel extends ViewModel {
    private MutableLiveData<ArrayList<MiCrypto>> misCrptos = new MutableLiveData<>();
    private Realtime database = new Realtime();

    public void buscarMisCryptos(Activity activity){
        database.obtenerListaMisCryptos(activity, new Respuesta() {
            @Override
            public void respuesta(Object respuesta) {
                if(respuesta != null){
                    ArrayList<MiCrypto> lista = (ArrayList<MiCrypto>) respuesta;
                    misCrptos.setValue(lista);
                }else{
                    mostrarMensaje("Ocurrio un error");
                }
            }
        });
    }



    public void eliminarCrypto(MiCrypto miCrypto) {
        database.borrrarMiCrypto(miCrypto, new Respuesta() {
            @Override
            public void respuesta(Object respuesta) {
                if(respuesta != null){
                    mostrarMensaje("Se elimino correctamente");
                }else{
                    mostrarMensaje("Ocurrio un error al eliminar el dato");
                }
            }
        });
    }

    public MutableLiveData<ArrayList<MiCrypto>> getMisCrptos() {
        return misCrptos;
    }

    private void mostrarMensaje(String mensaje) {

    }
}