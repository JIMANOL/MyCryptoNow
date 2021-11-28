package com.example.mycryptonow.models;

import com.google.firebase.database.DataSnapshot;

import java.io.Serializable;

public class MiCrypto implements Serializable {

    private String id;
    private String direccionWallet;
    private String cantidadCryptos;
    private String nombreBilletera;
    private String nombreCrypto;

    public MiCrypto(String direccionWallet, String cantidadCryptos, String nombreBilletera, String nombreCrypto) {
        this.direccionWallet = direccionWallet;
        this.cantidadCryptos = cantidadCryptos;
        this.nombreBilletera = nombreBilletera;
        this.nombreCrypto = nombreCrypto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDireccionWallet() {
        return direccionWallet;
    }

    public void setDireccionWallet(String direccionWallet) {
        this.direccionWallet = direccionWallet;
    }

    public String getCantidadCryptos() {
        return cantidadCryptos;
    }

    public void setCantidadCryptos(String cantidadCryptos) {
        this.cantidadCryptos = cantidadCryptos;
    }

    public String getNombreBilletera() {
        return nombreBilletera;
    }

    public void setNombreBilletera(String nombreBilletera) {
        this.nombreBilletera = nombreBilletera;
    }

    public String getNombreCrypto() {
        return nombreCrypto;
    }

    public void setNombreCrypto(String nombreCrypto) {
        this.nombreCrypto = nombreCrypto;
    }

    public void fromSnapShot(DataSnapshot dataSnapshot){
        id = dataSnapshot.getKey();
        direccionWallet = dataSnapshot.child("direccionWallet").getValue().toString();
        cantidadCryptos = dataSnapshot.child("cantidadCryptos").getValue().toString();
        nombreBilletera = dataSnapshot.child("nombreBilletera").getValue().toString();
        nombreCrypto = dataSnapshot.child("nombreCrypto").getValue().toString();
    }
}
