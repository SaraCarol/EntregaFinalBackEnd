package com.backend.ejercicioOdontologoSpring.service;

import com.backend.ejercicioOdontologoSpring.dto.entrada.PacienteDtoEntrada;
import com.backend.ejercicioOdontologoSpring.dto.salida.PacienteDtoSalida;

import java.util.List;

public interface IPacienteService {
    PacienteDtoSalida registrarPaciente(PacienteDtoEntrada pacienteDtoEntrada);
    PacienteDtoSalida buscarPacientePorId(Long id);
    List<PacienteDtoSalida> listarPacientes();
    PacienteDtoSalida actualizarPaciente(PacienteDtoEntrada pacienteDtoEntrada, Long id);

    void eliminarPaciente(Long id);
}
