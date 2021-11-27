package com.example.mycryptonow.models;


import com.google.firebase.database.DataSnapshot;

import java.io.Serializable;

public class Usuario implements Serializable {

    private String id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String nombreUsuario;
    private String correoUsuario;
    private String tipoUsuario;

    public Usuario() {

    }


    public Usuario(String nombre, String apellidoPaterno, String apellidoMaterno, String nombreUsuario, String correoUsuario, String tipoUsuario) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.nombreUsuario = nombreUsuario;
        this.correoUsuario = correoUsuario;
        this.tipoUsuario = tipoUsuario;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }


    public void fromSnapShot(DataSnapshot dataSnapshot) {
        id = dataSnapshot.getKey();
        nombre = dataSnapshot.child("nombre").getValue().toString();
        apellidoPaterno = dataSnapshot.child("apellidoPaterno").getValue().toString();
        apellidoMaterno = dataSnapshot.child("apellidoMaterno").getValue().toString();
        nombreUsuario =  dataSnapshot.child("nombreUsuario").getValue().toString();
        correoUsuario = dataSnapshot.child("correoUsuario").getValue().toString();
        tipoUsuario = dataSnapshot.child("tipoUsuario").getValue().toString();
    }
}
