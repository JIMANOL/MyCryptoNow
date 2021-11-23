package com.example.mycryptonow.reporteerroresadmin;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mycryptonow.R;

public class reporteErroresAdmin extends Fragment {

    private ReporteErroresAdminViewModel mViewModel;

    public static reporteErroresAdmin newInstance() {
        return new reporteErroresAdmin();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.reporte_errores_admin_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ReporteErroresAdminViewModel.class);
        // TODO: Use the ViewModel
    }

}