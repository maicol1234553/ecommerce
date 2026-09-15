package com.ecommerce.auth.domain.usecase;

import com.ecommerce.auth.domain.model.Usuario;
import com.ecommerce.auth.domain.model.gateway.UsuarioGateway;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class UsuarioUseCaseTest {

    @Test
    void LanzarExcepcion() {

        UsuarioGateway gateway = new UsuarioGateway() {

            @Override
            public Usuario guardarUsuario(Usuario usuario) {
                return usuario;
            }

            @Override
            public Usuario buscarUsuarioPorId(String usuarioId) {
                return null;
            }

            @Override
            public Usuario actualizarUsuario(Usuario usuario) {
                return null;
            }

            @Override
            public void eliminarUsuario(String usuarioId) {
            }
        };

        UsuarioUseCase useCase = new UsuarioUseCase(gateway);

        // Creamos un usuario con el nombre vacío
        Usuario usuario = new Usuario(
                "1",
                "sharik",
                "sharikgonzalezb@gmail.com",
                "",
                "admin",
                20,
                "3001234567"
        );

        RuntimeException excepcion = assertThrows(
                RuntimeException.class,
                () -> useCase.guardarUsuario(usuario)
        );

        System.out.println("Excepción: " + excepcion.getMessage());
    }
}