package com.openbootcamp.App.Barbershop.service;

import com.openbootcamp.App.Barbershop.entities.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {

    Optional<Cliente> findById(Long id);

    List<Cliente> findAll();

    Cliente save (Cliente cliente);

    boolean deleteById (Long id);

}
