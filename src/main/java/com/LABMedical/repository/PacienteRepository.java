package com.LABMedical.repository;

import com.LABMedical.model.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    @Query("SELECT p FROM Paciente p WHERE (:nome IS NULL OR p.nomeCompleto LIKE %:nome%) AND (:numeroRegistro IS NULL OR p.numeroConvenio LIKE %:numeroRegistro%)")
    Page<Paciente> findByNomeOrNumeroRegistro(@Param("nome") String nome, @Param("numeroRegistro") String numeroRegistro, Pageable pageable);
}
