package com.umanizales.apibatallanaval.service;

import com.umanizales.apibatallanaval.model.dto.RespuestaDTO;
import com.umanizales.apibatallanaval.model.entities.Juego;
import com.umanizales.apibatallanaval.model.entities.TipoUsuario;
import com.umanizales.apibatallanaval.model.entities.Usuario;
import com.umanizales.apibatallanaval.repository.JuegoRepository;
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
    @Autowired
    public JuegoService(UsuarioRepository usuarioRepository, JuegoRepository juegoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.juegoRepository  = juegoRepository;
    }
    public ResponseEntity<Object> create(Juego juego)
    {
        try
        {
            Usuario usuario = this.usuarioRepository.obtenerUsuariosPorCorreoRol(juego.getCreadoPor(), TipoUsuario.TIPO_ADMINISTRADOR);
            if(usuario != null){
                juegoRepository.save(juego);
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