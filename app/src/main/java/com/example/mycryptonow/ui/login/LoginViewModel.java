package com.example.mycryptonow.ui.login;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mycryptonow.R;
import com.example.mycryptonow.db.Realtime;
import com.example.mycryptonow.interfaces.Respuesta;
import com.example.mycryptonow.models.Ingresos;
import com.example.mycryptonow.models.Usuario;
import com.example.mycryptonow.ui.home.MainActivity;

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

    public void agregarIngresos(Ingresos ingresos, Activity activity) {
        database.agregarIngreso(ingresos, activity, new Respuesta() {
            @Override
            public void respuesta(Object respuesta) {
                if (respuesta != null){
                    Intent intent = new Intent(activity, MainActivity.class);
                    activity.startActivity(intent);
                    activity.finish();
                }else{
                    Toast.makeText(activity,"No se pudo crear el ingreso",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
