package exception;

public class EmptyFieldException extends Exception {

    public EmptyFieldException() {
        super("All fields are required!");
    }
}
