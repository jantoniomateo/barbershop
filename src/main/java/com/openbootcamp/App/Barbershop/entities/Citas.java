package com.openbootcamp.App.Barbershop.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


@Entity
@Table(name = "citas")
public class Citas implements Serializable {

    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime fecha;
    private Integer duracion;
    @Column(length = 40)
    private String description;


    // asociaciones: OneToOne, OneToMany, ManyToOne, ManyToMany

    // cliente
    //con el objeto de evitar que aparezcan en el listado las citas y los clientes y
    //a continuación las citas de ese cliente al tener una relación bidireccional
    //utilizamos JsonIgnore Properties para decirle que ignore la propiedad citas
    //de la clase Clientes y de esa manera no hay redundancia de datos.
    // se pone entre llaves porque el valor debe ser un array de string.
    // se da cuando tenemos listas en las relaciones. como no tenemos más listas en
    // otras clases estaría ya estaría.

    // @JsonIgnore //ignora el cliente por completo
    @JsonIgnoreProperties(value = "{citas}") // ignora atributos específicos del cliente.
    @ManyToOne  //muchas citas tienen un mismo cliente
    @JoinColumn(name = "cliente_id", foreignKey = @ForeignKey(name = "fk_citas_clientes"))
    private Clientes cliente;

    // servicio
    @ManyToOne  //muchas citas tienen un mismo servicio
    @JoinColumn(name = "servicios_id", foreignKey = @ForeignKey(name = "fk_citas_servicios"))  //determina el nombre de la columna de la relación en la BD.
    private Servicios servicios;

    //empleados
    @ManyToOne  //muchas citas tiene un mismo empleado
    @JoinColumn(name = "empleado_id", foreignKey = @ForeignKey(name = "fk_citas_empleados"))
    private Empleados empleados;

    //constructores

    public Citas(){}

    public Citas(Long id, LocalDateTime fecha, Integer duracion, String description) {
        this.id = id;
        this.fecha = fecha;
        this.duracion = duracion;
        this.description = description;
    }

    // Getter and Setter y demás
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Servicios getServicios() {
        return servicios;
    }

    public void setServicios(Servicios servicios) {
        this.servicios = servicios;
    }

    public Empleados getEmpleados() {
        return empleados;
    }

    public void setEmpleados(Empleados empleados) {
        this.empleados = empleados;
    }

    @Override
    public String toString() {
        return "Citas{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", duracion=" + duracion +
                '}';
    }
}
