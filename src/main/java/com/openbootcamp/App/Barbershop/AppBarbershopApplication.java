package com.openbootcamp.App.Barbershop;

import com.openbootcamp.App.Barbershop.entities.Cita;
import com.openbootcamp.App.Barbershop.entities.Cliente;
import com.openbootcamp.App.Barbershop.entities.Empleado;
import com.openbootcamp.App.Barbershop.entities.Servicio;
import com.openbootcamp.App.Barbershop.repository.CitaRepository;
import com.openbootcamp.App.Barbershop.repository.ClienteRepository;
import com.openbootcamp.App.Barbershop.repository.EmpleadoRepository;
import com.openbootcamp.App.Barbershop.repository.ServicioRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class AppBarbershopApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(AppBarbershopApplication.class, args);


        //CITA
        CitaRepository citasRepository = context.getBean(CitaRepository.class);
        Cita cita1 = new Cita(null, LocalDateTime.of(2022, 8, 24, 10, 30), 20, "corte de pelo con rapado lateral");
        citasRepository.save(cita1);

		//CLIENTE
        ClienteRepository clientesRepository = context.getBean(ClienteRepository.class);
        Cliente cliente1 = new Cliente(null, "Juan Antonio", "Mateo Heredia", "jantonio.mateo@outlook.es", LocalDate.of(1976, 2, 8));
		clientesRepository.save(cliente1);

        //RELACIÓN CITAS - CLIENTES
        cita1.setCliente(cliente1);
        citasRepository.save(cita1);

        //ORDEN INVERSO  CLIENTES - CITAS (IMPORTANTE: Como Customer no es owner de la asociación entonces no se guarda en base de datos esa asociación )
        Cita cita2 = new Cita(null, LocalDateTime.of(2022, 8, 24, 12, 5), 20, "Corte de pelo con cuello largo");
        citasRepository.save(cita2);
        Cita cita3 = new Cita(null, LocalDateTime.of(2022, 8, 24, 13, 20), 20, "corte de pelo con cuello corto");
        citasRepository.save(cita3);

        Cliente cliente2 = new Cliente(null, "Juan Antonio", "Mateo Cordero", "jantonio.mateo@icloud.es", LocalDate.of(2000, 4, 10));
        cliente2.getCitas().add(cita2);
        cliente2.getCitas().add(cita3);
        clientesRepository.save(cliente2);


        //SERVICIOS
        ServicioRepository serviciosRepository = context.getBean(ServicioRepository.class);
        Servicio cortePelo = new Servicio(null, "Corte de Pelo", 20d, 20);
        Servicio corteBarba = new Servicio(null, "Corte de Barba", 15d, 20);
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
        EmpleadoRepository empleadosRepository = context.getBean(EmpleadoRepository.class);
        Empleado empleado1 = new Empleado(null, "Alberto", "Perez Sanchez", "alberto@gmail.com", null, "41/1234561210", "31666456R");
        empleadosRepository.save(empleado1);

        // CITAS - EMPLEADOS
        cita1.setEmpleados(empleado1);
        cita2.setEmpleados(empleado1);
        citasRepository.saveAll(List.of(cita1,cita2));

        Servicio s1 = new Servicio(null,"Corte de pelo M",15.0,40);
        Servicio s2 = new Servicio(null,"Corte de pelo F",30.0, 40);
        Servicio s3 = new Servicio(null,"Corte de pelo M avance",20.0, 40);
        Servicio s4 = new Servicio(null,"Corte de pelo San Valentín",20.0, 40);
        serviciosRepository.saveAll(List.of(s1,s2,s3,s4));

        Cita app1 = new Cita(null,LocalDateTime.of(2022,1,1,13,30),50,"");
        app1.setServicios(s1);
        app1.setCliente(cliente2);
        citasRepository.save(app1);


        Cita app2 = new Cita(null,LocalDateTime.of(2022,1,14,16,30),50,"");
        app2.setServicios(s2);
        app2.setCliente(cliente2);
        citasRepository.save(app2);

        Cita app3 = new Cita(null,LocalDateTime.of(2022,1,31,20,30),50,"");
        app3.setServicios(s3);
        citasRepository.save(app3);

        Cita app4 = new Cita(null,LocalDateTime.of(2022,2,14,20,30),50,"");
        app4.setServicios(s4);
        citasRepository.save(app4);




    }

}
