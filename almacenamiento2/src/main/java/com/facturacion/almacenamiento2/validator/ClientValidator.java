package com.facturacion.almacenamiento2.validator;

import com.facturacion.almacenamiento2.entity.Client;
import com.facturacion.almacenamiento2.exception.ValidateServiceException;

public class ClientValidator {

    public static void save(Client cliente) {
        if (cliente.getNombre() == null || cliente.getNombre().trim().isEmpty()) {
            throw new ValidateServiceException("El nombre es requerido.");
        }

        if (cliente.getNombre().length() > 100) {
            throw new ValidateServiceException("El nombre es muy extenso (máximo 100 caracteres).");
        }

        if (cliente.getDocumento() != null && cliente.getDocumento().length() > 15) {
            throw new ValidateServiceException("El documento es muy extenso (máximo 15 caracteres).");
        }

        if (cliente.getTelefono() != null && cliente.getTelefono().length() > 15) {
            throw new ValidateServiceException("El teléfono es muy extenso (máximo 15 caracteres).");
        }

        if (cliente.getDireccion() != null && cliente.getDireccion().length() > 100) {
            throw new ValidateServiceException("La dirección es muy extensa (máximo 100 caracteres).");
        }

        if (cliente.getEmail() != null && cliente.getEmail().length() > 50) {
            throw new ValidateServiceException("El email es muy extenso (máximo 50 caracteres).");
        }
    }
}
