// Se comunica con la base de datos

package com.facturacion.almacenamiento2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.facturacion.almacenamiento2.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
	public Product findByNombre(String nombre);
	public List<Product> findByNombreContaining (String nombre);
}
