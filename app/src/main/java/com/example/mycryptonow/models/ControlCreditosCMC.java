package com.example.mycryptonow.models;

import com.google.firebase.database.DataSnapshot;

import java.io.Serializable;

public class ControlCreditosCMC implements Serializable {
    public static final int maximoDeCreditos = 333;

    private int conteoCreditos;
    private String fecha;

    public ControlCreditosCMC() {
    }

    public ControlCreditosCMC(int conteoCreditos, String fecha) {
        this.conteoCreditos = conteoCreditos;
        this.fecha = fecha;
    }

    public static int getMaximoDeCreditos() {
        return maximoDeCreditos;
    }
    public int getConteoCreditos() {
        return conteoCreditos;
    }

    public void setConteoCreditos(int conteoCreditos) {
        this.conteoCreditos = conteoCreditos;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void fromSnapshot(DataSnapshot dataSnapshot){
        conteoCreditos = Integer.parseInt(dataSnapshot.child("conteoCreditos").getValue().toString());
        fecha = dataSnapshot.child("fecha").getValue().toString();
    }
}
