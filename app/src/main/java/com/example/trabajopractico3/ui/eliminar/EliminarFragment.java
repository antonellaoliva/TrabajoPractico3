package com.example.trabajopractico3.ui.eliminar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.trabajopractico3.databinding.FragmentEliminarBinding;

public class EliminarFragment extends Fragment {

    private FragmentEliminarBinding binding;
    private EliminarViewModel mv;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentEliminarBinding.inflate(inflater, container, false);
        mv = new ViewModelProvider(this).get(EliminarViewModel.class);

        mv.getProductoEncontrado().observe(getViewLifecycleOwner(), producto -> {
            binding.tvCodigo.setText(producto.getCodigo());
            binding.tvDescripcion.setText(producto.getDescripcion());
            binding.tvPrecio.setText(String.valueOf(producto.getPrecio()));
        });

        mv.getMensaje().observe(getViewLifecycleOwner(),
                mensaje -> Toast.makeText(getContext(), mensaje, Toast.LENGTH_SHORT).show());

        binding.btnBuscar.setOnClickListener(v -> mv.buscarProducto(binding.etCodigo.getText().toString()));

        binding.btnEliminar.setOnClickListener(v -> mv.eliminarProducto());

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}




