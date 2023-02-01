// package com.tp3055.GestionColis.config;

// import java.io.IOException;
// import java.util.Optional;

// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.filter.OncePerRequestFilter;
// import com.tp3055.GestionColis.Model.Entity.User;
// import com.tp3055.GestionColis.Service.TokenService;

// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;

// public class TokenAuthenticationFilter extends OncePerRequestFilter {

//     @Autowired
//     private TokenService tokenService;

//     @Override
//     protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//             throws ServletException, IOException {

//         String header = request.getHeader("Authorization");

//         if(header == null || !header.startsWith("Bearer ")){
//             response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Unauthorized");
//             return;
//         }

//         String token = header.substring(7);
//         Optional<User> user = tokenService.getUserByToken(token);
//         if(!user.isPresent()){
//             response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Unauthorized");
//             return;
//         }

//         Authentication authentication = new UsernamePasswordAuthenticationToken(user.get(),null,user.get().getAuthorities());
//         authentication.setAuthenticated(true);
//         SecurityContextHolder.getContext().setAuthentication(authentication);
//         filterChain.doFilter(request, response);
//     }

// }
