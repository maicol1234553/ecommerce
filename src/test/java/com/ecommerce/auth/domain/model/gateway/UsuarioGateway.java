package com.ecommerce.auth.domain.model.gateway;

import com.ecommerce.auth.domain.model.Usuario;
public interface UsuarioGateway {

    //contrato de las 4 APIs (contrato es la funcion que debe de cumplir)
    Usuario guardarUsuario(Usuario usuario);

    Usuario buscarUsuarioPorId(String usuarioId);

    Usuario actualizarUsuario(Usuario usuario);

    void eliminarUsuario(String usuarioId);
}
