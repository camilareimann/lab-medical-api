package com.LABMedical.service;

import com.LABMedical.dto.UsuarioDTO;
import com.LABMedical.exception.ResourceNotFoundException;
import com.LABMedical.exception.UserAlreadyExistsException;
import com.LABMedical.model.Perfil;
import com.LABMedical.model.Usuario;
import com.LABMedical.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public UsuarioDTO criarUsuario(UsuarioDTO usuarioDTO) {
        if (usuarioRepository.findByUsername(usuarioDTO.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException("Usuário já cadastrado com username: " + usuarioDTO.getUsername());
        }

        Usuario usuario = new Usuario();
        usuario.setUsername(usuarioDTO.getUsername());
        usuario.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setCpf(usuarioDTO.getCpf());
        usuario.setDataNascimento(usuarioDTO.getDataNascimento());

        Set<Perfil> perfis = usuarioDTO.getPerfil().stream()
                .map(nomePerfil -> Perfil.valueOf(nomePerfil.toUpperCase()))
                .collect(Collectors.toSet());
        usuario.setPerfil(perfis);

        usuario = usuarioRepository.save(usuario);

        usuarioDTO.setId(usuario.getId());
        return usuarioDTO;
    }

    public Usuario validarUsuario(String username, String password) {

        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> {
                    return new ResourceNotFoundException("Credenciais inválidas");
                });

        if (!passwordEncoder.matches(password, usuario.getPassword())) {
            throw new ResourceNotFoundException("Credenciais inválidas");
        }

        return usuario;
    }
}