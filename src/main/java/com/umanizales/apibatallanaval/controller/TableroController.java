package com.umanizales.apibatallanaval.controller;

import com.umanizales.apibatallanaval.model.entities.Tablero;
import com.umanizales.apibatallanaval.service.TableroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/juegos/{juegoId}/tableros")
//@RequestMapping(path = "/tableros")
@Validated
public class TableroController {
    private TableroService tableroService;

    @Autowired
    public TableroController(TableroService tableroService) {
        this.tableroService = tableroService;
    }

    @PostMapping
    public @ResponseBody ResponseEntity<Object> create(@PathVariable("juegoId") int juegoId,@RequestBody Tablero tablero){
        tablero.setJuegoId(juegoId);
        return tableroService.create(tablero);
    }

    @PostMapping(path = "iniciar_tablero")
    public @ResponseBody  ResponseEntity<Object> iniciarTablero(@RequestBody Tablero tablero)
    {
        return tableroService.inicializarTablero(tablero.getFilas(), tablero.getCols());
    }


}
