package com.umanizales.apibatallanaval.repository;

import com.umanizales.apibatallanaval.model.entities.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UsuarioRepository  extends CrudRepository<Usuario,Integer> {

    //Traera todos los usuarios por el rol que se de por parametro
    @Query("SELECT usuario FROM Usuario usuario where usuario.tipoUsuario.codigo=?1")
    //Para obtener por tipo de usuario , 1 - Administrador, 2 - Jugador
    List<Usuario> obtenerUsuariosPorRol(short codigoRol);


    //Traera todos los usuarios por el rol que se de por parametro
    @Query("SELECT usuario FROM Usuario usuario where usuario.id=?1 AND usuario.tipoUsuario.codigo=?2")
    //Para obtener por tipo de usuario , 1 - Administrador, 2 - Jugador
    Usuario obtenerUsuariosPorCorreoRol(int id, short codigoRol);
}


