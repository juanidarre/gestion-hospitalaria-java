/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.jcr.entidades;

import org.jcr.enums.EspecialidadMedica;
import org.jcr.enums.TipoSangre;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Medico extends Persona {
    private Matricula matricula;
    private EspecialidadMedica especialidad;
    private Departamento departamento;
    private List<Cita> citas;

    public Medico(String nombre, String apellido, String dni, LocalDate fechaNacimiento, TipoSangre tipoSangre, String numeroMatricula, EspecialidadMedica especialidad) {
        super(nombre, apellido, dni, fechaNacimiento, tipoSangre);
        Validaciones.noNulo(especialidad, "La especialidad médica es obligatoria");
        this.especialidad = especialidad;
        this.matricula = new Matricula(numeroMatricula);
        this.citas = new ArrayList<>();
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public EspecialidadMedica getEspecialidad() {
        return especialidad;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        if (departamento != null && this.especialidad != departamento.getEspecialidad()) {
            throw new IllegalArgumentException("La especialidad del médico no coincide con el departamento");
        }
        if (this.departamento == departamento) {
            return;
        }
        Departamento anterior = this.departamento;
        this.departamento = departamento;
        if (anterior != null) {
            anterior.getInternalMedicos().remove(this);
        }
        if (departamento != null && !departamento.getInternalMedicos().contains(this)) {
            departamento.getInternalMedicos().add(this);
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