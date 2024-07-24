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

    private String nomeCompleto;

    private String genero;

    private LocalDate dataNascimento;

    private String cpf;

    private String rg;

    private String estadoCivil;

    private String telefone;

    private String email;

    private String naturalidade;

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
    private Usuario usuario;
}