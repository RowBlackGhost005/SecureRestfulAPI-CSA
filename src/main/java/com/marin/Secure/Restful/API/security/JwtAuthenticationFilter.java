package com.marin.Secure.Restful.API.security;

import com.marin.Secure.Restful.API.service.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private JwtUtil jwtUtil;

    private CustomUserDetailsService userDetailService;

    public JwtAuthenticationFilter(JwtUtil jwtUtil, CustomUserDetailsService userDetailService) {
        this.jwtUtil = jwtUtil;
        this.userDetailService = userDetailService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //String token = request.getHeader("Authorization");

        String token = extractJwtFromRequest(request);


        if (token != null) {
            String username = jwtUtil.extractUsername(token);
            UserDetails userDetails = userDetailService.loadUserByUsername(username);

            if (jwtUtil.validateToken(token , userDetails) && userDetails.getUsername().equals(username)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }


        /*
        String token = extractJwtFromRequest(request);

        if(token != null && jwtUtil.validateToken(token , userDetailService.loadUserByUsername(jwtUtil.extractUsername(token)))){
            UserDetails userDetails = userDetailService.loadUserByUsername(jwtUtil.extractUsername(token));

            UsernamePasswordAuthenticationToken authentication =  new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request , response);

        */

    private String extractJwtFromRequest(HttpServletRequest request){
        String authorizationHeader = request.getHeader("Authorization");

        return (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) ? authorizationHeader.substring(7) : null;
    }
}
