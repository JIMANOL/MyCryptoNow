package com.example.mycryptonow.ui.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.os.Bundle;

import com.example.mycryptonow.R;
import com.example.mycryptonow.interfaces.APIInterface;

public class MainActivity extends AppCompatActivity {
    private APIInterface apiInterface;
    private MainViewModel modelo;
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inicializacion
        getSupportActionBar().hide();

        modelo = new ViewModelProvider(this).get(MainViewModel.class);
        activity = this;

        /*
        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<CryptoCoinMarket> call2 = apiInterface.doGetUserList("1","2", "MXN");
        call2.enqueue(new Callback<CryptoCoinMarket>() {
            @Override
            public void onResponse(Call<CryptoCoinMarket> call, Response<CryptoCoinMarket> response) {
                CryptoCoinMarket list = response.body();
                Log.d("Informacion", list.getData().toString());
                modelo.agregarInformacionCryptos(list.getData().get(0),activity);
            }

            @Override
            public void onFailure(Call<CryptoCoinMarket> call, Throwable t) {
                Toast.makeText(MainActivity.this, "onFailure", Toast.LENGTH_SHORT).show();
                Log.d("XXXX", t.getLocalizedMessage());
                call.cancel();
            }
        });*/

    }
}