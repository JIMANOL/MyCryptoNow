package com.example.mycryptonow.ui.detallecrypto;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mycryptonow.R;

public class DetalleCryptoFragment extends Fragment {

    private DetalleCryptoViewModel mViewModel;

    public static DetalleCryptoFragment newInstance() {
        return new DetalleCryptoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detalle_crypto, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DetalleCryptoViewModel.class);
        // TODO: Use the ViewModel
    }

}