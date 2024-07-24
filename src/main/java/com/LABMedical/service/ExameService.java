package com.LABMedical.service;

import com.LABMedical.dto.ExameDTO;
import com.LABMedical.model.Exame;
import com.LABMedical.model.Paciente;
import com.LABMedical.repository.ExameRepository;
import com.LABMedical.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExameService {

    private final ExameRepository exameRepository;
    private final PacienteRepository pacienteRepository;

    @Transactional
    public ExameDTO criarExame(ExameDTO exameDTO) {
        Paciente paciente = pacienteRepository.findById(exameDTO.getIdPaciente())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        Exame exame = new Exame();
        exame.setNome(exameDTO.getNome());
        exame.setDataExame(exameDTO.getDataExame());
        exame.setHorarioExame(exameDTO.getHorarioExame());
        exame.setTipo(exameDTO.getTipo());
        exame.setLaboratorio(exameDTO.getLaboratorio());
        exame.setUrlDocumento(exameDTO.getUrlDocumento());
        exame.setResultados(exameDTO.getResultados());
        exame.setPaciente(paciente);

        exame = exameRepository.save(exame);
        exameDTO.setId(exame.getId());
        return exameDTO;
    }

    public ExameDTO buscarExame(Long id) {
        Exame exame = exameRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exame não encontrado"));
        return mapToDTO(exame);
    }

    public List<ExameDTO> listarExames() {
        return exameRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public ExameDTO atualizarExame(Long id, ExameDTO exameDTO) {
        Exame exame = exameRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exame não encontrado"));

        exame.setNome(exameDTO.getNome());
        exame.setDataExame(exameDTO.getDataExame());
        exame.setHorarioExame(exameDTO.getHorarioExame());
        exame.setTipo(exameDTO.getTipo());
        exame.setLaboratorio(exameDTO.getLaboratorio());
        exame.setUrlDocumento(exameDTO.getUrlDocumento());
        exame.setResultados(exameDTO.getResultados());
        exame.setPaciente(pacienteRepository.findById(exameDTO.getIdPaciente())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado")));

        exame = exameRepository.save(exame);
        return mapToDTO(exame);
    }

    @Transactional
    public void deletarExame(Long id) {
        exameRepository.deleteById(id);
    }

    private ExameDTO mapToDTO(Exame exame) {
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
}