package com.backend.ejercicioOdontologoSpring.service;

import com.backend.ejercicioOdontologoSpring.dto.entrada.TurnoDtoEntrada;
import com.backend.ejercicioOdontologoSpring.dto.salida.TurnoDtoSalida;

import java.util.List;

public interface ITurnoService {
    List<TurnoDtoSalida> listarTurnos();

    TurnoDtoSalida buscarTurnoPorId(Long id);

    TurnoDtoSalida actualizarTurno(TurnoDtoEntrada turnoDtoEntrada, Long id);

    void eliminarTurno(Long id);
}
