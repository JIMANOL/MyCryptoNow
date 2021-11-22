package com.example.mycryptonow.ui.login;

import android.app.Activity;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mycryptonow.R;
import com.example.mycryptonow.db.Realtime;
import com.example.mycryptonow.interfaces.Respuesta;
import com.example.mycryptonow.models.Usuario;

public class LoginViewModel extends ViewModel {
    private MutableLiveData<Usuario> usuario = new MutableLiveData();
    private Realtime database = new Realtime();

    public MutableLiveData getUsuarip(){
        return usuario;
    }

    public void buscarCorroe(String correo, Activity activity){
        database.buscarCorreos(correo, activity, new Respuesta() {
            @Override
            public void respuesta(Object respuesta) {
                if (respuesta == null){
                    usuario.setValue(null);
                }else{
                    usuario.setValue((Usuario) respuesta);
                }
            }
        });
    }
}
