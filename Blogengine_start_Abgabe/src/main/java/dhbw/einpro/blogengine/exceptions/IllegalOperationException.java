package dhbw.einpro.blogengine.exceptions;

public class IllegalOperationException extends Exception
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public IllegalOperationException()
    {
        super();
    }

    public IllegalOperationException(String p_errorMessage)
    {
        super(p_errorMessage);
    }
}
