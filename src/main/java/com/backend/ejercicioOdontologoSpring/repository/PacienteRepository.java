package com.backend.ejercicioOdontologoSpring.repository;

import com.backend.ejercicioOdontologoSpring.entitty.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

}
