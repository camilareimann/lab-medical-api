package com.LABMedical.service;

import com.LABMedical.dto.EnderecoDTO;
import com.LABMedical.dto.PacienteDTO;
import com.LABMedical.model.Endereco;
import com.LABMedical.model.Paciente;
import com.LABMedical.model.Usuario;
import com.LABMedical.repository.PacienteRepository;
import com.LABMedical.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PacienteService {

    private final PacienteRepository pacienteRepository;
    private final UsuarioRepository usuarioRepository;

    @Transactional
    public PacienteDTO criarPaciente(PacienteDTO pacienteDTO) {
        Usuario usuario = usuarioRepository.findById(pacienteDTO.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

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
        // Usando o construtor com todos os parâmetros
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
        return mapToDTO(paciente);
    }

    public List<PacienteDTO> listarPacientes() {
        return pacienteRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public PacienteDTO atualizarPaciente(Long id, PacienteDTO pacienteDTO) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

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
        // Usando o construtor com todos os parâmetros
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
        paciente.setUsuario(usuarioRepository.findById(pacienteDTO.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado")));

        paciente = pacienteRepository.save(paciente);
        return mapToDTO(paciente);
    }

    @Transactional
    public void deletarPaciente(Long id) {
        pacienteRepository.deleteById(id);
    }

    private PacienteDTO mapToDTO(Paciente paciente) {
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
        pacienteDTO.setIdUsuario(paciente.getUsuario().getId());
        return pacienteDTO;
    }
}