package com.virtuocode.publicite.Exeptions.campagne;

public class CampagneNonTrouveeException extends RuntimeException {

    public CampagneNonTrouveeException(Long id) {
        super("Campagne non trouvée avec l'ID: " + id);
    }
}