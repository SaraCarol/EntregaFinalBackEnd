package com.backend.ejercicioOdontologoSpring.service;

import com.backend.ejercicioOdontologoSpring.dto.entrada.PacienteEntradaDto;
import com.backend.ejercicioOdontologoSpring.dto.salida.PacienteSalidaDto;
import com.backend.ejercicioOdontologoSpring.exceptions.ResourceNotFoundException;

import java.util.List;

public interface IPacienteService {
    PacienteSalidaDto registrarPaciente(PacienteEntradaDto pacienteDtoEntrada);
    PacienteSalidaDto buscarPacientePorId(Long id);
    List<PacienteSalidaDto> listarPacientes();
    PacienteSalidaDto actualizarPaciente(PacienteEntradaDto pacienteDtoEntrada, Long id);

    void eliminarPaciente(Long id) throws ResourceNotFoundException;
}
