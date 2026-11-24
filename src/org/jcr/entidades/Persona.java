/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.jcr.entidades;

import org.jcr.enums.TipoSangre;
import java.time.LocalDate;
import java.time.Period;

public abstract class Persona {
    protected String nombre;
    protected String apellido;
    protected String dni;
    protected LocalDate fechaNacimiento;
    protected TipoSangre tipoSangre;

    public Persona(String nombre, String apellido, String dni, LocalDate fechaNacimiento, TipoSangre tipoSangre) {
        this.nombre = Validaciones.textoObligatorio(nombre, "El nombre es obligatorio");
        this.apellido = Validaciones.textoObligatorio(apellido, "El apellido es obligatorio");
        validarDni(dni);
        this.dni = dni;
        Validaciones.noNulo(fechaNacimiento, "La fecha de nacimiento es obligatoria");
        this.fechaNacimiento = fechaNacimiento;
        Validaciones.noNulo(tipoSangre, "El tipo de sangre es obligatorio");
        this.tipoSangre = tipoSangre;
    }

    private void validarDni(String dni) {
        Validaciones.textoObligatorio(dni, "El DNI es obligatorio");
        if (dni.length() < 7 || dni.length() > 8 || !Validaciones.sonDigitos(dni)) {
            throw new IllegalArgumentException("DNI inválido");
        }
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDni() {
        return dni;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public TipoSangre getTipoSangre() {
        return tipoSangre;
    }

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }

    public int getEdad() {
        return Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }
}