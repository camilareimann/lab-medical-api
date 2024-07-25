package com.LABMedical.repository;

import com.LABMedical.model.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {
    Optional<Perfil> findByAuthority(String authority);
}
