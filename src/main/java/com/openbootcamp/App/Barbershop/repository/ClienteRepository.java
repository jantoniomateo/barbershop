package com.openbootcamp.App.Barbershop.repository;

import com.openbootcamp.App.Barbershop.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
