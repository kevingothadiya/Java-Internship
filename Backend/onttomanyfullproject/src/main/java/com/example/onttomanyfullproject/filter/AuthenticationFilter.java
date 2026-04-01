package com.example.onttomanyfullproject.filter;

import com.example.onttomanyfullproject.utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil util;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authToken = request.getHeader("Authorization");

        if(authToken == null || !authToken.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }
        String token = authToken.substring(7);
        String username = util.extractUsername(token);

        if(username != null  && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails user = userDetailsService.loadUserByUsername(username);

            ArrayList roles = util.extractClaim(token, claims -> claims.get("roles", ArrayList.class));

            List<SimpleGrantedAuthority> simpleGrantedAuthorities = roles.stream().map(r->new SimpleGrantedAuthority((String) r)).toList();


            if(util.validateToken(token,user)){
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username,null,simpleGrantedAuthorities);

                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        filterChain.doFilter(request,response);
    }
}
