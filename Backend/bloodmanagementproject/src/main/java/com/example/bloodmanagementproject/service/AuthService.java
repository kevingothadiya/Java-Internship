package com.example.bloodmanagementproject.service;

import com.example.bloodmanagementproject.model.AuthRequest;
import com.example.bloodmanagementproject.model.AuthResponse;
import com.example.bloodmanagementproject.model.ForgetPassWord;
import com.example.bloodmanagementproject.proxy.UserProxy;

public interface AuthService {

    String register(UserProxy userProxy);

    AuthResponse login(AuthRequest authRequest);

    String forget(ForgetPassWord forgetPassWord);
}
