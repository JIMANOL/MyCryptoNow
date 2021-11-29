package com.example.mycryptonow.models;

import com.google.firebase.database.DataSnapshot;

import java.util.Date;

public class MensajeChat {
    private String mensaje;
    private String usuario;
    private String hora;

    public MensajeChat(String texto, String user, String hora) {
        this.mensaje = texto;
        this.usuario = user;
        this.hora = hora;
    }
    public MensajeChat( ) {
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
    public void fromSnapshot(DataSnapshot dataSnapshot){
        //id = dataSnapshot.getKey();
        mensaje = dataSnapshot.child("mensaje").getValue().toString();
        usuario = dataSnapshot.child("usuario").getValue().toString();
        hora = dataSnapshot.child("hora").getValue().toString();
    }
}
