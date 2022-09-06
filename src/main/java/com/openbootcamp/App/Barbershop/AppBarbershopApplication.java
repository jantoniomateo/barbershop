package com.openbootcamp.App.Barbershop;

import com.openbootcamp.App.Barbershop.entities.Citas;
import com.openbootcamp.App.Barbershop.entities.Clientes;
import com.openbootcamp.App.Barbershop.entities.Empleados;
import com.openbootcamp.App.Barbershop.entities.Servicios;
import com.openbootcamp.App.Barbershop.repository.CitasRepository;
import com.openbootcamp.App.Barbershop.repository.ClientesRepository;
import com.openbootcamp.App.Barbershop.repository.EmpleadosRepository;
import com.openbootcamp.App.Barbershop.repository.ServiciosRepository;
import net.bytebuddy.asm.Advice;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@SpringBootApplication
public class AppBarbershopApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(AppBarbershopApplication.class, args);


        //CITA
        CitasRepository citasRepository = context.getBean(CitasRepository.class);
        Citas cita1 = new Citas(null, LocalDateTime.of(2022, 8, 24, 10, 30), 20, "corte de pelo con rapado lateral");
        citasRepository.save(cita1);

		//CLIENTE
        ClientesRepository clientesRepository = context.getBean(ClientesRepository.class);
        Clientes cliente1 = new Clientes(null, "Juan Antonio", "Mateo Heredia", "jantonio.mateo@outlook.es", LocalDate.of(1976, 2, 8));
		clientesRepository.save(cliente1);

        //RELACIÓN CITAS - CLIENTES
        cita1.setCliente(cliente1);
        citasRepository.save(cita1);

        //ORDEN INVERSO  CLIENTES - CITAS (IMPORTANTE: Como Customer no es owner de la asociación entonces no se guarda en base de datos esa asociación )
        Citas cita2 = new Citas(null, LocalDateTime.of(2022, 8, 24, 12, 5), 20, "Corte de pelo con cuello largo");
        citasRepository.save(cita2);
        Citas cita3 = new Citas(null, LocalDateTime.of(2022, 8, 24, 13, 20), 20, "corte de pelo con cuello corto");
        citasRepository.save(cita3);

        Clientes cliente2 = new Clientes(null, "Juan Antonio", "Mateo Cordero", "jantonio.mateo@icloud.es", LocalDate.of(2000, 4, 10));
        cliente2.getCitas().add(cita2);
        cliente2.getCitas().add(cita3);
        clientesRepository.save(cliente2);


        //SERVICIOS
        ServiciosRepository serviciosRepository = context.getBean(ServiciosRepository.class);
        Servicios cortePelo = new Servicios(null, "Corte de Pelo", 20d, 20);
        Servicios corteBarba = new Servicios(null, "Corte de Barba", 15d, 20);
        serviciosRepository.save(cortePelo);
        serviciosRepository.save(corteBarba);

        //SERVICIOS - CITAS
        cita1.setServicios(cortePelo);
        cita2.setServicios(cortePelo);
        cita3.setServicios(corteBarba);
        citasRepository.save(cita1);
        citasRepository.save(cita2);
        citasRepository.save(cita3);

        //EMPLEADOS
        EmpleadosRepository empleadosRepository = context.getBean(EmpleadosRepository.class);
        Empleados empleado1 = new Empleados(null, "Alberto", "Perez Sanchez", "alberto@gmail.com", null, "41/1234561210", "31666456R");
        empleadosRepository.save(empleado1);

        // CITAS - EMPLEADOS
        cita1.setEmpleados(empleado1);
        cita2.setEmpleados(empleado1);
        citasRepository.saveAll(List.of(cita1,cita2));

    }

}
