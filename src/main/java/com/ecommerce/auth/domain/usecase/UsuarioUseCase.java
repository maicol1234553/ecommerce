package com.ecommerce.auth.domain.usecase;

import com.ecommerce.auth.domain.model.Usuario;
import com.ecommerce.auth.domain.model.gateway.UsuarioGateway;
import lombok.RequiredArgsConstructor; // los inmutables

@RequiredArgsConstructor

//logica de negocio - logica
public class UsuarioUseCase {

    private final UsuarioGateway usuarioGateway;

    public Usuario guardarUsuario(Usuario usuario) {
        if (usuario.getCorreo() == null && usuario.getPassword() == null){
            throw new NullPointerException("dato nulo");
        }
        if (usuario.getEdad() < 18) {
            throw new RuntimeException("Usuario menor de edad");
        }
        Usuario usuarioGuardado = usuarioGateway.guardarUsuario(usuario);

        return usuarioGuardado;

    }
}