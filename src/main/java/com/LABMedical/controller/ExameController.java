package com.LABMedical.controller;

import com.LABMedical.dto.ExameDTO;
import com.LABMedical.service.ExameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/exames")
@RequiredArgsConstructor
public class ExameController {

    private final ExameService exameService;

    @PostMapping
    public ResponseEntity<ExameDTO> criarExame(@Valid @RequestBody ExameDTO exameDTO) {
        ExameDTO exameCriado = exameService.criarExame(exameDTO);
        return ResponseEntity.status(201).body(exameCriado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExameDTO> buscarExame(@PathVariable Long id) {
        ExameDTO exame = exameService.buscarExame(id);
        return ResponseEntity.ok(exame);
    }

    @GetMapping
    public ResponseEntity<List<ExameDTO>> listarExames() {
        List<ExameDTO> exames = exameService.listarExames();
        return ResponseEntity.ok(exames);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExameDTO> atualizarExame(@PathVariable Long id, @Valid @RequestBody ExameDTO exameDTO) {
        ExameDTO exameAtualizado = exameService.atualizarExame(id, exameDTO);
        return ResponseEntity.ok(exameAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarExame(@PathVariable Long id) {
        exameService.deletarExame(id);
        return ResponseEntity.noContent().build();
    }
}