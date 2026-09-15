package com.ecommerce.auth.infraestructure.entry_points;


import com.ecommerce.auth.domain.model.Usuario;
import com.ecommerce.auth.domain.usecase.UsuarioUseCase;
import com.ecommerce.auth.infraestructure.driver_adapters.UsuarioData;
import com.ecommerce.auth.infraestructure.mapper.UsuarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/ecommerce/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioUseCase usuarioUseCase;
    private final UsuarioMapper usuarioMapper;

    @PostMapping("/save")
    public ResponseEntity<Usuario> saveUsuario(@RequestBody UsuarioData usuarioData){
        Usuario usuario = usuarioMapper.toUsuario(usuarioData);
        Usuario usuarioGuardado = usuarioUseCase.guardarUsuario(usuario);

        if(usuarioGuardado.getIdUsuario() != null){
            return new ResponseEntity<>(usuarioGuardado, HttpStatus.OK);
        }

        return new ResponseEntity<>(usuarioGuardado, HttpStatus.CONFLICT);
    }

}