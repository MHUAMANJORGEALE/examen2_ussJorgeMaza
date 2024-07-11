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

import com.facturacion.almacenamiento2.entity.Category;
import com.facturacion.almacenamiento2.service.CategoryService;

@RestController
@RequestMapping("/api/categorias")
public class CategoryController {
	@Autowired
	private CategoryService service;
	
	@GetMapping()
	public ResponseEntity<List<Category>> getAll(){
		List<Category> categorias = service.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(categorias);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Category> getById(@PathVariable("id") int id){
		Category category = service.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(category);
	}
	
	@PostMapping
	public ResponseEntity<Category> create(@RequestBody Category category){
		Category categoryDB = service.create(category);
		return ResponseEntity.status(HttpStatus.CREATED).body(categoryDB);
	}
	
	@PutMapping
	public ResponseEntity<Category> update(@RequestBody Category category){
		Category categoryDB = service.update(category);
		return ResponseEntity.status(HttpStatus.CREATED).body(categoryDB);
	}
	
	@DeleteMapping(value="/{id}")
	public int delete(@PathVariable("id") int id) {
		return service.delete(id);
	}
	
	
}