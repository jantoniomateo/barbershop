package com.openbootcamp.App.Barbershop.service;

import com.openbootcamp.App.Barbershop.entities.Empleado;

import java.util.List;
import java.util.Optional;

public interface EmpleadoService {

    Optional<Empleado> findById(Long id);

    List<Empleado> findAll();

    Empleado save (Empleado empleados);

    boolean deleteById (Long id);

}
