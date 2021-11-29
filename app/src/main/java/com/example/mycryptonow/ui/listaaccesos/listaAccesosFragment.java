package com.example.mycryptonow.ui.listaaccesos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mycryptonow.R;
import com.example.mycryptonow.models.Ingresos;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;



public class listaAccesosFragment extends Fragment implements View.OnClickListener{

    private ListaAccesosViewModel mViewModel;

    public static listaAccesosFragment newInstance() {
        return new listaAccesosFragment();
    }

   // private ListarViewModel listarViewModel;
    private ListaAccesosViewModel listarViewModel;

    static ArrayList<Ingresos> Accesos = new ArrayList<Ingresos>();
    static ArrayList<String> ListaAccesos = new ArrayList<String>();
    //public firebase FireBase = new firebase();


    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ListView list;

    public void iniciarFirebase(Context contexto) {
        FirebaseApp.initializeApp(contexto);
        this.firebaseDatabase = FirebaseDatabase.getInstance();
        this.databaseReference = firebaseDatabase.getReference();
        //Toast.makeText(contexto,"test: "+databaseReference.toString(),Toast.LENGTH_LONG).show();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ///return inflater.inflate(R.layout.lista_accesos_fragment, container, false);
        listarViewModel =
                new ViewModelProvider(this).get(ListaAccesosViewModel.class);
        View root = inflater.inflate(R.layout.lista_accesos_fragment, container, false);
        //final TextView textView = root.findViewById(R.id.text_slideshow);
        listarViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });
        iniciarFirebase(this.getContext());
        list=root.findViewById(R.id.lvListarAccesos);
        System.out.println("-->"+databaseReference.toString());

        ////////////////////////////////LISTAR ACCESOS////////////////////////////////////////////
        databaseReference.child("informacion_ingresos").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot){
                ListaAccesos.clear();
                Accesos.clear();
                for(DataSnapshot objSnapshot: snapshot.getChildren()){
                    System.out.println("-->" + FirebaseAuth.getInstance().getCurrentUser().getUid()+"=="+objSnapshot.getKey());

                    if(FirebaseAuth.getInstance().getCurrentUser().getUid().toString().equals(objSnapshot.getKey().toString())){
                    for(DataSnapshot objSnapshot2: objSnapshot.getChildren()){
                        Ingresos acceso = new Ingresos();
                        acceso.fromSnapshot(objSnapshot2);
                        ListaAccesos.add("(" + acceso.getFecha() + ") ");
                        Accesos.add(acceso);
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, ListaAccesos);
                        list.setAdapter(adapter);
                    }
                    }
                }

            }

            public void onCancelled(@NonNull DatabaseError error){}

        });
        ////////////////////////////////////////////////////////////////////////////

        /*if(registros.isEmpty()){
            sinRegistros.add("Sin Registros");
            ArrayAdapter<String> adapter = new  ArrayAdapter<String> (getContext(), android.R.layout.simple_list_item_1, sinRegistros);
            list.setAdapter(adapter);
        }else {
            ArrayAdapter<alumno_MB> adapter = new ArrayAdapter<alumno_MB>(getContext(), android.R.layout.simple_list_item_1, registros);
            list.setAdapter(adapter);
        }*/
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                View dialogView=LayoutInflater.from(getContext()).inflate(R.layout.cuadro_dialogo,null);
                String datos =
                        "ID: \n"+Accesos.get(i).getId()+"\n";
                datos+="FECHA: \n"+Accesos.get(i).getFecha()+"\n";
                datos+="DISPOSITIVO: \n"+Accesos.get(i).getDispositivo()+"\n";
                datos+="UBICACIÓN: \n"+Accesos.get(i).getDireccion()+"\n";
                ((TextView)dialogView.findViewById(R.id.tvpInfoDetallada)).setText(datos);
                AlertDialog.Builder dialogo=new  AlertDialog.Builder(getContext());
                dialogo.setTitle("Datos del Acceso");
                dialogo.setView(dialogView);
                dialogo.setPositiveButton("Aceptar",null);
                dialogo.show();

            }
        });


        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ListaAccesosViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onClick(View view) {

    }
}