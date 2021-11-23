package com.example.mycryptonow.ui.loginusuario;

import android.app.Activity;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mycryptonow.db.Autenticacion;
import com.example.mycryptonow.interfaces.Respuesta;
import com.example.mycryptonow.models.Usuario;

public class LoginUsuarioViewModel extends ViewModel {
    private MutableLiveData<Usuario> usuario = new MutableLiveData<>();
    private Autenticacion autenticacion = new Autenticacion();

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

    public void recuperarContrasenia(String correo, Respuesta respuesta1){
        autenticacion.mandarCorreoEstablcerContrasenia(correo, new Respuesta() {
            @Override
            public void respuesta(Object respuesta) {
                respuesta1.respuesta(new Object());
            }
        });
    }
}
