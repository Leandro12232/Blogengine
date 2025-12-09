package dhbw.einpro.blogengine.impl;

import java.util.ArrayList;
import java.util.List;
import dhbw.einpro.blogengine.exceptions.IllegalOperationException;
import dhbw.einpro.blogengine.exceptions.UserNotFoundException;
import dhbw.einpro.blogengine.interfaces.IBlogEngine;
import dhbw.einpro.blogengine.interfaces.IComment;
import dhbw.einpro.blogengine.interfaces.IPost;
import dhbw.einpro.blogengine.interfaces.IUser;

/**
 * Die Klasse implementiert einen Post im Blog-System.
 */
public class Post implements IPost {

    private static final long serialVersionUID = 1L;

    private int id;
    private String title;
    private String content;
    private IUser author;

    // Referenz zur Engine, wird für Validierungen benötigt (siehe Konstruktor in App.java)
    private IBlogEngine blogEngine;

    // Listen müssen initialisiert werden, um NullPointerExceptions zu vermeiden
    private List<IComment> comments = new ArrayList<>();
    private List<IUser> likes = new ArrayList<>();
    private List<IUser> dislikes = new ArrayList<>();

    /**
     * Konstruktor passend zu BlogEngineHelper.java und App.java
     */
    public Post(String title, String content, IUser author, IBlogEngine blogEngine) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.blogEngine = blogEngine;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int p_id) {
        this.id = p_id;
    }

    @Override
    public List<IComment> getComments() {
        return comments;
    }

    @Override
    public void addComment(IComment p_comment) throws IllegalOperationException, UserNotFoundException {
        // Prüfen ob Autor des Kommentars gesetzt ist
        if (p_comment.getAuthor() == null) {
            throw new UserNotFoundException("Autor des Kommentars ist nicht gesetzt.");
        }

        // Regel: Autor des Posts darf nicht seinen eigenen Post kommentieren
        if (this.author != null && this.author.equals(p_comment.getAuthor())) {
            throw new IllegalOperationException("Der Autor des Posts darf seinen eigenen Post nicht kommentieren.");
        }

        // Regel: Kommentar max 256 Zeichen (wird oft auch im Comment-Objekt geprüft, hier zur Sicherheit nochmal)
        if (p_comment.getContent() != null && p_comment.getContent().length() > 256) {
            throw new IllegalOperationException("Kommentar zu lang (max 256 Zeichen).");
        }

        // Verknüpfung herstellen
        comments.add(p_comment);
        p_comment.setPost(this);
    }

    @Override
    public void removeComment(IUser p_user, IComment p_comment) throws IllegalOperationException {
        // Regel: Nur der Autor des Kommentars darf ihn löschen
        if (!p_comment.getAuthor().equals(p_user)) {
            throw new IllegalOperationException("Nur der Autor darf den Kommentar löschen.");
        }
        comments.remove(p_comment);
    }

    @Override
    public void like(IUser p_person) throws IllegalOperationException, UserNotFoundException {
        // Validierung: User muss existieren
        if (p_person == null) throw new UserNotFoundException("User ist null");
        if (blogEngine != null && !blogEngine.containsUser(p_person)) {
            throw new UserNotFoundException("User ist nicht im System registriert.");
        }

        // Regel: Autor darf eigenen Post nicht liken
        if (this.author != null && this.author.equals(p_person)) {
            throw new IllegalOperationException("Autor darf eigenen Post nicht liken.");
        }

        // Wenn User in Dislikes war -> dort entfernen
        if (dislikes.contains(p_person)) {
            dislikes.remove(p_person);
        }

        // Wenn noch nicht geliked -> hinzufügen (verhindert doppelte Likes)
        if (!likes.contains(p_person)) {
            likes.add(p_person);
        }
    }

    @Override
    public void disLike(IUser p_person) throws IllegalOperationException, UserNotFoundException {
        // Validierung
        if (p_person == null) throw new UserNotFoundException("User ist null");
        if (blogEngine != null && !blogEngine.containsUser(p_person)) {
            throw new UserNotFoundException("User ist nicht im System registriert.");
        }

        // Regel: Autor darf eigenen Post nicht disliken
        if (this.author != null && this.author.equals(p_person)) {
            throw new IllegalOperationException("Autor darf eigenen Post nicht disliken.");
        }

        // Wenn User in Likes war -> dort entfernen
        if (likes.contains(p_person)) {
            likes.remove(p_person);
        }

        // Wenn noch nicht disliked -> hinzufügen
        if (!dislikes.contains(p_person)) {
            dislikes.add(p_person);
        }
    }

    @Override
    public int getScore() {
        // Regel: (likes - dislikes) * 10
        return (likes.size() - dislikes.size()) * 10;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String p_title) {
        this.title = p_title;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public void setContent(String p_content) {
        this.content = p_content;
    }

    @Override
    public IUser getAuthor() {
        return author;
    }

    @Override
    public void setAuthor(IUser p_author) {
        this.author = p_author;
    }

    @Override
    public List<IUser> getLikes() {
        return likes;
    }

    @Override
    public List<IUser> getDisLikes() {
        return dislikes;
    }
}