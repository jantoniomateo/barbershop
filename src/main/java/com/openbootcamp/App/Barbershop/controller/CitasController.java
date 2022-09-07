package com.openbootcamp.App.Barbershop.controller;

import com.openbootcamp.App.Barbershop.dto.BeneficiosDTO;
import com.openbootcamp.App.Barbershop.entities.Citas;
import com.openbootcamp.App.Barbershop.service.ServicioCitas;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")  //Con esta anotación indicamos que todas las rutas empiezan por api. http://localhost:8080/api
public class CitasController {

    //Atributo
    private final ServicioCitas servicioCitas;

    //Constructor

    public CitasController(ServicioCitas servicioCitas) {
        this.servicioCitas = servicioCitas;
    }


    //MÉTODOS CRUD

    /**
     * GET http://localhost:8080/api/citas
     * @return
     * Para éste caso la respuesta es la propia lista completa
     */
    @GetMapping("/citas")
    public List<Citas> findAll(){
        return servicioCitas.findAll();
    }

    @GetMapping("/citas/search/clientes/email/{email}")
    public List<Citas> findAllByClienteEmail(@PathVariable String email){
        return servicioCitas.findAllByClienteEmail(email);
    }

    @GetMapping("/citas/search/empleados/dni/{dni}")
    public List<Citas> findAllByDniEmpleados(@PathVariable String dni){
        return servicioCitas.findAllByDniEmpleados(dni);
    }

    @GetMapping("/citas/search/servicios/precio/{precio}")
    public List<Citas> findAllByServiciosPrecioLessThanEqual(@PathVariable Double precio){
        return servicioCitas.findAllByServiciosPrecioLessThanEqual(precio);
    }

    /**
     * Para éste caso la respuesta es de tipo entity
     * @return
     */
    @GetMapping("/citas/{id}")
    public ResponseEntity<Citas> findById(@PathVariable Long id){
        //Como findById nos devuelve un optional, recogemos el dato con una variable optional
        //para poder trabajar con ella
        Optional<Citas> citasOpt = servicioCitas.findById(id);
        if(citasOpt.isPresent()){
            return ResponseEntity.ok(citasOpt.get());}

        return ResponseEntity.notFound().build();
    }

    /**
     * Creamos la cita
     * POST en http://localhost:8080/api/citas
     * @param cita
     * @return
     */
    @PostMapping("/citas")
    public ResponseEntity<Citas> create(@RequestBody Citas cita){
        //comprobamos que la cita no esté creada
        if (cita.getId()!=null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(servicioCitas.save(cita));
    }

    /**
     * Actualizamos una cita existente
     * PUT http://api/citas
     * @param cita
     * @return
     */
    @PutMapping("/citas")
    public ResponseEntity<Citas> update(@RequestBody Citas cita){
        //comprobamos que la cita no esté creada
        if (cita.getId() == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(servicioCitas.save(cita));
    }

    /**
     * Borra de cita por id
    * DELETE http://api/citas
    */
    @DeleteMapping("/citas/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        boolean resultado = servicioCitas.deleteById(id);
        if (resultado)
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.badRequest().build();
    }

    @GetMapping("/citas/beneficios/{year}/{month}/{day}")
    public ResponseEntity<BeneficiosDTO> calcularBeneficioPorDia(@PathVariable int year, @PathVariable int month, @PathVariable int day){
        double beneficios = servicioCitas.calcularBeneficioPorDia(LocalDate.of(year,month,day));
        BeneficiosDTO beneficiosDTO = new BeneficiosDTO(beneficios);
        return ResponseEntity.ok(beneficiosDTO);

    }
    @GetMapping("/citas/beneficios/{year}/{month}")
    public ResponseEntity<BeneficiosDTO> calcularBeneficioPorMes(@PathVariable int year, @PathVariable int month){
        double beneficios = servicioCitas.calcularBeneficioPorMes(year, Month.of(month));
        BeneficiosDTO beneficiosDTO = new BeneficiosDTO(beneficios);
        return ResponseEntity.ok(beneficiosDTO);
    }
    @GetMapping("/citas/beneficios/{year}")
    public ResponseEntity<BeneficiosDTO> calcularBeneficioPorYear(@PathVariable int year) {
        double beneficios = servicioCitas.calcularBeneficioPorYear(year);
        BeneficiosDTO beneficiosDTO = new BeneficiosDTO(beneficios);
        return ResponseEntity.ok(beneficiosDTO);
    }
}
