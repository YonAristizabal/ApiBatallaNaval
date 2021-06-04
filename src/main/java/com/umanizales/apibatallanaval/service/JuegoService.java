package com.umanizales.apibatallanaval.service;

import com.umanizales.apibatallanaval.model.dto.RespuestaDTO;
import com.umanizales.apibatallanaval.model.entities.Juego;
import com.umanizales.apibatallanaval.model.entities.Tablero;
import com.umanizales.apibatallanaval.model.entities.TipoUsuario;
import com.umanizales.apibatallanaval.model.entities.Usuario;
import com.umanizales.apibatallanaval.repository.JuegoRepository;
import com.umanizales.apibatallanaval.repository.TableroRepository;
import com.umanizales.apibatallanaval.repository.UsuarioRepository;
import com.umanizales.apibatallanaval.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class JuegoService {
    private UsuarioRepository usuarioRepository;
    private JuegoRepository juegoRepository;
    private TableroRepository tableroRepository;
    @Autowired
    public JuegoService(UsuarioRepository usuarioRepository, JuegoRepository juegoRepository,TableroRepository tableroRepository) {
        this.usuarioRepository = usuarioRepository;
        this.juegoRepository  = juegoRepository;
        this.tableroRepository = tableroRepository;
    }
    public ResponseEntity<Object> create(Juego juego)
    {
        /**int cantidadBarcos = JUegoBarco.getCantidadBarcos();
        for(int i=i<cantiudadBarcos;i++){
            JuegoBarco juegoBarcoTmp = New JuegoBarco();
            juegoBarcoTmp.setJuegoId(juegoBarco.getJuegoId());
            juegoBarcoTmp.setTipoBarco(juegoBarco.getTipoBarco());
            juegoBarcoRepository.save(juegoBarcoTmp);
        }


         */
        try
        {
            Usuario usuario = this.usuarioRepository.obtenerUsuariosPorCorreoRol(juego.getCreadoPor(), TipoUsuario.TIPO_ADMINISTRADOR);
            if(usuario != null){
                juegoRepository.save(juego);
                Tablero tablero1 =new Tablero();
                tablero1.calcularFilasColumnasDesdeBarcos(0);
                tablero1.setJuegoId(juego.getId());
                Tablero tablero2 =new Tablero();
                tablero2.calcularFilasColumnasDesdeBarcos(0);
                tablero2.setJuegoId(juego.getId());
                tableroRepository.save(tablero1);
                tableroRepository.save(tablero2);
                return new ResponseEntity<>(new RespuestaDTO(Constants.SUCCESSFUL,
                        juego,null), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(new RespuestaDTO(Constants.ERROR,
                        null,Constants.ERROR_USER_TYPE),
                        HttpStatus.UNAUTHORIZED);
            }
        }
        catch(Exception ex)
        {
            return new ResponseEntity<>(new RespuestaDTO(Constants.ERROR,
                    null,Constants.ERROR_SAVE_GAME),
                    HttpStatus.CONFLICT);
        }
    }
}