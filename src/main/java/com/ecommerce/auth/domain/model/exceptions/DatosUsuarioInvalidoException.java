package com.ecommerce.auth.domain.model.exceptions;

public class DatosUsuarioInvalidoException extends RuntimeException {

    public DatosUsuarioInvalidoException(String mensaje) {
        super(mensaje);
    }
}
