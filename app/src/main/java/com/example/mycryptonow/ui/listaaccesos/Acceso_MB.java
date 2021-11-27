package com.example.mycryptonow.ui.listaaccesos;

public class Acceso_MB {

    private String Fecha;
    private String ID;
    private String Hora;
    private String Dispositivo;
    private String Ubicacion;

    public Acceso_MB() {

    }

    public Acceso_MB(String fecha, String hora, String dispositivo, String ubicacion) {
        Fecha = fecha;
        Hora = hora;
        Dispositivo = dispositivo;
        Ubicacion = ubicacion;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getHora() {
        return Hora;
    }

    public void setHora(String hora) {
        Hora = hora;
    }

    public String getDispositivo() {
        return Dispositivo;
    }

    public void setDispositivo(String dispositivo) {
        Dispositivo = dispositivo;
    }

    public String getUbicacion() {
        return Ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        Ubicacion = ubicacion;
    }
}
