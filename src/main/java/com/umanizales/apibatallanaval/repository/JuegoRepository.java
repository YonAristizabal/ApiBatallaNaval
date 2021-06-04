package com.umanizales.apibatallanaval.repository;

import com.umanizales.apibatallanaval.model.entities.Juego;
import com.umanizales.apibatallanaval.model.entities.Tablero;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface JuegoRepository  extends CrudRepository<Juego,Integer> {

/**
    @Query("SELECT tablero FROM Tablero tablero where tablero.juegoId=?1")
        //Obtener una lista de los tableros actuales
    List<Tablero> obtenerTablerosPorJuego(int juegoId);
*/
}
