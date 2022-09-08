package com.openbootcamp.App.Barbershop.controller;

import com.openbootcamp.App.Barbershop.dto.BeneficiosDTO;
import com.openbootcamp.App.Barbershop.entities.Cliente;
import com.openbootcamp.App.Barbershop.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")  //Con esta anotación indicamos que todas las rutas empiezan por api. http://localhost:8080/api
public class ClienteController {

    //Atributo
    private final ClienteService clienteService;

    //Constructor

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }


    //MÉTODOS CRUD

    /**
     * GET http://localhost:8080/api/Clientes
     * @return
     * Para éste caso la respuesta es la propia lista completa
     */
    @GetMapping("/clientes")
    public List<Cliente> findAll(){
        return clienteService.findAll();
    }

      /**
     * Para éste caso la respuesta es de tipo entity
     * @return
     */
    @GetMapping("/clientes/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable Long id){
        //Como findById nos devuelve un optional, recogemos el dato con una variable optional
        //para poder trabajar con ella
        Optional<Cliente> ClienteOpt = clienteService.findById(id);
        if(ClienteOpt.isPresent()){
            return ResponseEntity.ok(ClienteOpt.get());}

        return ResponseEntity.notFound().build();

        //Alternativa aplicar programación funcional
        //clienteService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * Creamos la Cliente
     * POST en http://localhost:8080/api/Clientes
     * @param Cliente
     * @return
     */
    @PostMapping("/clientes")
    public ResponseEntity<Cliente> create(@RequestBody Cliente Cliente){
        //comprobamos que la Cliente no esté creada
        if (Cliente.getId()!=null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(clienteService.save(Cliente));
    }

    /**
     * Actualizamos una Cliente existente
     * PUT http://api/Clientes
     * @param Cliente
     * @return
     */
    @PutMapping("/Clientes")
    public ResponseEntity<Cliente> update(@RequestBody Cliente Cliente){
        //comprobamos que la Cliente no esté creada
        if (Cliente.getId() == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(clienteService.save(Cliente));
    }

    /**
     * Borra de Cliente por id
    * DELETE http://api/Clientes
    */
    @DeleteMapping("/Clientes/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        boolean resultado = clienteService.deleteById(id);
        if (resultado)
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.badRequest().build();
    }

}
