package com.LABMedical.controller;

import com.LABMedical.dto.PacienteDTO;
import com.LABMedical.dto.ProntuarioDTO;
import com.LABMedical.service.PacienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/pacientes")
@RequiredArgsConstructor
public class PacienteController {

    private final PacienteService pacienteService;

    @PostMapping
    public ResponseEntity<PacienteDTO> criarPaciente(@Valid @RequestBody PacienteDTO pacienteDTO) {
        PacienteDTO pacienteCriado = pacienteService.criarPaciente(pacienteDTO);
        return ResponseEntity.status(201).body(pacienteCriado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> buscarPaciente(@PathVariable Long id) {
        PacienteDTO paciente = pacienteService.buscarPaciente(id);
        return ResponseEntity.ok(paciente);
    }

    @GetMapping
    public ResponseEntity<List<PacienteDTO>> listarPacientes() {
        List<PacienteDTO> pacientes = pacienteService.listarPacientes();
        return ResponseEntity.ok(pacientes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteDTO> atualizarPaciente(@PathVariable Long id, @Valid @RequestBody PacienteDTO pacienteDTO) {
        PacienteDTO pacienteAtualizado = pacienteService.atualizarPaciente(id, pacienteDTO);
        return ResponseEntity.ok(pacienteAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPaciente(@PathVariable Long id) {
        pacienteService.deletarPaciente(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/prontuarios")
    public ResponseEntity<List<PacienteDTO>> listarPacientesProntuarios(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String numeroRegistro,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {
        List<PacienteDTO> pacientes = pacienteService.listarPacientesProntuarios(nome, numeroRegistro, page, size);
        if (pacientes.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.ok(pacientes);
    }

    @GetMapping("/{id}/prontuarios")
    public ResponseEntity<ProntuarioDTO> listarProntuariosDoPaciente(@PathVariable Long id) {
        ProntuarioDTO prontuario = pacienteService.listarProntuariosDoPaciente(id);
        if (prontuario == null) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.ok(prontuario);
    }
}