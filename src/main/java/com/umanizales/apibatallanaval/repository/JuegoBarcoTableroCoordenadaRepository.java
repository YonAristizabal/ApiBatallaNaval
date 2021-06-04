package com.umanizales.apibatallanaval.repository;


import com.umanizales.apibatallanaval.model.entities.JuegoBarcoTableroCoordenada;
import com.umanizales.apibatallanaval.model.entities.Tablero;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface JuegoBarcoTableroCoordenadaRepository extends CrudRepository<JuegoBarcoTableroCoordenada,Integer> {

    @Query("SELECT juegoBarcoTableroCoordenada FROM JuegoBarcoTableroCoordenada juegoBarcoTableroCoordenada  where juegoBarcoTableroCoordenada.juegoBarcoTablero.tableroId=?1 AND juegoBarcoTableroCoordenada.x=?2 AND juegoBarcoTableroCoordenada.y=?3")
        //Obtener una lista de los tableros actuales
    JuegoBarcoTableroCoordenada getJuegoBarcoTableroCoordenaPorTableroCoordenadas(int tableroId,int x, int y);
}
