package com.LABMedical.service;

import com.LABMedical.dto.UsuarioDTO;
import com.LABMedical.model.Perfil;
import com.LABMedical.model.Usuario;
import com.LABMedical.repository.PerfilRepository;
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
    private final PerfilRepository perfilRepository;
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
                .map(nomePerfil -> perfilRepository.findByAuthority("SCOPE_" + nomePerfil)
                        .orElseThrow(() -> new RuntimeException("Role not found: " + nomePerfil)))
                .collect(Collectors.toSet());
        usuario.setPerfil(perfis);

        usuario = usuarioRepository.save(usuario);

        usuarioDTO.setId(usuario.getId());
        return usuarioDTO;
    }

    public Usuario validarUsuario(String username, String password) {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Credenciais inválidas"));

        if (!passwordEncoder.matches(password, usuario.getPassword())) {
            throw new RuntimeException("Credenciais inválidas");
        }

        return usuario;
    }
}
