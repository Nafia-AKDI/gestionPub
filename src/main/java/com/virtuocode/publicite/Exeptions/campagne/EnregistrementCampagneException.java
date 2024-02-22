package com.virtuocode.publicite.Exeptions.campagne;

public class EnregistrementCampagneException extends RuntimeException {

    public EnregistrementCampagneException() {
        super("Erreur lors de l'enregistrement de la campagne");
    }
}