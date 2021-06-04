package com.umanizales.apibatallanaval.repository;

import com.umanizales.apibatallanaval.model.entities.JuegoBarcoTablero;
import com.umanizales.apibatallanaval.model.entities.JuegoBarcoTableroCoordenada;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface JuegoBarcoTableroRepository extends CrudRepository<JuegoBarcoTablero,Integer> {
    @Query("SELECT juegoBarcoTablero FROM JuegoBarcoTablero juegoBarcoTablero where juegoBarcoTablero.tableroId=?1 AND juegoBarcoTablero.juegoBarcoId=?2")
        //Obtener una lista de los tableros actuales
    JuegoBarcoTablero getJuegoBarcoTableroPorTableroJuegoBarco(int tableroId, int juegoBarcoId);
}
