package com.virtuocode.publicite.Exeptions.emplacement;

public class EnregistrementEmplacementException extends RuntimeException {

    public EnregistrementEmplacementException() {
        super("Erreur lors de l'enregistrement de l'emplacement");
    }
}