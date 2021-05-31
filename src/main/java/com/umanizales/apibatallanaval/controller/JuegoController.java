
package com.umanizales.apibatallanaval.controller;


import com.umanizales.apibatallanaval.model.dto.RespuestaDTO;
import com.umanizales.apibatallanaval.model.entities.Barco;
import com.umanizales.apibatallanaval.model.entities.Juego;
import com.umanizales.apibatallanaval.model.entities.Usuario;
import com.umanizales.apibatallanaval.repository.UsuarioRepository;
import com.umanizales.apibatallanaval.service.BarcoService;
import com.umanizales.apibatallanaval.service.JuegoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "juegos")
@Validated
//Clase para controlar el barco
public class JuegoController {
    private JuegoService juegoService;

    @Autowired
    public JuegoController(JuegoService juegoService) {
        this.juegoService = juegoService;
    }

    /**
    @GetMapping
    public @ResponseBody
    ResponseEntity<Object> findAll() {
        UsuarioRepository usuarioRepository=new U
        return new ResponseEntity<>(new RespuestaDTO("Exitoso",
                usuarioRepository.obtenerUsuariosPorRol(codeRol),null), HttpStatus.OK);
    }*/

    @PostMapping
    public @ResponseBody ResponseEntity<Object> create(@RequestBody Juego juego){
        return juegoService.create(juego);
    }
}