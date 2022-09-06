package com.openbootcamp.App.Barbershop.repository;

import com.openbootcamp.App.Barbershop.entities.Citas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CitasRepository extends JpaRepository <Citas, Long>{

    List<Citas> findAllByFechaBetween(LocalDateTime min, LocalDateTime max);
}
