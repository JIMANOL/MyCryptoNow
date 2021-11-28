package com.example.mycryptonow.models;

import com.google.firebase.database.DataSnapshot;

public class Ingresos {

    private String id;
    private String fecha;
    private  String dispositivo;
    private  String direccion;

    public Ingresos() {
    }
    public Ingresos(String fecha, String dispositivo, String direccion) {
        this.fecha = fecha;
        this.dispositivo = dispositivo;
        this.direccion = direccion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(String dispositivo) {
        this.dispositivo = dispositivo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void fromSnapshot(DataSnapshot dataSnapshot){
        id = dataSnapshot.getKey();
        fecha = dataSnapshot.child("fecha").getValue().toString();
        dispositivo = dataSnapshot.child("dispositivo").getValue().toString();
        direccion = dataSnapshot.child("direccion").getValue().toString();
    }
}
