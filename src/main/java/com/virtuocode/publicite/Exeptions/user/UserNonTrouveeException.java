package com.virtuocode.publicite.Exeptions.user;

public class UserNonTrouveeException extends RuntimeException {

    public UserNonTrouveeException(Long id) {
        super("User non trouvée avec l'ID: " + id);
    }
}