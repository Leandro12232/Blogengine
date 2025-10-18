package dhbw.einpro.blogengine.exceptions;

public class DuplicateUserException extends Exception
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public DuplicateUserException(String p_errorMessage)
    {
        super(p_errorMessage);
    }

    public DuplicateUserException()
    {
        super();
    }
}
