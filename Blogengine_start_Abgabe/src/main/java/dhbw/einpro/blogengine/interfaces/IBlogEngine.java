package dhbw.einpro.blogengine.interfaces;

import java.util.List;

import dhbw.einpro.blogengine.exceptions.DuplicateEmailException;
import dhbw.einpro.blogengine.exceptions.DuplicateUserException;
import dhbw.einpro.blogengine.exceptions.IllegalOperationException;
import dhbw.einpro.blogengine.exceptions.PostNotFoundException;
import dhbw.einpro.blogengine.exceptions.UserNotFoundException;

//Achtung: Diese Datei darf nicht editiert werden!

/**
 * Das Interface beschreibt eine Blog Engine.
 *
 */
public interface IBlogEngine
{

    /**
     * Liefert die Anzahl der Benutzer im Blog-System.
     * 
     * @return Anzahl der Benutzer im Blog-System.
     */
    int size();

    /**
     * Fügt einen neune Benutzer zum Blog-Sysstem
     * 
     * @param p_user Benutzer der zum Blog-System hinzugefügt werden soll.
     * @return Liefert true falls der Benutzer hinzugefügt werden konnte. Sonst
     *         false.
     * @throws DuplicateEmailException wird ausgelöst, falls bereits ein User mit
     *                                 der gleichen Email-Adresse im System
     *                                 vorhanden ist.
     * @throws DuplicateUserException  wird ausgelöst, falls der gleiche User erneut
     *                                 in das Blog-System hinzugefügt werden soll.
     */
    boolean addUser(IUser p_user) throws DuplicateEmailException, DuplicateUserException;

    /**
     * Entfernt einen Benutzer aus dem Blog-System
     * 
     * @param p_user Benutzer, der aus dem Blog-System entfernt werden soll
     * @return liefert true, fall der Benutzer aus dem Blog-System entfernt werden
     *         konnte. Sonst false.
     */
    boolean removeUser(IUser p_user);

    /**
     * Fügt ein Post zum Blog-System hinzu.
     *
     * @param p_post Post, der zum Blog-System hinzugefügt werden soll. Posts werden
     *             aufsteigend ab der Zahl 1 nummeriert. Beispiel: Wenn Sie einen
     *             ersten Post zum Blog-System hinzufügen, dann bekommt der Post die
     *             Id 1. Der zweite Post bekommt die Id 2 und alle weiteren Posts
     *             bekommen aufsteigende Nummern.
     * @return Id des neu hinzugefügten Posts.
     * @throws UserNotFoundException     wird ausgelöst, falls der im Parameter
     *                                   author angegebene Benutzer noch nicht als Benutzer registriert ist.     *
     */
    int addPost(IPost p_post) throws UserNotFoundException;

    /**
     * Entfernt Post aus dem Blog-System
     *
     * @param p_author Autor des Posts, der entfernt werden soll
     * @param p_postId Id des gesuchten Posts
     * @throws PostNotFoundException     wird ausgelöst, falls der Post nicht
     *                                   gefunden wurde
     * @throws IllegalOperationException wird ausgelöst, falls der im Parameter
     *                                   author angegebene Benutzer nicht der
     *                                   tatsächliche Autor des zu löschenden Posts
     *                                   ist
     */
    void removePost(IUser p_author, int p_postId) throws PostNotFoundException, IllegalOperationException;

    /**
     * Liefert eine Liste aller Posts, die im Blog-System erstellt wurden
     *
     * @return Liste aller Posts im Blog-System
     */
    List<IPost> getPosts();

    /**
     * Liefert eine Liste aller Posts des angegebenen Autors zurück
     *
     * @param p_author Benutzer dessen Posts gesucht werden sollen
     * @return Liste aller Posts des angegebenen Benutzers
     */
    List<IPost> findPostsByAuthor(IUser p_author);

    /**
     * Liefert einen Post zur angegebenen Id zurück
     *
     * @param p_postId Id des gesuchten Posts
     * @return Post mit der spezifizierten Id
     */
    IPost findPostById(int p_postId);

    /**
     * Prüft, ob ein Post mit der Id im Blog-System existiert
     *
     * @param p_postId Id des gesuchten Post
     * @return true falls der Post gefunden wurde
     */
    boolean containsPost(int p_postId);

    /**
     * Prüft, ob ein bestimmter Benutzer im Blog-System existiert.
     *
     * @param user der gesuchte Benutzer
     * @return true falls der Benutzer gefunden wurde
     */
    boolean containsUser(IUser user);

    /**
     * Liefert den entsprechenden Benutzer des Blog-Systems mit der angegebenen
     * Email-Adresse
     *
     * @param p_email Email-Adresse des Benutzers, der gesucht werden soll
     * @return Instanz der Klasse User
     * @throws UserNotFoundException wird ausgelöst, falls der Benutzer nicht
     *                               gefunden wurde
     */
    IUser findUserByEmail(String p_email) throws UserNotFoundException;

    /**
     * Sortiert die Posts anhand des Titels
     * 
     * @return Liste aller Posts im Blog-System sortiert nach dem Titel
     */
    List<IPost> sortPostsByTitle();

    /**
     * Liefert all Posts zu einem gegebenen Titel.
     * 
     * @param title Titel des zu suchenden Posts
     * @return Liste aller Posts
     */
    List<IPost> findPostsByTitle(String title);
}
