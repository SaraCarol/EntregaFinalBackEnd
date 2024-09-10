package com.backend.ejercicioOdontologoSpring.service.impl;

import com.backend.ejercicioOdontologoSpring.dto.entrada.PacienteEntradaDto;
import com.backend.ejercicioOdontologoSpring.dto.salida.PacienteSalidaDto;
import com.backend.ejercicioOdontologoSpring.entitty.Paciente;
import com.backend.ejercicioOdontologoSpring.exceptions.ResourceNotFoundException;
import com.backend.ejercicioOdontologoSpring.repository.PacienteRepository;
import com.backend.ejercicioOdontologoSpring.service.IPacienteService;
import com.backend.ejercicioOdontologoSpring.utils.JsonPrinter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService implements IPacienteService {


    private final Logger LOGGER = LoggerFactory.getLogger(PacienteService.class);
    private final PacienteRepository pacienteRepository;
    private final ModelMapper modelMapper;

    public PacienteService(PacienteRepository pacienteRepository, ModelMapper modelMapper) {
        this.pacienteRepository = pacienteRepository;
        this.modelMapper = modelMapper;
        configurarMapping();
    }

    @Override
    public PacienteSalidaDto registrarPaciente(PacienteEntradaDto pacienteDtoEntrada) {
        LOGGER.info("PacienteDtoEntrada: {}", JsonPrinter.toString(pacienteDtoEntrada));
        Paciente paciente = modelMapper.map(pacienteDtoEntrada, Paciente.class);
        LOGGER.info("Paciente Entidad: {}", JsonPrinter.toString(paciente));
        Paciente pacienteRegistrado = pacienteRepository.save(paciente);
        LOGGER.info("Paciente Registrado: {}", JsonPrinter.toString(pacienteRegistrado));
        PacienteSalidaDto pacienteSalidaDto = modelMapper.map(pacienteRegistrado, PacienteSalidaDto.class);
        LOGGER.info("PacienteDtoSalida: {}", JsonPrinter.toString(pacienteSalidaDto));

        return pacienteSalidaDto;
    }

    @Override
    public PacienteSalidaDto buscarPacientePorId(Long id) {
        Paciente pacienteBuscado = pacienteRepository.findById(id).orElse(null);
        LOGGER.info("Paciente buscado: {}", JsonPrinter.toString(pacienteBuscado));
        PacienteSalidaDto pacienteSalidaDto = null;
        if(pacienteBuscado != null){
            pacienteSalidaDto = modelMapper.map(pacienteBuscado, PacienteSalidaDto.class);
            LOGGER.info("Paciente encontrado: {}", JsonPrinter.toString(pacienteSalidaDto));
        } else LOGGER.error("No se ha encontrado al paciente con id: {}", id);

        return pacienteSalidaDto;
    }

    @Override
    public List<PacienteSalidaDto> listarPacientes() {
        List<PacienteSalidaDto> pacienteSalidaDtos = pacienteRepository.findAll()
                .stream()
                .map(paciente -> modelMapper.map(paciente, PacienteSalidaDto.class))
                .toList();
        LOGGER.info("Listado pacientes: {}", JsonPrinter.toString(pacienteSalidaDtos));
        return pacienteSalidaDtos;
    }

    @Override
    public PacienteSalidaDto actualizarPaciente(PacienteEntradaDto pacienteDtoEntrada, Long id) {
        Paciente pacienteParaActualizar = pacienteRepository.findById(id).orElse(null);
        Paciente pacienteRecibido = modelMapper.map(pacienteDtoEntrada, Paciente.class);
        PacienteSalidaDto pacienteSalidaDto = null;

        if(pacienteParaActualizar != null){
            pacienteRecibido.setId(pacienteParaActualizar.getId());
            pacienteRecibido.getDomicilio().setId(pacienteParaActualizar.getDomicilio().getId());

            pacienteParaActualizar = pacienteRecibido;
            pacienteRepository.save(pacienteParaActualizar);

            pacienteSalidaDto = modelMapper.map(pacienteParaActualizar, PacienteSalidaDto.class);
            LOGGER.warn("Paciente actualizado: {}", JsonPrinter.toString(pacienteSalidaDto));

        } else LOGGER.error("No fue posible actualizar el paciente porque no se encuentra registrado");
        return pacienteSalidaDto;
    }

    @Override
    public void eliminarPaciente(Long id) throws ResourceNotFoundException {
        if(buscarPacientePorId(id) != null){
            pacienteRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado correctamete al paciente con id: {}", id);
        } else {
            throw new ResourceNotFoundException("No existe el paciente con id " + id);
        };
    }

    private void configurarMapping(){
        modelMapper.typeMap(PacienteEntradaDto.class, Paciente.class)
                .addMappings(mapper -> mapper.map(PacienteEntradaDto::getDomicilioEntradaDto, Paciente::setDomicilio));
        modelMapper.typeMap(Paciente.class, PacienteSalidaDto.class)
                .addMappings(mapper -> mapper.map(Paciente::getDomicilio, PacienteSalidaDto::setDomicilioSalidaDto));
    }
}
