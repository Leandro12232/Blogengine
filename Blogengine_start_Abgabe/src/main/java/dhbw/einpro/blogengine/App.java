package dhbw.einpro.blogengine;

import dhbw.einpro.blogengine.exceptions.DuplicateEmailException;
import dhbw.einpro.blogengine.exceptions.DuplicateUserException;
import dhbw.einpro.blogengine.exceptions.IllegalOperationException;
import dhbw.einpro.blogengine.exceptions.UserNotFoundException;
import dhbw.einpro.blogengine.impl.Post;
import dhbw.einpro.blogengine.impl.User;
import dhbw.einpro.blogengine.interfaces.IBlogEngine;
import dhbw.einpro.blogengine.interfaces.IComment;
import dhbw.einpro.blogengine.interfaces.IPost;
import dhbw.einpro.blogengine.interfaces.IUser;

//Achtung: Diese Datei darf nicht editiert werden!
/**
 * Diese Klasse ist eine Testanwendung der Blogengine.
 */
public class App
{

    /**
     * Beispiel-Code:
     * 
     * Erzeugt einen neuen Beispiel Post und fügt diesen zur Default Blog Engine.
     *
     * @return Neue Post-Instanz
     * @throws DuplicateUserException
     * @throws DuplicateEmailException
     *
     */
    public static final Post createPost() throws DuplicateUserException, DuplicateEmailException, IllegalOperationException, UserNotFoundException {
        User l_user1 = new User("Max", "Mustermann", "max.mustermann@mail.de");

        BlogEngineHelper.getDefaultBlogEngine().addUser(l_user1);

        Post l_post1 = new Post("Title des Posts1", "Content des Posts1", l_user1, BlogEngineHelper.getDefaultBlogEngine());
        
        BlogEngineHelper.getDefaultBlogEngine().addPost(l_post1);
        
        return l_post1;

    }

    /**
     * Beispiel-Code:
     * 
     * Erzeugt einen neuen Beispiel Benutzer und fügt diesen zur Default Blog Engine.
     *
     * @return Neue Post-Instanz
     */
    public static final User addUser() throws DuplicateUserException, DuplicateEmailException
    {
        User l_user1 = new User("Max", "Mustermann", "max.mustermann@mail.de");
        
        BlogEngineHelper.getDefaultBlogEngine().addUser(l_user1);
        
        return l_user1;
    }

    /**
     * Beispiel-Code:
     * 
     * Erzeugt einen neuen Beispiel Kommentar und fügt diesen zu einem Post in der Default Engine hinzu.
     *
     * @return Neue Post-Instanz
     */
    public static final IComment addComment()
            throws DuplicateUserException, DuplicateEmailException, IllegalOperationException, UserNotFoundException {
        User l_user1 = new User("Max", "Mustermann", "max.mustermann@mail.de");
        User l_user2 = new User("Nathalie", "MusterFrau", "nathalie.musterfrau@mail.de");

        BlogEngineHelper.getDefaultBlogEngine().addUser(l_user1);
        BlogEngineHelper.getDefaultBlogEngine().addUser(l_user2);

        Post l_post1 = new Post("Titel 1", "Inhalt von Post 1", l_user1,BlogEngineHelper.getDefaultBlogEngine());
        Post l_post2 = new Post("Titel 2", "Inhalt von Post 2", l_user2, BlogEngineHelper.getDefaultBlogEngine());

        BlogEngineHelper.getDefaultBlogEngine().addPost(l_post1);
        BlogEngineHelper.getDefaultBlogEngine().addPost(l_post2);
        
        IComment comment = BlogEngineHelper.createComment("Da kann ich nur zustimmen", l_user2);

        l_post1.addComment(comment);
        
        return comment;
    }
    
    public static void main(String[] argv)
            throws DuplicateUserException, DuplicateEmailException, IllegalOperationException, UserNotFoundException {

        System.out.println("Anwendung wird gestartet");
        
        IBlogEngine l_blogEngine = BlogEngineHelper.getDefaultBlogEngine();
        IUser l_user1 = BlogEngineHelper.createUser("Max", "Mustermann", "max.mustermann@mail.de");
        IUser l_user2 = new User("Nathalie", "MusterFrau", "nathalie.musterfrau@mail.de");

        l_blogEngine.addUser(l_user1);
        l_blogEngine.addUser(l_user2);

        IPost l_post1 = BlogEngineHelper.createPost("Titel 1", "Inhalt 1", l_user1, BlogEngineHelper.getDefaultBlogEngine());
        IPost l_post2 = BlogEngineHelper.createPost("Titel 2", "Inhalt 2", l_user2, BlogEngineHelper.getDefaultBlogEngine());
        IPost l_post3 = BlogEngineHelper.createPost("Titel 3", "Inhalt 3", l_user2, BlogEngineHelper.getDefaultBlogEngine());

        l_blogEngine.addPost(l_post1);
        l_blogEngine.addPost(l_post2);
        l_blogEngine.addPost(l_post3);
        
        System.out.println("Anwendung konnte gestartet werden!");
    }
}
