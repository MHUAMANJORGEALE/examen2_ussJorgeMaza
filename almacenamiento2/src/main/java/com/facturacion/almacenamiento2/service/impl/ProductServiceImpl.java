package com.facturacion.almacenamiento2.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.facturacion.almacenamiento2.entity.Category;
import com.facturacion.almacenamiento2.entity.Product;
import com.facturacion.almacenamiento2.exception.GeneralServiceException;
import com.facturacion.almacenamiento2.exception.ValidateServiceException;
import com.facturacion.almacenamiento2.repository.CategoryRepository;
import com.facturacion.almacenamiento2.repository.ProductRepository;
import com.facturacion.almacenamiento2.service.ProductService;
import com.facturacion.almacenamiento2.validator.ProductValidator;


@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository repository;

	@Autowired
    private CategoryRepository categoryRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Product> findAll() {
		try {
			return repository.findAll();
		} 
		catch (Exception e) {
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Product findById(int id) {
		try {
			Product product = repository.findById(id).
					orElseThrow(() -> new ValidateServiceException("No hay un registro con ese ID. "));
			
			return product;
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
	public Product findByNombre(String nombre) {
		try {
			return repository.findByNombre(nombre);
		} 
		catch (Exception e) {
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Product> findByNombreContaining(String nombre) {
		try {
			return repository.findByNombreContaining(nombre);
		} 
		catch (Exception e) {
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = false)
	public Product create(Product obj) {
	    try {
	        ProductValidator.save(obj);

	        // Verificar si la categoría existe
	        Category category = obj.getCategoria();
	        if (category != null && category.getId() != 0) {
	            category = categoryRepository.findById(category.getId())
	                                          .orElseThrow(() -> new ValidateServiceException("La categoría especificada no existe."));
	        } else {
	            throw new ValidateServiceException("La categoría no puede estar vacía.");
	        }

	        // Verificar si ya existe un producto con el mismo nombre
	        Product existingProduct = findByNombre(obj.getNombre());
	        if (existingProduct != null) {
	            throw new ValidateServiceException("Ya hay un registro con ese nombre.");
	        }

	        // Asignar la categoría al producto
	        obj.setCategoria(category);
	        obj.setActivo(true);

	        return repository.save(obj);
	    } catch (ValidateServiceException e) {
	        throw new GeneralServiceException(e.getMessage());
	    } catch (Exception e) {
	        throw new GeneralServiceException("Error en el servidor.");
	    }
	}

	@Override
	@Transactional
	public Product update(Product obj) {
	    try {
	        
	        ProductValidator.save(obj);
	        Product productDb = findById(obj.getId());

	        Product existingProduct = findByNombre(obj.getNombre());
	        if (existingProduct != null) {
	            throw new ValidateServiceException("Ya hay un registro con ese nombre.");
	        }
	        productDb.setNombre(obj.getNombre());
	        productDb.setActivo(obj.getActivo());

	        return repository.save(productDb);
	    } catch (ValidateServiceException e) {
	        throw new GeneralServiceException(e.getMessage());
	    } catch (Exception e) {
	        throw new GeneralServiceException("Error en el servidor.");
	    }
	}


	@Override
	@Transactional
	public int delete(int id) {
		try {
			Product product = findById(id);
			if( product == null ) {
				return 0;
			} else {
				repository.delete(product);
				return 1;
			}
		} 
		catch (Exception e) {
			throw e;
		}
	}
	
}
