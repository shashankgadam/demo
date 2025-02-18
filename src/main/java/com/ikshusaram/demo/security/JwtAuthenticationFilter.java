package com.ikshusaram.demo.security;

import java.io.IOException;
import java.util.stream.Collectors;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    JwtUtil jwtUtil;
    public JwtAuthenticationFilter(JwtUtil jwtUtil){
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");

        String email = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7); // Remove "Bearer " prefix
            email = jwtUtil.extractEmail(jwt); // Extract email from the JWT
            // mobileNo = jwtUtil.extractMobileNo(jwt); // Extract mobile number from the JWT
        }

        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // List<String> authorities = jwtUtil.extractAuthorities(jwt);
            // List<GrantedAuthority> grantedAuthorities = authorities.stream().map(SimpleGrantedAuthority::new)
            //         .collect(Collectors.toList());

            boolean validToken = jwtUtil.validateToken(jwt);
            if (validToken) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email,
                        null);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response); // Continue the filter chain
    } 
}
