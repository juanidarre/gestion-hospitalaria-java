/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.jcr.entidades;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sala {
    private String numero;
    private String tipo;
    private Departamento departamento;
    private List<Cita> citas;

    Sala(String numero, String tipo, Departamento departamento) {
        this.numero = Validaciones.textoObligatorio(numero, "El número de sala es obligatorio");
        this.tipo = Validaciones.textoObligatorio(tipo, "El tipo de sala es obligatorio");
        Validaciones.noNulo(departamento, "El departamento es obligatorio");
        this.departamento = departamento;
        this.citas = new ArrayList<>();
    }

    public String getNumero() {
        return numero;
    }

    public String getTipo() {
        return tipo;
    }

    public Departamento getDepartamento() {
        return departamento;
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