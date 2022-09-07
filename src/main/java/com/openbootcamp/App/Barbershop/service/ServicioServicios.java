package com.openbootcamp.App.Barbershop.service;

import com.openbootcamp.App.Barbershop.entities.Servicios;

import java.util.List;
import java.util.Optional;

public interface ServicioServicios {

    Optional<Servicios> findById(Long id);

    List<Servicios> findAll();

    Servicios save (Servicios servicio);

    boolean deleteById (Long id);

}
