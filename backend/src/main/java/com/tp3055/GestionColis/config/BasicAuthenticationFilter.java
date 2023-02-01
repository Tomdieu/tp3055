// package com.tp3055.GestionColis.config;

// import java.io.IOException;
// import java.util.Base64;

// import org.springframework.web.filter.OncePerRequestFilter;

// import com.tp3055.GestionColis.Model.Entity.Token;
// import com.tp3055.GestionColis.Model.Entity.User;
// import com.tp3055.GestionColis.Service.TokenService;
// import com.tp3055.GestionColis.Service.UserService;

// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;

// public class BasicAuthenticationFilter extends OncePerRequestFilter {
//     private final TokenService tokenService;

//     private final UserService userService;

//     public BasicAuthenticationFilter(TokenService tokenService,UserService userService) {
//         this.tokenService = tokenService;
//         this.userService = userService;
//     }

//     @Override
//     protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//         String header = request.getHeader("Authorization");
//         if (header == null || !header.startsWith("Basic ")) {
//             response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
//             return;
//         }

//         String[] tokens = decodeHeader(header.substring(6));
//         String username = tokens[0];
//         String password = tokens[1];

//         User user = authenticate(username, password);
//         if (user == null) {
//             response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
//             return;
//         }

//         Token token = tokenService.generateToken(user);
//         response.addHeader("Authorization", "Bearer " + token.getKey());

//         filterChain.doFilter(request, response);
//     }

//     private String[] decodeHeader(String header) {
//         byte[] decodedBytes = Base64.getDecoder().decode(header);
//         return new String(decodedBytes).split(":");
//     }

//     private User authenticate(String username, String password) {
//         return userService.userExistWith(username,password);
//         // Perform authentication against a data store, such as a database
//         // If the authentication is successful, return the corresponding User object
//         // Otherwise, return null
//     }
// }
