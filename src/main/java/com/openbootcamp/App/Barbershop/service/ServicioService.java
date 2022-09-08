package com.openbootcamp.App.Barbershop.service;

import com.openbootcamp.App.Barbershop.entities.Servicio;

import java.util.List;
import java.util.Optional;

public interface ServicioService {

    Optional<Servicio> findById(Long id);

    List<Servicio> findAll();

    Servicio save (Servicio servicio);

    boolean deleteById (Long id);

}
