package com.tp3055.GestionColis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class SecurityConfig extends WebSecurityConfigurerAdapter{
    
    @Autowired
    private TokenAuthenticationFilter tokenAuthenticationFilter;

    @Override
    protected void configure(HttpSecurity http)throws Exception{
        http.csrf().disable().authorizeRequests().antMatchers("/api/user/login").permitAll().anyRequest().authenticated().and().exceptionHandling();
        http.addFilterBefore(tokenAuthenticationFilter, BasicAuthenticationFilter.class);
    }

}
