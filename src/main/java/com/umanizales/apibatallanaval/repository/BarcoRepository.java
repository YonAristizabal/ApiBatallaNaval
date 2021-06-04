package com.umanizales.apibatallanaval.repository;

import com.umanizales.apibatallanaval.model.entities.Barco;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

//Entrega los metodos genericos de los barcos
public interface BarcoRepository extends CrudRepository<Barco,Integer> {

    //Para realizar una consulta, es para dar el orden de los paramatros, como lo va llamando
    @Query("SELECT COUNT(barquito.id) FROM Barco barquito WHERE barquito.id<>?1 AND barquito.numeroCasillas=?2")
    int encontrarBarcoPorNumeroCasillas(int barcoActualId, short numeroCasillas);

    @Query("SELECT barquitos FROM Barco barquitos WHERE barquitos.id=?1")
    Barco getBarcoById(int id);

}
