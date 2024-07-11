package com.facturacion.almacenamiento2.validator;

import com.facturacion.almacenamiento2.entity.Product;
import com.facturacion.almacenamiento2.exception.ValidateServiceException;



public class ProductValidator {
	public static void save( Product producto ) 
	{
		if ( producto.getNombre() == null || producto.getNombre().trim().isEmpty() ) 
		{
			throw new ValidateServiceException("El nombre es requerido.");
		}
		
		if ( producto.getNombre().length() > 100 ) 
		{
			throw new ValidateServiceException("El nombre es muy extenso.");
		}
		
		if ( producto.getBarcode() == null || producto.getBarcode().trim().isEmpty() ) 
		{
			throw new ValidateServiceException("El Barcode es requerido.");
		}
		
		if ( producto.getBarcode().length() > 64 ) 
		{
			throw new ValidateServiceException("El Barcode es muy extenso.");
		}
		
		if ( producto.getStock() <= 1) 
		{
			throw new ValidateServiceException("El stock es muy bajo.");
		}
		
		if ( producto.getPrecio() <= 1) 
		{
			throw new ValidateServiceException("Error en el ingreso del precio.");
		}
	}
}
