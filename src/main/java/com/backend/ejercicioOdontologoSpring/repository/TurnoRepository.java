package com.backend.ejercicioOdontologoSpring.repository;

import com.backend.ejercicioOdontologoSpring.entitty.Turno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TurnoRepository extends JpaRepository<Turno, Long> {
}
