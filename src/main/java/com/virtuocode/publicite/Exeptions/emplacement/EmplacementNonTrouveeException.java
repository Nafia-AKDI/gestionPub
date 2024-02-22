package com.virtuocode.publicite.Exeptions.emplacement;

public class EmplacementNonTrouveeException extends RuntimeException {

    public EmplacementNonTrouveeException(Long id) {
        super("Emplacement non trouvée avec l'ID: " + id);
    }
}