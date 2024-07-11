package com.facturacion.almacenamiento2.service;


import java.util.List;

import com.facturacion.almacenamiento2.entity.Category;

public interface CategoryService {
	public List<Category> findAll();
	public Category findById(int id);
	public Category findByNombre(String nombre);
	public List<Category> findByNombreContaining(String name);
	public Category create(Category obj);
	public Category update(Category obj);
	public int delete(int id);
}