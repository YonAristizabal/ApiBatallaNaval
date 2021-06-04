package com.umanizales.apibatallanaval.repository;


import com.umanizales.apibatallanaval.model.entities.JuegoBarco;
import com.umanizales.apibatallanaval.model.entities.Tablero;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TableroRepository extends CrudRepository<Tablero,Integer> {

    @Query("SELECT tablero FROM Tablero tablero where tablero.juegoId=?1")
    //Obtener una lista de los tableros actuales
    List<Tablero> obtenerTablerosPorJuego(int juegoId);
    @Query("SELECT tablero FROM Tablero tablero where tablero.id=?1")
        //Obtener una lista de los tableros actuales
    Tablero getTableroPorId(int id);
}
