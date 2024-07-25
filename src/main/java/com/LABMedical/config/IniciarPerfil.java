package com.LABMedical.config;

import com.LABMedical.model.Perfil;
import com.LABMedical.repository.PerfilRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class IniciarPerfil {

    private final PerfilRepository perfilRepository;

    @Bean
    public CommandLineRunner initializeRoles() {
        return args -> {
            if (perfilRepository.findByAuthority("SCOPE_ADMIN").isEmpty()) {
                Perfil adminPerfil = new Perfil();
                adminPerfil.setAuthority("SCOPE_ADMIN");
                perfilRepository.save(adminPerfil);
            }

            if (perfilRepository.findByAuthority("SCOPE_MEDICO").isEmpty()) {
                Perfil medicoPerfil = new Perfil();
                medicoPerfil.setAuthority("SCOPE_MEDICO");
                perfilRepository.save(medicoPerfil);
            }

            if (perfilRepository.findByAuthority("SCOPE_PACIENTE").isEmpty()) {
                Perfil pacientePerfil = new Perfil();
                pacientePerfil.setAuthority("SCOPE_PACIENTE");
                perfilRepository.save(pacientePerfil);
            }
        };
    }
}
