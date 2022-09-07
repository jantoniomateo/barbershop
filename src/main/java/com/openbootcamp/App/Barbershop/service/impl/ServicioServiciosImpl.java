package com.openbootcamp.App.Barbershop.service.impl;

import com.openbootcamp.App.Barbershop.entities.Servicios;
import com.openbootcamp.App.Barbershop.repository.EmpleadosRepository;
import com.openbootcamp.App.Barbershop.repository.ServiciosRepository;
import com.openbootcamp.App.Barbershop.service.ServicioServicios;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

public class ServicioServiciosImpl implements ServicioServicios {

    private final ServiciosRepository serviciosRepository;

    public ServicioServiciosImpl(ServiciosRepository serviciosRepository) {
        this.serviciosRepository = serviciosRepository;
    }

    @Override
    public Optional<Servicios> findById(Long id) {
        if (id == null || id <= 0)
            return Optional.empty();

        return serviciosRepository.findById(id);
    }

    @Override
    public List<Servicios> findAll() {
        return serviciosRepository.findAll();
    }

    @Override
    public Servicios save(Servicios servicio) {
        if (servicio == null )
            throw new IllegalArgumentException("Servicio incorrecto");
        return serviciosRepository.save(servicio);
    }

    @Override
    public boolean deleteById(Long id) {
        if(id == null || !serviciosRepository.existsById(id))
            return false;

        serviciosRepository.deleteById(id);
        return true;
    }
}
