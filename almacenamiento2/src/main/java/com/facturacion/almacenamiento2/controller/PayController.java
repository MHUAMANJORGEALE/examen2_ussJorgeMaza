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

import com.facturacion.almacenamiento2.entity.Pay;
import com.facturacion.almacenamiento2.service.PayService;

@RestController
@RequestMapping("/api/pagos")
public class PayController {

    @Autowired
    private PayService service;

    @GetMapping()
    public ResponseEntity<List<Pay>> getAll() {
        List<Pay> pagos = service.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(pagos);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Pay> getById(@PathVariable("id") int id) {
        Pay pago = service.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(pago);
    }

    @GetMapping(value = "/documento/{documentoInquilino}")
    public ResponseEntity<List<Pay>> getByDocumentoInquilino(@PathVariable("documentoInquilino") String documentoInquilino) {
        List<Pay> pagos = service.findByDocumentoInquilino(documentoInquilino);
        return ResponseEntity.status(HttpStatus.OK).body(pagos);
    }

    @GetMapping(value = "/metodo/{metodoPago}")
    public ResponseEntity<List<Pay>> getByMetodoPago(@PathVariable("metodoPago") String metodoPago) {
        List<Pay> pagos = service.findByMetodoPago(metodoPago);
        return ResponseEntity.status(HttpStatus.OK).body(pagos);
    }

    @GetMapping(value = "/descripcion/{descripcion}")
    public ResponseEntity<List<Pay>> getByDescripcionContaining(@PathVariable("descripcion") String descripcion) {
        List<Pay> pagos = service.findByDescripcionContaining(descripcion);
        return ResponseEntity.status(HttpStatus.OK).body(pagos);
    }

    @PostMapping
    public ResponseEntity<Pay> create(@RequestBody Pay pay) {
        Pay createdPay = service.create(pay);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPay);
    }

    @PutMapping
    public ResponseEntity<Pay> update(@RequestBody Pay pay) {
        Pay updatedPay = service.update(pay);
        return ResponseEntity.status(HttpStatus.OK).body(updatedPay);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
