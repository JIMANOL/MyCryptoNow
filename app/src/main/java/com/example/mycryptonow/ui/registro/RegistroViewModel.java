package com.example.mycryptonow.ui.registro;


import android.app.Activity;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mycryptonow.db.Autenticacion;
import com.example.mycryptonow.db.Realtime;
import com.example.mycryptonow.interfaces.Respuesta;
import com.example.mycryptonow.models.Usuario;

public class RegistroViewModel extends ViewModel {
    private MutableLiveData<Usuario> usuarioMutableLiveData = new MutableLiveData<Usuario>();
    private Realtime database= new Realtime();
    private Autenticacion autenticacion = new Autenticacion();

    public MutableLiveData getUsuario(){
        return usuarioMutableLiveData;
    }

    public void crearUsuario(Usuario usuario, String contrasenia, Activity activity){
        autenticacion.crearUsuarioConEmail(usuario, contrasenia, activity, new Respuesta() {
            @Override
            public void respuesta(Object respuesta) {
                if(respuesta != null){
                    database.agregarUsuario(usuario, activity,new Respuesta() {
                        @Override
                        public void respuesta(Object respuesta) {
                            if(respuesta != null){
                                usuarioMutableLiveData.setValue(new Usuario());
                            }else{
                                autenticacion.borrarUsuario(new Respuesta() {
                                    @Override
                                    public void respuesta(Object respuesta) {
                                        usuarioMutableLiveData.setValue(null);
                                    }
                                });
                            }
                        }
                    });
                }else{
                    usuarioMutableLiveData.setValue(null);
                }
            }
        });
    }

}
