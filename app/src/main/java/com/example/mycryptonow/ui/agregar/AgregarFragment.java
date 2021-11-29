package com.example.mycryptonow.ui.agregar;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.mycryptonow.R;
import com.example.mycryptonow.models.Datum;
import com.example.mycryptonow.ui.listamiscryptos.ListaMisCryptosFragment;

public class AgregarFragment extends Fragment {

    private AgregarViewModel mViewModel;

    public static AgregarFragment newInstance() {
        return new AgregarFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_agregar, container, false);

        //Datum dato = (Datum) getArguments().getSerializable("hola");
        //Log.d("Paso dato",String.valueOf(dato.getId()));

        iniciarCompoentes(root);

        return root;
    }

    private void iniciarCompoentes(View root) {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AgregarViewModel.class);
        // TODO: Use the ViewModel
    }

}