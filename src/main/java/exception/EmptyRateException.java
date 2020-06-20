package exception;

public class EmptyRateException extends Exception{
    public EmptyRateException() {
        super("A rating is required!");
    }
}
