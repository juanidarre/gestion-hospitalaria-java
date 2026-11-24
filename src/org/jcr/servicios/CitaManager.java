/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.jcr.servicios;

import org.jcr.entidades.Cita;
import org.jcr.entidades.Medico;
import org.jcr.entidades.Paciente;
import org.jcr.entidades.Sala;
import org.jcr.excepciones.CitaException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CitaManager implements CitaService {
    private static final int HORAS_ENTRE_CITAS = 2;
    private List<Cita> citas;

    public CitaManager() {
        this.citas = new ArrayList<>();
    }

    @Override
    public Cita programarCita(Paciente paciente, Medico medico, Sala sala, LocalDateTime fechaHora, double costo) throws CitaException {
        if (paciente == null || medico == null || sala == null) {
            throw new CitaException("Faltan datos obligatorios (paciente, médico o sala)");
        }
        
        validarCita(fechaHora, costo);
        
        if (medico.getEspecialidad() != sala.getDepartamento().getEspecialidad()) {
            throw new CitaException("El médico debe pertenecer a un departamento de la misma especialidad que la sala");
        }
        
        if (!esMedicoDisponible(medico, fechaHora)) {
            throw new CitaException("Superposición de citas: el médico no tiene disponibilidad (" + HORAS_ENTRE_CITAS + " horas de margen requeridas)");
        }
        
        if (!esSalaDisponible(sala, fechaHora)) {
            throw new CitaException("Superposición de citas: la sala no tiene disponibilidad (" + HORAS_ENTRE_CITAS + " horas de margen requeridas)");
        }

        Cita nuevaCita = new Cita(paciente, medico, sala, fechaHora, costo);
        this.citas.add(nuevaCita);
        paciente.addCita(nuevaCita);
        medico.addCita(nuevaCita);
        sala.addCita(nuevaCita);

        return nuevaCita;
    }

    @Override
    public List<Cita> getCitasPorPaciente(Paciente paciente) {
        List<Cita> filtradas = new ArrayList<>();
        for (Cita c : citas) {
            if (c.getPaciente().equals(paciente)) {
                filtradas.add(c);
            }
        }
        return Collections.unmodifiableList(filtradas);
    }

    @Override
    public List<Cita> getCitasPorMedico(Medico medico) {
        List<Cita> filtradas = new ArrayList<>();
        for (Cita c : citas) {
            if (c.getMedico().equals(medico)) {
                filtradas.add(c);
            }
        }
        return Collections.unmodifiableList(filtradas);
    }

    @Override
    public List<Cita> getCitasPorSala(Sala sala) {
        List<Cita> filtradas = new ArrayList<>();
        for (Cita c : citas) {
            if (c.getSala().equals(sala)) {
                filtradas.add(c);
            }
        }
        return Collections.unmodifiableList(filtradas);
    }

    private void validarCita(LocalDateTime fechaHora, double costo) throws CitaException {
        if (fechaHora == null) {
            throw new CitaException("La fecha y hora son obligatorias");
        }
        if (fechaHora.isBefore(LocalDateTime.now())) {
            throw new CitaException("La cita no puede programarse en el pasado");
        }
        if (costo <= 0) {
            throw new CitaException("El costo de la cita debe ser mayor a 0");
        }
    }

    private boolean esMedicoDisponible(Medico medico, LocalDateTime fechaHora) {
        for (Cita c : getCitasPorMedico(medico)) {
            if (seSuperponen(c.getFechaHora(), fechaHora)) {
                return false;
            }
        }
        return true;
    }

    private boolean esSalaDisponible(Sala sala, LocalDateTime fechaHora) {
        for (Cita c : getCitasPorSala(sala)) {
            if (seSuperponen(c.getFechaHora(), fechaHora)) {
                return false;
            }
        }
        return true;
    }

    private boolean seSuperponen(LocalDateTime existente, LocalDateTime nueva) {
        long diferenciaEnMinutos = Math.abs(Duration.between(existente, nueva).toMinutes());
        return diferenciaEnMinutos < HORAS_ENTRE_CITAS * 60;
    }
}