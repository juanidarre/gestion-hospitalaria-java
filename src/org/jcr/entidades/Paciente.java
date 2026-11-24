/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.jcr.entidades;

import org.jcr.enums.TipoSangre;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Paciente extends Persona {
    private String telefono;
    private String direccion;
    private HistoriaClinica historiaClinica;
    private Hospital hospital;
    private List<Cita> citas;

    public Paciente(String nombre, String apellido, String dni, LocalDate fechaNacimiento, TipoSangre tipoSangre, String telefono, String direccion) {
        super(nombre, apellido, dni, fechaNacimiento, tipoSangre);
        this.telefono = Validaciones.textoObligatorio(telefono, "El teléfono es obligatorio");
        this.direccion = Validaciones.textoObligatorio(direccion, "La dirección es obligatoria");
        this.historiaClinica = new HistoriaClinica(this);
        this.citas = new ArrayList<>();
    }

    public HistoriaClinica getHistoriaClinica() {
        return historiaClinica;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        if (this.hospital == hospital) {
            return;
        }
        Hospital anterior = this.hospital;
        this.hospital = hospital;
        if (anterior != null) {
            anterior.getInternalPacientes().remove(this);
        }
        if (hospital != null && !hospital.getInternalPacientes().contains(this)) {
            hospital.getInternalPacientes().add(this);
        }
    }

    public void addCita(Cita cita) {
        if (cita != null && !this.citas.contains(cita)) {
            this.citas.add(cita);
        }
    }

    public List<Cita> getCitas() {
        return Collections.unmodifiableList(citas);
    }
}