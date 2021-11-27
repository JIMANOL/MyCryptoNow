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


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity{

    private APIInterface apiInterface;
    private MainViewModel modelo;
    private Activity activity;
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    private int creditos=3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_lista_cryptos, R.id.nav_lista_mis_cryptos)
                .setOpenableLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        //Inicializacion

        //getSupportActionBar().hide();

        modelo = new ViewModelProvider(this).get(MainViewModel.class);
        activity = this;

        Realtime base = new Realtime();
        base.obtenerListaCryptos(this, new Respuesta() {
            @Override
            public void respuesta(Object respuesta) {
                ArrayList<ArrayList<Datum>> listaCryptos = (ArrayList<ArrayList<Datum>>) respuesta;
                for (ArrayList<Datum> lista : listaCryptos){
                    for (Datum dato : lista){
                        Log.d("Informacion", dato.getName()+" "+dato.getQuote().getMxn().getPercentChange1h());
                    }
                }
            }
        });

        apiInterface = APIClient.getClient().create(APIInterface.class);
        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                do{
                    Call<CryptoCoinMarket> call2 = apiInterface.doGetUserList("1","10", "MXN");
                    call2.enqueue(new Callback<CryptoCoinMarket>() {
                        @Override
                        public void onResponse(Call<CryptoCoinMarket> call, Response<CryptoCoinMarket> response) {
                            CryptoCoinMarket list = response.body();
                            Log.d("Creditos",String.valueOf(creditos));
                            creditos += list.getStatus().getCreditCount();
                            for (Datum crypto : list.getData()) {
                                modelo.agregarInformacionCryptos(crypto,activity);
                            }
                        }

                        @Override
                        public void onFailure(Call<CryptoCoinMarket> call, Throwable t) {
                            Toast.makeText(activity, "onFailure", Toast.LENGTH_SHORT).show();
                            Log.d("XXXX", t.getLocalizedMessage());
                            call.cancel();
                        }
                    });
                    try {
                        Thread.sleep(1800000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.d("Creditos",String.valueOf(creditos));
                }while (creditos<333);
            }
        });

        //hilo.start();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
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