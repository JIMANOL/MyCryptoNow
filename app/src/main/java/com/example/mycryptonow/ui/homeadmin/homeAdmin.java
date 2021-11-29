package com.example.mycryptonow.ui.homeadmin;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mycryptonow.R;
import com.example.mycryptonow.interfaces.APIInterface;
import com.example.mycryptonow.models.APIClient;
import com.example.mycryptonow.models.ControlCreditosCMC;
import com.example.mycryptonow.models.CryptoCoinMarket;
import com.example.mycryptonow.models.Datum;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class homeAdmin extends Fragment {

    private HomeAdminViewModel modelo;
    private APIInterface apiInterface;
    private ControlCreditosCMC controlCreditosCMC;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/YYYY");
    private Button btnActualizar;
    private TextView reloj;

    public static homeAdmin newInstance() {
        return new homeAdmin();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.home_admin_fragment, container, false);

        modelo = new ViewModelProvider(this).get(HomeAdminViewModel.class);
        controlCreditosCMC = new ControlCreditosCMC(0,simpleDateFormat.format(new Date()));

        modelo.getInformacion().observe(getViewLifecycleOwner(), new Observer<ControlCreditosCMC>() {
            @Override
            public void onChanged(ControlCreditosCMC controlCreditosCMC2) {
                if(controlCreditosCMC2 != null){
                    if((simpleDateFormat.format(new Date()).equalsIgnoreCase(controlCreditosCMC2.getFecha()))){
                        controlCreditosCMC = controlCreditosCMC2;
                        temporizador();
                    }else{
                        controlCreditosCMC = new ControlCreditosCMC(0,simpleDateFormat.format(new Date()));
                    }
                }else{
                    controlCreditosCMC = new ControlCreditosCMC(0,simpleDateFormat.format(new Date()));
                }
            }
        });

        btnActualizar = root.findViewById(R.id.btnActualizarCryptos);
        reloj = root.findViewById(R.id.textView2);

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizarCrypto();
            }
        });

        modelo.obtenerControlCryptos(getActivity());


        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    public void actualizarCrypto(){
        apiInterface = APIClient.getClient().create(APIInterface.class);
        if(controlCreditosCMC.getConteoCreditos() <= 333){
            Call<CryptoCoinMarket> call2 = apiInterface.doGetUserList("1","10", "MXN");
            call2.enqueue(new Callback<CryptoCoinMarket>() {
                @Override
                public void onResponse(Call<CryptoCoinMarket> call, Response<CryptoCoinMarket> response) {
                    CryptoCoinMarket list = response.body();
                    controlCreditosCMC.setConteoCreditos(controlCreditosCMC.getConteoCreditos()+list.getStatus().getCreditCount());
                    modelo.actualizarControlCryptos(controlCreditosCMC,getActivity());
                    for (Datum crypto : list.getData()) {
                        modelo.agregarInformacionCryptos(crypto,getActivity());
                    }
                    Toast.makeText(getContext(), "Se actualizo correctamen el precio",Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(Call<CryptoCoinMarket> call, Throwable t) {
                    Toast.makeText(getContext(), "onFailure", Toast.LENGTH_SHORT).show();
                    Log.d("Error", t.getLocalizedMessage());
                    call.cancel();
                }
            });
        }else{
            Toast.makeText(getContext(),"Ya no hay mas creditos",Toast.LENGTH_LONG).show();
        }
    }

    public void temporizador(){
        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                int time5=300000;
                int minutos=5;
                int segundos = 0;

                while (time5>0){
                    reloj.setText("Tiempo= "+(minutos+":"+segundos));
                    if(segundos==0){
                        minutos--;
                        segundos=60;
                    }else{
                        segundos--;
                    }
                    time5-=1000;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        hilo.start();
    }

    public void procesoSegundoPlano(){
        temporizador();
        Intent intent = new Intent(getActivity(),ConsultaValorSegundoPlano.class);
        intent.putExtra("limite", 150);
        getActivity().startService(intent);

    }

    @Override
    public void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter("broadcast");
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(broadcastReceiver,intentFilter);
        procesoSegundoPlano();

    }

    @Override
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(broadcastReceiver);
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            actualizarCrypto();
        }
    };

}