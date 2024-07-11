package com.facturacion.almacenamiento2.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.facturacion.almacenamiento2.entity.Pay;
import com.facturacion.almacenamiento2.exception.GeneralServiceException;
import com.facturacion.almacenamiento2.exception.ValidateServiceException;
import com.facturacion.almacenamiento2.repository.PayRepository;
import com.facturacion.almacenamiento2.service.PayService;
import com.facturacion.almacenamiento2.validator.PayValidator;

@Service
public class PayServiceImpl implements PayService {

    @Autowired
    private PayRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Pay> findAll() {
        try {
            return repository.findAll();
        } catch (Exception e) {
            throw new GeneralServiceException("Error al obtener todos los pagos.", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Pay findById(int id) {
        try {
            return repository.findById(id)
                    .orElseThrow(() -> new ValidateServiceException("No se encontró el pago con ID: " + id));
        } catch (ValidateServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new GeneralServiceException("Error al obtener el pago con ID: " + id, e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pay> findByDocumentoInquilino(String documentoInquilino) {
        try {
            return repository.findByDocumentoInquilino(documentoInquilino);
        } catch (Exception e) {
            throw new GeneralServiceException("Error al buscar pagos por documento del inquilino: " + documentoInquilino, e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pay> findByMetodoPago(String metodoPago) {
        try {
            return repository.findByMetodoPago(metodoPago);
        } catch (Exception e) {
            throw new GeneralServiceException("Error al buscar pagos por método de pago: " + metodoPago, e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pay> findByDescripcionContaining(String descripcion) {
        try {
            return repository.findByDescripcionContaining(descripcion);
        } catch (Exception e) {
            throw new GeneralServiceException("Error al buscar pagos por descripción que contiene: " + descripcion, e);
        }
    }

    @Override
    @Transactional
    public Pay create(Pay pay) {
        try {
            PayValidator.save(pay);
            // Puedes realizar más validaciones si es necesario

            pay.setActivo(true); // Ejemplo de activar automáticamente el pago al crearlo
            return repository.save(pay);
        } catch (ValidateServiceException e) {
            throw new GeneralServiceException(e.getMessage(), e);
        } catch (Exception e) {
            throw new GeneralServiceException("Error al crear el pago.", e);
        }
    }

    @Override
    @Transactional
    public Pay update(Pay pay) {
        try {
            PayValidator.save(pay);
            Pay existingPay = findById(pay.getId());

            // Copiar los atributos actualizados
            existingPay.setDocumentoInquilino(pay.getDocumentoInquilino());
            existingPay.setFecha(pay.getFecha());
            existingPay.setMontoPago(pay.getMontoPago());
            existingPay.setMetodoPago(pay.getMetodoPago());
            existingPay.setDescripcion(pay.getDescripcion());
            existingPay.setActivo(pay.getActivo());

            return repository.save(existingPay);
        } catch (ValidateServiceException e) {
            throw new GeneralServiceException(e.getMessage(), e);
        } catch (Exception e) {
            throw new GeneralServiceException("Error al actualizar el pago con ID: " + pay.getId(), e);
        }
    }

    @Override
    @Transactional
    public void delete(int id) {
        try {
            Pay pay = findById(id);
            if (pay == null) {
                throw new ValidateServiceException("No se encontró el pago con ID: " + id);
            }

            repository.delete(pay);
        } catch (ValidateServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new GeneralServiceException("Error al eliminar el pago con ID: " + id, e);
        }
    }
}
