package com.backend.ejercicioOdontologoSpring.dto.salida;

public class TurnoOdontologoSalidaDto {
    private String apellido;
    private String nombre;

    public TurnoOdontologoSalidaDto() {
    }

    public TurnoOdontologoSalidaDto(String apellido, String nombre) {
        this.apellido = apellido;
        this.nombre = nombre;
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
