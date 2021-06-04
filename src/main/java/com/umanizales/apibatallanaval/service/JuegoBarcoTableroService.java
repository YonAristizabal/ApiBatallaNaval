package com.umanizales.apibatallanaval.service;

import com.umanizales.apibatallanaval.model.dto.RespuestaDTO;
import com.umanizales.apibatallanaval.model.entities.*;
import com.umanizales.apibatallanaval.repository.*;
import com.umanizales.apibatallanaval.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JuegoBarcoTableroService {
    JuegoBarcoTableroRepository juegoBarcoTableroRepository;
    JuegoBarcoTableroCoordenadaRepository juegoBarcoTableroCoordenadaRepository;
    BarcoRepository barcoRepository;
    TableroRepository tableroRepository;
    JuegoBarcoRepository juegoBarcoRepository;

    @Autowired
    public JuegoBarcoTableroService(JuegoBarcoTableroRepository juegoBarcoTableroRepository, JuegoBarcoTableroCoordenadaRepository juegoBarcoTableroCoordenadaRepository, BarcoRepository barcoRepository, TableroRepository tableroRepository, JuegoBarcoRepository juegoBarcoRepository) {
        this.juegoBarcoTableroRepository = juegoBarcoTableroRepository;
        this.juegoBarcoTableroCoordenadaRepository = juegoBarcoTableroCoordenadaRepository;
        this.barcoRepository = barcoRepository;
        this.tableroRepository = tableroRepository;
        this.juegoBarcoRepository = juegoBarcoRepository;
    }


    public ResponseEntity<Object> asociarBarcoTablero(JuegoBarcoTablero juegoBarcoTablero, JuegoBarcoTableroCoordenada juegoBarcoTableroCoordenada) {
        JuegoBarcoTablero juegoBarcoTablero1= this.juegoBarcoTableroRepository.getJuegoBarcoTableroPorTableroJuegoBarco(juegoBarcoTablero.getTableroId(),juegoBarcoTablero.getJuegoBarcoId());
        if (juegoBarcoTablero1 == null) {
            JuegoBarco juegoBarco = this.juegoBarcoRepository.getJuegoBarcoPorId(juegoBarcoTablero.getJuegoBarcoId());
            Barco barco = this.barcoRepository.getBarcoById(juegoBarco.getTipoBarco());
            Tablero tablero = this.tableroRepository.getTableroPorId(juegoBarcoTablero.getTableroId());
            if (tablero.sePuedeColocarBarco(juegoBarcoTableroCoordenada.getX(), juegoBarcoTableroCoordenada.getY(), barco.getNumeroCasillas(), juegoBarcoTablero.getOrientacion())) {
                boolean sePuedeInsertar = true;
                List<JuegoBarcoTableroCoordenada> juegoBarcoTableroCoordenadas = juegoBarcoTableroCoordenada.construirCoordenadas(barco.getNumeroCasillas(), juegoBarcoTablero.getOrientacion());
                for (JuegoBarcoTableroCoordenada juegoBarcoTableroCoordenada1 : juegoBarcoTableroCoordenadas) {
                    JuegoBarcoTableroCoordenada juegoBarcoTableroCoordenadaTmp = juegoBarcoTableroCoordenadaRepository.getJuegoBarcoTableroCoordenaPorTableroCoordenadas(juegoBarcoTablero.getTableroId(), juegoBarcoTableroCoordenada1.getX(), juegoBarcoTableroCoordenada1.getY());
                    if (juegoBarcoTableroCoordenadaTmp != null) {
                        sePuedeInsertar = false;
                        break;
                    }
                }
                if (sePuedeInsertar) {
                    juegoBarcoTableroRepository.save(juegoBarcoTablero);
                    for (JuegoBarcoTableroCoordenada juegoBarcoTableroCoordenada1 : juegoBarcoTableroCoordenadas) {
                        juegoBarcoTableroCoordenada1.setJuegoBarcoTablero(juegoBarcoTablero);
                        juegoBarcoTableroCoordenadaRepository.save(juegoBarcoTableroCoordenada1);
                    }
                    return new ResponseEntity<>(new RespuestaDTO(Constants.SUCCESSFUL,
                            Constants.MESSAGE_SUCCESSFUL, null), HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(new RespuestaDTO(Constants.ERROR,
                            null, "El barco no se puede insertar en el tablero,las coordenas ya estan ocupadas"),
                            HttpStatus.CONFLICT);
                }
            } else {
                return new ResponseEntity<>(new RespuestaDTO(Constants.ERROR,
                        null, "Las coordenas suministradas exceden el tamaño del tablero"),
                        HttpStatus.CONFLICT);
            }
        } else {
            return new ResponseEntity<>(new RespuestaDTO(Constants.ERROR,
                    null, Constants.ERROR_EXIST),
                    HttpStatus.CONFLICT);
        }
    }
}
