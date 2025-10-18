package dhbw.einpro.blogengine.interfaces;

import java.io.Serializable;
import java.util.List;

import dhbw.einpro.blogengine.exceptions.IllegalOperationException;
import dhbw.einpro.blogengine.exceptions.UserNotFoundException;

//Achtung: Diese Datei darf nicht editiert werden!

/**
 * Das Interface beschreibt Posts, die im Blog-System erstellt und
 * veröffentlicht werden können.
 */
public interface IPost extends Serializable
{
    /**
     * Liefert die Id des Post.
     * 
     * @return Id des Posts
     */
    int getId();

    /**
     * Setzt die Id des Posts
     * 
     * @param p_id neue Id für den Post
     */
    void setId(int p_id);

    /**
     * Liefert die Liste aller Kommentare zum Post zurück
     * 
     * @return Liste von Kommentaren zum Post
     */
    List<IComment> getComments();

    /**
     * Fügt den angegebenen Kommentar zum Post hinzu
     * 
     * @param p_comment Kommentar, der zum Post hinzugefügt werden soll
     * @throws IllegalOperationException wird ausgelöst, falls der Autor des
     *                                   Kommentars gleichzeitig Autor des Posts
     *                                   ist.
     *                                   Diese Exception wird ebenfalls ausgelöst,
     *                                   falls der Inhalt (content) des Kommentars mehr als 256 Zeichen umfasst.
     * @throws UserNotFoundException wird ausgelöst, falls der als Author angegebene Benutzer
     *                               nicht in der Blog Engine registriert ist
     *
     */
    void addComment(IComment p_comment) throws IllegalOperationException, UserNotFoundException;

    /**
     * Entfernt einen Comment für einen User.
     * 
     * @param p_user Benutzer, der die Methode aufruft
     * @param p_comment Kommentar, der entfernt werden soll
     * @throws IllegalOperationException wird ausgelöst, falls der Benutzer, der die Methode aufruft nicht gleichzeitig Autor des Kommentars ist.
     */
    void removeComment(IUser p_user, IComment p_comment) throws IllegalOperationException;

    /**
     * Fügt den Benutzer zur Liste der Benutzer hinzu, die den Post gut finden.
     * 
     * @param p_person Benutzer, der den Post gut findet. Der Benutzer wird in die
     *               Liste der Benutzer aufgenommen, die den Post gut finden (Siehe
     *               auch {@link #getLikes()}). Falls der gleiche Benutzer diese
     *               Methode mehrmals aufruft, so wird er nur das erste Mal in die
     *               Liste aufgenommen.
    *                Falls der Benutzer bereits in der Liste der Benutzer, die den 
     *               Post nicht gut finden ist, wird dieser daraus entfernt, bevor
     *               er in die Liste der Benutzer, die den Post gut finden auf-
     *               genommen wird.
     * @throws IllegalOperationException wird ausgelöst, falls der User zugleich
     *                                   Autor des Posts ist.
     * @throws UserNotFoundException wird ausgelöst, falls der angegebene Benutzer
     *                                     nicht im Blog-System registriert ist
     */

    void like(IUser p_person) throws IllegalOperationException, UserNotFoundException;

    /**
     * Fügt den Benutzer zur Liste der Benutzer hinzu, die den Post nicht gut
     * finden.
     * 
     * @param p_person User, der den Post nicht gut findet
*                    Falls der Benutzer bereits in der Liste der Benutzer, die den 
     *               Post gut finden ist, wird dieser daraus entfernt, bevor
     *               er in die Liste der Benutzer, die den Post nicht gut finden auf-
     *               genommen wird.
     * @throws IllegalOperationException wird ausgelöst, falls der User zugleich
     *                                   Autor des Posts ist.
     * @throws UserNotFoundException wird ausgelöst, falls der angegebene Benutzer
     *                                   nicht in der Blog Engine registriert ist
     */

    void disLike(IUser p_person) throws IllegalOperationException, UserNotFoundException;

    /**
     *
     *
     * Berechnet die Resonanz eines Posts. Zur Berechnung des Scores werden die
     * Anzahl der likes und dislikes verwendet. Beispiel: Ein Post wird von 3
     * Benutzern bewertet. Die ersten 2 Benutzer finden den Post gut (like). Der
     * dritte Benutzer findet den Post nicht gut (dislike). Der Score des Posts wird
     * wie folgt berechnet: (2-1)*10 = 10
     *
     * @return Score des Posts
     */
    int getScore();

    /**
     * Liefert den Titel des Posts zurück
     * 
     * @return Titel des Posts
     */
    String getTitle();

    /**
     * Setzt oder aktualisiert den Titel des Posts
     * 
     * @param p_title Der neue Titel des Posts
     */

    void setTitle(String p_title);

    /**
     * Liefert den Inhalt des Posts zurück
     * 
     * @return Inhalt des Posts
     */
    String getContent();

    /**
     * Setzt bzw. aktualisiert den Inhalt des Posts
     * 
     * @param p_content Der neue Inhalt des Posts
     */
    void setContent(String p_content);

    /**
     * Liefert den Autor des Posts zurück
     * 
     * @return Autor des Posts
     */
    IUser getAuthor();

    /**
     * Setzt oder aktualisiert den Autor des Posts
     * 
     * @param p_author Autor, der gesetzt werden soll
     */
    void setAuthor(IUser p_author);

    /**
     * Liefert eine Liste von Personen zurück, die den Post gut finden
     * 
     * @return Liste von Personen
     */
    List<IUser> getLikes();

    /**
     * Liefert eine Liste von Personen zurück, die den Post nicht gut finden
     * 
     * @return Liste von Personen
     */
    List<IUser> getDisLikes();
}
