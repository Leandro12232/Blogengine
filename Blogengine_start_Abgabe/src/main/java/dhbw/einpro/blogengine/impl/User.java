package dhbw.einpro.blogengine.impl;

import java.util.Objects;

import dhbw.einpro.blogengine.interfaces.IUser;

/**
 * Klasse enthält Informationen zu einem Benutzer des Blog-Systems
 */
public class User implements Comparable<User>, IUser
{

    /**
     * Vergleicht die im Parameter o übergebene Person mit der aktuellen Instanz.
     * Dabei werden die Attribute des Benutzers in der Reihenfolge Nachname, Vorname
     * und Email-Adresse verglichen
     *
     * @param o Benutzer, der mit der aktuellen Instanz verglichen werden soll
     * @return Liefert einen Integer-Wert zurück @see Interface Comparable
     */
    @Override
    public int compareTo(User o)
    {
    }

}
