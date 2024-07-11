package com.facturacion.almacenamiento2.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.facturacion.almacenamiento2.entity.Client;
import com.facturacion.almacenamiento2.exception.GeneralServiceException;
import com.facturacion.almacenamiento2.exception.ValidateServiceException;
import com.facturacion.almacenamiento2.repository.ClientRepository;
import com.facturacion.almacenamiento2.service.ClientService;
import com.facturacion.almacenamiento2.validator.ClientValidator;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Client> findAll() {
        try {
            return repository.findAll();
        } catch (Exception e) {
            throw new GeneralServiceException("Error al obtener todos los clientes.", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Client findById(int id) {
        try {
            return repository.findById(id)
                    .orElseThrow(() -> new ValidateServiceException("No se encontró el cliente con ID: " + id));
        } catch (ValidateServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new GeneralServiceException("Error al obtener el cliente con ID: " + id, e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Client findByNombre(String nombre) {
        try {
            return repository.findByNombre(nombre);
        } catch (Exception e) {
            throw new GeneralServiceException("Error al buscar cliente por nombre: " + nombre, e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Client> findByNombreContaining(String nombre) {
        try {
            return repository.findByNombreContaining(nombre);
        } catch (Exception e) {
            throw new GeneralServiceException("Error al buscar clientes por nombre que contiene: " + nombre, e);
        }
    }

    @Override
    @Transactional
    public Client create(Client cliente) {
        try {
            ClientValidator.save(cliente);
            Client existingCliente = findByNombre(cliente.getNombre());
            if (existingCliente != null) {
                throw new ValidateServiceException("Ya existe un cliente con el nombre: " + cliente.getNombre());
            }

            cliente.setActivo(true); // Ejemplo de activar automáticamente el cliente al crearlo
            return repository.save(cliente);
        } catch (ValidateServiceException e) {
            throw new GeneralServiceException(e.getMessage(), e);
        } catch (Exception e) {
            throw new GeneralServiceException("Error al crear el cliente: " + cliente.getNombre(), e);
        }
    }

    @Override
    @Transactional
    public Client update(Client cliente) {
        try {
            ClientValidator.save(cliente);
            Client existingCliente = findById(cliente.getId());
            Client clienteWithSameName = findByNombre(cliente.getNombre());

            // Copiar los atributos actualizados
            existingCliente.setNombre(cliente.getNombre());
            existingCliente.setDocumento(cliente.getDocumento());
            existingCliente.setTelefono(cliente.getTelefono());
            existingCliente.setDireccion(cliente.getDireccion());
            existingCliente.setEmail(cliente.getEmail());

            return repository.save(existingCliente);
        } catch (ValidateServiceException e) {
            throw new GeneralServiceException(e.getMessage(), e);
        } catch (Exception e) {
            throw new GeneralServiceException("Error al actualizar el cliente con ID: " + cliente.getId(), e);
        }
    }

    @Override
    @Transactional
    public void delete(int id) {
        try {
            Client cliente = findById(id);
            if (cliente == null) {
                throw new ValidateServiceException("No se encontró el cliente con ID: " + id);
            }

            repository.delete(cliente);
        } catch (ValidateServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new GeneralServiceException("Error al eliminar el cliente con ID: " + id, e);
        }
    }
}
