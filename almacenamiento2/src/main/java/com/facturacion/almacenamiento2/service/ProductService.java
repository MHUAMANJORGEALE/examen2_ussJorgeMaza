package com.facturacion.almacenamiento2.service;

import java.util.List;

import com.facturacion.almacenamiento2.entity.Product;

public interface ProductService {
	public List<Product> findAll();
	public Product findById(int id);
	public Product findByNombre(String nombre);
	public List<Product> findByNombreContaining(String name);
	public Product create(Product obj);
	public Product update(Product obj);
	public int delete(int id);
}