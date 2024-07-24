package com.LABMedical.dto;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ExameDTO {
    private Long id;

    @NotBlank(message = "O nome do exame é obrigatório")
    @Size(min = 8, max = 64, message = "O nome do exame deve ter entre 8 e 64 caracteres")
    private String nome;

    @NotNull(message = "A data do exame é obrigatória")
    private LocalDate dataExame;

    @NotNull(message = "O horário do exame é obrigatório")
    private LocalTime horarioExame;

    @NotBlank(message = "O tipo do exame é obrigatório")
    @Size(min = 4, max = 32, message = "O tipo do exame deve ter entre 4 e 32 caracteres")
    private String tipo;

    @NotBlank(message = "O laboratório é obrigatório")
    @Size(min = 4, max = 32, message = "O laboratório deve ter entre 4 e 32 caracteres")
    private String laboratorio;

    private String urlDocumento;

    @Size(min = 16, max = 1024, message = "Os resultados devem ter entre 16 e 1024 caracteres")
    private String resultados;

    @NotNull(message = "O ID do paciente é obrigatório")
    private Long idPaciente;
}