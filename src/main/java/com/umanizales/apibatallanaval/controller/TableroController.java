package com.umanizales.apibatallanaval.controller;


import com.umanizales.apibatallanaval.model.entities.Juego;
import com.umanizales.apibatallanaval.service.JuegoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/juegos/{idJuego}/tableros")
@Validated
public class TableroController {
    private JuegoService juegoService;

    @Autowired
    public TableroController(JuegoService juegoService) {
        this.juegoService = juegoService;
    }
    @PostMapping
    public @ResponseBody
    ResponseEntity<Object> create(@PathVariable("idJuego") long idJuego,@RequestBody Juego juego){
        System.out.println(idJuego);
        return juegoService.create(juego);
    }
}
