/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package it.uniba.app;

/**
* Lista dei comandi accettati.
*/
public enum Comandi {
    /**
     * Riferimento all'inserimento del comando "/nuova".
     */
    NUOVA,
    /**
     * Riferimento all'inserimento del comando "/mostra".
     */
    MOSTRA,
    /**
     * Riferimento all'inserimento del comando "/gioca".
     */
    GIOCA,
    /**
     * Riferimento all'inserimento del comando "/esci".
     */
    ESCI,
    /**
     * Riferimento all'inserimento del comando "/abbandona".
     */
    ABBANDONA,
    /**
     * Riferimento all'inserimento del comando "/help".
     */
    AIUTO,
    /**
     * Riferimento all'inserimento di un comando non esistente.
     */
    ERRORE
}
