package com.ugr.muii.userservice.Security;

import com.ugr.muii.userservice.Service.CustomUserDetailsService;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final CustomUserDetailsService customUserDetailsService;

    public JwtAuthenticationFilter(JwtService jwtService, CustomUserDetailsService customUserDetailsService) {
        this.jwtService = jwtService;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader("Authorization");
        if(header!= null && header.startsWith("Bearer")){
            String jwt = header.substring(7);
            System.out.println("JWT "+jwt);
            try {
                String username = jwtService.extractUsername(jwt);

                if(username!= null && SecurityContextHolder.getContext().getAuthentication() == null){
                    UserDetails userDetails =
                            customUserDetailsService.loadUserByUsername(username);

                    if(jwtService.isTokenValid(jwt, username)){
                        UsernamePasswordAuthenticationToken authentication =
                                new UsernamePasswordAuthenticationToken(
                                        userDetails,
                                        null,
                                        userDetails.getAuthorities()
                                );

                        authentication.setDetails(new WebAuthenticationDetailsSource()
                                .buildDetails(request)
                        );

                        SecurityContextHolder.getContext().setAuthentication(authentication);
                        System.out.println("JWT VALIDADO CORRECTAMENTE");
                    }
                }
            }catch (JwtException | org.springframework.security.core.userdetails.UsernameNotFoundException e){
                System.out.println("ERROR JWT: " + e.getMessage());
                SecurityContextHolder.clearContext();
            }


        }

        filterChain.doFilter(request, response);
    }
}
