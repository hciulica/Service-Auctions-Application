package exception;

public class NoProvSelectedException extends Exception {
    public NoProvSelectedException() {
        super("You must select a business to rate!");
    }
}
