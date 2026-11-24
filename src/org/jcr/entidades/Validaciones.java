/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.jcr.entidades;

class Validaciones {

    static String textoObligatorio(String valor, String mensajeError) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(mensajeError);
        }
        return valor;
    }

    static void noNulo(Object valor, String mensajeError) {
        if (valor == null) {
            throw new IllegalArgumentException(mensajeError);
        }
    }

    static boolean sonDigitos(String texto) {
        if (texto == null) {
            return false;
        }
        for (char c : texto.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}