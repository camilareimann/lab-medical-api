package com.LABMedical.repository;

import com.LABMedical.model.Exame;
import com.LABMedical.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExameRepository extends JpaRepository<Exame, Long> {
    List<Exame> findByPacienteOrderByDataExameAsc(Paciente paciente);
}