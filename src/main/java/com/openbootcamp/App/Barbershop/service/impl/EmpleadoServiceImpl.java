package com.openbootcamp.App.Barbershop.service.impl;

import com.openbootcamp.App.Barbershop.entities.Empleado;
import com.openbootcamp.App.Barbershop.repository.EmpleadoRepository;
import com.openbootcamp.App.Barbershop.service.EmpleadoService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    private final EmpleadoRepository empleadosRepository;

    public EmpleadoServiceImpl(EmpleadoRepository empleadosRepository) {
        this.empleadosRepository = empleadosRepository;
    }

    @Override
    public Optional<Empleado> findById(Long id) {
        if (id == null || id <= 0)
            return Optional.empty();

        return empleadosRepository.findById(id);
    }

    @Override
    public List<Empleado> findAll() {
        return empleadosRepository.findAll();
    }

    @Override
    public Empleado save(Empleado empleados) {
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
