package com.facturacion.almacenamiento2.validator;

import com.facturacion.almacenamiento2.entity.Category;
import com.facturacion.almacenamiento2.exception.ValidateServiceException;

public class CategoryValidator {
	public static void save( Category category ) 
	{
		if ( category.getNombre() == null || category.getNombre().trim().isEmpty() ) 
		{
			throw new ValidateServiceException("El nombre es requerido.");
		}
		
		if ( category.getNombre().length() > 100 ) 
		{
			throw new ValidateServiceException("El nombre es muy extenso.");
		}
	}
}
