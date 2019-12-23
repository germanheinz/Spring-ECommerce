package com.springbootangular.api.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


/*
* Creando una clase de configuracion
* Extendemos la clase a WebSecurityConfigurerAdapter
* Inyectamos la variable UserDetailService ya que nuestra clase la implementa
* Sobreescribimos un metodo para la configuracion del login configure(AuthenticationManagerBuilder auth)
* Usamos una instancia de encriptar un password para mas seguridad
* Ya que luego vamos a utilizar este metodo passwordEncoder en otras las clases, lo marcamos como Bean
* parecido a la anotacion component, pero component es para una clase que creemos nosotros, en este caso
* es un metodo y devuelve un objeto, entonces debe ir como @Bean
* Anotandolo como @Bean nos dejara inyectarlo en otra clase
*
* */
/*
* Habilitamos modo de seguridad para usar anotaciones
* Luego en el controlador debe indicarse @Secured({"ROLE_ADMIN","ROLE_USER"}) ej.
* */
@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public UserDetailsService usuarioService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
        auth.userDetailsService(this.usuarioService).passwordEncoder(passwordEncoder());
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    //Desactiva la funcion CSRS - En Spring - linea 58 (falsificacion de peticion en sitios cruzados)
    //Desactiva la opcion/uso de sessiones, ya que en un Api Rest con token los datos del usuario estaran en el token por el lado del cliente y no por el servidor, linea 59
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest()
                .authenticated()
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

}
