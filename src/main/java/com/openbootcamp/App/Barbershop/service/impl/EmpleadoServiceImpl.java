package com.openbootcamp.App.Barbershop.service.impl;

import com.openbootcamp.App.Barbershop.entities.Cita;
import com.openbootcamp.App.Barbershop.entities.Direccion;
import com.openbootcamp.App.Barbershop.entities.Empleado;
import com.openbootcamp.App.Barbershop.repository.EmpleadoRepository;
import com.openbootcamp.App.Barbershop.service.CitaService;
import com.openbootcamp.App.Barbershop.service.DireccionService;
import com.openbootcamp.App.Barbershop.service.EmpleadoService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    private final EmpleadoRepository empleadoRepository;
    private final CitaService citaService;
    private final DireccionService direccionService;

    public EmpleadoServiceImpl(EmpleadoRepository empleadoRepository, CitaService citaService, DireccionService direccionService) {
        this.empleadoRepository = empleadoRepository;
        this.citaService = citaService;
        this.direccionService = direccionService;
    }

    @Override
    public Optional<Empleado> findById(Long id) {
        if (id == null || id <= 0)
            return Optional.empty();

        return empleadoRepository.findById(id);
    }

    @Override
    public List<Empleado> findAll() {
        return empleadoRepository.findAll();
    }

    /**
     * Casuiticas:
     * 1.- Crear un empleado sin dirección
     * 2.- Crear un empleado con nueva dirección sin id
     * 3.- Crear un empleado con dirección con id falso
     * 4.- Crear un empleado don dirección ya existente no ocupada
     * 5.- Crear un empleado con dirección existente pero sin los campos, solo el id
     * 6.- Crear un empleado don dirección ya existente si ocupada
     * @param empleado
     * @return
     */
    @Override
    public Empleado save(Empleado empleado) {
        if (empleado == null || !StringUtils.hasLength(empleado.getEmail()))
            throw new IllegalArgumentException("Email incorrecto");

        if(empleado.getDireccion() != null && empleado.getDireccion().getId() != null){

            Optional<Direccion> direccionOpt = direccionService.findById(empleado.getDireccion().getId());
            if (direccionOpt.isPresent()){
                Direccion direccion = direccionOpt.get();
                direccion.setCalle(StringUtils.hasLength(empleado.getDireccion().getCalle()) ? empleado.getDireccion().getCalle() : direccion.getCalle());
                direccion.setCiudad(StringUtils.hasLength(empleado.getDireccion().getCiudad()) ? empleado.getDireccion().getCiudad() : direccion.getCiudad());
                direccion.setCodigoPostal(StringUtils.hasLength(empleado.getDireccion().getCodigoPostal()) ? empleado.getDireccion().getCodigoPostal() : direccion.getCodigoPostal());
                direccion.setPais(StringUtils.hasLength(empleado.getDireccion().getPais()) ? empleado.getDireccion().getPais() : direccion.getPais());
                empleado.setDireccion(direccion);
            }else {
                empleado.setDireccion(null);
            }
        }
        return empleadoRepository.save(empleado);
    }

    @Override
    public boolean deleteById(Long id) {
        if(id == null || !empleadoRepository.existsById(id))
            return false;

        List<Cita> citasDesasociadas =citaService.findAllByEmpleadosId(id);
        citasDesasociadas.forEach(app -> app.setEmpleados(null));
        citaService.saveAll(citasDesasociadas);

        empleadoRepository.deleteById(id);
        return true;
    }
}
