package com.example.mycryptonow.ui.registro;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.airbnb.lottie.LottieAnimationView;
import com.example.mycryptonow.R;
import com.example.mycryptonow.models.Usuario;

public class RegistroActivity extends AppCompatActivity implements View.OnClickListener {
    //Variables globales
    private TextView tvRegresar;
    private TextView tvMensaje;
    private TextView tvMensaje1;
    private TextView tvMensaje2;
    private TextView tvMensaje3;
    private TextView tvMensaje4;
    private TextView tvMensaje5;
    private TextView tvMensaje6;
    private TextView tvMensaje7;
    private Button btnCrearUsuario;
    private RegistroViewModel modelo;
    private LottieAnimationView enviar;
    private LottieAnimationView cargar;
    private EditText etNombre;
    private EditText etApPaterno;
    private EditText etApMaterno;
    private EditText etNombreUsuario;
    private EditText etCorreo;
    private EditText etContrasenia;
    private EditText etConfirmacionContrasenia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        //Inicializacion
        getSupportActionBar().hide();
        inicioComponentes();

    }

    private void inicioComponentes() {

        //Inicializacion
        tvRegresar = findViewById(R.id.tvRegresarRegistro);
        tvMensaje = findViewById(R.id.tvMensajeRegistro);
        tvMensaje1 = findViewById(R.id.tvMensaje1);
        tvMensaje2 = findViewById(R.id.tvMensaje2);
        tvMensaje3 = findViewById(R.id.tvMensaje3);
        tvMensaje4 = findViewById(R.id.tvMensaje4);
        tvMensaje5 = findViewById(R.id.tvMensaje5);
        tvMensaje6 = findViewById(R.id.tvMensaje6);
        tvMensaje7 = findViewById(R.id.tvMensaje7);
        btnCrearUsuario = findViewById(R.id.btnRegistrarUsuario);
        enviar = findViewById(R.id.animacionEnviar);
        cargar = findViewById(R.id.animacionxCargar);
        etNombre = findViewById(R.id.etNombreRegistro);
        etApPaterno = findViewById(R.id.etApellidoPaternoRegistro);
        etApMaterno = findViewById(R.id.etApellidoMaternoRegistro);
        etNombreUsuario = findViewById(R.id.etNombreUsuarioRegistro);
        etCorreo = findViewById(R.id.etCorreoRegistro);
        etContrasenia = findViewById(R.id.etContraseniaRegistro);
        etConfirmacionContrasenia = findViewById(R.id.etContraseniaConfirmadaRegistro);
        modelo = new ViewModelProvider(this).get(RegistroViewModel.class);

        //Comportamiento
        tvRegresar.setOnClickListener(this);
        btnCrearUsuario.setOnClickListener(this);
        modelo.getUsuario().observe(this, new Observer() {
            @Override
            public void onChanged(Object o) {
                if(o != null){
                    enviar.setVisibility(View.VISIBLE);
                    tvMensaje.setVisibility(View.VISIBLE);
                    enviar.playAnimation();
                    enviar.addAnimatorListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            enviar.setVisibility(View.GONE);
                        }
                    });
                    mostrarMensaje("Regresando al login");
                    Thread hilo = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(5000);
                                regresar();
                            } catch (InterruptedException e) {
                                Log.e("Hilo",e.getMessage());
                            }
                        }
                    });

                    hilo.start();

                }else{
                    mostrarMensaje("Ocurrio un error inesperado, intento mas tarde");
                }
            }
        });
    }

    private void regresar() {
        finish();
    }

    private void mostrarMensaje(String mensaje) {
        Toast toast = Toast.makeText(this,mensaje,Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnRegistrarUsuario:
                validarInformacion();
                break;
            case R.id.tvRegresarRegistro:
                regresar();
                break;
        }

    }

    private void validarInformacion() {
        String nombre;
        String apellidoPaterno;
        String apellidoMaterno;
        String nombreUsuario;
        String correo;
        String contrasenia;
        String contraseniaConfirmada;
        boolean ban=true;

        nombre = etNombre.getText().toString();
        if(!nombre.matches("[A-z]+")){
            ban=false;
            tvMensaje1.setVisibility(View.VISIBLE);
        }else{
            tvMensaje1.setVisibility(View.GONE);
        }
        apellidoPaterno = etApPaterno.getText().toString();
        if(!apellidoPaterno.matches("[A-z]+")){
            ban=false;
            tvMensaje2.setVisibility(View.VISIBLE);
        }else{
            tvMensaje2.setVisibility(View.GONE);
        }
        apellidoMaterno = etApMaterno.getText().toString();
        if(!apellidoMaterno.matches("[A-z]+")){
            ban=false;
            tvMensaje3.setVisibility(View.VISIBLE);
        }else{
            tvMensaje3.setVisibility(View.GONE);
        }
        nombreUsuario = etNombreUsuario.getText().toString();
        if(!nombreUsuario.matches("([A-z]*[0-9]*)*")){
            ban=false;
            tvMensaje4.setVisibility(View.VISIBLE);
        }else{
            tvMensaje4.setVisibility(View.GONE);
        }
        correo = etCorreo.getText().toString();
        if(!correo.matches("([A-z]+.*)+@([A-z]*.+)+[A-z]+")){
            ban=false;
            tvMensaje5.setVisibility(View.VISIBLE);
        }else{
            tvMensaje5.setVisibility(View.GONE);
        }
        contrasenia = etContrasenia.getText().toString();
        if(!(contrasenia.length() == 8)){
            ban=false;
            tvMensaje6.setVisibility(View.VISIBLE);
        }else{
            tvMensaje6.setVisibility(View.GONE);
        }
        contraseniaConfirmada = etConfirmacionContrasenia.getText().toString();
        if(!contrasenia.equals(contraseniaConfirmada)){
            ban=false;
            tvMensaje7.setVisibility(View.VISIBLE);
        }else{
            if(tvMensaje7.getVisibility() == View.VISIBLE){
                tvMensaje7.setVisibility(View.GONE);
            }
        }


        if(nombre.equals("") ||
        apellidoPaterno.equals("") ||
        apellidoMaterno.equals("") ||
        nombreUsuario.equals("") ||
        correo.equals("") ||
        contrasenia.equals("") ||
        contraseniaConfirmada.equals("")){
            mostrarMensaje("Ingrese todos los campos solicitados, por favor.");
        }else{
            if(ban){
                Usuario usuario = new Usuario(nombre,apellidoPaterno,apellidoMaterno,nombreUsuario,correo,"0");
                modelo.crearUsuario(usuario,contrasenia,this);
            }else{
                mostrarMensaje("Campos incorrectos, por favor verifique su informacion");
            }
        }



    }
}