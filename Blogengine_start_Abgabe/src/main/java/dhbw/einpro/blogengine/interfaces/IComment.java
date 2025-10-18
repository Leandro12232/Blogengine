package dhbw.einpro.blogengine.interfaces;

import java.io.Serializable;
//Achtung: Diese Datei darf nicht editiert werden!

import dhbw.einpro.blogengine.exceptions.IllegalOperationException;

/**
 * Das Interface beschreibt Kommentare zu einem Post.
 *
 */
public interface IComment extends Serializable
{

    /**
     * Liefert den Inhalt eines Kommentars.
     *
     * @return content
     */
    String getContent();

    /**
     * Setzt den Inhalt des Kommentars Der Inhalt eines Kommentars darf maximal 256
     * Zeichen haben.
     * 
     * @param p_content Inhalt des Kommentars
     * @throws IllegalOperationException wird ausgelöst, falls der Inhalt mehr als
     *                                   256 Zeichen hat.
     *
     */
    void setContent(String p_content) throws IllegalOperationException;

    /**
     * Liefert den Autor des Kommentars zurück.
     *
     * @return User
     */
    IUser getAuthor();

    /**
     * Aktualisiert den Autor des Kommentars
     * 
     * @param p_author Autor des Kommentars
     */
    void setAuthor(IUser p_author);

    /**
     * Verknüpft den aktuellen Kommentar mit dem dazugehörigen Post
     * 
     * @param p_post Post zu dem der Kommentar gehört
     */
    void setPost(IPost p_post);
}
