package com.example.authservice.filter;

import com.example.authservice.service.implementation.MyUserDetailService;
import com.example.authservice.utility.JwtUtil;
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
    private MyUserDetailService myUserDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authToken = request.getHeader("Authorization");

        if(authToken!=null&&authToken.startsWith("Bearer ")){
            String token = authToken.substring(7);

            String username = util.extractUsername(token);

            if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){

                UserDetails userDetails = myUserDetailService.loadUserByUsername(username);

                List<String> roles = util.extractClaim(token, claims -> claims.get("roles", ArrayList.class));

                List<SimpleGrantedAuthority> simpleGrantedAuthorities = roles.stream().map(r -> new SimpleGrantedAuthority(r)).toList();

                if (util.validateToken(token,userDetails)){
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,null,simpleGrantedAuthorities);

                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }
        filterChain.doFilter(request,response);
    }
}
