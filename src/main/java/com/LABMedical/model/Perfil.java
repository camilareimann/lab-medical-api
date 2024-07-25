package com.LABMedical.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.*;

@Entity
@Table(name = "perfis")
@Data
public class Perfil implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String authority;

    @Override
    public String getAuthority() {
        return authority;
    }
}
