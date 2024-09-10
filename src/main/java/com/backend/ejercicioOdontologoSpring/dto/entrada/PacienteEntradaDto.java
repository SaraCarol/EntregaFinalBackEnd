package com.backend.ejercicioOdontologoSpring.dto.entrada;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class PacienteEntradaDto {

    @NotBlank(message = "El campo nombre no puede estar vacío")
    @Size(min = 3, max = 20, message = "El campo nombre debe tener mínimo 3 carácteres y máximo 20")
    private String nombre;

    @NotBlank(message = "El campo apellido no puede estar vacío")
    @Size(min = 3, max = 20, message = "El campo apellido debe tener mínimo 3 carácteres y máximo 20")
    private String apellido;

    @Positive(message = "El DNI no puede ser nulo y debe ser un número positivo")
    private int dni;

    @FutureOrPresent(message = "La fecha de ingreso no puede ser anterior a la fecha actual")
    @NotNull(message = "El campo Fecha de Ingreso no puede estar vacío")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "AAAA-MM-DD")
    private LocalDate fechaIngreso;

    @NotNull(message = "El domicilio del paciente no puede ser nulo")
    @Valid
    private DomicilioEntradaDto domicilioEntradaDto;

    public PacienteEntradaDto() {
    }

    public PacienteEntradaDto(String nombre, String apellido, int dni, LocalDate fechaIngreso, DomicilioEntradaDto domicilioEntradaDto) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaIngreso = fechaIngreso;
        this.domicilioEntradaDto = domicilioEntradaDto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public DomicilioEntradaDto getDomicilioEntradaDto() {
        return domicilioEntradaDto;
    }

    public void setDomicilio(DomicilioEntradaDto domicilioEntradaDto) {
        this.domicilioEntradaDto = domicilioEntradaDto;
    }
}
