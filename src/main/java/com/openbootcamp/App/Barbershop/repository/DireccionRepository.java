package com.openbootcamp.App.Barbershop.repository;

import com.openbootcamp.App.Barbershop.entities.Direccion;
import com.openbootcamp.App.Barbershop.entities.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DireccionRepository extends JpaRepository<Direccion, Long> {

}
