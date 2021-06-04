package com.umanizales.apibatallanaval.service;

import com.umanizales.apibatallanaval.model.dto.CasillaBarco;
import com.umanizales.apibatallanaval.model.dto.RespuestaDTO;
import com.umanizales.apibatallanaval.model.entities.Tablero;
import com.umanizales.apibatallanaval.model.entities.TipoUsuario;
import com.umanizales.apibatallanaval.model.entities.Usuario;
import com.umanizales.apibatallanaval.repository.TableroRepository;
import com.umanizales.apibatallanaval.repository.UsuarioRepository;
import com.umanizales.apibatallanaval.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.scanner.Constant;

@Service
public class TableroService {
    private UsuarioRepository usuarioRepository;
    private TableroRepository tableroRepository;
    private CasillaBarco[][] tableroBarcos;

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
                return new ResponseEntity<>(new RespuestaDTO(Constants.SUCCESSFUL,
                        tablero,null), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(new RespuestaDTO(Constants.ERROR_PERSISTENCE_SAVE,
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

    public ResponseEntity<Object> inicializarTablero(int filas, int cols)
    {
        if(filas <0 || cols <0)
        {
            return new ResponseEntity<>(
                    new RespuestaDTO(Constants.MESSAGE_ROWS_COLS_POSITIVE,null,
                            Constants.ERROR_ROWS_COLS_POSITIVE)
                    , HttpStatus.CONFLICT);
        }
        tableroBarcos = new CasillaBarco[filas][cols];
        return new ResponseEntity<>(
                new RespuestaDTO(Constants.SUCCESSFUL,null,null),HttpStatus.CREATED
        );
    }
}
