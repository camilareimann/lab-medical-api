package com.LABMedical.model;

import lombok.Data;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "pacientes")
@Data
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nomeCompleto;

    @Column(nullable = false)
    private String genero;

    @Column(nullable = false)
    private LocalDate dataNascimento;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false)
    private String rg;

    @Column(nullable = false)
    private String estadoCivil;

    @Column(nullable = false)
    private String telefone;

    private String email;

    @Column(nullable = false)
    private String naturalidade;

    @Column(nullable = false)
    private String contatoEmergencia;

    @ElementCollection
    private List<String> alergias;

    @ElementCollection
    private List<String> cuidadosEspecificos;

    private String convenio;

    private String numeroConvenio;

    private LocalDate validadeConvenio;

    @Embedded
    private Endereco endereco;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
}