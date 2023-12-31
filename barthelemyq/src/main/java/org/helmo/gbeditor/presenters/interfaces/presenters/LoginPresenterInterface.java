package org.helmo.gbeditor.presenters.interfaces.presenters;

import org.helmo.gbeditor.presenters.interfaces.views.ViewInterface;

/**
 * Interface du presentateur de connexion
 */
public interface LoginPresenterInterface {

    /**
     * Méthode d'enregistrement d'auteur : crée un auteur et affiche l'espace de travail si les champs ont bien été remplis
     * @param id (matricule)
     * @param surname (prénom)
     * @param name (nom)
     */
    void login(String id, String surname, String name);

    /**
     * Renseigne une vue au presentateur
     * @param view (LoginViewInterface)
     */
    void setView(ViewInterface view);

}
