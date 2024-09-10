package com.backend.ejercicioOdontologoSpring.service.impl;

import com.backend.ejercicioOdontologoSpring.dto.entrada.TurnoEntradaDto;
import com.backend.ejercicioOdontologoSpring.dto.salida.PacienteSalidaDto;
import com.backend.ejercicioOdontologoSpring.dto.salida.TurnoSalidaDto;
import com.backend.ejercicioOdontologoSpring.entitty.Turno;
import com.backend.ejercicioOdontologoSpring.exceptions.ResourceNotFoundException;
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
    private final PacienteService pacienteService;
    private final OdontologoService odontologoService;

    public TurnoService(TurnoRepository turnoRepository, ModelMapper modelMapper, PacienteService pacienteService, OdontologoService odontologoService) {
        this.turnoRepository = turnoRepository;
        this.modelMapper = modelMapper;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }
//    public TurnoSalidaDto resgistrarTurno(TurnoEntradaDto turnoDtoEntrada){
//       PacienteSalidaDto pacienteSalidaDto = pacienteService.buscarPacientePorId()
//    }
    @Override
    public List<TurnoSalidaDto> listarTurnos() {
        List<TurnoSalidaDto> turnoSalidaDtos = turnoRepository.findAll()
                .stream()
                .map(turno -> modelMapper.map(turno, TurnoSalidaDto.class))
                .toList();
        LOGGER.info("Listado turnos: {}", JsonPrinter.toString(turnoSalidaDtos));
        return turnoSalidaDtos;
    }

    @Override
    public TurnoSalidaDto buscarTurnoPorId(Long id) {
        Turno turnoBuscado = turnoRepository.findById(id).orElse(null);
        LOGGER.info("Turno buscado: {}", JsonPrinter.toString(turnoBuscado));
        TurnoSalidaDto turnoSalidaDto = null;
        if(turnoBuscado != null){
            turnoSalidaDto = modelMapper.map(turnoBuscado, TurnoSalidaDto.class);
            LOGGER.info("Turno encontrado: {}", JsonPrinter.toString(turnoSalidaDto));
        } else LOGGER.error("No se ha encontrado al turno con id: {}", id);

        return turnoSalidaDto;
    }

    @Override
    public TurnoSalidaDto actualizarTurno(TurnoEntradaDto turnoDtoEntrada, Long id) {
        Turno turnoParaActualizar = turnoRepository.findById(id).orElse(null);
        Turno turnoRecibido = modelMapper.map(turnoDtoEntrada, Turno.class);
        TurnoSalidaDto turnoSalidaDto = null;

        if(turnoParaActualizar != null){
            turnoRecibido.setId(turnoParaActualizar.getId());
            turnoRecibido.getOdontologo().setId(turnoParaActualizar.getOdontologo().getId());
            turnoRecibido.getPaciente().setId(turnoParaActualizar.getPaciente().getId());

            turnoParaActualizar = turnoRecibido;
            turnoRepository.save(turnoParaActualizar);

            turnoSalidaDto = modelMapper.map(turnoParaActualizar, TurnoSalidaDto.class);
            LOGGER.warn("Paciente actualizado: {}", JsonPrinter.toString(turnoSalidaDto));

        } else LOGGER.error("No fue posible actualizar el turno porque no se encuentra registrado");
        return turnoSalidaDto;
    }

    @Override
    public void eliminarTurno(Long id) throws ResourceNotFoundException {
        if(buscarTurnoPorId(id) != null){
            turnoRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado correctamete al turno con id: {}", id);
        } else {
            throw new ResourceNotFoundException("No existe el turno con id " + id);
        };
    }

}
