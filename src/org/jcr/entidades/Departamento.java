/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.jcr.entidades;

import org.jcr.enums.EspecialidadMedica;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Departamento {
    private String nombre;
    private EspecialidadMedica especialidad;
    private Hospital hospital;
    private List<Medico> medicos;
    private List<Sala> salas;

    public Departamento(String nombre, EspecialidadMedica especialidad) {
        this.nombre = Validaciones.textoObligatorio(nombre, "El nombre del departamento es obligatorio");
        Validaciones.noNulo(especialidad, "La especialidad es obligatoria");
        this.especialidad = especialidad;
        this.medicos = new ArrayList<>();
        this.salas = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public EspecialidadMedica getEspecialidad() {
        return especialidad;
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
            anterior.getInternalDepartamentos().remove(this);
        }
        if (hospital != null && !hospital.getInternalDepartamentos().contains(this)) {
            hospital.getInternalDepartamentos().add(this);
        }
    }

    public void agregarMedico(Medico medico) {
        if (medico != null) {
            if (this.especialidad != medico.getEspecialidad()) {
                throw new IllegalArgumentException("La especialidad del médico no coincide con el departamento");
            }
            if (!this.medicos.contains(medico)) {
                this.medicos.add(medico);
                medico.setDepartamento(this);
            }
        }
    }

    public Sala crearSala(String numero, String tipo) {
        Sala nuevaSala = new Sala(numero, tipo, this);
        this.salas.add(nuevaSala);
        return nuevaSala;
    }

    public List<Medico> getMedicos() {
        return Collections.unmodifiableList(medicos);
    }

    public List<Sala> getSalas() {
        return Collections.unmodifiableList(salas);
    }

    List<Medico> getInternalMedicos() {
        return medicos;
    }
}