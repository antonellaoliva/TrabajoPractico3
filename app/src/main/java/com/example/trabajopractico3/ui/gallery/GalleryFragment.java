package com.example.trabajopractico3.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.trabajopractico3.databinding.FragmentGalleryBinding;
import com.example.trabajopractico3.ui.ListaAdapter;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;
    private GalleryViewModel mv;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        mv = new ViewModelProvider(this).get(GalleryViewModel.class);

        // RecyclerView
        mv.getMLista().observe(getViewLifecycleOwner(), productos -> {
            ListaAdapter la = new ListaAdapter(productos, getLayoutInflater(), getContext());
            binding.rvLista.setLayoutManager(new GridLayoutManager(getContext(), 1));
            binding.rvLista.setAdapter(la);
        });

        mv.getMensaje().observe(getViewLifecycleOwner(), msg ->
                Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show()
        );

        mv.getProductoAgregado().observe(getViewLifecycleOwner(), agregado -> {
            binding.etCodigo.setText("");
            binding.etDescripcion.setText("");
            binding.etPrecio.setText("");
        });

        binding.btnAgregar.setOnClickListener(v -> {
            String codigo = binding.etCodigo.getText().toString();
            String descripcion = binding.etDescripcion.getText().toString();
            String strPrecio = binding.etPrecio.getText().toString();
            mv.agregarProducto(codigo, descripcion, strPrecio);
        });

        mv.cargarLista();

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}


