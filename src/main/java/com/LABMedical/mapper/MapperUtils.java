package com.LABMedical.mapper;

import com.LABMedical.dto.ConsultaDTO;
import com.LABMedical.dto.EnderecoDTO;
import com.LABMedical.dto.ExameDTO;
import com.LABMedical.dto.PacienteDTO;
import com.LABMedical.model.Consulta;
import com.LABMedical.model.Exame;
import com.LABMedical.model.Paciente;

public class MapperUtils {

    public static PacienteDTO mapToPacienteDTO(Paciente paciente) {
        PacienteDTO pacienteDTO = new PacienteDTO();
        pacienteDTO.setId(paciente.getId());
        pacienteDTO.setNomeCompleto(paciente.getNomeCompleto());
        pacienteDTO.setGenero(paciente.getGenero());
        pacienteDTO.setDataNascimento(paciente.getDataNascimento());
        pacienteDTO.setCpf(paciente.getCpf());
        pacienteDTO.setRg(paciente.getRg());
        pacienteDTO.setEstadoCivil(paciente.getEstadoCivil());
        pacienteDTO.setTelefone(paciente.getTelefone());
        pacienteDTO.setEmail(paciente.getEmail());
        pacienteDTO.setNaturalidade(paciente.getNaturalidade());
        pacienteDTO.setContatoEmergencia(paciente.getContatoEmergencia());
        pacienteDTO.setAlergias(paciente.getAlergias());
        pacienteDTO.setCuidadosEspecificos(paciente.getCuidadosEspecificos());
        pacienteDTO.setConvenio(paciente.getConvenio());
        pacienteDTO.setNumeroConvenio(paciente.getNumeroConvenio());
        pacienteDTO.setValidadeConvenio(paciente.getValidadeConvenio());
        pacienteDTO.setEndereco(new EnderecoDTO(
                paciente.getEndereco().getCep(),
                paciente.getEndereco().getCidade(),
                paciente.getEndereco().getEstado(),
                paciente.getEndereco().getLogradouro(),
                paciente.getEndereco().getNumero(),
                paciente.getEndereco().getComplemento(),
                paciente.getEndereco().getBairro(),
                paciente.getEndereco().getPontoDeReferencia()
        ));
        pacienteDTO.setUsername(paciente.getUsuario().getUsername());
        return pacienteDTO;
    }

    public static ExameDTO mapToExameDTO(Exame exame) {
        ExameDTO exameDTO = new ExameDTO();
        exameDTO.setId(exame.getId());
        exameDTO.setNome(exame.getNome());
        exameDTO.setDataExame(exame.getDataExame());
        exameDTO.setHorarioExame(exame.getHorarioExame());
        exameDTO.setTipo(exame.getTipo());
        exameDTO.setLaboratorio(exame.getLaboratorio());
        exameDTO.setUrlDocumento(exame.getUrlDocumento());
        exameDTO.setResultados(exame.getResultados());
        exameDTO.setIdPaciente(exame.getPaciente().getId());
        return exameDTO;
    }

    public static ConsultaDTO mapToConsultaDTO(Consulta consulta) {
        ConsultaDTO consultaDTO = new ConsultaDTO();
        consultaDTO.setId(consulta.getId());
        consultaDTO.setMotivo(consulta.getMotivo());
        consultaDTO.setDataConsulta(consulta.getDataConsulta());
        consultaDTO.setHorarioConsulta(consulta.getHorarioConsulta());
        consultaDTO.setDescricaoProblema(consulta.getDescricaoProblema());
        consultaDTO.setMedicacaoReceitada(consulta.getMedicacaoReceitada());
        consultaDTO.setDosagemPrecaucao(consulta.getDosagemPrecaucao());
        consultaDTO.setIdPaciente(consulta.getPaciente().getId());
        return consultaDTO;
    }

}
