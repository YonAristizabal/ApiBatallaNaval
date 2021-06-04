package com.umanizales.apibatallanaval.controller;

import com.umanizales.apibatallanaval.model.entities.Juego;
import com.umanizales.apibatallanaval.model.entities.JuegoBarco;
import com.umanizales.apibatallanaval.service.JuegoBarcoService;
import com.umanizales.apibatallanaval.service.JuegoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/juego/{juegoId}/barco")
@Validated
public class JuegoBarcoController {
    private JuegoBarcoService juegoBarcoService;

    @Autowired
    public JuegoBarcoController(JuegoBarcoService juegoBarcoService) {
        this.juegoBarcoService = juegoBarcoService;
    }

    @PostMapping
    public @ResponseBody
    ResponseEntity<Object> create(@PathVariable("juegoId") int juegoId,@RequestBody JuegoBarco juegoBarco){
        juegoBarco.setJuegoId(juegoId);
        return this.juegoBarcoService.crearCantidadBarcos(juegoBarco);
    }
}
