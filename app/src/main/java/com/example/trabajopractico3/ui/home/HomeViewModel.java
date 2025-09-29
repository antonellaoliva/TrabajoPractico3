package com.example.trabajopractico3.ui.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.trabajopractico3.model.Producto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class HomeViewModel extends AndroidViewModel {

    private MutableLiveData<ArrayList<Producto>> lista;

    public HomeViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<ArrayList<Producto>> getLista() {
        if (lista == null) lista = new MutableLiveData<>();
        return lista;
    }

    public void cargarProductos() {
        if (lista == null) lista = new MutableLiveData<>();


        ArrayList<Producto> copia = new ArrayList<>(com.example.trabajopractico3.MainActivity.stock);
        Collections.sort(copia, Comparator.comparing(Producto::getDescripcion));

        lista.setValue(copia);
    }
}

