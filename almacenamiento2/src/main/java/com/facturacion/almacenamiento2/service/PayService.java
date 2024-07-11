package com.facturacion.almacenamiento2.service;

import java.util.List;

import com.facturacion.almacenamiento2.entity.Pay;

public interface PayService {

    // Encuentra todos los pagos
    List<Pay> findAll();
    
    // Encuentra un pago por su ID
    Pay findById(int id);
    
    // Encuentra pagos por el documento del inquilino
    List<Pay> findByDocumentoInquilino(String documentoInquilino);
    
    // Encuentra pagos por el método de pago
    List<Pay> findByMetodoPago(String metodoPago);
    
    // Encuentra pagos por descripción (contiene)
    List<Pay> findByDescripcionContaining(String descripcion);
    
    // Crea un nuevo pago
    Pay create(Pay pay);
    
    // Actualiza un pago existente
    Pay update(Pay pay);
    
    // Elimina un pago por su ID
    void delete(int id);
}
