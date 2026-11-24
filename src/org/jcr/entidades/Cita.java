/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.jcr.entidades;

import org.jcr.enums.EstadoCita;
import java.time.LocalDateTime;

public class Cita {
    private Paciente paciente;
    private Medico medico;
    private Sala sala;
    private LocalDateTime fechaHora;
    private double costo;
    private EstadoCita estado;
    private String observaciones;

    public Cita(Paciente paciente, Medico medico, Sala sala, LocalDateTime fechaHora, double costo) {
        Validaciones.noNulo(paciente, "El paciente es obligatorio");
        Validaciones.noNulo(medico, "El médico es obligatorio");
        Validaciones.noNulo(sala, "La sala es obligatoria");
        Validaciones.noNulo(fechaHora, "La fecha y hora son obligatorias");
        
        this.paciente = paciente;
        this.medico = medico;
        this.sala = sala;
        this.fechaHora = fechaHora;
        this.costo = costo;
        this.estado = EstadoCita.PROGRAMADA;
        this.observaciones = "";
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public Sala getSala() {
        return sala;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public double getCosto() {
        return costo;
    }

    public EstadoCita getEstado() {
        return estado;
    }

    public void setEstado(EstadoCita estado) {
        Validaciones.noNulo(estado, "El estado no puede ser nulo");
        this.estado = estado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        if (observaciones == null) {
            this.observaciones = "";
        } else {
            this.observaciones = observaciones;
        }
    }
}