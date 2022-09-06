package com.openbootcamp.App.Barbershop.service;

import com.openbootcamp.App.Barbershop.entities.Citas;
import com.openbootcamp.App.Barbershop.repository.CitasRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioCitasImpl implements ServicioCitas{

    private final CitasRepository citasRepository;

    public ServicioCitasImpl(CitasRepository citasRepository) {
        this.citasRepository = citasRepository;
    }

    @Override
    public Optional<Citas> findById(Long id) {
        if (id == null || id <= 0)
            return Optional.empty();

        return citasRepository.findById(id);
    }

    @Override
    public List<Citas> findAll() {
        return citasRepository.findAll();
    }

    @Override
    public Double calcularBeneficioPorDia(LocalDate dia) {
        if (dia == null)
            return 0d;

        LocalDateTime min = dia.atTime(0,0);
        LocalDateTime max = dia.atTime(23,59);

        List<Citas> citas = citasRepository.findAllByFechaBetween(min, max);
        double beneficios = 0;
        for (Citas cita: citas){
            if (cita.getServicios() == null)
                continue;

            beneficios += cita.getServicios().getPrecio();
        }
        return beneficios;
    }

    @Override
    public Citas save(Citas cita) throws IllegalArgumentException{
        if (cita == null || cita.getFecha() == null)
            throw new IllegalArgumentException("Cita incorrecta");
        return citasRepository.save(cita);

    }

    @Override
    public boolean deleteById(Long id) {
        if(id == null || !citasRepository.existsById(id))
            return false;

        citasRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean deleteAll() {
        citasRepository.deleteAll();
        return true;
    }
}
