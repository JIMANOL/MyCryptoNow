package com.example.mycryptonow.ui.listamiscryptos;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mycryptonow.R;
import com.example.mycryptonow.models.Datum;
import com.example.mycryptonow.ui.agregar.AgregarFragment;
import com.example.mycryptonow.ui.listacryptos.ListaCryptosAdapter;
import com.example.mycryptonow.ui.listacryptos.ListaCryptosFragment;
import com.example.mycryptonow.ui.listacryptos.ListaCryptosViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListaMisCryptosFragment extends Fragment {

    private ListarMisCryptosViewModel mViewModel;
    private FloatingActionButton btnAgregarCrypto;
    private RecyclerView recyclerView;
    //private ListaMisCryptosAdapter adapter = new ListaMisCryptosAdapter();


    public static ListaMisCryptosFragment newInstance() {
        return new ListaMisCryptosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root =inflater.inflate(R.layout.fragment_lista_mis_cryptos, container, false);

        initComponents(root);


        recyclerView=(RecyclerView) root.findViewById(R.id.RecyclerId);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        llenarLista();


        //ListaMisCryptosAdapter adapter=new ListaMisCryptosAdapter(listaMisCryptos);
        //recyclerView.setAdapter(adapter);



        return root;
    }

    private void llenarLista() {

        //listaMisCryptos.add(new ListaMisCryptos("direccion",5, "cartera","bitcoin"));

    }

    private void initComponents(View root) {

        btnAgregarCrypto = root.findViewById(R.id.btnAgregarCrypto);
        btnAgregarCrypto.setVisibility(View.VISIBLE);

        btnAgregarCrypto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Datum dato = new Datum();
                dato.setId(20);
                Log.d("Paso dato",String.valueOf(dato.getId()));
                Bundle bundle = new Bundle();
                bundle.putSerializable("hola", dato);
                Navigation.findNavController(view).navigate(R.id.nav_agrega,bundle);
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ListarMisCryptosViewModel.class);
        // TODO: Use the ViewModel
    }

}