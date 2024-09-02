package com.backend.ejercicioOdontologoSpring.dto.entrada;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

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
