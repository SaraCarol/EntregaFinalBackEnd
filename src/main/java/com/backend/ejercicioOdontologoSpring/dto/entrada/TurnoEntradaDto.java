package com.backend.ejercicioOdontologoSpring.dto.entrada;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class TurnoEntradaDto {

    @NotNull(message = "El Paciente no puede ser nulo")
    @Valid
    private TurnoPacienteEntradaDto turnoPacienteEntradaDto;

    @NotNull(message = "El Odontologo no puede ser nulo")
    @Valid
    private TurnoOdontologoEntradaDto turnoOdontologoEntradaDto;

    public TurnoEntradaDto() {
    }

    public TurnoEntradaDto(TurnoPacienteEntradaDto turnoPacienteEntradaDto, TurnoOdontologoEntradaDto turnoOdontologoEntradaDto) {
        this.turnoPacienteEntradaDto = turnoPacienteEntradaDto;
        this.turnoOdontologoEntradaDto = turnoOdontologoEntradaDto;
    }

    public TurnoPacienteEntradaDto getTurnoPacienteDtoEntrada() {
        return turnoPacienteEntradaDto;
    }

    public void setTurnoPacienteDtoEntrada(TurnoPacienteEntradaDto turnoPacienteEntradaDto) {
        this.turnoPacienteEntradaDto = turnoPacienteEntradaDto;
    }

    public TurnoOdontologoEntradaDto getTurnoOdontologoDtoEntrada() {
        return turnoOdontologoEntradaDto;
    }

    public void setTurnoOdontologoDtoEntrada(TurnoOdontologoEntradaDto turnoOdontologoEntradaDto) {
        this.turnoOdontologoEntradaDto = turnoOdontologoEntradaDto;
    }
}
