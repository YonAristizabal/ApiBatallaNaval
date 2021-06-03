package com.umanizales.apibatallanaval.service;

import com.umanizales.apibatallanaval.model.dto.RespuestaDTO;
import com.umanizales.apibatallanaval.model.entities.Usuario;
import com.umanizales.apibatallanaval.repository.UsuarioRepository;
import com.umanizales.apibatallanaval.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
//Clase que controla los servicios de los usuarios
public class UsuarioService {
    private UsuarioRepository usuarioRepository;
    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public ResponseEntity<Object> findAll()
    {
        return new ResponseEntity<>(new RespuestaDTO(Constants.SUCCESSFUL,
                usuarioRepository.findAll(),null), HttpStatus.OK);
    }

    public ResponseEntity<Object> create(Usuario usuario)
    {
        try
        {
            Usuario usuarioGuardado= usuarioRepository.save(usuario);
            return new ResponseEntity<>(new RespuestaDTO(Constants.SUCCESSFUL,
                   usuarioGuardado,null), HttpStatus.OK);
        }
        catch(Exception ex)
        {
            return new ResponseEntity<>(new RespuestaDTO(Constants.ERROR,
                    null,Constants.ERROR_PERSISTENCE_SAVE),
                    HttpStatus.CONFLICT);
        }
    }

    public ResponseEntity<Object> findUsersByRol(short codeRol)
    {
        return new ResponseEntity<>(new RespuestaDTO(Constants.SUCCESSFUL,
                usuarioRepository.obtenerUsuariosPorRol(codeRol),null), HttpStatus.OK);
    }
}
