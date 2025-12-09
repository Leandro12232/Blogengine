package dhbw.einpro.blogengine.impl;

import java.util.Objects;
import dhbw.einpro.blogengine.interfaces.IUser;

/**
 * Klasse enthält Informationen zu einem Benutzer des Blog-Systems
 */
public class User implements Comparable<User>, IUser {

    private String firstName;
    private String lastName;
    private String email;

    /**
     * Konstruktor, der vom BlogEngineHelper benötigt wird.
     */
    public User(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    /**
     * Vergleicht die im Parameter o übergebene Person mit der aktuellen Instanz.
     * Dabei werden die Attribute des Benutzers in der Reihenfolge Nachname, Vorname
     * und Email-Adresse verglichen.
     */
    @Override
    public int compareTo(User o) {
        // 1. Nachname vergleichen
        int lastCmp = this.lastName.compareTo(o.getLastName());
        if (lastCmp != 0) {
            return lastCmp;
        }

        // 2. Wenn Nachnamen gleich sind, Vorname vergleichen
        int firstCmp = this.firstName.compareTo(o.getFirstName());
        if (firstCmp != 0) {
            return firstCmp;
        }

        // 3. Wenn Vornamen auch gleich sind, Email vergleichen
        return this.email.compareTo(o.getEmail());
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String p_email) {
        this.email = p_email;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String p_firstName) {
        this.firstName = p_firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String p_lastName) {
        this.lastName = p_lastName;
    }

    /**
     * WICHTIG: Laut Aufgabenstellung wird Gleichheit basierend auf
     * E-Mail, Nachname und Vorname bestimmt.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, firstName, lastName);
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " (" + email + ")";
    }
}