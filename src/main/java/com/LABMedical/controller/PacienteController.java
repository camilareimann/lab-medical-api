package com.LABMedical.controller;

import com.LABMedical.dto.PacienteDTO;
import com.LABMedical.service.PacienteService;
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
    public ResponseEntity<PacienteDTO> criarPaciente(@RequestBody PacienteDTO pacienteDTO) {
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
    public ResponseEntity<PacienteDTO> atualizarPaciente(@PathVariable Long id, @RequestBody PacienteDTO pacienteDTO) {
        PacienteDTO pacienteAtualizado = pacienteService.atualizarPaciente(id, pacienteDTO);
        return ResponseEntity.ok(pacienteAtualizado);
