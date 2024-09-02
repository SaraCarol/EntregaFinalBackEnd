package com.backend.ejercicioOdontologoSpring.dto.salida;

import java.time.LocalDate;

public class TurnoDtoSalida {
    private Long id;
    private TurnoOdontologoDtoSalida odontologoSalida;
    private TurnoPacienteDtoSalida pacienteDtoSalida;
    private LocalDate fechaTurno;
    private LocalDate horaTurno;

    public TurnoDtoSalida() {
    }
    
}
