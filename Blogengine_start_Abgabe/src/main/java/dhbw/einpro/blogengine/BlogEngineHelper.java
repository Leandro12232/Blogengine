package dhbw.einpro.blogengine;

import dhbw.einpro.blogengine.exceptions.IllegalOperationException;
import dhbw.einpro.blogengine.impl.BlogEngine;
import dhbw.einpro.blogengine.impl.Comment;
import dhbw.einpro.blogengine.impl.Post;
import dhbw.einpro.blogengine.impl.User;
import dhbw.einpro.blogengine.interfaces.IBlogEngine;
import dhbw.einpro.blogengine.interfaces.IComment;
import dhbw.einpro.blogengine.interfaces.IPost;
import dhbw.einpro.blogengine.interfaces.IUser;

/**
 * Anmerkung: Diese Datei darf nicht editiert werden!
 *
 * Hilfsklasse, welche verwendet wird, um auf die eigentlichen Implementierungen
 * zuzugreifen. Diese darf nicht veraendert werden.
 *
 * @author rbimaz
 *
 */
public final class BlogEngineHelper
{

    /**
     * Hält die Instanz der Default Blog Engine
     */
    private static IBlogEngine defaultBlogEngine;

    /**
     * Erzeugt eine neue Instanz der Blog Engine.
     * 
     * @return BlogEngine
     */
    public static final IBlogEngine createBlogEngine()
    {
        defaultBlogEngine = new BlogEngine();
        return defaultBlogEngine;
    }
    
    /**
     * Liefert die Instanz der Default BlogEngine.
     * 
     * @return Default BlogEngine
     */
    public static IBlogEngine getDefaultBlogEngine()
    {
        if (defaultBlogEngine == null)
        {
            defaultBlogEngine = new BlogEngine();
        }
        
        return defaultBlogEngine;
    }    

    /**
     * Hilfmethode zum Anlegen eines Post.
     * 
     * @param titel
     * @param content
     * @param user
     * @return
     */
    public static IPost createPost(String titel, String content, IUser user, IBlogEngine blogEngine)
    {
        return new Post(titel, content, user, blogEngine);
    }

    /**
     * Hilfsmethode zum anlegen eines Users.
     * 
     * @param firstName
     * @param lastName
     * @param email
     * @return
     */
    public static IUser createUser(String firstName, String lastName, String email)
    {
        return new User(firstName, lastName, email);
    }
    
    /**
     * Hilfsmethode zum Anlegen eines Kommentars.
     * 
     * @param comment Inhalt des Kommentars. Der Inhalt darf maximal 255 Zeichen umfassen.
     * @param author
     * @return Instanz der Klasse Comment
     */
    public static IComment createComment(String comment, IUser author) throws IllegalOperationException  {
        if (comment.length() > 255) {
            throw new IllegalOperationException("Der Kommentar darf maximal 255 Zeichen umfassen.");
        }

        return new Comment(comment, author);
    }    
}
