package com.trabajo.ferreteria.service;

import com.trabajo.ferreteria.models.Producto;

import java.util.List;

public interface IProductoService {

    // Métodos para el CRUD

    // READ
    List<Producto> obtenerProductos();
    Producto obtenerProductoPorId(Long id);

    // CREATE
    Producto crearProducto(Producto producto);

    // UPDATE
    Producto editarProducto(Long id, Producto producto);

    // DELETE
    boolean eliminarProducto(Long id);
}