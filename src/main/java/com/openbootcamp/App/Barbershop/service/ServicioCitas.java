package com.openbootcamp.App.Barbershop.service;

import com.openbootcamp.App.Barbershop.entities.Citas;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * CRUD Create, Retrieve/Read, Update, Delete
 */

public interface ServicioCitas {

    Optional<Citas> findById(Long id);
    List<Citas> findAll();
    Double calcularBeneficioPorDia(LocalDate dia);
    Citas save(Citas citas);

    boolean deleteById(Long id);
    boolean deleteAll();

}
