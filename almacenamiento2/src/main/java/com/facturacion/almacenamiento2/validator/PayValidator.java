package com.facturacion.almacenamiento2.validator;

import java.math.BigDecimal;

import com.facturacion.almacenamiento2.entity.Pay;
import com.facturacion.almacenamiento2.exception.ValidateServiceException;

public class PayValidator {

    public static void save(Pay pay) {
        // Validar documento del inquilino
        if (pay.getDocumentoInquilino() == null || pay.getDocumentoInquilino().trim().isEmpty()) {
            throw new ValidateServiceException("El documento del inquilino es requerido.");
        }

        if (pay.getDocumentoInquilino().length() > 15) {
            throw new ValidateServiceException("El documento del inquilino es muy extenso (máximo 15 caracteres).");
        }

        // Validar fecha
        if (pay.getFecha() == null) {
            throw new ValidateServiceException("La fecha es requerida.");
        }

        // Validar monto de pago
        if (pay.getMontoPago() == null || pay.getMontoPago().compareTo(BigDecimal.ZERO) <= 0) {
            throw new ValidateServiceException("El monto de pago debe ser mayor que cero.");
        }

        // Validar método de pago
        if (pay.getMetodoPago() == null || pay.getMetodoPago().trim().isEmpty()) {
            throw new ValidateServiceException("El método de pago es requerido.");
        }

        if (pay.getMetodoPago().length() > 20) {
            throw new ValidateServiceException("El método de pago es muy extenso (máximo 20 caracteres).");
        }

        // Validar descripción
        if (pay.getDescripcion() == null || pay.getDescripcion().trim().isEmpty()) {
            throw new ValidateServiceException("La descripción es requerida.");
        }

        if (pay.getDescripcion().length() > 255) {
            throw new ValidateServiceException("La descripción es muy extensa (máximo 255 caracteres).");
        }
    }
}
