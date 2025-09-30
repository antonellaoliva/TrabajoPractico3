package com.example.trabajopractico3.ui.eliminar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.trabajopractico3.MainActivity;
import com.example.trabajopractico3.model.Producto;

import java.util.Optional;

public class EliminarViewModel extends ViewModel {

    private MutableLiveData<String> codigoInput;
    private MutableLiveData<Producto> productoEncontrado;
    private MutableLiveData<String> mensaje;
    private MutableLiveData<Boolean> productoEliminado;

    public LiveData<String> getCodigoInput() {
        if (codigoInput == null) codigoInput = new MutableLiveData<>("");
        return codigoInput;
    }

    public LiveData<Producto> getProductoEncontrado() {
        if (productoEncontrado == null) productoEncontrado = new MutableLiveData<>(new Producto("", "", 0));
        return productoEncontrado;
    }

    public LiveData<String> getMensaje() {
        if (mensaje == null) mensaje = new MutableLiveData<>("");
        return mensaje;
    }

    public LiveData<Boolean> getProductoEliminado() {
        if (productoEliminado == null) productoEliminado = new MutableLiveData<>(false);
        return productoEliminado;
    }

    public void buscarProducto(String codigo) {
        getCodigoInputMutable().setValue(codigo);

        Optional<Producto> prod = MainActivity.stock.stream()
                .filter(p -> p.getCodigo().equals(codigo))
                .findFirst();

        if (prod.isPresent()) {
            getProductoEncontradoMutable().setValue(prod.get());
            getMensajeMutable().setValue("");
        } else {
            getProductoEncontradoMutable().setValue(new Producto("", "", 0));
            getMensajeMutable().setValue("Producto no encontrado");
        }
        getProductoEliminadoMutable().setValue(false);
    }

    public void eliminarProducto() {
        Producto p = getProductoEncontrado().getValue();
        if (p != null && !p.getCodigo().isEmpty()) {
            MainActivity.stock.removeIf(prod -> prod.getCodigo().equals(p.getCodigo()));
            getMensajeMutable().setValue("Producto eliminado");
            getProductoEncontradoMutable().setValue(new Producto("", "", 0));
            getProductoEliminadoMutable().setValue(true);
        }
    }

    private MutableLiveData<String> getCodigoInputMutable() { return (MutableLiveData<String>) getCodigoInput(); }
    private MutableLiveData<Producto> getProductoEncontradoMutable() { return (MutableLiveData<Producto>) getProductoEncontrado(); }
    private MutableLiveData<String> getMensajeMutable() { return (MutableLiveData<String>) getMensaje(); }
    private MutableLiveData<Boolean> getProductoEliminadoMutable() { return (MutableLiveData<Boolean>) getProductoEliminado(); }
}




