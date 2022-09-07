package com.openbootcamp.App.Barbershop.service.impl;

import com.openbootcamp.App.Barbershop.entities.Clientes;
import com.openbootcamp.App.Barbershop.repository.ClientesRepository;
import com.openbootcamp.App.Barbershop.repository.ServiciosRepository;
import com.openbootcamp.App.Barbershop.service.ServicioClientes;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioClientesImpl implements ServicioClientes {

    private final ClientesRepository clientesRepository;

    public ServicioClientesImpl(ClientesRepository clientesRepository) {
        this.clientesRepository = clientesRepository;
    }

    @Override
    public Optional<Clientes> findById(Long id) {
        if (id == null || id <= 0)
            return Optional.empty();

        return clientesRepository.findById(id);
    }

    @Override
    public List<Clientes> findAll() {
        return clientesRepository.findAll();
    }

    @Override
    public Clientes save(Clientes cliente) {
        if (cliente == null || !StringUtils.hasLength(cliente.getEmail()))
            throw new IllegalArgumentException("Email incorrecto");
        return clientesRepository.save(cliente);
    }

    @Override
    public boolean deleteById(Long id) {
        if(id == null || !clientesRepository.existsById(id))
            return false;

        clientesRepository.deleteById(id);
        return true;
    }
}
