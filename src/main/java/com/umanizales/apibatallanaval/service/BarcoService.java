package com.umanizales.apibatallanaval.service;

import com.umanizales.apibatallanaval.model.dto.RespuestaDTO;
import com.umanizales.apibatallanaval.model.entities.Barco;
import com.umanizales.apibatallanaval.repository.BarcoRepository;
import com.umanizales.apibatallanaval.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
//Clase para implementar el servicio del Barco
public class BarcoService {
    private BarcoRepository barcoRepository;

    @Autowired
    public BarcoService(BarcoRepository barcoRepository) {
        this.barcoRepository = barcoRepository;
    }

    public ResponseEntity<Object> findAll() {
        return new ResponseEntity<>(new RespuestaDTO(Constants.SUCCESSFUL,
                barcoRepository.findAll(), null), HttpStatus.OK);
    }

    public ResponseEntity<Object> create(Barco barco) {
        try {
            //Consultar si ya existe un barco con ese número de casilla
            int cantidadBarcos= barcoRepository.encontrarBarcoPorNumeroCasillas(-1,barco.getNumeroCasillas());
            if (cantidadBarcos == 0) {
                Barco barcoGuardado = barcoRepository.save(barco);
                return new ResponseEntity<>(new RespuestaDTO(Constants.SUCCESSFUL,
                        barcoGuardado, null), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new RespuestaDTO(Constants.ERROR,
                        null, Constants.ERROR_DUPLICATE_BOX),
                        HttpStatus.CONFLICT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(new RespuestaDTO(Constants.ERROR,
                    null, Constants.ERROR_PERSISTENCE_SAVE),
                    HttpStatus.CONFLICT);
        }
    }

    public ResponseEntity<Object> deleteBarcoById(int id)
    {
        if(barcoRepository.existsById(id))
        {
            barcoRepository.deleteById(id);
            return new ResponseEntity<>(new RespuestaDTO(Constants.SUCCESSFUL,
                    Constants.MESSAGE_SUCCESSFUL, null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new RespuestaDTO(Constants.ERROR,
                null, Constants.ERROR_PERSISTENCE_DELETE),
                HttpStatus.CONFLICT);
    }

    public ResponseEntity<Object> updateBarco(Barco barco) {

        int cantidadBarcos = barcoRepository.encontrarBarcoPorNumeroCasillas(barco.getId(),barco.getNumeroCasillas());
        if (cantidadBarcos == 0) {
            if (barcoRepository.existsById(barco.getId())) {
                try {
                    Barco barcoGuardado = barcoRepository.save(barco);
                    return new ResponseEntity<>(new RespuestaDTO(Constants.SUCCESSFUL,
                            barcoGuardado, null), HttpStatus.OK);
                } catch (Exception ex) {
                    return new ResponseEntity<>(new RespuestaDTO(Constants.ERROR_PERSISTENCE_SAVE,
                            null, Constants.ERROR_PERSISTENCE_SAVE),
                            HttpStatus.UNAUTHORIZED);
                }
            } else {
                return new ResponseEntity<>(new RespuestaDTO(Constants.ERROR_PERSISTENCE_SAVE,
                        null, Constants.MESSAGE_ROWS_COLS_POSITIVE),
                        HttpStatus.CONFLICT);
            }
        }
            else
                {
                    return new ResponseEntity<>(new RespuestaDTO(Constants.ERROR,
                            null, Constants.ERROR_DUPLICATE_BOX),
                            HttpStatus.CONFLICT);
                }
        }
}


