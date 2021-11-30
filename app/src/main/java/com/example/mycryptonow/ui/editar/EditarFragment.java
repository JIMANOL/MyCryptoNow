package com.example.mycryptonow.ui.editar;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mycryptonow.R;
import com.example.mycryptonow.db.Realtime;
import com.example.mycryptonow.models.MiCrypto;
import com.example.mycryptonow.ui.agregar.AgregarViewModel;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class EditarFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private EditarViewModel mViewModel;
    private ImageView ivQR;
    private EditText etDireccion,etCantidad,etNombre;
    private Spinner spnTipoCryp;
    private Button btnEditar;
    public static String t;
    private TextView tvMensajedireccion,tvMensajecantidad,tvMensajenombre;
    Realtime database;
    private EditarViewModel modelo;
    private MiCrypto miCrypto;

    public static EditarFragment newInstance() {
        return new EditarFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_editar, container, false);

        iniciarCompoentes(root);
        miCrypto=(MiCrypto) getArguments().getSerializable("hola");
        insertarDatos();

        return root;
    }

    private void insertarDatos() {
        etDireccion.setText(miCrypto.getDireccionWallet());
        etCantidad.setText(miCrypto.getCantidadCryptos());
        etNombre.setText(miCrypto.getNombreBilletera());

        if(miCrypto.getNombreCrypto().equals("Bitcoin")){
            spnTipoCryp.setSelection(1);
        }
        if(miCrypto.getNombreCrypto().equals("Ethereum")){
            spnTipoCryp.setSelection(2);
        }
        if(miCrypto.getNombreCrypto().equals("Litecoin")){
            spnTipoCryp.setSelection(3);
        }
        if(miCrypto.getNombreCrypto().equals("NEO")){
            spnTipoCryp.setSelection(4);
        }else{
            spnTipoCryp.setSelection(0);
        }


    }

    private void iniciarCompoentes(View root) {

        EditTextComponentes(root);
        ButtonComponentes(root);
        SpinnerComponentes(root);

        modelo = new ViewModelProvider(this).get(EditarViewModel.class);
        modelo.getCrypto().observe(getActivity(), new Observer() {
            @Override
            public void onChanged(Object o) {
                if(o!=null){
                    mostrarMensaje("Se actualizo correctamente");
                }else{
                    mostrarMensaje("Ocurrio un error");
                }
            }
        });


    }
    private void EditTextComponentes(View root){
        etDireccion=root.findViewById(R.id.etdireccionEd);
        etNombre=root.findViewById(R.id.etnombreEd);
        etCantidad=root.findViewById(R.id.etcantidadEd);
        /*tvMensajedireccion=root.findViewById(R.id.tvMensajedirec);
        tvMensajecantidad=root.findViewById(R.id.tvMensajecant);
        tvMensajenombre=root.findViewById(R.id.tvMensajenom);*/
    }
    private void ButtonComponentes(View root){

        ivQR=root.findViewById(R.id.ivcQRED);
        btnEditar=root.findViewById(R.id.btnaeditarEd);
        btnEditar.setOnClickListener( this);
        ivQR.setOnClickListener(this);

    }
    private void SpinnerComponentes(View root){
        ArrayAdapter<CharSequence> tipoCryptAdapter;

        tipoCryptAdapter=ArrayAdapter.createFromResource(getContext(),R.array.tipo, android.R.layout.simple_spinner_item);


        spnTipoCryp=root.findViewById(R.id.spntipoEd);
        spnTipoCryp.setAdapter(tipoCryptAdapter);


        spnTipoCryp.setOnItemSelectedListener(this);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ivcQRED:


                IntentIntegrator.forSupportFragment(this).initiateScan();


                break;
            case R.id.btnaeditarEd:
                validarInformacion();
                break;




        }

    }

    private void validarInformacion() {
        String direccionWallet;
        String cantidadCryptos;
        String nombreBilletera;
        String nombreCrypto=t;
        boolean ban=true;

        direccionWallet = etDireccion.getText().toString().trim();

        cantidadCryptos = etCantidad.getText().toString().trim();
        if(!cantidadCryptos.matches("[0-9]+")){
            ban=false;
            //tvMensajecantidad.setVisibility(View.VISIBLE);
        }else{
            //tvMensajecantidad.setVisibility(View.GONE);
        }
        nombreBilletera = etNombre.getText().toString().trim();
        if(!nombreBilletera.matches("[A-z]+")){
            ban=false;
            //tvMensajenombre.setVisibility(View.VISIBLE);
        }else{
            //tvMensajenombre.setVisibility(View.GONE);
        }





        if(direccionWallet.equals("") ||
                cantidadCryptos.equals("") ||
                nombreBilletera.equals("") ||
                nombreCrypto.equals("")){
            mostrarMensaje("Ingrese todos los campos solicitados, por favor.");
        }else{
            if(ban){

                miCrypto.setDireccionWallet(direccionWallet);
                miCrypto.setCantidadCryptos(cantidadCryptos);
                miCrypto.setNombreBilletera(nombreBilletera);
                miCrypto.setNombreCrypto(nombreCrypto);

                modelo.editarCrypto(miCrypto);
                limpiardatos();
            }else{
                mostrarMensaje("Campos incorrectos, por favor verifique su informacion");
            }
        }



    }
    private void limpiardatos() {
        etDireccion.setText("");
        etNombre.setText("");
        etCantidad.setText("");

        ArrayAdapter<CharSequence> tipoCryptAdapter = ArrayAdapter.createFromResource(getContext(), R.array.tipo, android.R.layout.simple_spinner_item);
        t="";
        spnTipoCryp.setAdapter(tipoCryptAdapter);

    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
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

    private void mostrarMensaje(String mensaje) {
        Toast toast = Toast.makeText(getContext(),mensaje,Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.spntipoEd:
                if(position!=0){
                    t=parent.getItemAtPosition(position).toString();
                }else{
                    t="";
                }
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}