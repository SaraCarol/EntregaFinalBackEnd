package com.backend.ejercicioOdontologoSpring.service.impl;

import com.backend.ejercicioOdontologoSpring.dto.entrada.TurnoDtoEntrada;
import com.backend.ejercicioOdontologoSpring.dto.salida.TurnoDtoSalida;
import com.backend.ejercicioOdontologoSpring.entitty.Turno;
import com.backend.ejercicioOdontologoSpring.repository.TurnoRepository;
import com.backend.ejercicioOdontologoSpring.service.ITurnoService;
import com.backend.ejercicioOdontologoSpring.utils.JsonPrinter;
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
        List<TurnoDtoSalida> turnoDtoSalidas = turnoRepository.findAll()
                .stream()
                .map(turno -> modelMapper.map(turno, TurnoDtoSalida.class))
                .toList();
        LOGGER.info("Listado turnos: {}", JsonPrinter.toString(turnoDtoSalidas));
        return turnoDtoSalidas;
    }

    @Override
    public TurnoDtoSalida buscarTurnoPorId(Long id) {
        Turno turnoBuscado = turnoRepository.findById(id).orElse(null);
        LOGGER.info("Turno buscado: {}", JsonPrinter.toString(turnoBuscado));
        TurnoDtoSalida turnoDtoSalida = null;
        if(turnoBuscado != null){
            turnoDtoSalida = modelMapper.map(turnoBuscado, TurnoDtoSalida.class);
            LOGGER.info("Turno encontrado: {}", JsonPrinter.toString(turnoDtoSalida));
        } else LOGGER.error("No se ha encontrado al turno con id: {}", id);

        return turnoDtoSalida;
    }

    @Override
    public TurnoDtoSalida actualizarTurno(TurnoDtoEntrada turnoDtoEntrada, Long id) {
        Turno turnoParaActualizar = turnoRepository.findById(id).orElse(null);
        Turno turnoRecibido = modelMapper.map(turnoDtoEntrada, Turno.class);
        TurnoDtoSalida turnoDtoSalida = null;

        if(turnoParaActualizar != null){
            turnoRecibido.setId(turnoParaActualizar.getId());
            turnoRecibido.getOdontologo().setId(turnoParaActualizar.getOdontologo().getId());
            turnoRecibido.getPaciente().setId(turnoParaActualizar.getPaciente().getId());

            turnoParaActualizar = turnoRecibido;
            turnoRepository.save(turnoParaActualizar);

            turnoDtoSalida = modelMapper.map(turnoParaActualizar, TurnoDtoSalida.class);
            LOGGER.warn("Paciente actualizado: {}", JsonPrinter.toString(turnoDtoSalida));

        } else LOGGER.error("No fue posible actualizar el turno porque no se encuentra registrado");
        return turnoDtoSalida;
    }

    @Override
    public void eliminarTurno(Long id) {
        if(buscarTurnoPorId(id) != null){
            turnoRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado correctamete al turno con id: {}", id);
        } else {
            //excepción personalizada
            LOGGER.info("Excepcion");
        };
    }

}
