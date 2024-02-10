package com.forum.security;

import lombok.Data;

import java.util.List;

@Data
public class JwtAuthenticationResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private String tokenBearerRole;

    public JwtAuthenticationResponse(String accessToken, String tokenBearerRole) {
        this.accessToken = accessToken;
        this.tokenBearerRole = tokenBearerRole;
    }

}