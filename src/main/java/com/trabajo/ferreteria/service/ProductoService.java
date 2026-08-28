package com.trabajo.ferreteria.service;

import com.trabajo.ferreteria.models.Producto;
import com.trabajo.ferreteria.repository.IProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService implements IProductoService {

    private final IProductoRepository productoRepository;

    public ProductoService(IProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public List<Producto> obtenerProductos() {
        return productoRepository.findAll();
    }

    @Override
    public Producto obtenerProductoPorId(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Override
    public Producto crearProducto(Producto producto) {
        // Validación
        if (producto == null) {
            return null;
        }
        // El id se genera automáticamente en la BD y se devuelve junto con el producto guardado
        return productoRepository.save(producto);
    }

    @Override
    public Producto editarProducto(Long id, Producto productoActualizado) {
        // 1. Buscar si el producto existe
        Producto productoExistente = productoRepository.findById(id).orElse(null);

        // 2. Validar que exista antes de intentar modificarlo
        if (productoExistente == null) {
            return null;
        }

        // 3. Copiar los campos nuevos sobre el producto existente,
        // solo si vienen con valor (así no se pisan con null los que no se mandaron)
        if (productoActualizado.getNombre() != null) {
            productoExistente.setNombre(productoActualizado.getNombre());
        }
        if (productoActualizado.getMarca() != null) {
            productoExistente.setMarca(productoActualizado.getMarca());
        }
        if (productoActualizado.getCategoria() != null) {
            productoExistente.setCategoria(productoActualizado.getCategoria());
        }
        if (productoActualizado.getPrecio() != null) {
            productoExistente.setPrecio(productoActualizado.getPrecio());
        }
        if (productoActualizado.getStock() != 0) {
            productoExistente.setStock(productoActualizado.getStock());
        }
        if (productoActualizado.getDescripcion() != null) {
            productoExistente.setDescripcion(productoActualizado.getDescripcion());
        }

        // 4. Guardar los cambios
        return productoRepository.save(productoExistente);
    }

    @Override
    public boolean eliminarProducto(Long id) {
        // 1. Buscar si el producto existe
        Producto productoExistente = productoRepository.findById(id).orElse(null);

        // 2. Validar que exista antes de borrarlo
        if (productoExistente == null) {
            return false;
        }

        // 3. Eliminar el producto encontrado
        productoRepository.delete(productoExistente);
        return true;
    }
}