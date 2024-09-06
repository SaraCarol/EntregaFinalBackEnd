package com.backend.ejercicioOdontologoSpring.controller;

import com.backend.ejercicioOdontologoSpring.dto.entrada.OdontologoDtoEntrada;
import com.backend.ejercicioOdontologoSpring.dto.salida.OdontologoDtoSalida;
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
    public ResponseEntity<OdontologoDtoSalida> registrarOdontologo(@RequestBody @Valid OdontologoDtoEntrada odontologoDtoEntrada){
        OdontologoDtoSalida odontologoDtoSalida = odontologoService.registrarOdontologo(odontologoDtoEntrada);
        return new ResponseEntity<>(odontologoDtoSalida, HttpStatus.CREATED);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<OdontologoDtoSalida>> listarOdontologos (){
            return new ResponseEntity<>(odontologoService.listarOdontologos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OdontologoDtoSalida> buscarOdontologoPorId(@PathVariable Long id){
        return new ResponseEntity<>(odontologoService.buscarOdontologoPorId(id), HttpStatus.OK);
    }
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<OdontologoDtoSalida> actualizarDatosOdontologo(@RequestBody @Valid OdontologoDtoEntrada odontologoDtoentrada, @PathVariable Long id){
        return new ResponseEntity<>(odontologoService.actualizarOdontologo(odontologoDtoentrada, id), HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Long id) throws ResourceNotFoundException {
        return new ResponseEntity<>("Odontologo eliminado con éxito", HttpStatus.NO_CONTENT);
    }
}
