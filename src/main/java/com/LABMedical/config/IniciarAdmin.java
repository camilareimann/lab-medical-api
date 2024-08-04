package com.LABMedical.config;

import com.LABMedical.model.Perfil;
import com.LABMedical.model.Usuario;
import com.LABMedical.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
@RequiredArgsConstructor
public class IniciarAdmin {

    private static final Logger log = LoggerFactory.getLogger(IniciarAdmin.class);

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner initializeAdminUser() {
        return args -> {
            if (usuarioRepository.findByUsername("admin").isEmpty()) {
                Usuario adminUser = new Usuario();
                adminUser.setUsername("admin");
                adminUser.setPassword(passwordEncoder.encode("admin123"));
                adminUser.setEmail("admin@example.com");
                adminUser.setCpf("000.000.000-00");
                adminUser.setDataNascimento(LocalDate.of(1990, 1, 1));
                adminUser.setPerfil(Set.of(Perfil.ADMIN));
                usuarioRepository.save(adminUser);
                log.info("Admin user created with username: admin and password: admin123");
            } else {
                log.info("Admin user already exists");
            }
        };
    }
}