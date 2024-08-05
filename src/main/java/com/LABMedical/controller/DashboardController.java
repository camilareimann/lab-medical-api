package com.LABMedical.controller;

import com.LABMedical.dto.DashboardDTO;
import com.LABMedical.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping
    public ResponseEntity<DashboardDTO> listarDadosDashboard() {
        DashboardDTO dashboardDados = dashboardService.listarDadosDashboard();
        return ResponseEntity.ok(dashboardDados);
    }
}