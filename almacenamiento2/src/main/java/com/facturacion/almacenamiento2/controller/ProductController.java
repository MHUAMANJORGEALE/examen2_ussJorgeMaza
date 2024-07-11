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

import com.facturacion.almacenamiento2.entity.Product;
import com.facturacion.almacenamiento2.service.ProductService;

@RestController
@RequestMapping("/api/productos")
public class ProductController {
	@Autowired
	private ProductService service;
	
	@GetMapping()
	public ResponseEntity<List<Product>> getAll(){
		List<Product> productos = service.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(productos);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Product> getById(@PathVariable("id") int id){
		Product productos = service.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(productos);
	}
	
	@PostMapping
	public ResponseEntity<Product> create(@RequestBody Product producto){
		Product productos = service.create(producto);
		return ResponseEntity.status(HttpStatus.CREATED).body(productos);
	}
	
	@PutMapping
	public ResponseEntity<Product> update(@RequestBody Product producto){
		Product productos = service.update(producto);
		return ResponseEntity.status(HttpStatus.CREATED).body(productos);
	}
	
	@DeleteMapping(value="/{id}")
	public int delete(@PathVariable("id") int id) {
		return service.delete(id);
	}
	
}
