package com.backend.ejercicioOdontologoSpring.service;

import com.backend.ejercicioOdontologoSpring.dto.entrada.OdontologoEntradaDto;
import com.backend.ejercicioOdontologoSpring.dto.salida.OdontologoSalidaDto;

import java.util.List;

public interface IOdontologoService {
    OdontologoSalidaDto registrarOdontologo(OdontologoEntradaDto odontologoAregistrar);

    List<OdontologoSalidaDto> listarOdontologos();

    OdontologoSalidaDto buscarOdontologoPorId(Long id);

    OdontologoSalidaDto actualizarOdontologo(OdontologoEntradaDto odontologoDtoEntrada, Long id);

    void eliminarOdontologo(Long id);
}
