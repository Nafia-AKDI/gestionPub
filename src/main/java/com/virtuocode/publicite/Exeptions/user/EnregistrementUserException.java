package com.virtuocode.publicite.Exeptions.user;

public class EnregistrementUserException extends RuntimeException {

    public EnregistrementUserException() {
        super("Erreur lors de l'enregistrement de la campagne");
    }
}