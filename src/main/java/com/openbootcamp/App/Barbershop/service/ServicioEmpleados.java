package com.openbootcamp.App.Barbershop.service;

import com.openbootcamp.App.Barbershop.entities.Empleados;

import java.util.List;
import java.util.Optional;

public interface ServicioEmpleados {

    Optional<Empleados> findById(Long id);

    List<Empleados> findAll();

    Empleados save (Empleados empleados);

    boolean deleteById (Long id);

}
