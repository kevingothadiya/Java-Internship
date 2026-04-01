package com.example.authservice.service;

import com.example.authservice.domain.Users;
import com.example.authservice.model.AuthRequest;
import com.example.authservice.model.AuthResponse;
import com.example.authservice.model.CheckToken;
import com.example.authservice.model.ForgetPassWord;
import com.example.authservice.proxy.TokenRoleProxy;
import com.example.authservice.proxy.UserProxy;

import java.util.List;

public interface AuthService {

    String register(UserProxy userProxy);

    AuthResponse login(AuthRequest authRequest);

    Boolean ValidateToken(TokenRoleProxy tokenRoleProxy);

    String forget(ForgetPassWord forgetPassWord);

    Users getUser(Long id);

    List<Users> getAllUsers();
}
