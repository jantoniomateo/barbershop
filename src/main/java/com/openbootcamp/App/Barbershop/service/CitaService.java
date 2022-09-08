package com.openbootcamp.App.Barbershop.service;

import com.openbootcamp.App.Barbershop.entities.Cita;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

/**
 * CRUD Create, Retrieve/Read, Update, Delete
 */

public interface CitaService {

    Optional<Cita> findById(Long id);
    List<Cita> findAll();
    List<Cita> findAllById(List<Long> ids);
    List<Cita> findAllByClienteEmail(String emailCliente) throws IllegalArgumentException;
    List<Cita> findAllByDniEmpleados(String dni);

    List<Cita> findAllByServiciosPrecioLessThanEqual(Double precio);
    double calcularBeneficioPorDia(LocalDate dia);
    double calcularBeneficioPorMes(int year, Month mes); //Se indica la clase Month y se importa.
    double calcularBeneficioPorYear(int year);
    Cita save(Cita citas);
    List<Cita> saveAll(List<Cita> cita);
    boolean deleteById(Long id);
    boolean deleteAll();

}
