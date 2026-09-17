package com.ecommerce.auth.domain.usecase;

import com.ecommerce.auth.domain.model.Usuario;
import com.ecommerce.auth.domain.model.gateway.UsuarioGateway;

public class UsuarioUseCase {

    private final UsuarioGateway usuarioGateway;

    public UsuarioUseCase(UsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    public Usuario guardarUsuario(Usuario usuario) {
        validarUsuario(usuario);

        return usuarioGateway.guardarUsuario(usuario);
    }

    public Usuario buscarUsuarioPorId(String usuarioId) {

        Usuario usuario = usuarioGateway.buscarUsuarioPorId(usuarioId);

        if (usuario == null) {
            throw new NullPointerException("Usuario no encontrado");
        }

        return usuario;
    }

    public Usuario actualizarUsuario(Usuario usuario) {

        validarUsuario(usuario);

        buscarUsuarioPorId(usuario.getIdUsuario());

        return usuarioGateway.actualizarUsuario(usuario);
    }

    public void eliminarUsuario(String usuarioId) {

        buscarUsuarioPorId(usuarioId);

        usuarioGateway.eliminarUsuario(usuarioId);
    }

    private void validarUsuario(Usuario usuario) {

        if (usuario == null) {
            throw new RuntimeException("El usuario es obligatorio");
        }

        if (usuario.getNombre() == null ||
                usuario.getNombre().isBlank()) {

            throw new RuntimeException("El nombre es obligatorio");
        }

        if (usuario.getCorreo() == null ||
                !usuario.getCorreo().contains("@")) {

            throw new RuntimeException("El correo no es válido");
        }

        if (usuario.getPassword() == null ||
                usuario.getPassword().length() < 8) {

            throw new RuntimeException(
                    "La contraseña debe tener mínimo 8 caracteres"
            );
        }

        if (usuario.getRol() == null ||
                usuario.getRol().isBlank()) {

            throw new RuntimeException("El rol es obligatorio");
        }

        if (usuario.getEdad() == null || usuario.getEdad() < 18) {

            throw new RuntimeException("Usuario menor de edad");
        }

        if (usuario.getNumeroTelefonico() == null ||
                usuario.getNumeroTelefonico().isBlank()) {

            throw new RuntimeException(
                    "El número telefónico es obligatorio"
            );
        }
    }
}