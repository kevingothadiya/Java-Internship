package com.example.full_project_impl.filter;

import com.example.full_project_impl.configuration.Security;
import com.example.full_project_impl.service.implementation.MyUserDetailsService;
import com.example.full_project_impl.utility.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil util;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authToken = request.getHeader("Authorization");

        if(authToken!=null && authToken.startsWith("Bearer ")){
            String token = authToken.substring(7);

            String username = util.extractUsername(token);

            if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){

                UserDetails user = userDetailsService.loadUserByUsername(username);

                List<String> roles = util.extractClaim(token, claims -> claims.get("roles", ArrayList.class));

                List<SimpleGrantedAuthority> simpleGrantedAuthorities = roles.stream().map(r -> new SimpleGrantedAuthority(r)).toList();

                if(util.validateToken(token,user)){
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username,null,simpleGrantedAuthorities);

                    auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
        }

        filterChain.doFilter(request,response);
    }
}
