package com.backend.ejercicioOdontologoSpring.dto.entrada;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class DomicilioEntradaDto {
    @NotBlank(message = "Se debe completar el campo Calle")
    @Size(min = 1, max = 10, message = "El campo Calle debe tener mínimo 1 carácter y máximo 10")
    private String calle;

    @Positive(message = "El número debe ser positivo")
    @Digits(integer = 8, fraction = 0, message = "El número debe ser entero de máximo 8 digitos")
    private int numero;

    @NotBlank(message = "Se debe completar el campo Localidad")
    @Size(min = 1, max = 10, message = "El campo Localidad debe tener mínimo 1 carácter y máximo 10")
    private String localidad;

    @NotBlank(message = "Se debe completar el campo Provincia")
    @Size(min = 1, max = 10, message = "El campo Provincia debe tener mínimo 1 carácter y máximo 10")
    private String provincia;

    public DomicilioEntradaDto() {
    }

    public DomicilioEntradaDto(String calle, int numero, String localidad, String provincia) {
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.provincia = provincia;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
}
