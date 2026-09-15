package com.ecommerce.auth.domain.usecase;

import com.ecommerce.auth.domain.model.Usuario;
import com.ecommerce.auth.domain.model.gateway.UsuarioGateway;
import lombok.RequiredArgsConstructor; // los inmutables

import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
public class UsuarioUseCase {

    private String connection;
    private final UsuarioGateway usuarioGateway;

    public Usuario guardarUsuario(Usuario usuario) {

        Objects.requireNonNull(usuario, "El usuario es obligatorio");

        Optional.ofNullable(usuario.getNombre())
                .filter(nombre -> !nombre.isBlank())
                .orElseThrow(() ->
                        new RuntimeException("El nombre es obligatorio"));

        Optional.ofNullable(usuario.getCorreo())
                .filter(correo -> !correo.isBlank())
                .filter(correo -> correo.contains("@"))
                .orElseThrow(() ->
                        new RuntimeException("El correo no es válido"));

        Optional.ofNullable(usuario.getPassword())
                .filter(password -> !password.isBlank())
                .filter(password -> password.length() >= 8)
                .orElseThrow(() ->
                        new RuntimeException(
                                "La contraseña debe tener mínimo 8 caracteres"));

        Optional.ofNullable(usuario.getRol())
                .filter(rol -> !rol.isBlank())
                .orElseThrow(() ->
                        new RuntimeException("El rol es obligatorio"));

        Optional.ofNullable(usuario.getEdad())
                .filter(edad -> edad > 0)
                .orElseThrow(() ->
                        new RuntimeException(
                                "La edad debe ser mayor que 0"));

        Optional.ofNullable(usuario.getNumeroTelefonico())
                .filter(telefono -> !telefono.isBlank())
                .orElseThrow(() ->
                        new RuntimeException(
                                "El número telefónico es obligatorio"));

        return usuarioGateway.guardarUsuario(usuario);
    }
}
