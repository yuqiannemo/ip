package nemo;

public class NemoException extends Exception {
    public NemoException(String message) {
        super(message);
    }
    public String toString() {
        return getMessage();
    }
}
