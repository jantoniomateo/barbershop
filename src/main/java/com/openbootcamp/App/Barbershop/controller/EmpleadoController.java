package com.openbootcamp.App.Barbershop.controller;

import com.openbootcamp.App.Barbershop.entities.Empleado;
import com.openbootcamp.App.Barbershop.service.EmpleadoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")  //Con esta anotación indicamos que todas las rutas empiezan por api. http://localhost:8080/api
public class EmpleadoController {

    //Atributo
    private final EmpleadoService empleadoService;

    //Constructor

    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }


    //MÉTODOS CRUD

    /**
     * GET http://localhost:8080/api/Empleados
     * @return
     * Para éste caso la respuesta es la propia lista completa
     */
    @GetMapping("/empleados")
    public List<Empleado> findAll(){
        return empleadoService.findAll();
    }

      /**
     * Para éste caso la respuesta es de tipo entity
     * @return
     */
    @GetMapping("/empleados/{id}")
    public ResponseEntity<Empleado> findById(@PathVariable Long id){
        //Como findById nos devuelve un optional, recogemos el dato con una variable optional
        //para poder trabajar con ella
        Optional<Empleado> EmpleadoOpt = empleadoService.findById(id);
        if(EmpleadoOpt.isPresent()){
            return ResponseEntity.ok(EmpleadoOpt.get());}

        return ResponseEntity.notFound().build();

        //Alternativa aplicar programación funcional
        //empleadoService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * Creamos la Empleado
     * POST en http://localhost:8080/api/Empleados
     * @param Empleado
     * @return
     */
    @PostMapping("/empleados")
    public ResponseEntity<Empleado> create(@RequestBody Empleado Empleado){
        //comprobamos que la Empleado no esté creada
        if (Empleado.getId()!=null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(empleadoService.save(Empleado));
    }

    /**
     * Actualizamos una Empleado existente
     * PUT http://api/Empleados
     * @param Empleado
     * @return
     */
    @PutMapping("/empleados")
    public ResponseEntity<Empleado> update(@RequestBody Empleado Empleado){
        //comprobamos que la Empleado no esté creada
        if (Empleado.getId() == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(empleadoService.save(Empleado));
    }

    /**
     * Borra de Empleado por id
    * DELETE http://api/Empleados
    */
    @DeleteMapping("/empleados/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        boolean resultado = empleadoService.deleteById(id);
        if (resultado)
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.badRequest().build();
    }

}
