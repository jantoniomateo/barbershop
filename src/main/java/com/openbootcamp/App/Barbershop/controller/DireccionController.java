package com.openbootcamp.App.Barbershop.controller;

import com.openbootcamp.App.Barbershop.entities.Direccion;
import com.openbootcamp.App.Barbershop.service.DireccionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")  //Con esta anotación indicamos que todas las rutas empiezan por api. http://localhost:8080/api
public class DireccionController {

    //Atributo
    private final DireccionService direccionService;

    //Constructor

    public DireccionController(DireccionService direccionService) {
        this.direccionService = direccionService;
    }


    //MÉTODOS CRUD

    /**
     * GET http://localhost:8080/api/Direccions
     * @return
     * Para éste caso la respuesta es la propia lista completa
     */
    @GetMapping("/direcciones")
    public List<Direccion> findAll(){
        return direccionService.findAll();
    }

      /**
     * Para éste caso la respuesta es de tipo entity
     * @return
     */
    @GetMapping("/direcciones/{id}")
    public ResponseEntity<Direccion> findById(@PathVariable Long id){
        //Como findById nos devuelve un optional, recogemos el dato con una variable optional
        //para poder trabajar con ella
        Optional<Direccion> DireccionOpt = direccionService.findById(id);
        if(DireccionOpt.isPresent()){
            return ResponseEntity.ok(DireccionOpt.get());}

        return ResponseEntity.notFound().build();

        //Alternativa aplicar programación funcional
        //direccionService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * Creamos la Direccion
     * POST en http://localhost:8080/api/Direccions
     * @param Direccion
     * @return
     */
    @PostMapping("/direcciones")
    public ResponseEntity<Direccion> create(@RequestBody Direccion Direccion){
        //comprobamos que la Direccion no esté creada
        if (Direccion.getId()!=null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(direccionService.save(Direccion));
    }

    /**
     * Actualizamos una Direccion existente
     * PUT http://api/Direccions
     * @param Direccion
     * @return
     */
    @PutMapping("/direcciones")
    public ResponseEntity<Direccion> update(@RequestBody Direccion Direccion){
        //comprobamos que la Direccion no esté creada
        if (Direccion.getId() == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(direccionService.save(Direccion));
    }

    /**
     * Borra de Direccion por id
    * DELETE http://api/Direccions
    */
    @DeleteMapping("/direcciones/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        boolean resultado = direccionService.deleteById(id);
        if (resultado)
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.badRequest().build();
    }

}
