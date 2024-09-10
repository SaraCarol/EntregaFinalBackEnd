package com.backend.ejercicioOdontologoSpring.dto.entrada;

import jakarta.validation.constraints.*;

public class OdontologoEntradaDto {

    @Positive(message = "El campo Número de Matrícula no puede estar vacío")
    @Digits(integer = 10, fraction = 0, message = "El campo Número de Matrícula debe tener una longitud máxima de 10")
    private int numeroMatricula;

    @NotBlank(message = "El campo nombre no puede estar vacío")
    @Size(min = 3, max = 20, message = "El campo nombre debe tener mínimo 3 carácteres y máximo 20")
    private String apellido;

    @NotBlank(message = "El campo apellido no puede estar vacío")
    @Size(min = 3, max = 20, message = "El campo apellido debe tener mínimo 3 carácteres y máximo 20")
    private String nombre;

    public OdontologoEntradaDto() {
    }

    public OdontologoEntradaDto(int numeroMatricula, String apellido, String nombre) {
        this.numeroMatricula = numeroMatricula;
        this.apellido = apellido;
        this.nombre = nombre;
    }

    public int getNumeroMatricula() {
        return numeroMatricula;
    }

    public void setNumeroMatricula(int numeroMatricula) {
        this.numeroMatricula = numeroMatricula;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
