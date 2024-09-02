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
    private Paciente paciente;

    @NotNull(message = "El Odontologo no puede ser nulo")
    @Valid
    private Odontologo odontologo;


}
