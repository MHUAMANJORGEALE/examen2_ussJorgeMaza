package com.facturacion.almacenamiento2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.ui.Model;

@Controller
public class MainController {

    @GetMapping("/categorias")
    public String getCategorias(Model model) {
        return "categoria";
    }

    @GetMapping("/clientes")
    public String getClient(Model model) {
        return "client";
    }

    @GetMapping("/pagos")
    public String getPagos(Model model) {
        return "pay"; // Nombre del archivo HTML sin extensión
    }
}
