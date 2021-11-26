package com.example.mycryptonow.ui.listacryptos;

import android.app.Activity;
import android.view.View;
import android.widget.Filter;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.airbnb.lottie.LottieAnimationView;
import com.example.mycryptonow.db.Autenticacion;
import com.example.mycryptonow.db.Realtime;
import com.example.mycryptonow.interfaces.Respuesta;
import com.example.mycryptonow.models.Datum;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class ListaCryptosViewModel extends ViewModel {
    private MutableLiveData<ArrayList<ArrayList<Datum>>> info = new MutableLiveData<>();
    private Realtime database = new Realtime();
    private Autenticacion autenticacion = new Autenticacion();

    public MutableLiveData<ArrayList<ArrayList<Datum>>> getInfo() {
        return info;
    }

    public void buscarCryptos(Activity activity, LottieAnimationView cargarAnimacion){
        cargarAnimacion.setVisibility(View.VISIBLE);
        cargarAnimacion.playAnimation();
        database.obtenerListaCryptos(activity, new Respuesta() {
            @Override
            public void respuesta(Object respuesta) {
                info.setValue((ArrayList<ArrayList<Datum>>) respuesta);
            }
        });
    }

    public void verificarCorreo(Activity activity) {
        autenticacion.mandarCorreoConfirmacionEmail(new Respuesta() {
            @Override
            public void respuesta(Object respuesta) {
                Toast.makeText(activity,"Se mando correctamente el correo",Toast.LENGTH_LONG).show();
                FirebaseAuth.getInstance().signOut();
                activity.finishAffinity();
            }
        });
    }
}