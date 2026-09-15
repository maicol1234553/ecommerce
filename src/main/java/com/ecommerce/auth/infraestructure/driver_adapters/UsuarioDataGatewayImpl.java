package com.ecommerce.auth.infraestructure.driver_adapters;

import com.ecommerce.auth.domain.model.Usuario;
import com.ecommerce.auth.domain.model.gateway.UsuarioGateway;
import com.ecommerce.auth.infraestructure.mapper.UsuarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UsuarioDataGatewayImpl implements UsuarioGateway {

    private final UsuarioDataJpaRepository UsuarioDataJpaRepository ;
    private final UsuarioMapper usuarioMapper;

    @Override
    public Usuario guardarUsuario(Usuario usuario) {
        UsuarioData usuarioMapeado = usuarioMapper.toUsuarioData(usuario);

        Usuario usuarioGuardado = usuarioMapper
                .toUsuario(UsuarioDataJpaRepository.save(usuarioMapeado));

        return usuarioGuardado;
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
}