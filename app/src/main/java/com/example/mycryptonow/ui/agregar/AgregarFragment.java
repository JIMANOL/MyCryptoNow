package com.example.mycryptonow.ui.agregar;

import androidx.activity.OnBackPressedCallback;
import androidx.core.content.FileProvider;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mycryptonow.R;
import com.example.mycryptonow.models.Datum;
import com.example.mycryptonow.ui.listamiscryptos.ListaMisCryptosFragment;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.File;
import java.util.Calendar;
import java.util.UUID;

public class AgregarFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private AgregarViewModel mViewModel;
    private ImageView ivQR;
    private EditText etDireccion,etCantidad,etNombre;
    private Spinner spnTipoCryp;
    private Button btnAgregar,btnRegresar;


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

        EditTextComponentes(root);
        ButtonComponentes(root);
        SpinnerComponentes(root);

    }
    private void EditTextComponentes(View root){
        etDireccion=root.findViewById(R.id.etdireccionAg);
        etNombre=root.findViewById(R.id.etnombreAg);
        etCantidad=root.findViewById(R.id.etcantidadAg);
    }
    private void ButtonComponentes(View root){

        ivQR=root.findViewById(R.id.ivcQRNP);
        btnAgregar=root.findViewById(R.id.btnagregarAg);
        btnRegresar=root.findViewById(R.id.btnregresarAg);
        btnAgregar.setOnClickListener( this);
        btnRegresar.setOnClickListener(this);
        ivQR.setOnClickListener(this);

    }
    private void SpinnerComponentes(View root){
        ArrayAdapter<CharSequence> tipoCryptAdapter;

        tipoCryptAdapter=ArrayAdapter.createFromResource(getContext(),R.array.tipo, android.R.layout.simple_spinner_item);


        spnTipoCryp=root.findViewById(R.id.spntipoAg);
        spnTipoCryp.setAdapter(tipoCryptAdapter);


        spnTipoCryp.setOnItemSelectedListener(this);


    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ivcQRNP:


                IntentIntegrator.forSupportFragment(this).initiateScan();


                break;
            case R.id.btnagregarAg:

                break;

            case R.id.btnregresarAg:

                break;


        }


    }

    public void onActivityResult(int requestCode, int resultCode,Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(getContext(), "Cancelled", Toast.LENGTH_LONG).show();
            } else {

                Toast.makeText(getContext(), "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                etDireccion.setText(result.getContents());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AgregarViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}