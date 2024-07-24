package com.LABMedical.service;

import com.LABMedical.dto.ConsultaDTO;
import com.LABMedical.model.Consulta;
import com.LABMedical.model.Paciente;
import com.LABMedical.repository.ConsultaRepository;
import com.LABMedical.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final PacienteRepository pacienteRepository;

    @Transactional
    public ConsultaDTO criarConsulta(ConsultaDTO consultaDTO) {
        Paciente paciente = pacienteRepository.findById(consultaDTO.getIdPaciente())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        Consulta consulta = new Consulta();
        consulta.setMotivo(consultaDTO.getMotivo());
        consulta.setDataConsulta(consultaDTO.getDataConsulta());
        consulta.setHorarioConsulta(consultaDTO.getHorarioConsulta());
        consulta.setDescricaoProblema(consultaDTO.getDescricaoProblema());
        consulta.setMedicacaoReceitada(consultaDTO.getMedicacaoReceitada());
        consulta.setDosagemPrecaucao(consultaDTO.getDosagemPrecaucao());
        consulta.setPaciente(paciente);

        consulta = consultaRepository.save(consulta);
        consultaDTO.setId(consulta.getId());
        return consultaDTO;
    }

    public ConsultaDTO buscarConsulta(Long id) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada"));
        return mapToDTO(consulta);
    }

    public List<ConsultaDTO> listarConsultas() {
        return consultaRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public ConsultaDTO atualizarConsulta(Long id, ConsultaDTO consultaDTO) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada"));

        consulta.setMotivo(consultaDTO.getMotivo());
        consulta.setDataConsulta(consultaDTO.getDataConsulta());
        consulta.setHorarioConsulta(consultaDTO.getHorarioConsulta());
        consulta.setDescricaoProblema(consultaDTO.getDescricaoProblema());
        consulta.setMedicacaoReceitada(consultaDTO.getMedicacaoReceitada());
        consulta.setDosagemPrecaucao(consultaDTO.getDosagemPrecaucao());
        consulta.setPaciente(pacienteRepository.findById(consultaDTO.getIdPaciente())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado")));

        consulta = consultaRepository.save(consulta);
        return mapToDTO(consulta);
    }

    @Transactional
    public void deletarConsulta(Long id) {
        consultaRepository.deleteById(id);
    }

    private ConsultaDTO mapToDTO(Consulta consulta) {
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
