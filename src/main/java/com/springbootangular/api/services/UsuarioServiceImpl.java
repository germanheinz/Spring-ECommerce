package com.springbootangular.api.services;

import com.springbootangular.api.v1.mapper.UsuarioMapper;
import com.springbootangular.api.v1.model.UsuarioDTO;
import com.springbootangular.api.domain.Usuario;
import com.springbootangular.api.repositories.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


/*
* 1 - Agregamos la implementacion de la interfaz UserDetailService de Spring Security
* 2 - Sobreescribimos el metodo loadUserByName
* 3 - Utilizamos Strem y el operador map de java 8 para transformar la lista de roles en GrantedAuthorities
* 4 - Utilizamos peek para mostrar un mensaje de info en cada uno de sus roles obtenidos
* 5 - Devolvemos un User que es una clase de Spring Security
* */

@Service
public class UsuarioServiceImpl implements UserDetailsService, UsuarioService {

    private Logger logger = LoggerFactory.getLogger(UsuarioServiceImpl.class);


    private final UsuarioMapper usuarioMapper;
    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioMapper usuarioMapper, UsuarioRepository usuarioRepository) {
        this.usuarioMapper = usuarioMapper;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UsuarioDTO findByUsername(String username) {
        return usuarioMapper.usuarioToUsuarioDTO(usuarioRepository.findByUsername(username));
    }
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByUsername(username);

        if(usuario == null){
            logger.info("Error en login: Usuario no existe con el username " + username);
            throw new UsernameNotFoundException("Error en login: Usuario no existe con el username " + username);
        }

        List<GrantedAuthority> authorities = usuario.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getNombre()))
                .peek(authority -> logger.info("Role " + authority.getAuthority()))
                .collect(Collectors.toList());

        return new User(usuario.getUsername(),usuario.getPassword(),usuario.getEnabled(),true,true,true,authorities);
    }

}
