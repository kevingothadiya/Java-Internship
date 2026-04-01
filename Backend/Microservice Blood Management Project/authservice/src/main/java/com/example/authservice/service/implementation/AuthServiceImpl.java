package com.example.authservice.service.implementation;

import com.example.authservice.domain.Users;
import com.example.authservice.helper.MapperHelper;
import com.example.authservice.model.AuthRequest;
import com.example.authservice.model.AuthResponse;
import com.example.authservice.model.CheckToken;
import com.example.authservice.model.ForgetPassWord;
import com.example.authservice.proxy.TokenRoleProxy;
import com.example.authservice.proxy.UserProxy;
import com.example.authservice.repository.UserRepo;
import com.example.authservice.service.AuthService;
import com.example.authservice.utility.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MapperHelper helper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailService userDetailService;

    @Autowired
    private JwtUtil util;

    @Override
    public String register(UserProxy userProxy) {
        if(userRepo.findByEmail(userProxy.getEmail()).isPresent()){
            throw new RuntimeException("Email Id Already Exist");
        }
        userProxy.setPassword(passwordEncoder.encode(userProxy.getPassword()));
        return userRepo.save(helper.map(userProxy, Users.class)).toString();
    }

    @Override
    public AuthResponse login(AuthRequest authRequest) {
        AuthResponse response = new AuthResponse();

        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));

        if(authenticate.isAuthenticated()){
            UserDetails userDetails = userDetailService.loadUserByUsername(authRequest.getEmail());
            String token = util.generateToken(userDetails);

            response.setToken(token);
            response.setEmail(authRequest.getEmail());
        }
        return response;
    }

    @Override
    public Boolean ValidateToken(TokenRoleProxy tokenRoleProxy) {

        try{

            String authToken = tokenRoleProxy.getToken();
            String role = tokenRoleProxy.getRole();
            if(authToken!=null&&authToken.startsWith("Bearer ")){
                String token = authToken.substring(7);

                String username = util.extractUsername(token);

                if(username!=null){

                    UserDetails userDetails = userDetailService.loadUserByUsername(username);

                    List<String> roles = util.extractClaim(token, claims -> claims.get("roles", ArrayList.class));

                    List<SimpleGrantedAuthority> simpleGrantedAuthorities = roles.stream().map(r -> new SimpleGrantedAuthority(r)).toList();

                    if (util.validateToken(token,userDetails)){
                        System.out.println("Validate");
                        if(roles.contains(role)){
                            return true;
                        }
                        else {
                            throw new RuntimeException("unauthorized");
                        }
                    }
                }
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    @Override
    public String forget(ForgetPassWord forgetPassWord) {
        String email = forgetPassWord.getEmail();
        Users user = userRepo.findByEmail(email).orElseThrow(()->new RuntimeException("No Email Id Found"));
        user.setPassword(passwordEncoder.encode(forgetPassWord.getPassword()));
        userRepo.save(user);

        return "Successfully Password Forget";
    }

    @Override
    public Users getUser(Long id) {
        return userRepo.findById(id).orElseThrow(() -> new RuntimeException("No User Found"));
    }

    @Override
    public List<Users> getAllUsers() {
        System.out.println("user");
        return userRepo.findAll();
    }
}
