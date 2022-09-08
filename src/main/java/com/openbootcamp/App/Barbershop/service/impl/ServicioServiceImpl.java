package com.openbootcamp.App.Barbershop.service.impl;

import com.openbootcamp.App.Barbershop.entities.Servicio;
import com.openbootcamp.App.Barbershop.repository.ServicioRepository;
import com.openbootcamp.App.Barbershop.service.ServicioService;

import java.util.List;
import java.util.Optional;

public class ServicioServiceImpl implements ServicioService {

    private final ServicioRepository serviciosRepository;

    public ServicioServiceImpl(ServicioRepository serviciosRepository) {
        this.serviciosRepository = serviciosRepository;
    }

    @Override
    public Optional<Servicio> findById(Long id) {
        if (id == null || id <= 0)
            return Optional.empty();

        return serviciosRepository.findById(id);
    }

    @Override
    public List<Servicio> findAll() {
        return serviciosRepository.findAll();
    }

    @Override
    public Servicio save(Servicio servicio) {
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
