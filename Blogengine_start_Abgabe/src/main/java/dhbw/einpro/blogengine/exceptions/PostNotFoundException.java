package dhbw.einpro.blogengine.exceptions;

public class PostNotFoundException extends Exception
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public PostNotFoundException(String p_errorMessage)
    {
        super(p_errorMessage);
    }

    public PostNotFoundException()
    {
        super();
    }
}
