package com.facturacion.almacenamiento2.service;

import java.util.List;

import com.facturacion.almacenamiento2.entity.Client;

public interface ClientService {
    
    List<Client> findAll();
    
    Client findById(int id);
    
    Client findByNombre(String nombre);
    
    List<Client> findByNombreContaining(String nombre);
    
    Client create(Client cliente);
    
    Client update(Client cliente);
    
    void delete(int id);
}
