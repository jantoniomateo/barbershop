package com.openbootcamp.App.Barbershop.service.impl;

import com.openbootcamp.App.Barbershop.entities.Cita;
import com.openbootcamp.App.Barbershop.entities.Cliente;
import com.openbootcamp.App.Barbershop.repository.ClienteRepository;
import com.openbootcamp.App.Barbershop.service.CitaService;
import com.openbootcamp.App.Barbershop.service.ClienteService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final CitaService citaService;

    public ClienteServiceImpl(ClienteRepository clientesRepository, CitaService citaService) {
        this.clienteRepository = clientesRepository;
        this.citaService = citaService;
    }

        @Override
        public Optional<Cliente> findById(Long id) {
            if (id == null || id <= 0)
                return Optional.empty();

            return clienteRepository.findById(id);
        }

        @Override
        public List<Cliente> findAll() {
            return clienteRepository.findAll();
        }

        @Override
        public Cliente save(Cliente cliente) {
            if (cliente == null || !StringUtils.hasLength(cliente.getEmail()))
                throw new IllegalArgumentException("Email incorrecto");
            //si queremos guardar las citas desde el controlador de cliente, hay que
            //guardar desde el lado del owner, es decir, desde el lado de las citas.

            //primeramente debemos guardar el cliente en base de datos para posteriormente asignarle las citas.
            Cliente clienteDB = clienteRepository.save(cliente);

            //Extraemos los ids de las citas del cliente que nos ha pasado para guardar.
            List<Long> ids = cliente.getCitas().stream().map(a -> a.getId()).toList();

            //Recuperamos desde base de datos las citas que nos han pasado de acuerdo a sus ids.
            //ya que citaService tira del repositorio.
            List<Cita> citas = citaService.findAllById(ids);

            for ( Cita cita : citas) { cita.setCliente(clienteDB);
            }

            clienteDB.setCitas(citaService.saveAll(citas));

            //Desasociamos las citas que no están en el update.
            List<Cita> citasDesasociadas =citaService.findAllByIdNotInAndClienteId(ids, clienteDB.getId());
            citasDesasociadas.forEach(app -> app.setCliente(null));
            citaService.saveAll(citasDesasociadas);

            return clienteDB;
        }

        @Override
        public boolean deleteById(Long id) {
            if(id == null || !clienteRepository.existsById(id))
                return false;

            //desasociamos las citas antes de borrar el cliente, para que nos permita su borrado debido a la asociación
            //que tenemos realizada, de tal forma que mientras existan citas apuntando a un cliente, no van a dejarnos
            //borrar dicho cliente.

            List<Cita> citasDesasociadas =citaService.findAllByClienteId(id);
            citasDesasociadas.forEach(app -> app.setCliente(null));
            citaService.saveAll(citasDesasociadas);

            clienteRepository.deleteById(id);
            return true;
        }
    }

