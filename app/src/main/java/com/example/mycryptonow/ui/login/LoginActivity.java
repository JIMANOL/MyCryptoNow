package com.example.mycryptonow.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mycryptonow.ui.home.MainActivity;
import com.example.mycryptonow.R;
import com.example.mycryptonow.models.Usuario;
import com.example.mycryptonow.ui.loginusuario.LoginUsuarioActivity;
import com.example.mycryptonow.ui.registro.RegistroActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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
    }

    private void mostrarMensaje(String mensaje) {
        Toast toast = Toast.makeText(this,mensaje,Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
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
}