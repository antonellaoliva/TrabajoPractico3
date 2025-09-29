package com.example.trabajopractico3.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabajopractico3.R;
import com.example.trabajopractico3.model.Producto;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListaAdapter extends RecyclerView.Adapter<ListaAdapter.ViewHolder> {

    private ArrayList<Producto> lista;
    private LayoutInflater inflater;
    private DecimalFormat formatoPrecio = new DecimalFormat("#,##0.00");

    public ListaAdapter(ArrayList<Producto> lista, LayoutInflater inflater, Context context) {

        Collections.sort(lista, Comparator.comparing(Producto::getDescripcion, String.CASE_INSENSITIVE_ORDER));
        this.lista = lista;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = inflater.inflate(R.layout.item, parent, false);
        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (lista.isEmpty()) {
            holder.tvCodigo.setText("");
            holder.tvDescripcion.setText(R.string.no_hay_productos_disponibles);
            holder.tvPrecio.setText("");
        } else {
            Producto p = lista.get(position);
            holder.tvCodigo.setText(p.getCodigo());
            holder.tvDescripcion.setText(p.getDescripcion());
            holder.tvPrecio.setText("$ " + formatoPrecio.format(p.getPrecio()));
        }
    }

    @Override
    public int getItemCount() {
        return lista.isEmpty() ? 1 : lista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCodigo, tvDescripcion, tvPrecio;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCodigo = itemView.findViewById(R.id.tvCodigo);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
            tvPrecio = itemView.findViewById(R.id.tvPrecio);
        }
    }
}


