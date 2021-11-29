package com.example.mycryptonow.ui.homeadmin;

import android.app.IntentService;
import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class ConsultaValorSegundoPlano  extends IntentService {


    /**
     * @deprecated
     */
    public ConsultaValorSegundoPlano() {
        super("Segundo plano valor");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        int limite = intent.getIntExtra("limite",0);
        int contador = 1;

        while (contador<=limite){
            try {
                contador++;
                Intent intent1 = new Intent("broadcast");
                LocalBroadcastManager.getInstance(this).sendBroadcast(intent1);
                Thread.sleep(600000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
