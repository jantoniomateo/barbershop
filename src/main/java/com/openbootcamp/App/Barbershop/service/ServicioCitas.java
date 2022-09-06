package com.openbootcamp.App.Barbershop.service;

import com.openbootcamp.App.Barbershop.entities.Citas;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

/**
 * CRUD Create, Retrieve/Read, Update, Delete
 */

public interface ServicioCitas {

    Optional<Citas> findById(Long id);
    List<Citas> findAll();
    List<Citas> findAllByClienteEmail(String emailCliente) throws IllegalArgumentException;
    List<Citas> findAllByDniEmpleado(String dni);

    List<Citas> findAllByPrecioServicioLessThanEqual(Double precio);
    double calcularBeneficioPorDia(LocalDate dia);
    double calcularBeneficioPorMes(int year, Month mes); //Se indica la clase Month y se importa.
    double calcularBeneficioPorYear(int year);
    Citas save(Citas citas);

    boolean deleteById(Long id);
    boolean deleteAll();

}
