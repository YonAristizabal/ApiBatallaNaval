package com.umanizales.apibatallanaval.service;

import com.umanizales.apibatallanaval.model.dto.RespuestaDTO;
import com.umanizales.apibatallanaval.model.entities.Tablero;
import com.umanizales.apibatallanaval.model.entities.TipoUsuario;
import com.umanizales.apibatallanaval.model.entities.Usuario;
import com.umanizales.apibatallanaval.repository.TableroRepository;
import com.umanizales.apibatallanaval.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TableroService {
    private UsuarioRepository usuarioRepository;
    private TableroRepository tableroRepository;

    @Autowired
    public TableroService(UsuarioRepository usuarioRepository, TableroRepository tableroRepository) {
        this.usuarioRepository = usuarioRepository;
        this.tableroRepository  = tableroRepository;
    }
    public ResponseEntity<Object> create(Tablero tablero)
    {
        try
        {
            Usuario usuario = this.usuarioRepository.obtenerUsuariosPorCorreoRol(tablero.getCreadoPor(), TipoUsuario.TIPO_ADMINISTRADOR);
            if(usuario != null){
                tableroRepository.save(tablero);
                return new ResponseEntity<>(new RespuestaDTO("Exitoso",
                        tablero,null), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(new RespuestaDTO("Error",
                        null,"El usuario no es de tipo administrador"),
                        HttpStatus.UNAUTHORIZED);
            }
        }
        catch(Exception ex)
        {
            return new ResponseEntity<>(new RespuestaDTO("Error",
                    null,"Ocurrió un error almacenando el juego"),
                    HttpStatus.CONFLICT);
        }
    }
}
