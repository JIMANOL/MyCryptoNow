package com.example.mycryptonow.db;

import android.app.Activity;

import androidx.annotation.NonNull;

import com.example.mycryptonow.interfaces.Respuesta;
import com.example.mycryptonow.models.Datum;
import com.example.mycryptonow.models.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.concurrent.Executor;

public class Realtime {
    //Constantes
    private final String COLECCION_USUARIOS_NOMBRE="usuarios";
    private final String COLECCION_CRYPTOS_INFO_NOMBRE="informacion_cryptos";

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    public Realtime() {
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
    }

    /**
     * Crea un usuario en firebase realtime, devuleve un objeto si se creo correctamente, devuelve un nulo
     * si no se pudo realizar la operacion.
     * @param usuario
     * @param respuesta
     */
    public void agregarUsuario(Usuario usuario, Activity activity, Respuesta respuesta){
        databaseReference.child(COLECCION_USUARIOS_NOMBRE).push().setValue(usuario).addOnCompleteListener(activity, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    respuesta.respuesta(new Object());
                }else{
                    respuesta.respuesta(null);
                }
            }
        });
    }

    /**
     * Agregar informacion en realtime acerca de la crypto, devuleve un objeto si se creo correctamente, devuelve un nulo
     * si no se pudo realizar la operacion.
     * @param crypto
     * @param respuesta
     */
    public void agregarCryptoInformacion(Datum crypto, Activity activity, Respuesta respuesta){
        databaseReference.child(COLECCION_CRYPTOS_INFO_NOMBRE).push().setValue(crypto).addOnCompleteListener(activity, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    respuesta.respuesta(new Object());
                }else{
                    respuesta.respuesta(null);
                }
            }
        });
    }

    /**
     * Busca si el correo existe y regresa al usuario correspondiente, en caso contrario regresa un nulo
     * @param correo
     * @param activity
     * @param respuesta
     */

    public void buscarCorreos(String correo, Activity activity, Respuesta respuesta){
        databaseReference.child(COLECCION_USUARIOS_NOMBRE).get().addOnCompleteListener(activity, new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){
                    Usuario usuarioFinal = null ;
                    Usuario usuario = new Usuario();
                    for (DataSnapshot dataSnapshot : task.getResult().getChildren()) {
                        usuario.fromSnapShot(dataSnapshot);
                        if(usuario.getCorreoUsuario().equals(correo)){
                            usuarioFinal=usuario;
                        }
                        usuario = new Usuario();
                    }
                    respuesta.respuesta(usuarioFinal);
                }
            }
        });
    }


}
