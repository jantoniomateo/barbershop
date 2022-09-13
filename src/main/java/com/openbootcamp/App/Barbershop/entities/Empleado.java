package com.openbootcamp.App.Barbershop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;


@Entity
@Table(name = "empleados")
public class Empleado implements Serializable {

    // Atributos
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 40, name = "nombre")
    private String nombre;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "NAF", nullable = false, unique = true)
    private String naf;

    @Column(name = "DNI", nullable = false, unique = true)
    private String dni;

    //En el JoinColumn indicamos unique = true para asegurar que la relación es uno a uno, es decir
    // que una direccion no sea asignada a varios empleados. Si no ponemos ese unique podremos asignar
    // una misma direccion a varios empleados.
    /*
    Con cascadeType.All podemos hacer que cuando guardemos el empleado automaticamente tambien se guarde
    la dirección o bien si eliminamos un empleado también se elimine la dirección. De esta manera no tenemos
    porque guardar previamente la dirección como indicábamos en el main para luego poder guardar esa dirección
    en el empleado.
  */
    /**
     * Si indicamos FetchType.LAZY, le indicamos que solo vamos a recuperar los datos del empleado
     * pero para ello además debemos indicarle con JsonIgnore que ignore Json, de lo contrario nos
     * dará error, por tanto deben ir de la mano ambos comandos. Si lo dejamos solo con el JsonIgnore
     * no se presentarán los datos de la dirección pero hará una consulta a la base de datos de dirección
     * por lo que estaremos consumiendo tiempo en la ejecución del software, cosa que impediro con LAZY.
     */
    //@JsonIgnore
    //@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "direccion_id", foreignKey = @ForeignKey(name = "fk_empleado_direccion"), unique = true)
    public Direccion direccion;
    public Empleado(){}

    public Empleado(Long id, String nombre, String apellidos, String email, LocalDate fechaNacimiento, String naf, String dni) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.naf = naf;
        this.dni = dni;
    }

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

    public String getNaf() {
        return naf;
    }

    public void setNaf(String naf) {
        this.naf = naf;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Direccion getDireccion() {
        return direccion;
    }
    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Empleados{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", email='" + email + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", naf='" + naf + '\'' +
                ", dni='" + dni + '\'' +
                '}';
    }
}
