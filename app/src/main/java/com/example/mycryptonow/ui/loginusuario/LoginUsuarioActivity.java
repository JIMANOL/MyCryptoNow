package com.example.mycryptonow.ui.loginusuario;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
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

import com.airbnb.lottie.LottieAnimationView;
import com.example.mycryptonow.R;
import com.example.mycryptonow.interfaces.Respuesta;
import com.example.mycryptonow.models.Ingresos;
import com.example.mycryptonow.models.Usuario;
import com.example.mycryptonow.ui.home.MainActivity;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.firebase.auth.FirebaseAuth;

import java.io.IOException;
import java.net.NetworkInterface;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class LoginUsuarioActivity extends AppCompatActivity implements View.OnClickListener {
    //Variables globales
    private TextView tvCorreo;
    private TextView tvNombreUsuario;
    private EditText etContrasenia;
    private Button btnAcccerder;
    private TextView tvRegresar;
    private TextView tvMensaje;
    private LoginUsuarioViewModel modelo;
    private TextView tvRestablecerContrasenia;
    private String correo;
    private String usuarioNombre;
    private Usuario usuario;
    private Activity activity;
    private LottieAnimationView laVerificacion;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/YYYY hh:mm:ss");
    private FusedLocationProviderClient fusedLocationClient;
    private LocationManager locManager;
    private Location loc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_usuario);

        //Obtencion de informacion
        activity = this;
        usuario= (Usuario) getIntent().getSerializableExtra("usuario");
        correo = usuario.getCorreoUsuario();
        usuarioNombre = usuario.getNombreUsuario();
        laVerificacion = findViewById(R.id.animacionValidarLogin);

        //Inizializacion
        getSupportActionBar().hide();
        iniciarComponentes();
    }

    public  void iniciarComponentes(){
        //Inicializacion
        tvNombreUsuario = findViewById(R.id.tvNombreUsuarioLogin);
        tvCorreo = findViewById(R.id.tvCorreoUsuarioLogin);
        etContrasenia = findViewById(R.id.etPasswordLogin);
        btnAcccerder = findViewById(R.id.btnAccederLogin);
        tvMensaje = findViewById(R.id.tvMensajeLogin);
        tvRegresar = findViewById(R.id.tvRegresoLoginUsuario);
        modelo = new ViewModelProvider(this).get(LoginUsuarioViewModel.class);

        //Funcionalidad
        btnAcccerder.setOnClickListener(this);
        tvRegresar.setOnClickListener(this);
        modelo.getUsuario().observe(this, new Observer() {
            @Override
            public void onChanged(Object o) {
                if(o != null){
                    laVerificacion.setVisibility(View.VISIBLE);
                    laVerificacion.playAnimation();
                    laVerificacion.addAnimatorListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            String fecha = simpleDateFormat.format(new Date());
                            String dispositivo = getMacAddress();
                            String direccion="";

                            if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {


                            }

                            locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                            loc = locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            if(loc != null){
                                try {
                                    Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                                    List<Address> list = geocoder.getFromLocation(
                                            loc.getLatitude(), loc.getLongitude(), 1);
                                    if (!list.isEmpty()) {
                                        Address DirCalle = list.get(0);
                                        direccion=DirCalle.getAddressLine(0);
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                            Ingresos ingresos = new Ingresos(fecha,dispositivo,direccion);

                            modelo.agregarIngresos(ingresos,activity,usuario);

                        }
                    });
                }else{
                    tvMensaje.setVisibility(View.VISIBLE);
                }
            }
        });

        //Inicializacion d evalores
        tvCorreo.setText(correo);
        tvNombreUsuario.setText(usuarioNombre);
    }

    private void mostrarMensaje(String mensaje) {
        Toast toast = Toast.makeText(this,mensaje,Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser() != null){
            Intent intent = new Intent(activity, MainActivity.class);
            intent.putExtra("usuario",usuario);
            activity.startActivity(intent);
            activity.finish();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnAccederLogin:
                String contrasenia = etContrasenia.getText().toString();
                modelo.verificarUsuario(correo,contrasenia,activity);
                break;
            case R.id.tvRegresoLoginUsuario:
                finish();
                break;
            case R.id.tvRecuperarContrasenia:
                modelo.recuperarContrasenia(correo, new Respuesta() {
                    @Override
                    public void respuesta(Object respuesta) {
                        mostrarMensaje("Se mando el correo para recuperar contraseña");
                    }
                });
                break;
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