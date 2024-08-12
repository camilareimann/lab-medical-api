package com.LABMedical.dto;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ConsultaDTO {
    private Long id;

    @NotBlank(message = "O motivo da consulta é obrigatório")
    @Size(min = 8, max = 64, message = "O motivo da consulta deve ter entre 8 e 64 caracteres")
    private String motivo;

    @NotNull(message = "A data da consulta é obrigatória")
    private LocalDate dataConsulta;

    @NotNull(message = "O horário da consulta é obrigatório")
    private LocalTime horarioConsulta;

    @NotBlank(message = "A descrição do problema é obrigatória")
    @Size(min = 16, max = 1024, message = "A descrição do problema deve ter entre 16 e 1024 caracteres")
    private String descricaoProblema;

    private String medicacaoReceitada;

    @Size(min = 16, max = 256, message = "A dosagem e precauções devem ter entre 16 e 256 caracteres")
    private String dosagemPrecaucao;

    @NotNull(message = "O ID do paciente é obrigatório")
    private Long idPaciente;
}
