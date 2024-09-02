package com.backend.ejercicioOdontologoSpring.service.impl;

import com.backend.ejercicioOdontologoSpring.dto.entrada.OdontologoDtoEntrada;
import com.backend.ejercicioOdontologoSpring.dto.salida.OdontologoDtoSalida;
import com.backend.ejercicioOdontologoSpring.dto.salida.PacienteDtoSalida;
import com.backend.ejercicioOdontologoSpring.entitty.Odontologo;
import com.backend.ejercicioOdontologoSpring.entitty.Paciente;
import com.backend.ejercicioOdontologoSpring.repository.OdontologoRepository;
import com.backend.ejercicioOdontologoSpring.service.IOdontologoService;
import com.backend.ejercicioOdontologoSpring.utils.JsonPrinter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdontologoService implements IOdontologoService {

    private final Logger LOGGER = LoggerFactory.getLogger(OdontologoService.class);
    private final OdontologoRepository odontologoRepository;
    private final ModelMapper modelMapper;

    public OdontologoService(OdontologoRepository odontologoRepository, ModelMapper modelMapper) {
        this.odontologoRepository = odontologoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public OdontologoDtoSalida registrarOdontologo(OdontologoDtoEntrada odontologoDtoEntrada) {
        LOGGER.info("OdontologoDtoEntrada: {}", JsonPrinter.toString(odontologoDtoEntrada));
        Odontologo odontologo = modelMapper.map(odontologoDtoEntrada, Odontologo.class);
        LOGGER.info("Odontologo Entidad: {}", JsonPrinter.toString(odontologo));
        Odontologo odontologoRegistrado = odontologoRepository.save(odontologo);
        LOGGER.info("Odontologo Registrado: {}", JsonPrinter.toString(odontologoRegistrado));
        OdontologoDtoSalida odontologoDtoSalida = modelMapper.map(odontologoRegistrado, OdontologoDtoSalida.class);
        LOGGER.info("OdontologoDtoSalida: {}", JsonPrinter.toString(odontologoDtoSalida));

        return odontologoDtoSalida;
    }

    @Override
    public List<OdontologoDtoSalida> listarOdontologos() {
        List<OdontologoDtoSalida> odontologoDtoSalidas = odontologoRepository.findAll()
                .stream()
                .map(odontologo -> modelMapper.map(odontologo, OdontologoDtoSalida.class))
                .toList();
        LOGGER.info("Lista de odontólogos: {}", JsonPrinter.toString(odontologoDtoSalidas));
        return odontologoDtoSalidas;
    }

    @Override
    public OdontologoDtoSalida buscarOdontologoPorId(Long id) {
        Odontologo odontologoBuscado = odontologoRepository.findById(id).orElse(null);
        LOGGER.info("Odontologo buscado: {}", JsonPrinter.toString(odontologoBuscado));
        OdontologoDtoSalida odontologoDtoSalida = null;
        if(odontologoBuscado != null){
            odontologoDtoSalida = modelMapper.map(odontologoBuscado, OdontologoDtoSalida.class);
            LOGGER.info("Odontologo encontrado: {]", JsonPrinter.toString(odontologoBuscado));
        } else LOGGER.error("No se ha encontrado al odontologo con id: {}", id);
        return odontologoDtoSalida;
    }

    @Override
    public OdontologoDtoSalida actualizarOdontologo(OdontologoDtoEntrada odontologoDtoEntrada, Long id) {
        Odontologo odontologoPorActualizar = odontologoRepository.findById(id).orElse(null);
        Odontologo odontologoRecibido = modelMapper.map(odontologoDtoEntrada, Odontologo.class);
        OdontologoDtoSalida odontologoDtoSalida = null;

        if(odontologoPorActualizar != null){
            odontologoRecibido.setId(odontologoPorActualizar.getId());
            odontologoPorActualizar = odontologoRecibido;
            odontologoRepository.save(odontologoPorActualizar);

            odontologoDtoSalida = modelMapper.map(odontologoPorActualizar, OdontologoDtoSalida.class);
            LOGGER.warn("Odontólogo actualizado: {}", odontologoDtoSalida);
            
        } else LOGGER.error("No se pudo actualizar el odontólogo porque no se encuentra registrado");
        return odontologoDtoSalida;
    }

    @Override
    public void eliminarOdontologo(Long id) {
        if (buscarOdontologoPorId(id) != null){
            odontologoRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado correctamete al odontólogo con id: {}", id);
        }
    }
}
