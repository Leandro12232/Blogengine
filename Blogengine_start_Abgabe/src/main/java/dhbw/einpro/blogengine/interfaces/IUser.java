package dhbw.einpro.blogengine.interfaces;

//Achtung: Diese Datei darf nicht editiert werden!
/**
 * Das Interface beschreibt Benutzer des Blog-Systems.
 * 
 * User Gleichheit wird basierend auf der Email-Adresse, Nachname und Vorname bestimmt.
 * 
 */
public interface IUser
{

    /**
     * Liefert die Email eines Benutzers zurück
     * 
     * @return Gibt die Email-Adresse des Benutzers
     */
    String getEmail();

    /**
     *
     * @param p_email aktualisiert die E-Mail-Adresse des Benutzers
     */

    void setEmail(String p_email);

    /**
     * Liefert den Vorname des Benutzers zurück
     * 
     * @return Vorname des Benutzers
     */
    String getFirstName();

    /**
     * Setzt den Vornamen des Benutzers
     * 
     * @param p_firstName Vorname des Benutzers
     */
    void setFirstName(String p_firstName);

    /**
     * Liefert den Nachnamen des Benutzers zurück
     * 
     * @return Nachname
     */
    String getLastName();

    /**
     * Setzt den Nachnamen des Benutzers
     * 
     * @param p_lastName Nachname der gesetzt werden soll
     */
    void setLastName(String p_lastName);

}
