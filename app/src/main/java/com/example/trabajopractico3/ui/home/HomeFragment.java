package com.example.trabajopractico3.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.trabajopractico3.databinding.FragmentHomeBinding;
import com.example.trabajopractico3.ui.ListaAdapter;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private HomeViewModel mv;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        mv = new ViewModelProvider(this).get(HomeViewModel.class);

        mv.getLista().observe(getViewLifecycleOwner(), productos -> {
            ListaAdapter adapter = new ListaAdapter(productos, getLayoutInflater(), getContext());
            binding.rvLista.setLayoutManager(new GridLayoutManager(getContext(), 1));
            binding.rvLista.setAdapter(adapter);
        });

        mv.cargarProductos();
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

