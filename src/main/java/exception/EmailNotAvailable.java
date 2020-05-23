package exception;

public class EmailNotAvailable extends Exception{
    public EmailNotAvailable()
    {
        super("This email address is not available.");
    }
}
