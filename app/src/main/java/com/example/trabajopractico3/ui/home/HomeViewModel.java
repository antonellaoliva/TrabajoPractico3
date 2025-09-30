package com.example.trabajopractico3.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.trabajopractico3.MainActivity;
import com.example.trabajopractico3.model.Producto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Producto>> lista;

    public LiveData<ArrayList<Producto>> getLista() {
        if (lista == null) lista = new MutableLiveData<>();
        return lista;
    }

    public void cargarProductos() {
        ArrayList<Producto> copia = new ArrayList<>(MainActivity.stock);
        Collections.sort(copia, Comparator.comparing(Producto::getDescripcion));
        lista.setValue(copia);
    }
}

