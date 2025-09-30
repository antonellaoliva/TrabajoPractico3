package com.example.trabajopractico3.ui.eliminar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.trabajopractico3.databinding.FragmentDetalleBinding;

public class DetalleFragment extends Fragment {

    private FragmentDetalleBinding binding;
    private DetalleViewModel mv;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDetalleBinding.inflate(inflater, container, false);
        mv = new ViewModelProvider(this).get(DetalleViewModel.class);

        String codigo = getArguments() != null ? getArguments().getString("codigo") : "";
        mv.cargarProducto(codigo);

        mv.getProducto().observe(getViewLifecycleOwner(), p -> {
            binding.tvCodigo.setText(p != null ? p.getCodigo() : "");
            binding.tvDescripcion.setText(p != null ? p.getDescripcion() : "");
            binding.tvPrecio.setText(p != null ? String.valueOf(p.getPrecio()) : "");
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}



