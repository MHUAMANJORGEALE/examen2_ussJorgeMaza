package com.facturacion.almacenamiento2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.facturacion.almacenamiento2.entity.Client;
import com.facturacion.almacenamiento2.service.ClientService;

@RestController
@RequestMapping("/api/clientes")
public class ClientController {

    @Autowired
    private ClientService service;

    @GetMapping()
    public ResponseEntity<List<Client>> getAll() {
        List<Client> clientes = service.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(clientes);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Client> getById(@PathVariable("id") int id) {
        Client cliente = service.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(cliente);
    }

    @PostMapping
    public ResponseEntity<Client> create(@RequestBody Client cliente) {
        Client createdCliente = service.create(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCliente);
    }

    @PutMapping
    public ResponseEntity<Client> update(@RequestBody Client cliente) {
        Client updatedCliente = service.update(cliente);
        return ResponseEntity.status(HttpStatus.OK).body(updatedCliente);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
