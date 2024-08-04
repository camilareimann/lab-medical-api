package com.LABMedical.service;

import com.LABMedical.dto.PacienteDTO;
import com.LABMedical.dto.ProntuarioDTO;
import com.LABMedical.mapper.MapperUtils;
import com.LABMedical.model.*;
import com.LABMedical.repository.ConsultaRepository;
import com.LABMedical.repository.ExameRepository;
import com.LABMedical.repository.PacienteRepository;
import com.LABMedical.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PacienteService {

    private final PacienteRepository pacienteRepository;
    private final UsuarioRepository usuarioRepository;
    private final ExameRepository exameRepository;
    private final ConsultaRepository consultaRepository;

    @Transactional
    public PacienteDTO criarPaciente(PacienteDTO pacienteDTO) {
        Usuario usuario = usuarioRepository.findByUsername(pacienteDTO.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuário não cadastrado"));

        if (!usuario.getCpf().equals(pacienteDTO.getCpf())) {
            throw new RuntimeException("CPF do usuário não corresponde ao CPF do paciente");
        }

        Paciente paciente = new Paciente();
        paciente.setNomeCompleto(pacienteDTO.getNomeCompleto());
        paciente.setGenero(pacienteDTO.getGenero());
        paciente.setDataNascimento(pacienteDTO.getDataNascimento());
        paciente.setCpf(pacienteDTO.getCpf());
        paciente.setRg(pacienteDTO.getRg());
        paciente.setEstadoCivil(pacienteDTO.getEstadoCivil());
        paciente.setTelefone(pacienteDTO.getTelefone());
        paciente.setEmail(pacienteDTO.getEmail());
        paciente.setNaturalidade(pacienteDTO.getNaturalidade());
        paciente.setContatoEmergencia(pacienteDTO.getContatoEmergencia());
        paciente.setAlergias(pacienteDTO.getAlergias());
        paciente.setCuidadosEspecificos(pacienteDTO.getCuidadosEspecificos());
        paciente.setConvenio(pacienteDTO.getConvenio());
        paciente.setNumeroConvenio(pacienteDTO.getNumeroConvenio());
        paciente.setValidadeConvenio(pacienteDTO.getValidadeConvenio());
        paciente.setEndereco(new Endereco(
                pacienteDTO.getEndereco().getCep(),
                pacienteDTO.getEndereco().getCidade(),
                pacienteDTO.getEndereco().getEstado(),
                pacienteDTO.getEndereco().getLogradouro(),
                pacienteDTO.getEndereco().getNumero(),
                pacienteDTO.getEndereco().getComplemento(),
                pacienteDTO.getEndereco().getBairro(),
                pacienteDTO.getEndereco().getPontoDeReferencia()
        ));
        paciente.setUsuario(usuario);

        paciente = pacienteRepository.save(paciente);
        pacienteDTO.setId(paciente.getId());
        return pacienteDTO;
    }

    public PacienteDTO buscarPaciente(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));
        return MapperUtils.mapToPacienteDTO(paciente);
    }

    public List<PacienteDTO> listarPacientes() {
        return pacienteRepository.findAll().stream()
                .map(MapperUtils::mapToPacienteDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public PacienteDTO atualizarPaciente(Long id, PacienteDTO pacienteDTO) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        Usuario usuario = usuarioRepository.findByUsername(pacienteDTO.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuário não cadastrado"));

        if (!usuario.getCpf().equals(pacienteDTO.getCpf())) {
            throw new RuntimeException("CPF do usuário não corresponde ao CPF do paciente");
        }

        paciente.setNomeCompleto(pacienteDTO.getNomeCompleto());
        paciente.setGenero(pacienteDTO.getGenero());
        paciente.setDataNascimento(pacienteDTO.getDataNascimento());
        paciente.setCpf(pacienteDTO.getCpf());
        paciente.setRg(pacienteDTO.getRg());
        paciente.setEstadoCivil(pacienteDTO.getEstadoCivil());
        paciente.setTelefone(pacienteDTO.getTelefone());
        paciente.setEmail(pacienteDTO.getEmail());
        paciente.setNaturalidade(pacienteDTO.getNaturalidade());
        paciente.setContatoEmergencia(pacienteDTO.getContatoEmergencia());
        paciente.setAlergias(pacienteDTO.getAlergias());
        paciente.setCuidadosEspecificos(pacienteDTO.getCuidadosEspecificos());
        paciente.setConvenio(pacienteDTO.getConvenio());
        paciente.setNumeroConvenio(pacienteDTO.getNumeroConvenio());
        paciente.setValidadeConvenio(pacienteDTO.getValidadeConvenio());
        paciente.setEndereco(new Endereco(
                pacienteDTO.getEndereco().getCep(),
                pacienteDTO.getEndereco().getCidade(),
                pacienteDTO.getEndereco().getEstado(),
                pacienteDTO.getEndereco().getLogradouro(),
                pacienteDTO.getEndereco().getNumero(),
                pacienteDTO.getEndereco().getComplemento(),
                pacienteDTO.getEndereco().getBairro(),
                pacienteDTO.getEndereco().getPontoDeReferencia()
        ));
        paciente.setUsuario(usuario);

        paciente = pacienteRepository.save(paciente);
        return MapperUtils.mapToPacienteDTO(paciente);
    }

    @Transactional
    public void deletarPaciente(Long id) {
        pacienteRepository.deleteById(id);
    }

    public List<PacienteDTO> listarPacientesProntuarios(String nome, String numeroRegistro, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Paciente> pacientesPage = pacienteRepository.findByNomeOrNumeroRegistro(nome, numeroRegistro, pageable);
        return pacientesPage.stream().map(MapperUtils::mapToPacienteDTO).collect(Collectors.toList());
    }

    public ProntuarioDTO listarProntuariosDoPaciente(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));
        List<Consulta> consultas = consultaRepository.findByPacienteOrderByDataConsultaAsc(paciente);
        List<Exame> exames = exameRepository.findByPacienteOrderByDataExameAsc(paciente);

        ProntuarioDTO prontuarioDTO = new ProntuarioDTO();
        prontuarioDTO.setNome(paciente.getNomeCompleto());
        prontuarioDTO.setConvenio(paciente.getConvenio());
        prontuarioDTO.setContatoEmergencia(paciente.getContatoEmergencia());
        prontuarioDTO.setAlergias(paciente.getAlergias());
        prontuarioDTO.setCuidadosEspecificos(paciente.getCuidadosEspecificos());
        prontuarioDTO.setConsultas(consultas.stream().map(MapperUtils::mapToConsultaDTO).collect(Collectors.toList()));
        prontuarioDTO.setExames(exames.stream().map(MapperUtils::mapToExameDTO).collect(Collectors.toList()));

        return prontuarioDTO;
    }
}