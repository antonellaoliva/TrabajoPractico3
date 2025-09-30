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
    private MutableLiveData<String> mensaje;
    private MutableLiveData<Boolean> productoAgregado;

    public LiveData<ArrayList<Producto>> getMLista() {
        if (mLista == null) mLista = new MutableLiveData<>();
        return mLista;
    }

    public LiveData<String> getMensaje() {
        if (mensaje == null) mensaje = new MutableLiveData<>();
        return mensaje;
    }

    public LiveData<Boolean> getProductoAgregado() {
        if (productoAgregado == null) productoAgregado = new MutableLiveData<>();
        return productoAgregado;
    }

    public void cargarLista() {
        ArrayList<Producto> copia = new ArrayList<>(MainActivity.stock);
        Collections.sort(copia, Comparator.comparing(Producto::getDescripcion));
        mLista.setValue(copia);
    }

    public void agregarProducto(String codigo, String descripcion, String strPrecio) {
        if (codigo.isEmpty() || descripcion.isEmpty() || strPrecio.isEmpty()) {
            mensaje.setValue("Complete todos los campos");
            return;
        }
        try {
            double precio = Double.parseDouble(strPrecio);
            for (Producto p : MainActivity.stock) {
                if (p.getCodigo().equals(codigo)) {
                    mensaje.setValue("Código duplicado");
                    return;
                }
            }
            Producto nuevo = new Producto(codigo, descripcion, precio);
            MainActivity.stock.add(nuevo);
            cargarLista();
            productoAgregado.setValue(true);
            mensaje.setValue("Producto agregado");
        } catch (NumberFormatException e) {
            mensaje.setValue("Precio inválido");
        }
    }
}

