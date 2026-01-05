package com.ejercicio2.Veterinaria.model;

import java.time.LocalDate;

public class Mascota {
    private Long id;
    private String nombre;
    private String especie;
    private LocalDate fechaNacimiento;
    private String propietario;

    // Constructor
    public Mascota(Long id, String nombre, String especie, LocalDate fechaNacimiento, String propietario) {
        this.id = id;
        this.nombre = nombre;
        this.especie = especie;
        this.fechaNacimiento = fechaNacimiento;
        this.propietario = propietario;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    // Metodo para calcular la edad
    public int getEdad() {
        return LocalDate.now().getYear() - this.fechaNacimiento.getYear();
    }
}
