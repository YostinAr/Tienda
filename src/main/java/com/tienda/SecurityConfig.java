
package com.tienda;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    
//Este metodo funciona para hacer la autentificación de usuarios

protected void configure(AuthenticationManagerBuilder auth) throws Exception{
      auth.inMemoryAuthentication()
              .withUser("juan")
              .password("{noop}123")
              .roles("ADMIN","VENDEDOR","USER")
              .and()
              .withUser("rebeca")
              .password("{noop}456")
              .roles("VENDEDOR","USER")
              .and()
              .withUser("pedro")
              .password("{noop}789")
              .roles("USER");
  }
//El metodo siguiente funciona para realizar la autorizacion de accesos al sition web

@Override
protected void configure(HttpSecurity http) throws Exception{
    http.authorizeRequests()
    .antMatchers("/articulo/nuevo", "/articulo/guardar",
                 "/articulo/modificar/**", "/articulo/eliminar/**",
                 "/categoria/nuevo**", "/categoria/guardar",
                 "/categoria/modificar/**", "/categoria/eliminar/**",
                 "/cliente/nuevo**", "/cliente/guardar",
                 "/cliente/modificar/**", "/cliente/eliminar/**",
                 "/usuario/nuevo**", "/usuario/guardar",
                 "/usuario/modificar/**", "/usuario/eliminar/**")
                 
            .hasRole("ADMIN")
                 .antMatchers("/articulo/listado",
                              "/categoria/listado",
                              "/cliente/listado")
                 .hasAnyRole("ADMIN", "VENDEDOR")
                 .antMatchers("/")
                 .hasAnyRole("ADMIN", "VENDEDOR","USER")
                 .and()
                 .formLogin()
                 .loginPage("/login")
                 .and()
                 .exceptionHandling().accessDeniedPage("/errores/403");
}

}