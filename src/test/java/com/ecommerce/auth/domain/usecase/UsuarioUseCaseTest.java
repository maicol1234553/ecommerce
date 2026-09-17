package com.ecommerce.auth.domain.usecase;

import com.ecommerce.auth.domain.model.Usuario;
import com.ecommerce.auth.domain.model.exceptions.DatosUsuarioInvalidoException;
import com.ecommerce.auth.domain.model.exceptions.UsuarioNoEncontradoException;
import com.ecommerce.auth.domain.model.gateway.UsuarioGateway;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class UsuarioUseCaseTest {

    private final UsuarioGateway gateway = new UsuarioGateway() {

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
            return usuario;
        }

        @Override
        public void eliminarUsuario(String usuarioId) {
        }
    };

    private final UsuarioUseCase useCase = new UsuarioUseCase(gateway);

    @Test
    void lanzarExcepcionCuandoLaPasswordEsInvalida() {
        Usuario usuario = new Usuario(
                "1",
                "sharik",
                "sharikgonzalezb@gmail.com",
                "",
                "admin",
                20,
                "3001234567"
        );

        assertThrows(
                DatosUsuarioInvalidoException.class,
                () -> useCase.guardarUsuario(usuario)
        );
    }

    @Test
    void lanzarExcepcionCuandoElUsuarioNoExiste() {
        assertThrows(
                UsuarioNoEncontradoException.class,
                () -> useCase.buscarUsuarioPorId("999")
        );
    }
}
