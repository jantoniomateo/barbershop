package com.openbootcamp.App.Barbershop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {

    //Atributos

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 40, name = "nombre")
    private String nombre;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "descripcion")
    private String descripcion;


    //asociaciones
    /* por defecto las asociaciones many son lazy (lazy significa que solo se recupera el dato de la base de datos cuando
    se accede al atributo*/
    /*
    indicamos que para cuando saquemos los datos de clientes, no nos saque de las citas de esos clientes
    de nuevo los clientes asociados a esas citas para no estar duplicando información. El valor de JsonIgnoreProperties
    es una array de datos por eso va entre llaves, y en éste caso sólo vamos a indicar que no incluya un único atributo
    que es el de cliente. Ignora el cliente de la cita.
     */
    //@JsonIgnore //ignora la lista de citas y solo sacaría los datos del cliente realizando solo una consulta de hibernate.
    @JsonIgnoreProperties (value = {"cliente"})
    @OneToMany(mappedBy = "cliente")
    private List<Cita> citas = new ArrayList<>();

    //constructores
    public Cliente(){}

    public Cliente(Long id, String nombre, String apellidos, String email, LocalDate fechaNacimiento) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
    }

    // Getter y Setter y demás

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public List<Cita> getCitas() {
        return citas;
    }

    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Clientes{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", email='" + email + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                '}';
    }
}
