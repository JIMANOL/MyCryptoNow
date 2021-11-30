package com.example.mycryptonow.db;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.mycryptonow.interfaces.Respuesta;
import com.example.mycryptonow.models.ControlCreditosCMC;
import com.example.mycryptonow.models.Datum;
import com.example.mycryptonow.models.Ingresos;
import com.example.mycryptonow.models.MensajeChat;
import com.example.mycryptonow.models.MiCrypto;
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
    private final String COLECCION_INGRESOS_NOMBRE="informacion_ingresos";
    private final String COLECCION_MIS_CRYPTOS_NOMBRE="informacion_de_los_usuarios";
    private final String COLECCION_CONTROL_CMC_NOMBRE="control_cmc_creditos";
    private final String COLECCION_MENSAJES_NOMBRE="mensajes_chat_errores";

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
     * Crea un usuario en firebase realtime, devuleve un objeto si se creo correctamente, devuelve un nulo
     * si no se pudo realizar la operacion.
     * @param ingresos
     * @param respuesta
     */
    public void agregarIngreso(Ingresos ingresos, Activity activity, Respuesta respuesta){
        databaseReference.child(COLECCION_INGRESOS_NOMBRE).child(FirebaseAuth.getInstance().getCurrentUser().getUid()).push().setValue(ingresos).addOnCompleteListener(activity, new OnCompleteListener<Void>() {
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
        databaseReference.child(COLECCION_CRYPTOS_INFO_NOMBRE).child(crypto.getName()).push().setValue(crypto).addOnCompleteListener(activity, new OnCompleteListener<Void>() {
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

    public void obtenerListaCryptos(Activity activity,Respuesta respuesta){
        databaseReference.child(COLECCION_CRYPTOS_INFO_NOMBRE).get().addOnCompleteListener(activity, new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                ArrayList<ArrayList<Datum>> listaCryptos = new ArrayList<>();
                ArrayList<Datum> listaInfoCrypto = new ArrayList<>();
                if (task.isSuccessful()){

                    for (DataSnapshot dataSnapshot : task.getResult().getChildren()){
                        for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren() ){
                            Datum dato = new Datum();
                            dato.fromSnapshot(dataSnapshot1);
                            listaInfoCrypto.add(dato);
                        }
                        listaCryptos.add(listaInfoCrypto);
                        listaInfoCrypto = new ArrayList<>();
                    }

                    respuesta.respuesta(listaCryptos);
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
                        if(usuario.getCorreoUsuario().equalsIgnoreCase(correo)){
                            usuarioFinal=usuario;
                        }
                        usuario = new Usuario();
                    }
                    respuesta.respuesta(usuarioFinal);
                }
            }
        });
    }


    public void agregarMiCrypto(MiCrypto crypto, Respuesta respuesta){
        databaseReference.child(COLECCION_MIS_CRYPTOS_NOMBRE).child(FirebaseAuth.getInstance().getCurrentUser().getUid()).push().setValue(crypto).addOnCompleteListener(new OnCompleteListener<Void>() {
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



    public void actualizarMiCrypto(MiCrypto crypto, Activity activity, Respuesta respuesta){
        databaseReference.child(COLECCION_MIS_CRYPTOS_NOMBRE).child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(crypto.getId()).setValue(crypto).addOnCompleteListener(new OnCompleteListener<Void>() {
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

    public void borrrarMiCrypto(MiCrypto crypto, Respuesta respuesta){
        databaseReference.child(COLECCION_MIS_CRYPTOS_NOMBRE).child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(crypto.getId()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
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

    public void obtenerListaMisCryptos(Activity activity,Respuesta respuesta){
        databaseReference.child(COLECCION_MIS_CRYPTOS_NOMBRE).child(FirebaseAuth.getInstance().getCurrentUser().getUid()).get().addOnCompleteListener(activity, new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                ArrayList<MiCrypto> listaInfoCrypto = new ArrayList<>();
                if (task.isSuccessful()){

                    for (DataSnapshot dataSnapshot : task.getResult().getChildren()){
                        MiCrypto dato = new MiCrypto();
                        dato.fromSnapShot(dataSnapshot);
                        listaInfoCrypto.add(dato);
                    }

                    respuesta.respuesta(listaInfoCrypto);
                }
            }
        });
    }

    public void agregarControlControlCreditos(ControlCreditosCMC controlCreditosCMC, Activity activity, Respuesta respuesta){
        databaseReference.child(COLECCION_CONTROL_CMC_NOMBRE).setValue(controlCreditosCMC).addOnCompleteListener(activity, new OnCompleteListener<Void>() {
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

    public void obtenerControlControlCreditos(Activity activity,Respuesta respuesta){
        databaseReference.child(COLECCION_CONTROL_CMC_NOMBRE).get().addOnCompleteListener(activity, new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){

                    ControlCreditosCMC dato = new ControlCreditosCMC();
                    try {
                        dato.fromSnapshot(task.getResult());
                        respuesta.respuesta(dato);
                    }catch (Exception e){
                        respuesta.respuesta(null);
                    }
                }
            }
        });
    }

    public void agregarMensajeAlChat(MensajeChat mensaje, Activity activity){
        databaseReference.child(COLECCION_MENSAJES_NOMBRE).child(FirebaseAuth.getInstance().getCurrentUser().getUid()).push().setValue(mensaje).addOnCompleteListener(activity, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    //respuesta.respuesta(new Object());
                }else{
                    //respuesta.respuesta(null);
                }
            }
        });
    }




}
