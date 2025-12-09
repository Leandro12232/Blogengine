package dhbw.einpro.blogengine.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import dhbw.einpro.blogengine.exceptions.DuplicateEmailException;
import dhbw.einpro.blogengine.exceptions.DuplicateUserException;
import dhbw.einpro.blogengine.exceptions.IllegalOperationException;
import dhbw.einpro.blogengine.exceptions.PostNotFoundException;
import dhbw.einpro.blogengine.exceptions.UserNotFoundException;
import dhbw.einpro.blogengine.interfaces.IBlogEngine;
import dhbw.einpro.blogengine.interfaces.IComment;
import dhbw.einpro.blogengine.interfaces.IPost;
import dhbw.einpro.blogengine.interfaces.IUser;

/**
 * Klasse implementiert die Funktionalität einer Blog Engine.
 */
public class BlogEngine implements IBlogEngine
{

    private List<IUser> users = new ArrayList<>();
    private List<IPost> posts = new ArrayList<>();

    @Override
    public int size() {
        return users.size();
    }

    @Override
    public boolean addUser(IUser p_user) throws DuplicateEmailException, DuplicateUserException {
        if (p_user == null) return false;

        // Prüfung: Existiert der User (Objektgleichheit/Equals) bereits?
        if (users.contains(p_user)) {
            throw new DuplicateUserException("Benutzer ist bereits im System vorhanden.");
        }

        // Prüfung: Existiert die E-Mail-Adresse bereits bei einem anderen User?
        for (IUser u : users) {
            if (u.getEmail().equalsIgnoreCase(p_user.getEmail())) {
                throw new DuplicateEmailException("Ein Benutzer mit dieser E-Mail existiert bereits: " + p_user.getEmail());
            }
        }

        return users.add(p_user);
    }

    @Override
    public boolean removeUser(IUser p_user) {
        return users.remove(p_user);
    }

    @Override
    public int addPost(IPost p_post) throws UserNotFoundException {
        if (p_post == null) return -1; // Oder Exception, aber -1 als Fehlercode ist hier sicher

        // Prüfung: Ist der Autor registriert?
        if (p_post.getAuthor() == null || !users.contains(p_post.getAuthor())) {
            throw new UserNotFoundException("Der Autor des Posts ist nicht im Blog-System registriert.");
        }

        // ID Generierung: Startet bei 1, sonst max(ID) + 1
        int nextId = posts.stream()
                .mapToInt(IPost::getId)
                .max()
                .orElse(0) + 1;

        p_post.setId(nextId);
        posts.add(p_post);

        return nextId;
    }

    @Override
    public void removePost(IUser p_author, int p_postId) throws PostNotFoundException, IllegalOperationException {
        IPost postToRemove = findPostById(p_postId);

        // Prüfung: Existiert der Post?
        if (postToRemove == null) {
            throw new PostNotFoundException("Post mit ID " + p_postId + " wurde nicht gefunden.");
        }

        // Prüfung: Ist der User der Autor des Posts?
        if (!postToRemove.getAuthor().equals(p_author)) {
            throw new IllegalOperationException("Nur der Autor des Posts darf diesen löschen.");
        }

        posts.remove(postToRemove);
    }

    @Override
    public List<IPost> getPosts() {
        // Wir geben eine Kopie zurück, damit die interne Liste geschützt bleibt
        return new ArrayList<>(posts);
    }

    @Override
    public List<IPost> findPostsByAuthor(IUser p_author) {
        return posts.stream()
                .filter(p -> p.getAuthor().equals(p_author))
                .collect(Collectors.toList());
    }

    @Override
    public IPost findPostById(int p_postId) {
        return posts.stream()
                .filter(p -> p.getId() == p_postId)
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean containsPost(int p_postId) {
        return findPostById(p_postId) != null;
    }

    @Override
    public boolean containsUser(IUser user) {
        return users.contains(user);
    }

    @Override
    public IUser findUserByEmail(String p_email) throws UserNotFoundException {
        return users.stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(p_email))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException("Kein Benutzer mit der E-Mail " + p_email + " gefunden."));
    }

    @Override
    public List<IPost> sortPostsByTitle() {
        return posts.stream()
                .sorted(Comparator.comparing(IPost::getTitle))
                .collect(Collectors.toList());
    }

    @Override
    public List<IPost> findPostsByTitle(String title) {
        return posts.stream()
                .filter(p -> p.getTitle().equals(title))
                .collect(Collectors.toList());
    }
}