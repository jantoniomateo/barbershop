package com.openbootcamp.App.Barbershop.controller;

import com.openbootcamp.App.Barbershop.entities.Servicio;
import com.openbootcamp.App.Barbershop.service.ServicioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")  //Con esta anotación indicamos que todas las rutas empiezan por api. http://localhost:8080/api
public class ServicioController {

    //Atributo
    private final ServicioService servicioService;

    //Constructor

    public ServicioController(ServicioService servicioService) {
        this.servicioService = servicioService;
    }


    //MÉTODOS CRUD

    /**
     * GET http://localhost:8080/api/Servicios
     * @return
     * Para éste caso la respuesta es la propia lista completa
     */
    @GetMapping("/servicios")
    public List<Servicio> findAll(){
        return servicioService.findAll();
    }

      /**
     * Para éste caso la respuesta es de tipo entity
     * @return
     */
    @GetMapping("/servicios/{id}")
    public ResponseEntity<Servicio> findById(@PathVariable Long id){
        //Como findById nos devuelve un optional, recogemos el dato con una variable optional
        //para poder trabajar con ella
        Optional<Servicio> ServicioOpt = servicioService.findById(id);
        if(ServicioOpt.isPresent()){
            return ResponseEntity.ok(ServicioOpt.get());}

        return ResponseEntity.notFound().build();

        //Alternativa aplicar programación funcional
        //servicioService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * Creamos la Servicio
     * POST en http://localhost:8080/api/Servicios
     * @param Servicio
     * @return
     */
    @PostMapping("/servicios")
    public ResponseEntity<Servicio> create(@RequestBody Servicio Servicio){
        //comprobamos que la Servicio no esté creada
        if (Servicio.getId()!=null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(servicioService.save(Servicio));
    }

    /**
     * Actualizamos una Servicio existente
     * PUT http://api/Servicios
     * @param Servicio
     * @return
     */
    @PutMapping("/servicios")
    public ResponseEntity<Servicio> update(@RequestBody Servicio Servicio){
        //comprobamos que la Servicio no esté creada
        if (Servicio.getId() == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(servicioService.save(Servicio));
    }

    /**
     * Borra de Servicio por id
    * DELETE http://api/Servicios
    */
    @DeleteMapping("/servicios/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        boolean resultado = servicioService.deleteById(id);
        if (resultado)
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.badRequest().build();
    }

}
