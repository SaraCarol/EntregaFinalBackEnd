package com.backend.ejercicioOdontologoSpring.controller;

import com.backend.ejercicioOdontologoSpring.dto.entrada.OdontologoEntradaDto;
import com.backend.ejercicioOdontologoSpring.dto.salida.OdontologoSalidaDto;
import com.backend.ejercicioOdontologoSpring.exceptions.ResourceNotFoundException;
import com.backend.ejercicioOdontologoSpring.service.IOdontologoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("odontologos")
public class OdontologoController {

    private IOdontologoService odontologoService;

    public OdontologoController(IOdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<OdontologoSalidaDto> registrarOdontologo(@RequestBody @Valid OdontologoEntradaDto odontologoDtoEntrada){
        OdontologoSalidaDto odontologoSalidaDto = odontologoService.registrarOdontologo(odontologoDtoEntrada);
        return new ResponseEntity<>(odontologoSalidaDto, HttpStatus.CREATED);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<OdontologoSalidaDto>> listarOdontologos (){
            return new ResponseEntity<>(odontologoService.listarOdontologos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OdontologoSalidaDto> buscarOdontologoPorId(@PathVariable Long id){
        return new ResponseEntity<>(odontologoService.buscarOdontologoPorId(id), HttpStatus.OK);
    }
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<OdontologoSalidaDto> actualizarDatosOdontologo(@RequestBody @Valid OdontologoEntradaDto odontologoDtoentrada, @PathVariable Long id){
        return new ResponseEntity<>(odontologoService.actualizarOdontologo(odontologoDtoentrada, id), HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Long id) throws ResourceNotFoundException {
        return new ResponseEntity<>("Odontologo eliminado con éxito", HttpStatus.NO_CONTENT);
    }
}
