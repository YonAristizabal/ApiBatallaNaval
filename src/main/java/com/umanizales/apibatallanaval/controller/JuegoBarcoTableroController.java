package com.umanizales.apibatallanaval.controller;


import com.umanizales.apibatallanaval.model.entities.JuegoBarco;
import com.umanizales.apibatallanaval.model.entities.JuegoBarcoTablero;
import com.umanizales.apibatallanaval.model.entities.JuegoBarcoTableroCoordenada;
import com.umanizales.apibatallanaval.service.JuegoBarcoTableroCoordenadaService;
import com.umanizales.apibatallanaval.service.JuegoBarcoTableroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/tablero/{tableroId}/barco/{juegoBarcoId}/coordenada")
@Validated
public class JuegoBarcoTableroController {
    JuegoBarcoTableroService juegoBarcoTableroService;
    JuegoBarcoTableroCoordenadaService juegoBarcoTableroCoordenadaService;

    @Autowired
    public JuegoBarcoTableroController(JuegoBarcoTableroService juegoBarcoTableroService, JuegoBarcoTableroCoordenadaService juegoBarcoTableroCoordenadaService) {
        this.juegoBarcoTableroService = juegoBarcoTableroService;
        this.juegoBarcoTableroCoordenadaService = juegoBarcoTableroCoordenadaService;
    }


    @PostMapping
    public @ResponseBody
    ResponseEntity<Object> create(@PathVariable("tableroId") int tableroId,@PathVariable("juegoBarcoId") int juegoBarcoId,
                                  @RequestBody JuegoBarcoTablero juegoBarcoTablero){
        juegoBarcoTablero.setJuegoBarcoId(juegoBarcoId);
        juegoBarcoTablero.setTableroId(tableroId);
        JuegoBarcoTableroCoordenada juegoBarcoTableroCoordenada = new JuegoBarcoTableroCoordenada(false, juegoBarcoTablero.getX(), juegoBarcoTablero.getY());
        return this.juegoBarcoTableroService.asociarBarcoTablero(juegoBarcoTablero,juegoBarcoTableroCoordenada);
    }
}
