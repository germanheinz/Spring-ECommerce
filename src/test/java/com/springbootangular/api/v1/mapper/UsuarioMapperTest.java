package com.springbootangular.api.v1.mapper;

import com.springbootangular.api.domain.Usuario;
import com.springbootangular.api.v1.model.UsuarioDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioMapperTest {

    public static final String NAME = "Jim";
    public static final long ID = 1L;

    UsuarioMapper usuarioMapper = UsuarioMapper.INSTANCE;


    @Test
    void usuarioToUsuarioDTO() {
        Usuario usuario = new Usuario();
        usuario.setId(ID);
        usuario.setNombre(NAME);

        UsuarioDTO usuarioDTO = usuarioMapper.usuarioToUsuarioDTO(usuario);

        assertEquals(String.valueOf(NAME), usuarioDTO.getNombre());
        assertEquals(usuario.getNombre(), usuarioDTO.getNombre());
    }

    @Test
    void usuarioDTOToUsuario() {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setNombre("german");

        Usuario usuario = usuarioMapper.usuarioDTOToUsuario(usuarioDTO);

        assertEquals(usuarioDTO.getNombre(), usuario.getNombre());
    }
}