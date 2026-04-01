package com.example.authservice.proxy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class TokenRoleProxy {
    private String role;
    private String token;
}
