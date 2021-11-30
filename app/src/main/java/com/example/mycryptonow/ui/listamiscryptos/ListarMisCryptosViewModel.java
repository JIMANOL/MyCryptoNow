package com.example.mycryptonow.ui.listamiscryptos;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mycryptonow.db.Realtime;
import com.example.mycryptonow.models.Datum;

import java.util.ArrayList;

public class ListarMisCryptosViewModel extends ViewModel {
    private Realtime database = new Realtime();

    public void buscarMisCryptos(){}
}