package com.umanizales.apibatallanaval.controller;

import com.umanizales.apibatallanaval.model.entities.Barco;
import com.umanizales.apibatallanaval.service.BarcoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "barco")
@Validated
//Clase para controlar el barco
public class BarcoController {
    private BarcoService barcoService;

    @Autowired
    public BarcoController (BarcoService barcoService) {
        this.barcoService = barcoService;
    }

    @GetMapping
    public @ResponseBody
    ResponseEntity<Object> findAll() {

        return barcoService.findAll();
    }

    @PostMapping
    public @ResponseBody ResponseEntity<Object> create(@RequestBody Barco barco){
        return barcoService.create(barco);
    }

    @PutMapping(path = "/{id}")
    public @ResponseBody ResponseEntity<Object> updateBarco(@PathVariable("id") int id,@RequestBody Barco barco)
    {
        barco.setId(id);
        return barcoService.updateBarco(barco);
    }

    @DeleteMapping(path="/{id}")
    public @ResponseBody ResponseEntity<Object> deleteBarco(@PathVariable("id") int id)
    {
        return barcoService.deleteBarcoById(id);
    }

}
