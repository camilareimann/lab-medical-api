package com.LABMedical.service;

import com.LABMedical.dto.UsuarioDTO;
import com.LABMedical.model.Perfil;
import com.LABMedical.model.Usuario;
import com.LABMedical.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private static final Logger log = LoggerFactory.getLogger(UsuarioService.class);

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public UsuarioDTO criarUsuario(UsuarioDTO usuarioDTO) {
        if (usuarioRepository.findByUsername(usuarioDTO.getUsername()).isPresent()) {
            throw new RuntimeException("Usuário já cadastrado");
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
        log.debug("Validating user with username: {}", username);

        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> {
                    log.error("User not found with username: {}", username);
                    return new RuntimeException("Credenciais inválidas");
                });

        if (!passwordEncoder.matches(password, usuario.getPassword())) {
            log.error("Invalid password for username: {}", username);
            throw new RuntimeException("Credenciais inválidas");
        }

        log.debug("User validated successfully for username: {}", username);
        return usuario;
    }
}