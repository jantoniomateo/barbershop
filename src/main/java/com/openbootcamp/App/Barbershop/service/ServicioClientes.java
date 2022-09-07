package com.openbootcamp.App.Barbershop.service;

import com.openbootcamp.App.Barbershop.entities.Clientes;

import java.util.List;
import java.util.Optional;

public interface ServicioClientes {

    Optional<Clientes> findById(Long id);

    List<Clientes> findAll();

    Clientes save (Clientes cliente);

    boolean deleteById (Long id);

}
