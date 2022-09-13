package com.openbootcamp.App.Barbershop.repository;

import com.openbootcamp.App.Barbershop.entities.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository <Cita, Long>{

    List<Cita> findAllByFechaBetween(LocalDateTime min, LocalDateTime max);
    List<Cita> findAllByClienteEmail(String email);
    List<Cita> findAllByEmpleadosDni(String dni);
    List<Cita> findAllByServiciosPrecioLessThanEqual(Double precio);
    List<Cita> findAllByIdNotInAndClienteId(List<Long> ids, Long id);

    List<Cita> findAllByClienteId(Long id);
    List<Cita> findAllByEmpleadosId(Long id);

    List<Cita> findAllByServiciosId(Long id);
}
