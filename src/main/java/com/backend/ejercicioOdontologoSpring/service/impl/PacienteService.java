package com.backend.ejercicioOdontologoSpring.service.impl;

import com.backend.ejercicioOdontologoSpring.dto.entrada.PacienteDtoEntrada;
import com.backend.ejercicioOdontologoSpring.dto.salida.PacienteDtoSalida;
import com.backend.ejercicioOdontologoSpring.entitty.Paciente;
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
    public PacienteDtoSalida registrarPaciente(PacienteDtoEntrada pacienteDtoEntrada) {
        LOGGER.info("PacienteDtoEntrada: {}", JsonPrinter.toString(pacienteDtoEntrada));
        Paciente paciente = modelMapper.map(pacienteDtoEntrada, Paciente.class);
        LOGGER.info("Paciente Entidad: {}", JsonPrinter.toString(paciente));
        Paciente pacienteRegistrado = pacienteRepository.save(paciente);
        LOGGER.info("Paciente Registrado: {}", JsonPrinter.toString(pacienteRegistrado));
        PacienteDtoSalida pacienteDtoSalida = modelMapper.map(pacienteRegistrado, PacienteDtoSalida.class);
        LOGGER.info("PacienteDtoSalida: {}", JsonPrinter.toString(pacienteDtoSalida));

        return pacienteDtoSalida;
    }

    @Override
    public PacienteDtoSalida buscarPacientePorId(Long id) {
        Paciente pacienteBuscado = pacienteRepository.findById(id).orElse(null);
        LOGGER.info("Paciente buscado: {}", JsonPrinter.toString(pacienteBuscado));
        PacienteDtoSalida pacienteDtoSalida = null;
        if(pacienteBuscado != null){
            pacienteDtoSalida = modelMapper.map(pacienteBuscado, PacienteDtoSalida.class);
            LOGGER.info("Paciente encontrado: {]", JsonPrinter.toString(pacienteDtoSalida));
        } else LOGGER.error("No se ha encontrado al paciente con id: {}", id);

        return pacienteDtoSalida;
    }

    @Override
    public List<PacienteDtoSalida> listarPacientes() {
        List<PacienteDtoSalida> pacienteDtoSalidas = pacienteRepository.findAll()
                .stream()
                .map(paciente -> modelMapper.map(paciente, PacienteDtoSalida.class))
                .toList();
        LOGGER.info("Listado pacientes: {}", JsonPrinter.toString(pacienteDtoSalidas));
        return pacienteDtoSalidas;
    }

    @Override
    public PacienteDtoSalida actualizarPaciente(PacienteDtoEntrada pacienteDtoEntrada, Long id) {
        Paciente pacienteParaActualizar = pacienteRepository.findById(id).orElse(null);
        Paciente pacienteRecibido = modelMapper.map(pacienteDtoEntrada, Paciente.class);
        PacienteDtoSalida pacienteDtoSalida = null;

        if(pacienteParaActualizar != null){
            pacienteRecibido.setId(pacienteParaActualizar.getId());
            pacienteRecibido.getDomicilio().setId(pacienteParaActualizar.getDomicilio().getId());

            pacienteParaActualizar = pacienteRecibido;
            pacienteRepository.save(pacienteParaActualizar);

            pacienteDtoSalida = modelMapper.map(pacienteParaActualizar, PacienteDtoSalida.class);
            LOGGER.warn("Paciente actualizado: {}", JsonPrinter.toString(pacienteDtoSalida));

        } else LOGGER.error("No fue posible actualizar el paciente porque no se encuentra registrado");
        return pacienteDtoSalida;
    }

    @Override
    public void eliminarPaciente(Long id) {
        if(buscarPacientePorId(id) != null){
            pacienteRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado correctamete al paciende con id: {}", id);
        } else {
            //excepción personalizada
            LOGGER.info("Excepcion");
        };
    }

    private void configurarMapping(){
        modelMapper.typeMap(PacienteDtoEntrada.class, Paciente.class)
                .addMappings(mapper -> mapper.map(
                        PacienteDtoEntrada::getDomicilio, Paciente::setDomicilio));
        modelMapper.typeMap(Paciente.class, PacienteDtoSalida.class)
                .addMappings(mapper -> mapper.map(
                        Paciente::getDomicilio, PacienteDtoSalida::setApellido));
    }
}
