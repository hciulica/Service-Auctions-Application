package exception;

public class EmptySignUpFieldException extends Exception {

    public EmptySignUpFieldException() {
        super("All fields are required!");
    }
}
