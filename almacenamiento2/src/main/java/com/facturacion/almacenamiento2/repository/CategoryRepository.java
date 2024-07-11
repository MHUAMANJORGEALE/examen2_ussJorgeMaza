// Se comunica con la base de datos

package com.facturacion.almacenamiento2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.facturacion.almacenamiento2.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{
	public Category findByNombre(String nombre);
	public List<Category> findByNombreContaining (String nombre);
}
