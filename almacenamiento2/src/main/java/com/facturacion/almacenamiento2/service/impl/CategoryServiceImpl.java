package com.facturacion.almacenamiento2.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.facturacion.almacenamiento2.entity.Category;
import com.facturacion.almacenamiento2.exception.GeneralServiceException;
import com.facturacion.almacenamiento2.exception.ValidateServiceException;
import com.facturacion.almacenamiento2.repository.CategoryRepository;
import com.facturacion.almacenamiento2.service.CategoryService;
import com.facturacion.almacenamiento2.validator.CategoryValidator;


@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository repository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Category> findAll() {
		try {
			return repository.findAll();
		} 
		catch (Exception e) {
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Category findById(int id) {
		try {
			Category category = repository.findById(id).
					orElseThrow(() -> new ValidateServiceException("No hay un registro con ese ID. "));
			
			return category;
		} 
		catch (ValidateServiceException e) {
			throw e;
		}
		catch ( Exception e) {
			throw new GeneralServiceException( "Error en el servidor. " );
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Category findByNombre(String nombre) {
		try {
			return repository.findByNombre(nombre);
		} 
		catch (Exception e) {
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Category> findByNombreContaining(String nombre) {
		try {
			return repository.findByNombreContaining(nombre);
		} 
		catch (Exception e) {
			throw e;
		}
	}

	@Override
	@Transactional
	public Category create(Category obj) {
		try {
			CategoryValidator.save(obj);
			Category category = findByNombre(obj.getNombre());
			if ( category != null ) {
				throw new ValidateServiceException("Ya hay un registro con ese nombre.");
			} 
			
			// Guardar categoria
			obj.setActivo(true);
			return repository.save(obj);
		} catch ( ValidateServiceException e ) {
			throw new GeneralServiceException(e.getMessage());
		} catch ( Exception e ) {
			throw new GeneralServiceException("Error en el servidor.");
		}
	}

	@Override
	@Transactional
	public Category update(Category obj) {
		try {
			CategoryValidator.save(obj);
			Category categoryDb = findById(obj.getId());
			 
			// Validar registro
			Category category = findByNombre(obj.getNombre());
			if ( category != null && obj.getId() != category.getId() ) {
				 throw new ValidateServiceException("Ya hay un registro con ese nombre. ");
			}
			
			categoryDb.setNombre(obj.getNombre());
			return repository.save(categoryDb);
		}
		catch (ValidateServiceException e) {
			throw new GeneralServiceException(e.getMessage());
		}
		catch (Exception e) {
			throw new GeneralServiceException("Error en el servidor. ");
		}
	}

	@Override
	@Transactional
	public int delete(int id) {
		try {
			Category categoriaDb = findById(id);
			if( categoriaDb == null ) {
				return 0;
			} else {
				repository.delete(categoriaDb);
				return 1;
			}
		} 
		catch (Exception e) {
			throw e;
		}
	}
	
}
