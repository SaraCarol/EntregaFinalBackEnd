package com.backend.ejercicioOdontologoSpring.service;

import com.backend.ejercicioOdontologoSpring.dto.entrada.OdontologoDtoEntrada;
import com.backend.ejercicioOdontologoSpring.dto.salida.OdontologoDtoSalida;
import com.backend.ejercicioOdontologoSpring.entitty.Odontologo;

import java.util.List;

public interface IOdontologoService {
    OdontologoDtoSalida registrarOdontologo(OdontologoDtoEntrada odontologoAregistrar);

    List<OdontologoDtoSalida> listarOdontologos();

    OdontologoDtoSalida buscarOdontologoPorId(Long id);

    OdontologoDtoSalida actualizarOdontologo(OdontologoDtoEntrada odontologoDtoEntrada, Long id);

    void eliminarOdontologo(Long id);
}
