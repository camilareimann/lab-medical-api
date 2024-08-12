package com.LABMedical.controller;

import com.LABMedical.dto.LoginRequest;
import com.LABMedical.dto.LoginResponse;
import com.LABMedical.exception.UnauthorizedException;
import com.LABMedical.model.Usuario;
import com.LABMedical.service.TokenService;
import com.LABMedical.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final UsuarioService usuarioService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            Usuario usuario = usuarioService.validarUsuario(loginRequest.getUsername(), loginRequest.getPassword());

            if (!passwordEncoder.matches(loginRequest.getPassword(), usuario.getPassword())) {
                throw new UnauthorizedException("Credenciais inválidas");
            }

            String token = tokenService.generateToken(usuario);
            return ResponseEntity.ok(new LoginResponse(token, 36000L));
        } catch (RuntimeException e) {
            throw new UnauthorizedException("Credenciais inválidas");
        }
    }
}