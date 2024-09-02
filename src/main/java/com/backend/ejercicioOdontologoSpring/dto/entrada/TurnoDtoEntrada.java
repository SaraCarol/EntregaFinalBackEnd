package com.backend.ejercicioOdontologoSpring.dto.entrada;

import com.backend.ejercicioOdontologoSpring.entitty.Odontologo;
import com.backend.ejercicioOdontologoSpring.entitty.Paciente;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class TurnoDtoEntrada {

    @NotNull(message = "El Paciente no puede ser nulo")
    @Valid
    private TurnoPacienteDtoEntrada turnoPacienteDtoEntrada;

    @NotNull(message = "El Odontologo no puede ser nulo")
    @Valid
    private TurnoOdontologoDtoEntrada turnoOdontologoDtoEntrada;

    public TurnoDtoEntrada() {
    }

    public TurnoDtoEntrada(TurnoPacienteDtoEntrada turnoPacienteDtoEntrada, TurnoOdontologoDtoEntrada turnoOdontologoDtoEntrada) {
        this.turnoPacienteDtoEntrada = turnoPacienteDtoEntrada;
        this.turnoOdontologoDtoEntrada = turnoOdontologoDtoEntrada;
    }

    public TurnoPacienteDtoEntrada getTurnoPacienteDtoEntrada() {
        return turnoPacienteDtoEntrada;
    }

    public void setTurnoPacienteDtoEntrada(TurnoPacienteDtoEntrada turnoPacienteDtoEntrada) {
        this.turnoPacienteDtoEntrada = turnoPacienteDtoEntrada;
    }

    public TurnoOdontologoDtoEntrada getTurnoOdontologoDtoEntrada() {
        return turnoOdontologoDtoEntrada;
    }

    public void setTurnoOdontologoDtoEntrada(TurnoOdontologoDtoEntrada turnoOdontologoDtoEntrada) {
        this.turnoOdontologoDtoEntrada = turnoOdontologoDtoEntrada;
    }
}
