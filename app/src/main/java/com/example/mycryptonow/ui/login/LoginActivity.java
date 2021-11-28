package com.example.mycryptonow.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mycryptonow.R;
import com.example.mycryptonow.models.Ingresos;
import com.example.mycryptonow.models.Usuario;
import com.example.mycryptonow.ui.home.MainActivity;
import com.example.mycryptonow.ui.loginusuario.LoginUsuarioActivity;
import com.example.mycryptonow.ui.registro.RegistroActivity;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;
import java.net.NetworkInterface;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    //Variables globales
    private FirebaseAuth firebaseAuth;
    private TextView tvRregistrate;
    private TextView tvMensaje;
    private Button btnBuscarCorreo;
    private LoginViewModel modelo;
    private Activity activity;
    private EditText etCorreo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Inicializacion
        getSupportActionBar().hide();
        firebaseAuth=FirebaseAuth.getInstance();
        activity = this;
        iniciarComonentes();

    }

    private void iniciarComonentes() {
        //Inicializacion
        tvRregistrate = findViewById(R.id.tvRegistroLogin);
        btnBuscarCorreo = findViewById(R.id.btnBuscarCorreo);
        tvMensaje = findViewById(R.id.tvMensajeLogin);
        etCorreo = findViewById(R.id.etCorreoLogin);
        modelo = new ViewModelProvider(this).get(LoginViewModel.class);
        //Comportamientos
        tvRregistrate.setOnClickListener(this);
        btnBuscarCorreo.setOnClickListener(this);
        modelo.getUsuarip().observe(this, new Observer() {
            @Override
            public void onChanged(Object o) {
                if(o == null){
                    mostrarMensaje("No se encontro el correo proporsionado.");
                    tvMensaje.setVisibility(View.VISIBLE);
                }else{
                    tvMensaje.setVisibility(View.GONE);
                    Intent intent = new Intent(activity, LoginUsuarioActivity.class);
                    Usuario usuario = (Usuario) o;
                    intent.putExtra("usuario", usuario);
                    startActivity(intent);
                }
            }
        });

        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if(currentUser != null){
            modelo.buscarCorroe(currentUser.getEmail(),activity);
        }
    }

    private void mostrarMensaje(String mensaje) {
        Toast toast = Toast.makeText(this,mensaje,Toast.LENGTH_LONG);
        toast.show();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnBuscarCorreo:
                validarInfo();
                break;
            case R.id.tvRegistroLogin:
                Intent intent = new Intent(this, RegistroActivity.class);
                startActivity(intent);
                break;
        }

    }

    private void validarInfo() {
        String correo;

        correo= etCorreo.getText().toString();

        if(correo != null){
            modelo.buscarCorroe(correo,activity);
        }else{
            mostrarMensaje("No ingreso ningun dato");
        }

    }

    public static String getMacAddress() {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;

                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return "";
                }

                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    res1.append(Integer.toHexString(b & 0xFF) + ":");
                }

                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }
        } catch (Exception ex) {
            Log.e("Error", ex.getMessage());
        }
        return "";
    }
}