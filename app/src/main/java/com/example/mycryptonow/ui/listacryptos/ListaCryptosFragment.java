package com.example.mycryptonow.ui.listacryptos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mycryptonow.R;
import com.example.mycryptonow.models.Datum;

import java.util.ArrayList;

public class ListaCryptosFragment extends Fragment {

    private ListaCryptosViewModel mViewModel;
    private RecyclerView recyclerView;
    private ListaCryptosAdapter adapter = new ListaCryptosAdapter();

    public static ListaCryptosFragment newInstance() {
        return new ListaCryptosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_lista_cryptos, container, false);

        recyclerView =  root.findViewById(R.id.rvListaCryptos);
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
            }
        });
        mViewModel.buscarCryptos(getActivity());
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
    }

}