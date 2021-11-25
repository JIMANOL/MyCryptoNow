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

import com.airbnb.lottie.LottieAnimationView;
import com.example.mycryptonow.R;
import com.example.mycryptonow.models.Datum;
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

    public static ListaCryptosFragment newInstance() {
        return new ListaCryptosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_lista_cryptos, container, false);

        recyclerView =  root.findViewById(R.id.rvListaCryptos);
        cargarAnimacion = root.findViewById(R.id.animacionCargarLista);
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
                mViewModel.buscarCryptos(getActivity(), cargarAnimacion);

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