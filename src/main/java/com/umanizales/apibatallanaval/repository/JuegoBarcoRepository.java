package com.umanizales.apibatallanaval.repository;

import com.umanizales.apibatallanaval.model.entities.JuegoBarco;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface JuegoBarcoRepository extends CrudRepository<JuegoBarco,Integer> {

    @Query("SELECT COUNT(juegoBarco.id) FROM JuegoBarco juegoBarco WHERE juegoBarco.juegoId=?1")
    int cantidadBarcosPorJuego(int juegoId);

    @Query("SELECT juegoBarco FROM JuegoBarco juegoBarco WHERE juegoBarco.id=?1")
    JuegoBarco getJuegoBarcoPorId(int id);
}
