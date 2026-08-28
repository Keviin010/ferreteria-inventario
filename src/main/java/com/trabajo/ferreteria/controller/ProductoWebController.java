package com.trabajo.ferreteria.controller;

import com.trabajo.ferreteria.models.Producto;
import com.trabajo.ferreteria.service.IProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/productos")
public class ProductoWebController {

    private final IProductoService service;

    public ProductoWebController(IProductoService service) {
        this.service = service;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("productos", service.obtenerProductos());
        return "producto/lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("producto", new Producto());
        return "producto/formulario";
    }

    @GetMapping("/editar/{codproducto}")
    public String editar(@PathVariable Long codproducto, Model model) {
        model.addAttribute("producto", service.obtenerProductoPorId(codproducto));
        return "producto/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Producto producto, RedirectAttributes attrs) {
        if (producto.getCodproducto() == null) {
            service.crearProducto(producto);
        } else {
            service.editarProducto(producto.getCodproducto(), producto);
        }
        attrs.addFlashAttribute("mensaje", "Producto guardado correctamente.");
        return "redirect:/productos";
    }

    @GetMapping("/eliminar/{codproducto}")
    public String eliminar(@PathVariable Long codproducto, RedirectAttributes attrs) {
        service.eliminarProducto(codproducto);
        attrs.addFlashAttribute("mensaje", "Producto eliminado.");
        return "redirect:/productos";
    }
}