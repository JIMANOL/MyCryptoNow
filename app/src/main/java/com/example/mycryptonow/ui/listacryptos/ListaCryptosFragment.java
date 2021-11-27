package com.example.mycryptonow.ui.listacryptos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.example.mycryptonow.R;
import com.example.mycryptonow.models.Datum;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListaCryptosFragment extends Fragment {

    private ListaCryptosViewModel mViewModel;
    private RecyclerView recyclerView;
    private ListaCryptosAdapter adapter = new ListaCryptosAdapter();
    private LottieAnimationView cargarAnimacion;
    private LinearLayout lyCorreoConfirmado;
    private LinearLayout lyNoCorreoConfirmado;
    private Button btnVerificar;

    public static ListaCryptosFragment newInstance() {
        return new ListaCryptosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_lista_cryptos, container, false);

        recyclerView =  root.findViewById(R.id.rvListaCryptos);
        cargarAnimacion = root.findViewById(R.id.animacionCargarLista);
        lyCorreoConfirmado = root.findViewById(R.id.lyInformacionVerificado);
        lyNoCorreoConfirmado= root.findViewById(R.id.lyInformacionNoVerificado);
        btnVerificar = root.findViewById(R.id.btnVerificarListaCryptos);

        btnVerificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.verificarCorreo(getActivity());
            }
        });

        Log.d("hola",String.valueOf(FirebaseAuth.getInstance().getCurrentUser().isEmailVerified()));
        if(FirebaseAuth.getInstance().getCurrentUser().isEmailVerified()){
            lyCorreoConfirmado.setVisibility(View.VISIBLE);
        }else{
            lyNoCorreoConfirmado.setVisibility(View.VISIBLE);
        }
        initRecyclerView();

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ListaCryptosViewModel.class);
        mViewModel.getInfo().observe(getViewLifecycleOwner(), new Observer<ArrayList<ArrayList<Datum>>>() {
            @Override
            public void onChanged(ArrayList<ArrayList<Datum>> arrayLists) {
                adapter.setData(arrayLists);
                recyclerView.setAdapter(adapter);
                cargarAnimacion.setVisibility(View.GONE);
            }
        });
        mViewModel.buscarCryptos(getActivity(), cargarAnimacion);

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (getActivity() != null){
                    mViewModel.buscarCryptos(getActivity(), cargarAnimacion);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("DataBase Realtime", "loadPost:onCancelled", databaseError.toException());
            }
        };

        FirebaseDatabase.getInstance().getReference().addValueEventListener(postListener);
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
    }

}