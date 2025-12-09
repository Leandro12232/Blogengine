package dhbw.einpro.blogengine.impl;

import dhbw.einpro.blogengine.exceptions.IllegalOperationException;
import dhbw.einpro.blogengine.interfaces.IComment;
import dhbw.einpro.blogengine.interfaces.IPost;
import dhbw.einpro.blogengine.interfaces.IUser;

/**
 * Klasse implementiert einen Kommentar zu einem Post.
 */
public class Comment implements IComment
{

    private static final long serialVersionUID = 1L;

    private String content;
    private IUser author;
    private IPost post;

    /**
     * Konstruktor, der vom BlogEngineHelper (createComment) benötigt wird.
     * * @param content Inhalt des Kommentars
     * @param author Autor des Kommentars
     */
    public Comment(String content, IUser author) {
        this.content = content;
        this.author = author;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public void setContent(String p_content) throws IllegalOperationException {
        // Laut Interface IComment: Maximal 256 Zeichen erlaubt
        if (p_content != null && p_content.length() > 256) {
            throw new IllegalOperationException("Der Inhalt eines Kommentars darf maximal 256 Zeichen haben.");
        }
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
    public void setPost(IPost p_post) {
        this.post = p_post;
    }
}