package com.backend.ejercicioOdontologoSpring.controller;

import com.backend.ejercicioOdontologoSpring.dto.entrada.PacienteDtoEntrada;
import com.backend.ejercicioOdontologoSpring.dto.salida.PacienteDtoSalida;
import com.backend.ejercicioOdontologoSpring.service.IPacienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pacientes")
public class PacienteController {
    private IPacienteService pacienteService;

    public PacienteController(IPacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<PacienteDtoSalida> registrarPaciente(@RequestBody @Valid PacienteDtoEntrada pacienteDtoEntrada){
        return new ResponseEntity<>(pacienteService.registrarPaciente(pacienteDtoEntrada), HttpStatus.CREATED);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<PacienteDtoSalida>> listarPacientes (){
        return new ResponseEntity<>(pacienteService.listarPacientes(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDtoSalida> buscarPacientePorId(@PathVariable Long id){
        return new ResponseEntity<>(pacienteService.buscarPacientePorId(id), HttpStatus.OK);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<PacienteDtoSalida> actualizarDatosPaciente(@RequestBody @Valid PacienteDtoEntrada pacienteDtoEntrada, @PathVariable Long id){
        return new ResponseEntity<>(pacienteService.actualizarPaciente(pacienteDtoEntrada, id), HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Long id){
        return new ResponseEntity<>("Paciente eliminado con éxito", HttpStatus.NO_CONTENT);
    }
}
