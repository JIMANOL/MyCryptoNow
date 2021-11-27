package com.example.mycryptonow.ui.listaaccesos;



import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ListaAccesosViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public ListaAccesosViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is listar fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}