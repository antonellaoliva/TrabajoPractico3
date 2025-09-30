package com.example.trabajopractico3.ui.eliminar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.trabajopractico3.MainActivity;
import com.example.trabajopractico3.model.Producto;

import java.util.Optional;

public class DetalleViewModel extends ViewModel {

    private MutableLiveData<Producto> producto;

    public LiveData<Producto> getProducto() {
        if (producto == null) producto = new MutableLiveData<>();
        return producto;
    }

    public void cargarProducto(String codigo) {
        Optional<Producto> p = MainActivity.stock.stream()
                .filter(prod -> prod.getCodigo().equals(codigo))
                .findFirst();
        producto.setValue(p.orElse(null));
    }
}




