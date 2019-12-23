package com.springbootangular.api.services;

import com.springbootangular.api.v1.mapper.UsuarioMapper;
import com.springbootangular.api.v1.model.UsuarioDTO;
import com.springbootangular.api.domain.Role;
import com.springbootangular.api.domain.Usuario;
import com.springbootangular.api.repositories.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


// Testing UsuarioService
class UsuarioServiceImplTest {

    public static final Long ID = 1L;
    public static final String NAME = "German";
    UsuarioService usuarioService;

    @Mock
    UsuarioRepository usuarioRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        usuarioService = new UsuarioServiceImpl(UsuarioMapper.INSTANCE, usuarioRepository);
    }

    @Test
    void findByUsername() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setUsername("German");
        usuario.setNombre("German");

        when(usuarioRepository.findByUsername(anyString())).thenReturn(usuario);

        UsuarioDTO usuarioDTO = usuarioService.findByUsername(usuario.getUsername());

        assertEquals(ID, usuario.getId());
        assertEquals(NAME, usuario.getUsername());
    }

    @Test
    void loadUserByUsername() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setUsername("GermanHeinz");
        usuario.setNombre("German");

        Role role = new Role();
        role.setNombre("ROLE_ADMIN");
        role.setNombre("ROLE_USER");

        List<Role> listaRoles = new ArrayList<Role>();
        listaRoles.add(role);

        usuario.setRoles(listaRoles);

        when(usuarioRepository.findByUsername(anyString())).thenReturn(usuario);

        assertEquals(ID, usuario.getId());
    }

}