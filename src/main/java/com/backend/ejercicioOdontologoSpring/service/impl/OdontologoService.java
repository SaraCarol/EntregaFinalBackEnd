package com.backend.ejercicioOdontologoSpring.service.impl;

import com.backend.ejercicioOdontologoSpring.dto.entrada.OdontologoEntradaDto;
import com.backend.ejercicioOdontologoSpring.dto.salida.OdontologoSalidaDto;
import com.backend.ejercicioOdontologoSpring.entitty.Odontologo;
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
    public OdontologoSalidaDto registrarOdontologo(OdontologoEntradaDto odontologoDtoEntrada) {
        LOGGER.info("OdontologoDtoEntrada: {}", JsonPrinter.toString(odontologoDtoEntrada));
        Odontologo odontologo = modelMapper.map(odontologoDtoEntrada, Odontologo.class);
        LOGGER.info("Odontologo Entidad: {}", JsonPrinter.toString(odontologo));
        Odontologo odontologoRegistrado = odontologoRepository.save(odontologo);
        LOGGER.info("Odontologo Registrado: {}", JsonPrinter.toString(odontologoRegistrado));
        OdontologoSalidaDto odontologoSalidaDto = modelMapper.map(odontologoRegistrado, OdontologoSalidaDto.class);
        LOGGER.info("OdontologoDtoSalida: {}", JsonPrinter.toString(odontologoSalidaDto));

        return odontologoSalidaDto;
    }

    @Override
    public List<OdontologoSalidaDto> listarOdontologos() {
        List<OdontologoSalidaDto> odontologoSalidaDtos = odontologoRepository.findAll()
                .stream()
                .map(odontologo -> modelMapper.map(odontologo, OdontologoSalidaDto.class))
                .toList();
        LOGGER.info("Lista de odontólogos: {}", JsonPrinter.toString(odontologoSalidaDtos));
        return odontologoSalidaDtos;
    }

    @Override
    public OdontologoSalidaDto buscarOdontologoPorId(Long id) {
        Odontologo odontologoBuscado = odontologoRepository.findById(id).orElse(null);
        LOGGER.info("Odontologo buscado: {}", JsonPrinter.toString(odontologoBuscado));
        OdontologoSalidaDto odontologoSalidaDto = null;
        if(odontologoBuscado != null){
            odontologoSalidaDto = modelMapper.map(odontologoBuscado, OdontologoSalidaDto.class);
            LOGGER.info("Odontologo encontrado: {]", JsonPrinter.toString(odontologoBuscado));
        } else LOGGER.error("No se ha encontrado al odontologo con id: {}", id);
        return odontologoSalidaDto;
    }

    @Override
    public OdontologoSalidaDto actualizarOdontologo(OdontologoEntradaDto odontologoDtoEntrada, Long id) {
        Odontologo odontologoPorActualizar = odontologoRepository.findById(id).orElse(null);
        Odontologo odontologoRecibido = modelMapper.map(odontologoDtoEntrada, Odontologo.class);
        OdontologoSalidaDto odontologoSalidaDto = null;

        if(odontologoPorActualizar != null){
            odontologoRecibido.setId(odontologoPorActualizar.getId());
            odontologoPorActualizar = odontologoRecibido;
            odontologoRepository.save(odontologoPorActualizar);

            odontologoSalidaDto = modelMapper.map(odontologoPorActualizar, OdontologoSalidaDto.class);
            LOGGER.warn("Odontólogo actualizado: {}", odontologoSalidaDto);

        } else LOGGER.error("No se pudo actualizar el odontólogo porque no se encuentra registrado");
        return odontologoSalidaDto;
    }

    @Override
    public void eliminarOdontologo(Long id) {
        if (buscarOdontologoPorId(id) != null){
            odontologoRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado correctamete al odontólogo con id: {}", id);
        }
    }
}
