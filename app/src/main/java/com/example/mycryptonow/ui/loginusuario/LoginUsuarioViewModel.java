package com.example.mycryptonow.ui.loginusuario;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mycryptonow.db.Autenticacion;
import com.example.mycryptonow.db.Realtime;
import com.example.mycryptonow.interfaces.Respuesta;
import com.example.mycryptonow.models.Ingresos;
import com.example.mycryptonow.models.Usuario;
import com.example.mycryptonow.ui.home.MainActivity;

public class LoginUsuarioViewModel extends ViewModel {
    private MutableLiveData<Usuario> usuario = new MutableLiveData<>();
    private Autenticacion autenticacion = new Autenticacion();
    private Realtime database = new Realtime();

    public MutableLiveData getUsuario(){
        return usuario;
    }

    public void verificarUsuario(String correo, String contrasenia, Activity activity){
        autenticacion.ingresarConCorreoPassword(correo, contrasenia, activity, new Respuesta() {
            @Override
            public void respuesta(Object respuesta) {
                if(respuesta != null){
                    usuario.setValue(new Usuario());
                }else{
                    usuario.setValue(null);
                }
            }
        });
    }

    public void agregarIngresos(Ingresos ingresos, Activity activity, Usuario usuario){
        database.agregarIngreso(ingresos, activity, new Respuesta() {
            @Override
            public void respuesta(Object respuesta) {
                if (respuesta != null){
                    Intent intent = new Intent(activity, MainActivity.class);
                    intent.putExtra("usuario",usuario);
                    activity.startActivity(intent);
                    activity.finish();
                }else{
                    Toast.makeText(activity,"No se pudo crear el ingreso",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void recuperarContrasenia(String correo, Respuesta respuesta1){
        autenticacion.mandarCorreoEstablcerContrasenia(correo, new Respuesta() {
            @Override
            public void respuesta(Object respuesta) {
                respuesta1.respuesta(new Object());
            }
        });
    }
}
