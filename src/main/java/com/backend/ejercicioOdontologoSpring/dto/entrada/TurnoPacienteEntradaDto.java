package com.backend.ejercicioOdontologoSpring.dto.entrada;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TurnoPacienteEntradaDto {
    @NotBlank(message = "El campo nombre no puede estar vacío")
    @Size(min = 3, max = 20, message = "El campo nombre debe tener mínimo 3 carácteres y máximo 20")
    private String nombre;

    @NotBlank(message = "El campo apellido no puede estar vacío")
    @Size(min = 3, max = 20, message = "El campo apellido debe tener mínimo 3 carácteres y máximo 20")
    private String apellido;

    public TurnoPacienteEntradaDto() {
    }

    public TurnoPacienteEntradaDto(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
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
}
