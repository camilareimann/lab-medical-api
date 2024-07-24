package com.LABMedical.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProntuarioDTO {
    private String nome;
    private String convenio;
    private String contatoEmergencia;
    private List<String> alergias;
    private List<String> cuidadosEspecificos;
    private List<ExameDTO> exames;
    private List<ConsultaDTO> consultas;
}