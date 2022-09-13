package com.openbootcamp.App.Barbershop.service.impl;

import com.openbootcamp.App.Barbershop.entities.Cita;
import com.openbootcamp.App.Barbershop.entities.Servicio;
import com.openbootcamp.App.Barbershop.repository.ServicioRepository;
import com.openbootcamp.App.Barbershop.service.CitaService;
import com.openbootcamp.App.Barbershop.service.ServicioService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioServiceImpl implements ServicioService {

    private final ServicioRepository serviciosRepository;
    private final CitaService citaService;

    public ServicioServiceImpl(ServicioRepository serviciosRepository, CitaService citaService) {
        this.serviciosRepository = serviciosRepository;
        this.citaService = citaService;
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

        //Desasociar citas antes de borrar servicios de corte de pelo
        List<Cita> citasDesasociadas =citaService.findAllByServiciosId(id);
        citasDesasociadas.forEach(app -> app.setServicios(null));
        citaService.saveAll(citasDesasociadas);

        serviciosRepository.deleteById(id);
        return true;
    }
}
