package com.openbootcamp.App.Barbershop.service.impl;

import com.openbootcamp.App.Barbershop.entities.Empleados;
import com.openbootcamp.App.Barbershop.repository.EmpleadosRepository;
import com.openbootcamp.App.Barbershop.service.ServicioEmpleados;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioEmpleadosImpl implements ServicioEmpleados {

    private final EmpleadosRepository empleadosRepository;

    public ServicioEmpleadosImpl(EmpleadosRepository empleadosRepository) {
        this.empleadosRepository = empleadosRepository;
    }

    @Override
    public Optional<Empleados> findById(Long id) {
        if (id == null || id <= 0)
            return Optional.empty();

        return empleadosRepository.findById(id);
    }

    @Override
    public List<Empleados> findAll() {
        return empleadosRepository.findAll();
    }

    @Override
    public Empleados save(Empleados empleados) {
        if (empleados == null || !StringUtils.hasLength(empleados.getEmail()))
            throw new IllegalArgumentException("Email incorrecto");
        return empleadosRepository.save(empleados);
    }

    @Override
    public boolean deleteById(Long id) {
        if(id == null || !empleadosRepository.existsById(id))
            return false;

        empleadosRepository.deleteById(id);
        return true;
    }
}
