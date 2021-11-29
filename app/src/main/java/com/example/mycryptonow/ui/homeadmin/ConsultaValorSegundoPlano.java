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
                Thread.sleep(1800000);
                contador++;
                Intent intent1 = new Intent("broadcast");
                intent1.putExtra("contador",contador);
                LocalBroadcastManager.getInstance(this).sendBroadcast(intent1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
