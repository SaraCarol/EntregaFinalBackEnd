package com.backend.ejercicioOdontologoSpring.dto.salida;

public class OdontologoSalidaDto {
    private Long id;
    private int numeroMatricula;
    private String apellido;
    private String nombre;

    public OdontologoSalidaDto() {
    }

    public OdontologoSalidaDto(Long id, int numeroMatricula, String apellido, String nombre) {
        this.id = id;
        this.numeroMatricula = numeroMatricula;
        this.apellido = apellido;
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
