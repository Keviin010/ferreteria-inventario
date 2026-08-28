package com.trabajo.ferreteria.controller;

import com.trabajo.ferreteria.models.Producto;
import com.trabajo.ferreteria.service.IProductoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")

public class ProductoController {

    private static final Logger log = LoggerFactory.getLogger(ProductoController.class);
    private final IProductoService service;

    public ProductoController(IProductoService service) {
        this.service = service;
    }

    //Read
    @GetMapping
    public ResponseEntity<?> obtenerProductos(){
        // Siempre devolvemos 200, aunque la lista esté vacía
        return ResponseEntity.ok(service.obtenerProductos());
    }

    //Read de producto específico
    @GetMapping("/{codPro}")
    public ResponseEntity<?> obtenerProducto(@PathVariable Long codPro){

        Producto producto = service.obtenerProductoPorId(codPro);

        if(producto == null){
            // Si no existe, devolvemos 404 con el body vacío
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("no se encuentra este producto, por favor verifique");
        }

        // Si existe, devolvemos 200 con el producto
        return ResponseEntity.ok(producto);
    }

    //Create
    @PostMapping
    public ResponseEntity<?> crearProducto(@RequestBody Producto producto){

        Producto productoCreado = service.crearProducto(producto);

        if(productoCreado == null){
            // El service devuelve null si el producto recibido era null
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("no se pudo crear el producto, verifique los datos enviados");
        }

        // Si se creó correctamente, devolvemos 201
        return ResponseEntity.status(HttpStatus.CREATED).body(productoCreado);
    }

    //Update
    @PutMapping("/{codPro}")
    public ResponseEntity<?> editarProducto(@PathVariable Long codPro, @RequestBody Producto producto){

        Producto productoActualizado = service.editarProducto(codPro, producto);

        if(productoActualizado == null){
            // El service busca el producto por id y actualiza campo por campo;
            // si devuelve null es porque no encontró el producto
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("no se encuentra este producto, por favor verifique");
        }

        return ResponseEntity.ok(productoActualizado);
    }

    //Delete
    @DeleteMapping("/{codPro}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Long codPro){

        // El service devuelve true si se eliminó, false si no existía
        boolean eliminado = service.eliminarProducto(codPro);

        if(!eliminado){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("no se encuentra este producto, por favor verifique");
        }

        // 204: eliminación exitosa, sin body que devolver
        return ResponseEntity.noContent().build();
    }
}