package org.helmo.gbeditor.models.exceptions;

/**
 * Exception Auteur invalide
 */
public class AuthorNotValidException extends Exception {

    /**
     *Remonte l'erreur donnée en paramètres
     * @param errorMessage (String) message d'erreur
     */
    public AuthorNotValidException(String errorMessage) {
        super(errorMessage);
    }
}
