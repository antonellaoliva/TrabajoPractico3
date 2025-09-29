package com.example.trabajopractico3.ui.gallery;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.trabajopractico3.MainActivity;
import com.example.trabajopractico3.model.Producto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GalleryViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Producto>> mLista;

    public LiveData<ArrayList<Producto>> getMLista() {
        if (mLista == null) {
            mLista = new MutableLiveData<>();
        }
        return mLista;
    }

    public void cargarLista() {
        if (mLista == null) mLista = new MutableLiveData<>();

        ArrayList<Producto> listaOrdenada = new ArrayList<>(MainActivity.stock);
        Collections.sort(listaOrdenada, Comparator.comparing(Producto::getDescripcion));

        mLista.setValue(listaOrdenada);
    }

    public boolean agregarProducto(Producto p) {
        for (Producto prod : MainActivity.stock) {
            if (prod.getCodigo().equals(p.getCodigo())) {
                return false;
            }
        }
        MainActivity.stock.add(p);
        cargarLista();
        return true;
    }
}
