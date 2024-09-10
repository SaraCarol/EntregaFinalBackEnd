package com.backend.ejercicioOdontologoSpring.dto.salida;

public class TurnoPacienteSalidaDto {
    private Long id;
    private String nombre;
    private String apellido;

    public TurnoPacienteSalidaDto() {
    }

    public TurnoPacienteSalidaDto(String nombre, String apellido, Long id) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.id = id;
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
    public Long getid() {
        return id;
    }
}


