package com.ecommerce.auth.domain.usecase;

import com.ecommerce.auth.domain.model.Usuario;
import com.ecommerce.auth.domain.model.exceptions.DatosUsuarioInvalidoException;
import com.ecommerce.auth.domain.model.exceptions.UsuarioNoEncontradoException;
import com.ecommerce.auth.domain.model.gateway.UsuarioGateway;
import lombok.RequiredArgsConstructor;

import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
//logica de negocio - logica
public class UsuarioUseCase {

    private final UsuarioGateway usuarioGateway;

    public Usuario guardarUsuario(Usuario usuario) {
        validarUsuario(usuario);

        return usuarioGateway.guardarUsuario(usuario);
    }

    public Usuario buscarUsuarioPorId(String usuarioId) {
        Usuario usuario = usuarioGateway.buscarUsuarioPorId(usuarioId);

        if (usuario == null) {
            throw new UsuarioNoEncontradoException("Usuario no encontrado con id: " + usuarioId);
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
        Objects.requireNonNull(usuario, "El usuario es obligatorio");

        Optional.ofNullable(usuario.getNombre())
                .filter(nombre -> !nombre.isBlank())
                .orElseThrow(() -> new DatosUsuarioInvalidoException("El nombre es obligatorio"));

        Optional.ofNullable(usuario.getCorreo())
                .filter(correo -> correo.contains("@"))
                .orElseThrow(() -> new DatosUsuarioInvalidoException("El correo no es válido"));

        Optional.ofNullable(usuario.getPassword())
                .filter(password -> password.length() >= 8)
                .orElseThrow(() -> new DatosUsuarioInvalidoException("La contraseña debe tener mínimo 8 caracteres"));

        Optional.ofNullable(usuario.getRol())
                .filter(rol -> !rol.isBlank())
                .orElseThrow(() -> new DatosUsuarioInvalidoException("El rol es obligatorio"));

        Optional.ofNullable(usuario.getEdad())
                .filter(edad -> edad >= 18)
                .orElseThrow(() -> new DatosUsuarioInvalidoException("Usuario menor de edad"));

        Optional.ofNullable(usuario.getNumeroTelefonico())
                .filter(telefono -> !telefono.isBlank())
                .orElseThrow(() -> new DatosUsuarioInvalidoException("El número telefónico es obligatorio"));
    }
}
