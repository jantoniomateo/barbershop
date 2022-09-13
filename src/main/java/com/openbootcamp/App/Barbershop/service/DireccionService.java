package com.openbootcamp.App.Barbershop.service;

import com.openbootcamp.App.Barbershop.entities.Direccion;

import java.util.List;
import java.util.Optional;

public interface DireccionService {

    Optional<Direccion> findById(Long id);

    List<Direccion> findAll();

    Direccion save (Direccion direccion);

    boolean deleteById (Long id);

}
