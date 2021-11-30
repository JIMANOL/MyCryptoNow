package com.example.mycryptonow.ui.home;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.example.mycryptonow.R;
import com.example.mycryptonow.databinding.ActivityMainBinding;
import com.example.mycryptonow.db.Realtime;
import com.example.mycryptonow.interfaces.APIInterface;
import com.example.mycryptonow.interfaces.Respuesta;
import com.example.mycryptonow.models.APIClient;
import com.example.mycryptonow.models.CryptoCoinMarket;
import com.example.mycryptonow.models.Datum;
import com.example.mycryptonow.models.Usuario;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;


import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity{

    private MainViewModel modelo;
    private Activity activity;
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private Usuario usuario;

    private int creditos=3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        usuario = (Usuario) getIntent().getSerializableExtra("usuario");

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_lista_cryptos, R.id.nav_lista_mis_cryptos,R.id.nav_lista_accesos, R.id.nav_creditos,
                R.id.nav_admin_home,R.id.nav_bienvenida,R.id.nav_reporte_errores, R.id.nav_videoinfo,R.id.nav_editar_crypto)
                .setOpenableLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        //Inicializacion

        //getSupportActionBar().hide();

        modelo = new ViewModelProvider(this).get(MainViewModel.class);
        activity = this;

        navigationView = (NavigationView) findViewById(R.id.nav_view);   // la vista del menu drawer
        if(usuario.getTipoUsuario().equals("0")){
            navigationView.getMenu().clear();      //elimina anterior menu drawer
            navigationView.inflateMenu(R.menu.activity_main_drawer); //carga el menu drawer
        }else{
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.activity_admin_main_drawer);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case  R.id.action_settings:
                return true;
            case R.id.action_logout:
                Toast.makeText(this,"Hasta luego",Toast.LENGTH_LONG).show();
                FirebaseAuth firebaseAuth=firebaseAuth= FirebaseAuth.getInstance();
                firebaseAuth.signOut();
                finishAffinity();
                return true;
            case R.id.action_salir:
                Toast.makeText(this,"Hasta luego",Toast.LENGTH_LONG).show();
                finishAffinity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


}