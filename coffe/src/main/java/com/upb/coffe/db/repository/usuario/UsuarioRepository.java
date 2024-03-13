package com.upb.coffe.db.repository.usuario;


import com.upb.coffe.db.model.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    @Query(" SELECT u FROM Usuario u " +
            " WHERE u.estado = false AND " +
            " u.id =:id")
    Optional<Usuario> findByIdAndEstadoFalse(@Param("id") Long id);

    @Query(" SELECT u FROM Usuario u " +
            " WHERE u.estado = false AND " +
            " u.nombreUsuario = :nombreUsuario AND u.password = :password ")
    Optional<Usuario> findByNombreAndAndPasswordAndEstadoFalse(@Param("nombreUsuario") String nombreUsuario, @Param("password") String password);

}
