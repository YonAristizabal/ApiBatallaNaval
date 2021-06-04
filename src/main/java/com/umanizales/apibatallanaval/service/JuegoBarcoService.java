package com.umanizales.apibatallanaval.service;

import com.umanizales.apibatallanaval.model.dto.RespuestaDTO;
import com.umanizales.apibatallanaval.model.entities.JuegoBarco;
import com.umanizales.apibatallanaval.model.entities.Tablero;
import com.umanizales.apibatallanaval.repository.JuegoBarcoRepository;
import com.umanizales.apibatallanaval.repository.TableroRepository;
import com.umanizales.apibatallanaval.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JuegoBarcoService {
    private JuegoBarcoRepository juegoBarcoRepository;
    private TableroRepository tableroRepository;

    @Autowired
    public JuegoBarcoService(JuegoBarcoRepository juegoBarcoRepository,TableroRepository tableroRepository) {
        this.juegoBarcoRepository = juegoBarcoRepository;
        this.tableroRepository = tableroRepository;
    }

    public ResponseEntity<Object> crearCantidadBarcos(JuegoBarco juegoBarco) {
        int cantidadBarcos = juegoBarco.getCantidadBarcos();

        for (int i = 0; i < cantidadBarcos; i++) {
            JuegoBarco juegoBarcoTmp = new JuegoBarco();
            juegoBarcoTmp.setJuegoId(juegoBarco.getJuegoId());
            juegoBarcoTmp.setTipoBarco(juegoBarco.getTipoBarco());
            juegoBarcoRepository.save(juegoBarcoTmp);
        }
        List<Tablero> tableros = tableroRepository.obtenerTablerosPorJuego(juegoBarco.getJuegoId());
        int cantidadTotalBarcos = juegoBarcoRepository.cantidadBarcosPorJuego(juegoBarco.getJuegoId());
        for (Tablero t:tableros) {
            t.calcularFilasColumnasDesdeBarcos(cantidadTotalBarcos);
            tableroRepository.save(t);
        }
        return new ResponseEntity<>(new RespuestaDTO(Constants.SUCCESSFUL,
                juegoBarco,null), HttpStatus.OK);
    }
}