package com.example.mycryptonow.ui.listamiscryptos;

import androidx.lifecycle.Observer;
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

import com.example.mycryptonow.R;
import com.example.mycryptonow.models.Datum;
import com.example.mycryptonow.models.MiCrypto;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListaMisCryptosFragment extends Fragment {

    private ListarMisCryptosViewModel mViewModel;
    private FloatingActionButton btnAgregarCrypto;
    private RecyclerView recyclerView;
    private ListaMisCryptosAdapter adapter = new ListaMisCryptosAdapter();
    private ArrayList<MiCrypto> listaMiCryptos = new ArrayList<>();


    public static ListaMisCryptosFragment newInstance() {
        return new ListaMisCryptosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root =inflater.inflate(R.layout.fragment_lista_mis_cryptos, container, false);

        initComponents(root);
        initRecyclerView();
        adapter.setDatosLista(listaMiCryptos);
        adapter.setModelo(mViewModel);
        recyclerView.setAdapter(adapter);
        mViewModel.buscarMisCryptos(getActivity());


        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                mViewModel.buscarMisCryptos(getActivity());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("DataBase Realtime", "loadPost:onCancelled", databaseError.toException());
            }
        };

        FirebaseDatabase.getInstance().getReference().addValueEventListener(postListener);

        return root;
    }


    private void initComponents(View root) {

        btnAgregarCrypto = root.findViewById(R.id.btnAgregarCrypto);
        btnAgregarCrypto.setVisibility(View.VISIBLE);
        recyclerView= root.findViewById(R.id.rvListaMiscryptos);
        mViewModel = new ViewModelProvider(this).get(ListarMisCryptosViewModel.class);


        mViewModel.getMisCrptos().observe(getActivity(), new Observer<ArrayList<MiCrypto>>() {
            @Override
            public void onChanged(ArrayList<MiCrypto> miCryptos) {
                adapter.setDatosLista(miCryptos);
                //recyclerView.setAdapter(adapter);
            }
        });


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

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
    }

}