package com.backend.ejercicioOdontologoSpring.service.impl;

import com.backend.ejercicioOdontologoSpring.dto.entrada.OdontologoDtoEntrada;
import com.backend.ejercicioOdontologoSpring.dto.salida.OdontologoDtoSalida;
import com.backend.ejercicioOdontologoSpring.entitty.Odontologo;
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
    private final IDao<Odontologo> odontologoIDao;
    private final ModelMapper modelMapper;

    public OdontologoService(IDao<Odontologo> odontologoIDao, ModelMapper modelMapper) {
        this.odontologoIDao = odontologoIDao;
        this.modelMapper = modelMapper;
    }


    @Override
    public OdontologoDtoSalida registrarOdontologo(OdontologoDtoEntrada odontologoDtoEntrada) {
        LOGGER.info("OdontologoDtoEntrada: {}", JsonPrinter.toString(odontologoDtoEntrada));
        Odontologo odontologo = modelMapper.map(odontologoDtoEntrada, Odontologo.class);
        LOGGER.info("Odontologo Entidad: {}", JsonPrinter.toString(odontologo));
        Odontologo odontologoRegistrado = odontologoIDao.registrar(odontologo);
        LOGGER.info("Odontologo Registrado: {}", JsonPrinter.toString(odontologoRegistrado));
        OdontologoDtoSalida odontologoDtoSalida = modelMapper.map(odontologoRegistrado, OdontologoDtoSalida.class);
        LOGGER.info("OdontologoDtoSalida: {}", JsonPrinter.toString(odontologoDtoSalida));

        return odontologoDtoSalida;
    }

    @Override
    public List<OdontologoDtoSalida> listarOdontologos() {
        List<OdontologoDtoSalida> odontologoDtoSalidas = odontologoIDao.listarTodos()
                .stream()
                .map(odontologo -> modelMapper.map(odontologo, OdontologoDtoSalida.class))
                .toList();
        LOGGER.info("Lista de odontólogos: {}", JsonPrinter.toString(odontologoDtoSalidas));
        return odontologoDtoSalidas;
    }

    @Override
    public OdontologoDtoSalida buscarOdontologoPorId(Long id) {
        return null;
    }

    @Override
    public OdontologoDtoSalida actualizarOdontologo(OdontologoDtoEntrada odontologoDtoEntrada, Long id) {
        return null;
    }

    @Override
    public void eliminarOdontologo(Long id) {
        if (buscarOdontologoPorId(id) != null){
            odontologoIDao.eliminar(id);
            LOGGER.warn("Se ha eliminado correctamete al odontólogo con id: {}", id);
        }
    }
}
