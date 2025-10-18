package dhbw.einpro.blogengine.exceptions;

public class DuplicateEmailException extends Exception
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public DuplicateEmailException(String p_errorMessage)
    {
        super(p_errorMessage);
    }

    public DuplicateEmailException()
    {
        super();
    }

}
