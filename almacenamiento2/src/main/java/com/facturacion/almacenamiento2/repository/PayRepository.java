package com.facturacion.almacenamiento2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.facturacion.almacenamiento2.entity.Pay;

@Repository
public interface PayRepository extends JpaRepository<Pay, Integer> {

    // Encuentra un pago por documento del inquilino
    List<Pay> findByDocumentoInquilino(String documentoInquilino);

    // Encuentra pagos por método de pago
    List<Pay> findByMetodoPago(String metodoPago);

    // Encuentra pagos por descripción (contiene)
    List<Pay> findByDescripcionContaining(String descripcion);

    // Puedes definir más métodos de consulta aquí según tus necesidades
}
