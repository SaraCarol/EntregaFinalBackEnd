package com.backend.ejercicioOdontologoSpring.service.impl;

import com.backend.ejercicioOdontologoSpring.dto.entrada.OdontologoDtoEntrada;
import com.backend.ejercicioOdontologoSpring.dto.entrada.TurnoDtoEntrada;
import com.backend.ejercicioOdontologoSpring.dto.salida.OdontologoDtoSalida;
import com.backend.ejercicioOdontologoSpring.dto.salida.TurnoDtoSalida;
import com.backend.ejercicioOdontologoSpring.repository.TurnoRepository;
import com.backend.ejercicioOdontologoSpring.service.ITurnoService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoService implements ITurnoService {

    private final Logger LOGGER = LoggerFactory.getLogger(PacienteService.class);
    private final TurnoRepository turnoRepository;
    private final ModelMapper modelMapper;

    public TurnoService(TurnoRepository turnoRepository, ModelMapper modelMapper) {
        this.turnoRepository = turnoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<TurnoDtoSalida> listarTurnos() {
        return null;
    }

    @Override
    public TurnoDtoSalida buscarTurnoPorId(Long id) {
        return null;
    }

    @Override
    public TurnoDtoSalida actualizarTurno(TurnoDtoEntrada turnoDtoEntrada, Long id) {
        return null;
    }

    @Override
    public void eliminarTurno(Long id) {

    }
}
