package com.openbootcamp.App.Barbershop.service.impl;

import com.openbootcamp.App.Barbershop.entities.Cita;
import com.openbootcamp.App.Barbershop.entities.Direccion;
import com.openbootcamp.App.Barbershop.repository.DireccionRepository;
import com.openbootcamp.App.Barbershop.service.CitaService;
import com.openbootcamp.App.Barbershop.service.DireccionService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class DireccionServiceImpl implements DireccionService {

    private final DireccionRepository direccionRepository;
    private final CitaService citaService;

    public DireccionServiceImpl(DireccionRepository direccionRepository, CitaService citaService) {
        this.direccionRepository = direccionRepository;
        this.citaService = citaService;
    }

    @Override
    public Optional<Direccion> findById(Long id) {
        if (id == null || id <= 0)
            return Optional.empty();

        return direccionRepository.findById(id);
    }

    @Override
    public List<Direccion> findAll() {
        return direccionRepository.findAll();
    }

    @Override
    public Direccion save(Direccion direccion) {
        if (direccion == null )
            throw new IllegalArgumentException("Formato dirección incorrecto");

        return direccionRepository.save(direccion);
    }

    @Override
    public boolean deleteById(Long id) {
        if(id == null || !direccionRepository.existsById(id))
            return false;

        direccionRepository.deleteById(id);
        return true;
    }
}
