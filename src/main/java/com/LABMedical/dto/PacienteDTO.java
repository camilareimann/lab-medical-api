package com.LABMedical.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class PacienteDTO {
    private Long id;

    @NotBlank(message = "O nome completo é obrigatório")
    @Size(min = 8, max = 64, message = "O nome completo deve ter entre 8 e 64 caracteres")
    private String nomeCompleto;

    @NotBlank(message = "O gênero é obrigatório")
    private String genero;

    @NotNull(message = "A data de nascimento é obrigatória")
    @Past(message = "A data de nascimento deve estar no passado")
    private LocalDate dataNascimento;

    @NotBlank(message = "O CPF é obrigatório")
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "O CPF deve estar no formato 000.000.000-00")
    private String cpf;

    @NotBlank(message = "O RG é obrigatório")
    @Size(max = 20, message = "O RG deve ter no máximo 20 caracteres")
    private String rg;

    @NotBlank(message = "O estado civil é obrigatório")
    private String estadoCivil;

    @NotBlank(message = "O telefone é obrigatório")
    @Pattern(regexp = "\\(\\d{2}\\) \\d{1} \\d{4}-\\d{4}", message = "O telefone deve estar no formato (99) 9 9999-99999")
    private String telefone;

    @Email(message = "O email deve ser válido")
    private String email;

    @NotBlank(message = "A naturalidade é obrigatória")
    @Size(min = 8, max = 64, message = "A naturalidade deve ter entre 8 e 64 caracteres")
    private String naturalidade;

    @NotBlank(message = "O contato de emergência é obrigatório")
    @Pattern(regexp = "\\(\\d{2}\\) \\d{1} \\d{4}-\\d{4}", message = "O contato de emergência deve estar no formato (99) 9 9999-99999")
    private String contatoEmergencia;

    private List<String> alergias;
    private List<String> cuidadosEspecificos;
    private String convenio;
    private String numeroConvenio;
    private LocalDate validadeConvenio;

    @NotNull(message = "O endereço é obrigatório")
    private EnderecoDTO endereco;

    @NotNull(message = "O ID do usuário é obrigatório")
    private Long idUsuario;
}