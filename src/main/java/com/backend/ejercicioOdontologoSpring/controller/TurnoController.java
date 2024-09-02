package com.backend.ejercicioOdontologoSpring.controller;

import com.backend.ejercicioOdontologoSpring.dto.entrada.TurnoDtoEntrada;
import com.backend.ejercicioOdontologoSpring.dto.salida.TurnoDtoSalida;
import com.backend.ejercicioOdontologoSpring.service.ITurnoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    private ITurnoService turnoService;

    public TurnoController(ITurnoService turnoService) {
        this.turnoService = turnoService;
    }

    /*@PostMapping("/registrar")
    public ResponseEntity<TurnoDtoSalida> registrarTurno(@RequestBody @Valid TurnoDtoEntrada turnoDtoEntrada) {
        return new ResponseEntity<>(turnoService.registrarTurno(turnoDtoEntrada), HttpStatus.CREATED);
    }*/

    @GetMapping("/listar")
    public ResponseEntity<List<TurnoDtoSalida>> listarTurnos (){
        return new ResponseEntity<>(turnoService.listarTurnos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurnoDtoSalida> buscarTurnoPorId(@PathVariable Long id){
        return new ResponseEntity<>(turnoService.buscarTurnoPorId(id), HttpStatus.OK);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<TurnoDtoSalida> actualizarDatosTurno(@RequestBody @Valid TurnoDtoEntrada turnoDtoEntrada, @PathVariable Long id){
        return new ResponseEntity<>(turnoService.actualizarTurno(turnoDtoEntrada, id), HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id){
        return new ResponseEntity<>("Turno eliminado con éxito", HttpStatus.NO_CONTENT);
    }
}
