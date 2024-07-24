package com.LABMedical.service;

import com.LABMedical.dto.DashboardDTO;
import com.LABMedical.repository.ConsultaRepository;
import com.LABMedical.repository.ExameRepository;
import com.LABMedical.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final PacienteRepository pacienteRepository;
    private final ConsultaRepository consultaRepository;
    private final ExameRepository exameRepository;

    public DashboardDTO listarDadosDashboard() {
        long totalPacientes = pacienteRepository.count();
        long totalConsultas = consultaRepository.count();
        long totalExames = exameRepository.count();

        DashboardDTO dashboardDTO = new DashboardDTO();
        dashboardDTO.setTotalPacientes(totalPacientes);
        dashboardDTO.setTotalConsultas(totalConsultas);
        dashboardDTO.setTotalExames(totalExames);

        return dashboardDTO;
    }
}
