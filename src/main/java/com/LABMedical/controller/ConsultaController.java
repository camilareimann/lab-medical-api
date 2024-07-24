package com.LABMedical.controller;

import com.LABMedical.dto.ConsultaDTO;
import com.LABMedical.service.ConsultaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/consultas")
@RequiredArgsConstructor
public class ConsultaController {

    private final ConsultaService consultaService;

    @PostMapping
    public ResponseEntity<ConsultaDTO> criarConsulta(@Valid @RequestBody ConsultaDTO consultaDTO) {
        ConsultaDTO consultaCriada = consultaService.criarConsulta(consultaDTO);
        return ResponseEntity.status(201).body(consultaCriada);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaDTO> buscarConsulta(@PathVariable Long id) {
        ConsultaDTO consulta = consultaService.buscarConsulta(id);
        return ResponseEntity.ok(consulta);
    }

    @GetMapping
    public ResponseEntity<List<ConsultaDTO>> listarConsultas() {
        List<ConsultaDTO> consultas = consultaService.listarConsultas();
        return ResponseEntity.ok(consultas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsultaDTO> atualizarConsulta(@PathVariable Long id, @Valid @RequestBody ConsultaDTO consultaDTO) {
        ConsultaDTO consultaAtualizada = consultaService.atualizarConsulta(id, consultaDTO);
        return ResponseEntity.ok(consultaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarConsulta(@PathVariable Long id) {
        consultaService.deletarConsulta(id);
        return ResponseEntity.noContent().build();
    }
}