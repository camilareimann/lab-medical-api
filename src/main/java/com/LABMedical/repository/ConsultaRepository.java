package com.LABMedical.repository;

import com.LABMedical.model.Consulta;
import com.LABMedical.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    List<Consulta> findByPacienteOrderByDataConsultaAsc(Paciente paciente);
}
