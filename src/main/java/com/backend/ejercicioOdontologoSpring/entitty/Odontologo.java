package com.backend.ejercicioOdontologoSpring.entitty;

import jakarta.persistence.*;

@Entity
@Table(name = "ODONTOLOGOS")
public class Odontologo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 10, nullable = false)
    private int numeroMatricula;
    @Column(length = 20, nullable = false)
    private String apellido;
    @Column(length = 20, nullable = false)
    private String nombre;

    public Odontologo() {
    }

    public Odontologo(Long id, int numeroMatricula, String apellido, String nombre) {
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
