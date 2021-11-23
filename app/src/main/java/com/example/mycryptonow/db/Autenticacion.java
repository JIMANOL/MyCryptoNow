package com.example.mycryptonow.db;

import android.app.Activity;
import android.net.Uri;

import androidx.annotation.NonNull;

import com.example.mycryptonow.interfaces.Respuesta;
import com.example.mycryptonow.models.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.concurrent.Executor;

public class Autenticacion  {
    private FirebaseAuth firebaseAuth;
    private UserProfileChangeRequest profileUpdates;
    private FirebaseUser user;

    public Autenticacion() {
        firebaseAuth = FirebaseAuth.getInstance();
    }

    /**
     * Este metodo crea un usuario con correo y contraseña, devolviendo el usuario creado en caso de
     * que se haya creado correctamente, en caso contrario regresa nulo.
     * @param usuario
     * @param contrasenia
     * @param respuesta
     */
    public void crearUsuarioConEmail(Usuario usuario, String contrasenia, Activity activity,Respuesta respuesta){
        firebaseAuth .createUserWithEmailAndPassword(usuario.getCorreoUsuario(), contrasenia)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            user = firebaseAuth.getCurrentUser();

                            profileUpdates = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(usuario.getNombreUsuario())
                                    .build();

                            user.updateProfile(profileUpdates);

                            respuesta.respuesta(user);

                        } else {
                            respuesta.respuesta(null);
                        }
                    }
                });
    }

    /**
     * Manda un correo de verificacion al usuario, regresa el usuario cuando se completa la operacion.
     * @param respuesta
     */
    public void mandarCorreoConfirmacionEmail(Respuesta respuesta){

        user = firebaseAuth.getCurrentUser();

        firebaseAuth.setLanguageCode("sp");

        user.sendEmailVerification()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            respuesta.respuesta(new Object());
                        }
                    }
                });
    }

    /**
     * Manda un correo para restablecer la contraseñia, la funcion devolvera un objeto cuando este
     * proceso se haya completado.
     * @param email
     * @param respuesta
     */
    public void mandarCorreoEstablcerContrasenia(String email, Respuesta respuesta){

        firebaseAuth.setLanguageCode("sp");

        firebaseAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            respuesta.respuesta(new Object());
                        }
                    }
                });

    }

    /**
     * Realiza el proceso de eliminacion de la cuenta de la base de auth, regresa un objeto cuando
     * este proceso haya finalizado.
     * @param respuesta
     */
    public void borrarUsuario(Respuesta respuesta){

        user = firebaseAuth.getCurrentUser();

        user.delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            respuesta.respuesta(new Object());
                        }
                    }
                });
    }

    /**
     * Este metodo realizara la deconecxion del usuario, regresara un objeto cuando haya realizado el proceso.
     * @param respuesta
     */
    public void logout(Respuesta respuesta){
        firebaseAuth.signOut();
        respuesta.respuesta(new Object());
    }

    /**
     * Este metodo es empleado para verificar las credenciales del usuario y otorgarles acceso a la aplicacion
     * o denegarles el accceso, se retornar aun objeto si fue exito y en caso contrario se regresara un nulo
     * @param correo
     * @param contrasenia
     * @param activity
     * @param respuesta
     */

    public void ingresarConCorreoPassword(String correo,String contrasenia, Activity activity ,Respuesta respuesta){

        firebaseAuth.signInWithEmailAndPassword(correo,contrasenia).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    respuesta.respuesta(new Object());
                }else{
                    respuesta.respuesta(null);
                }
            }
        });
    }

}
