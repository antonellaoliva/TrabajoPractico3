package com.example.trabajopractico3.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.trabajopractico3.databinding.FragmentGalleryBinding;
import com.example.trabajopractico3.model.Producto;
import com.example.trabajopractico3.ui.ListaAdapter;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;
    private GalleryViewModel mv;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        mv = new ViewModelProvider(this).get(GalleryViewModel.class);
        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mv.getMLista().observe(getViewLifecycleOwner(), new Observer<ArrayList<Producto>>() {
            @Override
            public void onChanged(ArrayList<Producto> productos) {
                ListaAdapter la = new ListaAdapter(productos, getLayoutInflater(), getContext());
                binding.rvLista.setLayoutManager(new GridLayoutManager(getContext(), 1));
                binding.rvLista.setAdapter(la);
            }
        });

        binding.btnAgregar.setOnClickListener(v -> {
            String codigo = binding.etCodigo.getText().toString().trim();
            String descripcion = binding.etDescripcion.getText().toString().trim();
            String strPrecio = binding.etPrecio.getText().toString().trim();

            if (codigo.isEmpty() || descripcion.isEmpty() || strPrecio.isEmpty()) {
                Toast.makeText(getContext(), "Complete todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                double precio = Double.parseDouble(strPrecio);
                Producto p = new Producto(codigo, descripcion, precio);
                mv.agregarProducto(p);

                Toast.makeText(getContext(), "Producto agregado", Toast.LENGTH_SHORT).show();

                binding.etCodigo.setText("");
                binding.etDescripcion.setText("");
                binding.etPrecio.setText("");

            } catch (NumberFormatException e) {
                Toast.makeText(getContext(), "Precio inválido", Toast.LENGTH_SHORT).show();
            }
        });

        mv.cargarLista();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
