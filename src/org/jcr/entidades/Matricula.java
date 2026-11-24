/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.jcr.entidades;

public class Matricula {
    private String numero;

    public Matricula(String numero) {
        validarMatricula(numero);
        this.numero = numero;
    }

    private void validarMatricula(String numero) {
        Validaciones.textoObligatorio(numero, "La matrícula es obligatoria");
        if (!numero.matches("MP-\\d{4,6}")) {
            throw new IllegalArgumentException("El formato de matrícula es inválido");
        }
    }

    public String getNumero() {
        return numero;
    }
}