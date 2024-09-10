package com.backend.ejercicioOdontologoSpring.dto.salida;

import java.time.LocalDate;

public class TurnoSalidaDto {
    private Long id;
    private TurnoOdontologoSalidaDto odontologoSalida;
    private TurnoPacienteSalidaDto pacienteDtoSalida;
    private LocalDate fechaTurno;
    private LocalDate horaTurno;

    public TurnoSalidaDto() {
    }
    
}
