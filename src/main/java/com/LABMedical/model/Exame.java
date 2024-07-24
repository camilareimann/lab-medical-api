package com.LABMedical.model;

import lombok.Data;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "exames")
@Data
public class Exame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private LocalDate dataExame;

    @Column(nullable = false)
    private LocalTime horarioExame;

    @Column(nullable = false)
    private String tipo;

    @Column(nullable = false)
    private String laboratorio;

    private String urlDocumento;

    @Column(length = 1024)
    private String resultados;

    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;
}