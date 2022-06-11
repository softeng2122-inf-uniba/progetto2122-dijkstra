package it.uniba.app;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */

/**
* lista di colori usati sui caratteri durante la partita.
*/
public enum Colori {
    /**
     * Riferimento all'inserimento di una lettera non presente nella parola.
     */
    GRIGIO,
    /**
     * Riferimento all'inserimento di una lettera presente nella parola ma in altra posizione.
     */
    GIALLO,
    /**
     * Riferimento all'inserimento di una lettera presente nella parola in posizione corretta.
     */
    VERDE
}