package org.helmo.gbeditor.presenters.viewmodels;

/**
 * Classe de stockage de données page pour les vues
 */
public class PageViewModel {

    public String text;

    public int pageNumber;

    /**
     * Constructeur de page
     * @param text (String) texte de la page
     */
    public PageViewModel(String text, int pageNumber) {
        this.text = text;
        this.pageNumber = pageNumber;
    }

    /**
     * Récupère le texte de la page
     * @return (String) texte de la page
     */
    public String getText() {
        return text;
    }

    /**
     * Récupère le numéro de la page
     * @return (int) numéro de page
     */
    public int getPageNumber() {
        return pageNumber;
    }
}
