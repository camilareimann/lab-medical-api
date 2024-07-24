package com.LABMedical.dto;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private Long tempoExpiracao;

    public LoginResponse(String token, Long tempoExpiracao) {
        this.token = token;
        this.tempoExpiracao = tempoExpiracao;
    }
}
