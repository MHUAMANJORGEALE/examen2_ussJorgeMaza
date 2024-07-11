package com.facturacion.almacenamiento2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.facturacion.almacenamiento2.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    Client findByNombre(String nombre);

    List<Client> findByNombreContaining(String nombre);

    // Puedes definir más métodos de consulta aquí según tus necesidades
}
