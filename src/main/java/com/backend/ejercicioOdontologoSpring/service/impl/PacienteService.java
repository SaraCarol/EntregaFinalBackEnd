package com.backend.ejercicioOdontologoSpring.service.impl;

import com.backend.ejercicioOdontologoSpring.dto.entrada.PacienteDtoEntrada;
import com.backend.ejercicioOdontologoSpring.dto.salida.PacienteDtoSalida;
import com.backend.ejercicioOdontologoSpring.entitty.Paciente;
import com.backend.ejercicioOdontologoSpring.repository.IDao;
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
    private final IDao<Paciente> pacienteIDao;

    public PacienteService(IDao<Paciente> pacienteIDao, ModelMapper modelMapper) {
        this.pacienteIDao = pacienteIDao;
        this.modelMapper = modelMapper;
        configurarMapping();
    }

    private final ModelMapper modelMapper;


    @Override
    public PacienteDtoSalida registrarPaciente(PacienteDtoEntrada pacienteDtoEntrada) {
        LOGGER.info("PacienteDtoEntrada: {}", JsonPrinter.toString(pacienteDtoEntrada));
        Paciente paciente = modelMapper.map(pacienteDtoEntrada, Paciente.class);
        LOGGER.info("Paciente Entidad: {}", JsonPrinter.toString(paciente));
        Paciente pacienteRegistrado = pacienteIDao.registrar(paciente);
        LOGGER.info("Paciente Registrado: {}", JsonPrinter.toString(pacienteRegistrado));
        PacienteDtoSalida pacienteDtoSalida = modelMapper.map(pacienteRegistrado, PacienteDtoSalida.class);
        LOGGER.info("PacienteDtoSalida: {}", JsonPrinter.toString(pacienteDtoSalida));

        return pacienteDtoSalida;
    }

    @Override
    public PacienteDtoSalida buscarPacientePorId(Long id) {
        return null;
    }

    @Override
    public List<PacienteDtoSalida> listarPacientes() {
        List<PacienteDtoSalida> pacienteDtoSalidas = pacienteIDao.listarTodos()
                .stream()
                .map(paciente -> modelMapper.map(paciente, PacienteDtoSalida.class))
                .toList();
        LOGGER.info("Listado pacientes: {}", JsonPrinter.toString(pacienteDtoSalidas));
        return pacienteDtoSalidas;
    }

    @Override
    public PacienteDtoSalida actualizarPaciente(PacienteDtoEntrada pacienteDtoEntrada, Long id) {
        return null;
    }

    @Override
    public void eliminarPaciente(Long id) {
        if(buscarPacientePorId(id) != null){
            pacienteIDao.eliminar(id);
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
