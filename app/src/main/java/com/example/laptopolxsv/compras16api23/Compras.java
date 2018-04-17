package com.example.laptopolxsv.compras16api23;

import java.text.DecimalFormat;

/**
 * Created by laptopolxsv on 10/4/2018.
 */

public class Compras {
    private Productos producto;
    private boolean estado;

    public Compras(Productos producto, boolean estado) {
        this.producto = producto;
        this.estado = estado;
    }

    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        DecimalFormat d=new DecimalFormat("0.00");
        return producto.getNombre()+"\n"+d.format(producto.getPrecio());
    }
}
