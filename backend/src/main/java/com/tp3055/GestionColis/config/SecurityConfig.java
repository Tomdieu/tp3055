// package com.tp3055.GestionColis.config;

// import com.tp3055.GestionColis.config.BasicAuthenticationFilter;

// import lombok.RequiredArgsConstructor;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// @Configuration
// @EnableWebSecurity
// @RequiredArgsConstructor
// public class SecurityConfig extends WebSecurityConfigurerAdapter{
    
//     @Autowired
//     private final TokenAuthenticationFilter tokenAuthenticationFilter;


//     @Override
//     protected void configure(HttpSecurity http)throws Exception{
//         http.csrf().disable().authorizeRequests().antMatchers("/api/user/login").permitAll().anyRequest().authenticated().and().exceptionHandling();
//         // http.addFilterBefore(tokenAuthenticationFilter,BasicAuthenticationFilter.class);
//     }

// }
