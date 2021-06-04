package com.umanizales.apibatallanaval.repository;


import com.umanizales.apibatallanaval.model.entities.Tablero;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TableroRepository extends CrudRepository<Tablero,Integer> {
    //Traera todos los usuarios por el rol que se de por parametro
    @Query("SELECT tablero FROM Tablero tablero where tablero.juegoId=?1")
    //Para obtener por tipo de usuario , 1 - Administrador, 2 - Jugador
    List<Tablero> obtenerTablerosPorJuego(int juegoId);
}
