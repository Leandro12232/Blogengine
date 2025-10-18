package dhbw.einpro.blogengine.exceptions;

public class UserNotFoundException extends Exception
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public UserNotFoundException(String p_errorMessage)
    {
        super(p_errorMessage);
    }

    public UserNotFoundException()
    {
        super();
    }
}
