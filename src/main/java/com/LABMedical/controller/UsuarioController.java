package com.LABMedical.controller;

import com.LABMedical.dto.UsuarioDTO;
import com.LABMedical.exception.UserAlreadyExistsException;
import com.LABMedical.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioDTO> criarUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        try {
            UsuarioDTO usuarioCriado = usuarioService.criarUsuario(usuarioDTO);
            return ResponseEntity.status(201).body(usuarioCriado);
        } catch (UserAlreadyExistsException ex) {
            throw new UserAlreadyExistsException("Usuário já cadastrado com username: " + usuarioDTO.getUsername());
        }
    }
}