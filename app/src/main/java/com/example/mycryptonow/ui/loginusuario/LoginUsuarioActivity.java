package com.example.mycryptonow.ui.loginusuario;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.mycryptonow.ui.home.MainActivity;
import com.example.mycryptonow.R;
import com.example.mycryptonow.interfaces.Respuesta;
import com.example.mycryptonow.models.Usuario;

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
                            Intent intent = new Intent(activity, MainActivity.class);
                            startActivity(intent);
                            finish();
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
}