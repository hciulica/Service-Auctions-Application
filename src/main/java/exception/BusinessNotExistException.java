package exception;


public class BusinessNotExistException extends Exception {

    public BusinessNotExistException() {
        super("This Business does not exist!");
    }
}
