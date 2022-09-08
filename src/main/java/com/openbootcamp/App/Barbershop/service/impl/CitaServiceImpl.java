package com.openbootcamp.App.Barbershop.service.impl;

import com.openbootcamp.App.Barbershop.entities.Cita;
import com.openbootcamp.App.Barbershop.repository.CitaRepository;
import com.openbootcamp.App.Barbershop.service.CitaService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CitaServiceImpl implements CitaService {

    private final CitaRepository citasRepository;

    public CitaServiceImpl(CitaRepository citasRepository) {
        this.citasRepository = citasRepository;
    }

    @Override
    public Optional<Cita> findById(Long id) {
        if (id == null || id <= 0)
            return Optional.empty();

        return citasRepository.findById(id);
    }

    @Override
    public List<Cita> findAll() {
        return citasRepository.findAll();
    }

    @Override
    public List<Cita> findAllById(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids))
            return new ArrayList<>();

        return citasRepository.findAllById(ids);
    }

    @Override
    public List<Cita> findAllByClienteEmail(String emailCliente) throws IllegalArgumentException {
        if (!StringUtils.hasLength(emailCliente) && !emailCliente.contains("@"))
            throw new IllegalArgumentException(("Email incorrecto"));

        return citasRepository.findAllByClienteEmail(emailCliente);
    }

    @Override
    public List<Cita> findAllByDniEmpleados(String dni) {
        if (!StringUtils.hasLength(dni))
            throw new IllegalArgumentException(("DNI Incorrecto"));

        return citasRepository.findAllByEmpleadosDni(dni);
    }

    @Override
    public List<Cita> findAllByServiciosPrecioLessThanEqual(Double precio) {
        //between
        //if (min == null || max == null || min <=0 || max<=0 || min > max )
        //    throw new IllegalArgumentException("Rango de precios incorrecto");

        if (precio == null || precio <= 0)
            throw new IllegalArgumentException("Precio incorrecto");

        return citasRepository.findAllByServiciosPrecioLessThanEqual(precio);
    }

    @Override
    public double calcularBeneficioPorDia(LocalDate dia) {
        if (dia == null)
            return 0d;

        LocalDateTime min = dia.atTime(0,0);
        LocalDateTime max = dia.atTime(23,59);

        //List<Citas> citas = citasRepository.findAllByFechaBetween(min, max);
        //double beneficios = 0;
        //for (Citas cita: citas){
        //    if (cita.getServicios() == null)
        //        continue;
        //    beneficios += cita.getServicios().getPrecio();
        //}
        //return beneficios;
        return extraerBeneficios(citasRepository.findAllByFechaBetween(min, max));
    }

    @Override
    public double calcularBeneficioPorMes(int year, Month mes) {
        //Calculamos los dias de un mes completo con min y max.
        LocalDateTime min = LocalDateTime.of(year,mes,1,0,0);
        LocalDateTime max = min.plusMonths(1);

        //Alternativa
        //LocalDateTime min = LocalDateTime.of(2022,1,1,0,0);
        //LocalDateTime max = min.with(TemporalAdjusters.lastDayOfMonth()).withHour(23).withMinute(59);

        return extraerBeneficios(citasRepository.findAllByFechaBetween(min, max));
    }

    @Override
    public double calcularBeneficioPorYear(int year) {
        LocalDateTime min = LocalDateTime.of(year,1,1,0,0);
        LocalDateTime max = min.plusYears(1);

        return extraerBeneficios(citasRepository.findAllByFechaBetween(min, max));
    }



    @Override
    public Cita save(Cita cita) throws IllegalArgumentException{
        if (cita == null || cita.getFecha() == null)
            throw new IllegalArgumentException("Cita incorrecta");
        return citasRepository.save(cita);

    }

    @Override
    public List<Cita> saveAll(List<Cita> cita) {
        if (!CollectionUtils.isEmpty(cita))  //CollectionUtils permite comprobar si la colección esta vacía.
            return citasRepository.saveAll(cita); //si no está vacía, guardamos las citas.

        return new ArrayList<>();  //si está vacía devolvemos un arraylist vacío.
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

    /**
     * Extrae el precio cobrado por cada cita y lo suma obteniendo así el beneficio total
     * aplicando programación funcional
     * @param citas lista de citas
     * @return beneficio total
     */
    private Double extraerBeneficios(List<Cita> citas) {
        return citas.stream().
                filter(a -> a.getServicios() != null).
                map(b -> b.getServicios().getPrecio()).
                reduce(Double::sum).
                orElse(0d);
    }
}
