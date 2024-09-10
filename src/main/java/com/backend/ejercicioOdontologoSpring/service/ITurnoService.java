package com.backend.ejercicioOdontologoSpring.service;

import com.backend.ejercicioOdontologoSpring.dto.entrada.TurnoEntradaDto;
import com.backend.ejercicioOdontologoSpring.dto.salida.TurnoSalidaDto;
import com.backend.ejercicioOdontologoSpring.exceptions.ResourceNotFoundException;

import java.util.List;

public interface ITurnoService {
    List<TurnoSalidaDto> listarTurnos();

    TurnoSalidaDto buscarTurnoPorId(Long id);

    TurnoSalidaDto actualizarTurno(TurnoEntradaDto turnoDtoEntrada, Long id);

    void eliminarTurno(Long id) throws ResourceNotFoundException;
}
