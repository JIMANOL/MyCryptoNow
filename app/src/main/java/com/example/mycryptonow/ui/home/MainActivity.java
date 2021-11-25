package com.example.mycryptonow.ui.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.example.mycryptonow.R;
import com.example.mycryptonow.db.Realtime;
import com.example.mycryptonow.interfaces.APIInterface;
import com.example.mycryptonow.interfaces.Respuesta;
import com.example.mycryptonow.models.APIClient;
import com.example.mycryptonow.models.Datum;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private APIInterface apiInterface;
    private MainViewModel modelo;
    private Activity activity;

    private int creditos=3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inicializacion
        getSupportActionBar().hide();

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
        /*Thread hilo = new Thread(new Runnable() {
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
                            Toast.makeText(MainActivity.this, "onFailure", Toast.LENGTH_SHORT).show();
                            Log.d("XXXX", t.getLocalizedMessage());
                            call.cancel();
                        }
                    });
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.d("Creditos",String.valueOf(creditos));
                }while (creditos<333);
            }
        });

        hilo.start();*/

    }
}