package com.example.mycryptonow.ui.bienvenidausuario;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mycryptonow.R;

public class BienvenidaFragment extends Fragment {

    private BienvenidaViewModel mViewModel;

    public static BienvenidaFragment newInstance() {
        return new BienvenidaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bienvenida, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(BienvenidaViewModel.class);
        // TODO: Use the ViewModel
    }

}