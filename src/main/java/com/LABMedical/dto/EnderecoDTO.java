package com.LABMedical.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Data
public class EnderecoDTO {
    @NotBlank(message = "O CEP é obrigatório")
    @Size(max = 8, message = "O CEP deve ter no máximo 8 caracteres")
    private String cep;

    @NotBlank(message = "A cidade é obrigatória")
    @Size(max = 64, message = "A cidade deve ter no máximo 64 caracteres")
    private String cidade;

    @NotBlank(message = "O estado é obrigatório")
    @Size(max = 2, message = "O estado deve ter no máximo 2 caracteres")
    private String estado;

    @NotBlank(message = "O logradouro é obrigatório")
    @Size(max = 255, message = "O logradouro deve ter no máximo 255 caracteres")
    private String logradouro;

    @NotBlank(message = "O número é obrigatório")
    @Size(max = 10, message = "O número deve ter no máximo 10 caracteres")
    private String numero;

    private String complemento;

    @NotBlank(message = "O bairro é obrigatório")
    @Size(max = 64, message = "O bairro deve ter no máximo 64 caracteres")
    private String bairro;

    private String pontoDeReferencia;
}