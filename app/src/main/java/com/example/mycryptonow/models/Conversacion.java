package com.example.mycryptonow.models;

import java.util.ArrayList;

public class Conversacion {

    private  String ID;
    private  String uidUser;
    private  String estado;
    private ArrayList <MensajeChat> ListaMensajes;

    public Conversacion(String ID, String uidUser, String estado, ArrayList<MensajeChat> listaMensajes) {
        this.ID = ID;
        this.uidUser = uidUser;
        this.estado = estado;
        ListaMensajes = listaMensajes;
    }
    public Conversacion(){
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getUidUser() {
        return uidUser;
    }

    public void setUidUser(String uidUser) {
        this.uidUser = uidUser;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public ArrayList<MensajeChat> getListaMensajes() {
        return ListaMensajes;
    }

    public void setListaMensajes(ArrayList<MensajeChat> listaMensajes) {
        ListaMensajes = listaMensajes;
    }
}
